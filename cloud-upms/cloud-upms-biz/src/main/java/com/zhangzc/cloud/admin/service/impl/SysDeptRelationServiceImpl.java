package com.zhangzc.cloud.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangzc.cloud.admin.mapper.SysDeptRelationMapper;
import com.zhangzc.cloud.admin.service.SysDeptRelationService;
import com.zhangzc.cloud.upms.api.entity.SysDept;
import com.zhangzc.cloud.upms.api.entity.SysDeptRelation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysDeptRelationServiceImpl extends ServiceImpl<SysDeptRelationMapper, SysDeptRelation> implements SysDeptRelationService {

    /**
     * 新建部门关系
     * @param sysDept 部门
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveDeptRelation(SysDept sysDept) {
        List<SysDeptRelation> relationList = baseMapper.selectList(
                Wrappers.<SysDeptRelation>query().lambda().eq(SysDeptRelation::getDescendant, sysDept.getParentId())).stream().map(relation -> {
                    relation.setDescendant(sysDept.getDeptId());
                    return relation;
                }).collect(Collectors.toList());

        if (CollUtil.isNotEmpty(relationList)) {
            this.saveBatch(relationList);
        }

        // 自己也要维护到关系表中
        SysDeptRelation own = new SysDeptRelation();
        own.setDescendant(sysDept.getDeptId());
        own.setAncestor(sysDept.getDeptId());
        baseMapper.insert(own);
    }

    /**
     * 通过ID删除部门关系
     * @param id
     */
    @Override
    public void removeDeptRelationById(Long id) {
        baseMapper.deleteDeptRelationsById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDeptRelation(SysDeptRelation relation) {
        baseMapper.deleteDeptRelations(relation);
        baseMapper.insertDeptRelations(relation);
    }
}
