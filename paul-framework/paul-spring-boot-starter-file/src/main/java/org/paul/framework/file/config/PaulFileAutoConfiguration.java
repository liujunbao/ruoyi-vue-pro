package org.paul.framework.file.config;

import org.paul.framework.file.core.client.FileClientFactory;
import org.paul.framework.file.core.client.FileClientFactoryImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 文件配置类
 *
 * @author 芋道源码
 */
@AutoConfiguration
public class PaulFileAutoConfiguration {

    @Bean
    public FileClientFactory fileClientFactory() {
        return new FileClientFactoryImpl();
    }

}
