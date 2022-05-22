package com.membrane;

import com.predic8.membrane.core.rules.ServiceProxy;
import com.predic8.membrane.core.rules.ServiceProxyKey;
import com.predic8.membrane.core.transport.ssl.SSLProvider;

import java.util.Map;

import static com.util.Constants.SSL_ENABLED;
import static java.lang.Boolean.parseBoolean;

public class CustomServiceProxy extends ServiceProxy {

    private final Map<String, String> properties;

    public CustomServiceProxy(Map<String, String> properties, ServiceProxyKey key, Target target) {
        this.properties = properties;
        this.key = key;
        this.target = target;
    }

    @Override
    public SSLProvider getSslOutboundContext() {
        return parseBoolean(properties.get(SSL_ENABLED)) ? super.getSslOutboundContext() : null;
    }
}
