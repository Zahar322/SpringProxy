package com.membrane;

import com.predic8.membrane.core.config.security.SSLParser;
import com.predic8.membrane.core.rules.AbstractServiceProxy;

import java.util.Map;

import static com.util.Constants.HOST;
import static com.util.Constants.PORT;
import static com.util.Constants.SSL_ENABLED;
import static java.lang.Boolean.parseBoolean;

public class CustomTarget extends AbstractServiceProxy.Target {

    private final Map<String, String> properties;

    public CustomTarget(Map<String, String> properties, SSLParser sslParser) {
        this.properties = properties;
        setSslParser(sslParser);
    }

    @Override
    public String getHost() {
        return properties.get(HOST);
    }

    @Override
    public int getPort() {
        return Integer.parseInt(properties.get(PORT));
    }

    @Override
    public SSLParser getSslParser() {
        return parseBoolean(properties.get(SSL_ENABLED)) ? super.getSslParser() : null;
    }
}
