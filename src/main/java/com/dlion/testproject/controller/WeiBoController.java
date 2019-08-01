package com.dlion.testproject.controller;

import com.alibaba.fastjson.JSONObject;
import com.dlion.testproject.service.HttpService;
import com.dlion.testproject.util.MapUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @author 李正元
 * @date 2019/8/1
 */
@RestController
@RequestMapping("/weibo")
public class WeiBoController {

    /**
     * appkey对应的secret，验证签名时使用。
     */
    private static String app_secret = "86dac43725c0313bb044dc75fdaa8510";

    /**
     * access_token
     */
    private static String access_token = "2.00XkXboF0Oc3l6a827a7a5a02_NdQB";

    /**
     * 粉丝消息接口
     */
    private static String message_url = "https://m.api.weibo.com/2/messages/reply.json?";

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HttpService httpService;

    @RequestMapping("/message")
    public String devMessage(@RequestParam(defaultValue = "") String signature,
                             @RequestParam(defaultValue = "") String nonce,
                             @RequestParam(defaultValue = "") String timestamp,
                             @RequestParam(defaultValue = "") String echostr,
                             @RequestBody(required = false) String msg) throws UnsupportedEncodingException {

        if (!ValidateSHA(signature, nonce, timestamp)) {
            logger.error("微信消息签名校验失败");
            return "";
        }
        if (StringUtils.isNotEmpty(echostr)) {
            return echostr;
        }

        JSONObject json = (JSONObject) JSONObject.parse(msg);

        logger.info("收到微博开放平台消息:{}", msg);

        sendMessage(json);

        return getResposeImage(json);
    }


    public void sendMessage(JSONObject json) throws UnsupportedEncodingException {

        Map<String, Object> request = new HashMap<>();
        request.put("access_token", access_token);
        request.put("receiver_id", json.getLong("sender_id"));
        request.put("type", "text");
        request.put("save_sender_box", 0);

        Map<String, Object> data = new HashMap<>();
        data.put("text", "主动发送的消息");

        JSONObject dataJson = new JSONObject(data);

        String dataStr = URLEncoder.encode(dataJson.toString(), "utf-8");

        request.put("data", dataStr);

        String params = MapUtil.getUrlParamsByMap(request);

        String url = message_url + params;

        try {

            Map<String, Object> result = httpService.doPost(url, "");

            logger.info("微博消息发送结果:{}", result);

        } catch (IOException e) {
            logger.error("微博消息发送异常", e);
        }

    }


    public String getResposeImage(JSONObject json) throws UnsupportedEncodingException {

        Map<String, Object> response = new HashMap<>();
        response.put("result", true);
        response.put("receiver_id", json.getLong("sender_id"));
        response.put("sender_id", json.getLong("receiver_id"));
        response.put("type", "image");

        Map<String, Object> data = new HashMap<>();

        data.put("vfid", 4400193962789959L);
        data.put("tovfid", 4400193962789959L);

        JSONObject dataJson = new JSONObject(data);

        String dataJsonStr = URLEncoder.encode(dataJson.toString(), "UTF-8");

        response.put("data", dataJsonStr);

        String result = new JSONObject(response).toString();


        return result;

    }

    public String getResposeArticles(JSONObject json) throws UnsupportedEncodingException {


        Map<String, Object> response = new HashMap<>();
        response.put("result", true);
        response.put("receiver_id", json.getLong("sender_id"));
        response.put("sender_id", json.getLong("receiver_id"));
        response.put("type", "articles");


        Map<String, Object> data = new HashMap<>();


        Map<String, Object> articles = new HashMap<>();

        articles.put("display_name", "两个故事");
        articles.put("summary", "今天讲两个故事，分享给你。谁是公司？谁又是中国人？");
        articles.put("image", "http://storage.mcp.weibo.cn/0JlIv.jpg");
        articles.put("url", "http://e.weibo.com/mediaprofile/article/detail?uid=1722052204&aid=983319");

        List<Map<String, Object>> articlesList = new ArrayList<>();
        articlesList.add(articles);

        data.put("articles", articlesList);

        JSONObject dataJson = new JSONObject(data);

        String dataJsonStr = URLEncoder.encode(dataJson.toString(), "UTF-8");

        response.put("data", dataJsonStr);

        String result = new JSONObject(response).toString();


        return result;

    }

    public String getResponseText(JSONObject json) throws UnsupportedEncodingException {

        Map<String, Object> response = new HashMap<>();
        response.put("result", true);
        response.put("receiver_id", json.getLong("sender_id"));
        response.put("sender_id", json.getLong("receiver_id"));
        response.put("type", "text");

        Map<String, Object> data = new HashMap<>();

        data.put("text", "已收到消息");

        JSONObject dataJson = new JSONObject(data);

        String dataJsonStr = URLEncoder.encode(dataJson.toString(), "UTF-8");

        response.put("data", dataJsonStr);

        String result = new JSONObject(response).toString();

        return result;
    }


    /**
     * 验证sha1签名，验证通过返回true，否则返回false
     *
     * @param signature
     * @param nonce
     * @param timestamp
     * @return
     */
    private boolean ValidateSHA(String signature, String nonce,
                                String timestamp) {
        if (signature == null || nonce == null || timestamp == null) {
            return false;
        }
        String sign = sha1(getSignContent(nonce, timestamp, app_secret));
        if (!signature.equals(sign)) {
            return false;
        }
        return true;
    }

    /**
     * 生产sha1签名
     *
     * @param strSrc
     * @return
     */
    private static String sha1(String strSrc) {
        MessageDigest md = null;
        String strDes = null;

        byte[] bt = strSrc.getBytes();
        try {
            md = MessageDigest.getInstance("SHA-1");
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            //TODO
            e.printStackTrace();
        }
        return strDes;
    }

    private static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;

        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));

            if (tmp.length() == 1) {
                des += "0";
            }

            des += tmp;
        }

        return des;
    }

    /**
     * 对非空参数按字典顺序升序构造签名串
     *
     * @param params
     * @return
     */
    private static String getSignContent(String... params) {
        List<String> list = new ArrayList(params.length);
        for (String temp : params) {
            if (StringUtils.isNotBlank(temp)) {
                list.add(temp);
            }
        }
        Collections.sort(list);
        StringBuilder strBuilder = new StringBuilder();
        for (String element : list) {
            strBuilder.append(element);
        }
        return strBuilder.toString();
    }


}
