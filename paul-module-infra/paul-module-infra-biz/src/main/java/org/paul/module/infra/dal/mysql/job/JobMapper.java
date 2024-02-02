package org.paul.module.infra.dal.mysql.job;

import org.paul.framework.common.pojo.PageResult;
import org.paul.framework.mybatis.core.mapper.BaseMapperX;
import org.paul.framework.mybatis.core.query.LambdaQueryWrapperX;
import org.paul.module.infra.controller.admin.job.vo.job.JobPageReqVO;
import org.paul.module.infra.dal.dataobject.job.JobDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 定时任务 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface JobMapper extends BaseMapperX<JobDO> {

    default JobDO selectByHandlerName(String handlerName) {
        return selectOne(JobDO::getHandlerName, handlerName);
    }

    default PageResult<JobDO> selectPage(JobPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<JobDO>()
                .likeIfPresent(JobDO::getName, reqVO.getName())
                .eqIfPresent(JobDO::getStatus, reqVO.getStatus())
                .likeIfPresent(JobDO::getHandlerName, reqVO.getHandlerName())
        );
    }

}
