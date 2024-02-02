package org.paul.framework.sms.config;

import org.paul.framework.sms.core.client.SmsClientFactory;
import org.paul.framework.sms.core.client.impl.SmsClientFactoryImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 短信配置类
 *
 * @author 芋道源码
 */
@AutoConfiguration
public class PaulSmsAutoConfiguration {

    @Bean
    public SmsClientFactory smsClientFactory() {
        return new SmsClientFactoryImpl();
    }

}
