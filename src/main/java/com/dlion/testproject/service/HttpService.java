package com.dlion.testproject.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * @author 李正元
 * @date 2019/8/1
 */
@Service
public class HttpService {

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private CloseableHttpClient httpClient;

    @Autowired
    private RequestConfig config;

    /**
     * post请求
     *
     * @param url        请求地址
     * @param postEntity 请求参数
     * @return
     */
    public Map<String, Object> doPost(String url, String postEntity) throws IOException {

        HttpPost httpPost = new HttpPost(url);

        if (!StringUtils.isEmpty(postEntity)) {
            StringEntity entity = new StringEntity(postEntity, Consts.UTF_8);
            httpPost.setEntity(entity);
        }
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {

            return handleResponse(response);
        }
    }

    /**
     * 不带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
     *
     * @param url 请求地址
     * @return
     * @throws Exception
     */
    public Map<String, Object> doGet(String url) throws IOException {
        // 声明 http get 请求
        HttpGet httpGet = new HttpGet(url);

        // 装载配置信息
        httpGet.setConfig(config);

        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpGet);

        return handleResponse(response);
    }

    /**
     * 带参数的get请求
     *
     * @param url 请求地址
     * @return
     * @throws Exception
     */
    public Map<String, Object> doGet(String url, Map<String, Object> map) throws IOException, URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(url);

        if (map != null) {
            // 遍历map,拼接请求参数
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }

        // 调用不带参数的get请求
        return this.doGet(uriBuilder.build().toString());

    }


    /**
     * 处理http调用结果
     *
     * @param response
     * @return
     * @throws IOException
     */
    private Map<String, Object> handleResponse(CloseableHttpResponse response) throws IOException {

        final StatusLine statusLine = response.getStatusLine();
        final HttpEntity entity = response.getEntity();
        if (statusLine.getStatusCode() >= HttpStatus.SC_MULTIPLE_CHOICES) {
            EntityUtils.consume(entity);
            throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
        }

        String res = EntityUtils.toString(entity, Consts.UTF_8);

        return mapper.readValue(res, new TypeReference<Map<String, Object>>() {
        });
    }

}
