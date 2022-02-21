package com.zhangzc.cloud.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangzc.cloud.admin.mapper.SysDeptMapper;
import com.zhangzc.cloud.admin.service.SysDeptRelationService;
import com.zhangzc.cloud.admin.service.SysDeptService;
import com.zhangzc.cloud.common.security.util.SecurityUtils;
import com.zhangzc.cloud.upms.api.entity.SysDept;
import com.zhangzc.cloud.upms.api.entity.SysDeptRelation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {
    private final SysDeptRelationService sysDeptRelationService;

    /**
     * 查询全部部门树
     * @return tree
     */
    @Override
    public List<Tree<Long>> listDeptTrees() {
        return getDeptTree(this.list(Wrappers.emptyWrapper()));
    }

    /**
     * 查询用户部门树
     * @return tree
     */
    @Override
    public List<Tree<Long>> listCurrentUserDeptTrees() {
        Long deptId = SecurityUtils.getUser().getDeptId();
        List<Long> descendantIdList = sysDeptRelationService
                .list(Wrappers.<SysDeptRelation>query().lambda().eq(SysDeptRelation::getAncestor, deptId)).stream()
                .map(SysDeptRelation::getDescendant).collect(Collectors.toList());

        List<SysDept> deptList = baseMapper.selectBatchIds(descendantIdList);
        return getDeptTree(deptList);
    }

    /**
     * 添加部门
     * @param sysDept
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveDept(SysDept sysDept) {
        SysDept dept = new SysDept();
        BeanUtils.copyProperties(sysDept, dept);
        this.save(dept);
        sysDeptRelationService.saveDeptRelation(dept);
        return Boolean.TRUE;
    }

    /**
     * 删除部门
     * @param id 部门 ID
     * @return 成功、失败
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean removeDeptById(Long id) {
        List<Long> idList = sysDeptRelationService.list(Wrappers.<SysDeptRelation>query().lambda().eq(SysDeptRelation::getAncestor, id))
                .stream().map(SysDeptRelation::getDescendant).collect(Collectors.toList());

        if (CollUtil.isNotEmpty(idList)) {
            this.removeByIds(idList);
        }

        // 删除部门级联关系
        sysDeptRelationService.removeDeptRelationById(id);
        return Boolean.TRUE;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateDeptById(SysDept sysDept) {
        // 更新部门状态
        this.updateById(sysDept);
        // 更新部门关系
        SysDeptRelation relation = new SysDeptRelation();
        relation.setAncestor(sysDept.getParentId());
        relation.setDescendant(sysDept.getDeptId());
        sysDeptRelationService.updateDeptRelation(relation);
        return Boolean.TRUE;
    }

    @Override
    public List<Long> listChildDeptId(Long deptId) {
        List<SysDeptRelation> deptRelations = sysDeptRelationService.list(Wrappers.<SysDeptRelation>lambdaQuery()
                .eq(SysDeptRelation::getAncestor, deptId).ne(SysDeptRelation::getDescendant, deptId));
        if (CollUtil.isNotEmpty(deptRelations)) {
            return deptRelations.stream().map(SysDeptRelation::getDescendant).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * 构建部门树
     * @param depts 部门
     * @return
     */
    private List<Tree<Long>> getDeptTree(List<SysDept> depts) {
        List<TreeNode<Long>> collect = depts.stream().filter(dept -> dept.getDeptId().intValue() != dept.getParentId())
                .sorted(Comparator.comparingInt(SysDept::getSortOrder)).map(dept -> {
                    TreeNode<Long> treeNode = new TreeNode();
                    treeNode.setId(dept.getDeptId());
                    treeNode.setParentId(dept.getParentId());
                    treeNode.setName(dept.getName());
                    treeNode.setWeight(dept.getSortOrder());
                    // 扩展属性
                    Map<String, Object> extra = new HashMap<>(4);
                    extra.put("createTime", dept.getCreateTime());
                    treeNode.setExtra(extra);
                    return treeNode;
                }).collect(Collectors.toList());

        return TreeUtil.build(collect, 0L);
    }
}
