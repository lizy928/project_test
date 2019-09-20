package com.dlion.testproject.http;

import com.dlion.testproject.service.HttpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * @author 李正元
 * @date 2019/8/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class http {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpService httpService;

    public static final String USERS_SHOW = "https://api.weibo.com/2/users/show.json?access_token=12.00XkXboFYqKCxB888d38a12cYbMBgC&uid=5328428833";

    @Test
    public void test(){

        String url = "https://api.weibo.com/2/users/show.json";

        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Encoding", "UTF-8");
        headers.add("Content-Type", "application/json; charset=UTF-8");

        headers.add("access_token", "12.00XkXboFYqKCxB888d38a12cYbMBgC");
        headers.add("uid", "5328428833");

        HttpEntity<String> formEntity = new HttpEntity<String>(headers);

        //Map forObject = restTemplate.getForObject(url, JSONObject.class);

        ResponseEntity<String> responseEntity;

        try{
            responseEntity = restTemplate.getForEntity(USERS_SHOW, String.class);
            String body = responseEntity.getBody();
            HttpStatus statusCode = responseEntity.getStatusCode();
            int statusCodeValue = responseEntity.getStatusCodeValue();
        }catch (Exception e){
            e.getMessage();
        }





    }


}
