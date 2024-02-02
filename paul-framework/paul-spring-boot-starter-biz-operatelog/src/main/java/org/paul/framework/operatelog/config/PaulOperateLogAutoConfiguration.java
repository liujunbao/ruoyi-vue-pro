package org.paul.framework.operatelog.config;

import org.paul.framework.operatelog.core.aop.OperateLogAspect;
import org.paul.framework.operatelog.core.service.OperateLogFrameworkService;
import org.paul.framework.operatelog.core.service.OperateLogFrameworkServiceImpl;
import org.paul.module.system.api.logger.OperateLogApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class PaulOperateLogAutoConfiguration {

    @Bean
    public OperateLogAspect operateLogAspect() {
        return new OperateLogAspect();
    }

    @Bean
    public OperateLogFrameworkService operateLogFrameworkService(OperateLogApi operateLogApi) {
        return new OperateLogFrameworkServiceImpl(operateLogApi);
    }

}
