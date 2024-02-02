package org.paul.module.system.api.logger;

import cn.hutool.core.collection.CollUtil;
import org.paul.framework.common.pojo.PageResult;
import org.paul.module.system.api.logger.dto.OperateLogCreateReqDTO;
import org.paul.module.system.api.logger.dto.OperateLogV2CreateReqDTO;
import org.paul.module.system.api.logger.dto.OperateLogV2PageReqDTO;
import org.paul.module.system.api.logger.dto.OperateLogV2RespDTO;
import org.paul.module.system.convert.logger.OperateLogConvert;
import org.paul.module.system.dal.dataobject.logger.OperateLogV2DO;
import org.paul.module.system.dal.dataobject.user.AdminUserDO;
import org.paul.module.system.service.logger.OperateLogService;
import org.paul.module.system.service.user.AdminUserService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;

import static org.paul.framework.common.util.collection.CollectionUtils.convertSet;

/**
 * 操作日志 API 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class OperateLogApiImpl implements OperateLogApi {

    @Resource
    private OperateLogService operateLogService;
    @Resource
    private AdminUserService adminUserService;

    @Override
    public void createOperateLog(OperateLogCreateReqDTO createReqDTO) {
        operateLogService.createOperateLog(createReqDTO);
    }

    @Override
    @Async
    public void createOperateLogV2(OperateLogV2CreateReqDTO createReqDTO) {
        operateLogService.createOperateLogV2(createReqDTO);
    }

    @Override
    public PageResult<OperateLogV2RespDTO> getOperateLogPage(OperateLogV2PageReqDTO pageReqVO) {
        PageResult<OperateLogV2DO> operateLogPage = operateLogService.getOperateLogPage(pageReqVO);
        if (CollUtil.isEmpty(operateLogPage.getList())) {
            return PageResult.empty();
        }

        // 获取用户
        List<AdminUserDO> userList = adminUserService.getUserList(
                convertSet(operateLogPage.getList(), OperateLogV2DO::getUserId));
        return OperateLogConvert.INSTANCE.convertPage(operateLogPage, userList);
    }

}
