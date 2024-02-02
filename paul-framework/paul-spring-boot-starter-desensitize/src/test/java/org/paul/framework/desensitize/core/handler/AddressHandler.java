package org.paul.framework.desensitize.core.handler;

import org.paul.framework.desensitize.core.DesensitizeTest;
import org.paul.framework.desensitize.core.annotation.Address;
import org.paul.framework.desensitize.core.base.handler.DesensitizationHandler;

/**
 * {@link Address} 的脱敏处理器
 *
 * 用于 {@link DesensitizeTest} 测试使用
 */
public class AddressHandler implements DesensitizationHandler<Address> {

    @Override
    public String desensitize(String origin, Address annotation) {
        return origin + annotation.replacer();
    }

}
