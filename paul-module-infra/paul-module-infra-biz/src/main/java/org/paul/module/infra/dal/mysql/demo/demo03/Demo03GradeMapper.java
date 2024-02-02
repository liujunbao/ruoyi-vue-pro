package org.paul.module.infra.dal.mysql.demo.demo03;

import org.paul.framework.common.pojo.PageParam;
import org.paul.framework.common.pojo.PageResult;
import org.paul.framework.mybatis.core.mapper.BaseMapperX;
import org.paul.framework.mybatis.core.query.LambdaQueryWrapperX;
import org.paul.module.infra.dal.dataobject.demo.demo03.Demo03GradeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 学生班级 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface Demo03GradeMapper extends BaseMapperX<Demo03GradeDO> {

    default PageResult<Demo03GradeDO> selectPage(PageParam reqVO, Long studentId) {
        return selectPage(reqVO, new LambdaQueryWrapperX<Demo03GradeDO>()
                .eq(Demo03GradeDO::getStudentId, studentId)
                .orderByDesc(Demo03GradeDO::getId));
    }

    default Demo03GradeDO selectByStudentId(Long studentId) {
        return selectOne(Demo03GradeDO::getStudentId, studentId);
    }

    default int deleteByStudentId(Long studentId) {
        return delete(Demo03GradeDO::getStudentId, studentId);
    }

}
