package cn.iocoder.yudao.framework.quartz.config;

import cn.iocoder.yudao.framework.quartz.core.job.JobLogJobHandler;
import cn.iocoder.yudao.framework.quartz.core.job.LogJobProperties;
import cn.iocoder.yudao.framework.quartz.core.scheduler.SchedulerManager;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Optional;

/**
 * 定时任务 Configuration
 */
@AutoConfiguration
@EnableScheduling // 开启 Spring 自带的定时任务
@Slf4j
@EnableConfigurationProperties(LogJobProperties.class)
public class YudaoQuartzAutoConfiguration {

    @Bean
    public SchedulerManager schedulerManager(Optional<Scheduler> scheduler) {
        if (!scheduler.isPresent()) {
            log.info("[定时任务 - 已禁用][参考 https://doc.iocoder.cn/job/ 开启]");
            return new SchedulerManager(null);
        }
        return new SchedulerManager(scheduler.get());
    }

    // TODO @j-sentinel：这个 job，先拿到 infra biz 里面实现哈；
    @Bean
    public JobLogJobHandler jobLogJobHandler(LogJobProperties logJobProperties){
        return new JobLogJobHandler(logJobProperties);
    }

}
