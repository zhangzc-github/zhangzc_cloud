package com.zhangzc.cloud.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangzc.cloud.upms.api.entity.SysDeptRelation;
import org.apache.ibatis.annotations.Mapper;

/**
 * SysDeptRelation Mapper接口
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/2/21 11:18 上午
 */
@Mapper
public interface SysDeptRelationMapper extends BaseMapper<SysDeptRelation> {
    /**
     * 删除部门节点关系
     * @param deptRelation 待删除的某一个部门节点
     */
    void deleteDeptRelations(SysDeptRelation deptRelation);

    /**
     * 删除部门节点关系,同时删除所有关联此部门子节点的部门关系
     * @param id 待删除的部门节点ID
     */
    void deleteDeptRelationsById(Long id);

    /**
     * 新增部门节点关系
     * @param deptRelation 待新增的部门节点关系
     */
    void insertDeptRelations(SysDeptRelation deptRelation);
}
