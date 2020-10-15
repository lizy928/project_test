package com.dlion.testproject.config;

import com.dlion.testproject.filter.AuthFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author lzy
 * @date 2020/10/15
 */
@Configuration
public class AuthFilterCondfig {

    @Resource
    AuthFilter myFilter;

    @Bean
    public FilterRegistrationBean<AuthFilter> thirdFilter() {
        FilterRegistrationBean<AuthFilter> filterRegistrationBean = new FilterRegistrationBean<>();

        filterRegistrationBean.setFilter(myFilter);

        filterRegistrationBean.setUrlPatterns(new ArrayList<>(Arrays.asList("/api/*")));

        return filterRegistrationBean;
    }
}
