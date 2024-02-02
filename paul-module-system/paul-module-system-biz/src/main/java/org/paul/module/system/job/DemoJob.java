package org.paul.module.system.job;

import org.paul.framework.quartz.core.handler.JobHandler;
import org.paul.framework.tenant.core.context.TenantContextHolder;
import org.paul.framework.tenant.core.job.TenantJob;
import org.paul.module.system.dal.dataobject.user.AdminUserDO;
import org.paul.module.system.dal.mysql.user.AdminUserMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class DemoJob implements JobHandler {

    @Resource
    private AdminUserMapper adminUserMapper;

    @Override
    @TenantJob // 标记多租户
    public String execute(String param) {
        System.out.println("当前租户：" + TenantContextHolder.getTenantId());
        List<AdminUserDO> users = adminUserMapper.selectList();
        return "用户数量：" + users.size();
    }

}
