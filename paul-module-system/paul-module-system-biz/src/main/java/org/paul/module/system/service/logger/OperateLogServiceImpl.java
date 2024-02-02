package org.paul.module.system.service.logger;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import org.paul.framework.common.pojo.PageResult;
import org.paul.framework.common.util.object.BeanUtils;
import org.paul.framework.common.util.string.StrUtils;
import org.paul.module.system.api.logger.dto.OperateLogCreateReqDTO;
import org.paul.module.system.api.logger.dto.OperateLogV2CreateReqDTO;
import org.paul.module.system.api.logger.dto.OperateLogV2PageReqDTO;
import org.paul.module.system.controller.admin.logger.vo.operatelog.OperateLogPageReqVO;
import org.paul.module.system.dal.dataobject.logger.OperateLogDO;
import org.paul.module.system.dal.dataobject.logger.OperateLogV2DO;
import org.paul.module.system.dal.dataobject.user.AdminUserDO;
import org.paul.module.system.dal.mysql.logger.OperateLogMapper;
import org.paul.module.system.dal.mysql.logger.OperateLogV2Mapper;
import org.paul.module.system.service.user.AdminUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;

import static org.paul.framework.common.util.collection.CollectionUtils.convertSet;
import static org.paul.module.system.dal.dataobject.logger.OperateLogDO.JAVA_METHOD_ARGS_MAX_LENGTH;
import static org.paul.module.system.dal.dataobject.logger.OperateLogDO.RESULT_MAX_LENGTH;

/**
 * 操作日志 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
@Slf4j
public class OperateLogServiceImpl implements OperateLogService {

    @Resource
    private OperateLogMapper operateLogMapper;
    @Resource
    private OperateLogV2Mapper operateLogV2Mapper;

    @Resource
    private AdminUserService userService;

    @Override
    public void createOperateLog(OperateLogCreateReqDTO createReqDTO) {
        OperateLogDO log = BeanUtils.toBean(createReqDTO, OperateLogDO.class);
        log.setJavaMethodArgs(StrUtils.maxLength(log.getJavaMethodArgs(), JAVA_METHOD_ARGS_MAX_LENGTH));
        log.setResultData(StrUtils.maxLength(log.getResultData(), RESULT_MAX_LENGTH));
        operateLogMapper.insert(log);
    }

    @Override
    public PageResult<OperateLogDO> getOperateLogPage(OperateLogPageReqVO pageReqVO) {
        // 处理基于用户昵称的查询
        Collection<Long> userIds = null;
        if (StrUtil.isNotEmpty(pageReqVO.getUserNickname())) {
            userIds = convertSet(userService.getUserListByNickname(pageReqVO.getUserNickname()), AdminUserDO::getId);
            if (CollUtil.isEmpty(userIds)) {
                return PageResult.empty();
            }
        }
        // 查询分页
        return operateLogMapper.selectPage(pageReqVO, userIds);
    }

    // ======================= LOG V2 =======================

    @Override
    public void createOperateLogV2(OperateLogV2CreateReqDTO createReqDTO) {
        OperateLogV2DO log = BeanUtils.toBean(createReqDTO, OperateLogV2DO.class);
        operateLogV2Mapper.insert(log);
    }

    @Override
    public PageResult<OperateLogV2DO> getOperateLogPage(OperateLogV2PageReqDTO pageReqDTO) {
        return operateLogV2Mapper.selectPage(pageReqDTO);
    }

}
