package com.dlion.testproject.config;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * HttpClient连接池配置,减少因为每次建立HTTP连接而导致的性能损耗
 *
 * @author lizhengyuan
 * @date 2019/4/24
 */
@Configuration
public class HttpClientConfiguration {

    /**
     * 实例化连接池管理器，设置最大连接数、并发连接数
     *
     * @return
     */
    @Bean(name = "httpClientConnectionManager")
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager =
                new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setMaxTotal(200);
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(200);
        return poolingHttpClientConnectionManager;
    }

    /**
     * 注入连接池，用于获取httpClient
     *
     * @param httpClientConnectionManager
     * @return
     */
    @Bean
    public CloseableHttpClient httpClient(@Qualifier("httpClientConnectionManager") PoolingHttpClientConnectionManager httpClientConnectionManager) {
        // 设置keep alive的连接策略
        ConnectionKeepAliveStrategy myStrategy = (response, context) -> {
            // Honor 'keep-alive' header
            HeaderElementIterator iterator = new BasicHeaderElementIterator(
                    response.headerIterator(HTTP.CONN_KEEP_ALIVE));
            while (iterator.hasNext()) {
                HeaderElement he = iterator.nextElement();
                String param = he.getName();
                String value = he.getValue();
                if (value != null && param.equalsIgnoreCase("timeout")) {
                    return Long.parseLong(value) * 1000;
                }
            }
            return 5 * 1000;
        };

        return HttpClients.custom().setKeepAliveStrategy(myStrategy)
                .setConnectionManager(httpClientConnectionManager).build();
    }

    /**
     * 使用builder构建一个RequestConfig对象
     *
     * @return
     */
    @Bean
    public RequestConfig getRequestConfig() {

        return RequestConfig.custom().setConnectTimeout(1000)
                .setConnectionRequestTimeout(1000)
                .setSocketTimeout(1000)
                .setStaleConnectionCheckEnabled(true).build();
    }


}
