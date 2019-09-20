package com.dlion.testproject.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author 李正元
 * @date 2019/9/6
 */
public class DownloadFile {

    @Test
    public static void xlsxz() throws ClientProtocolException, IOException {

        //1,导包
        //2,得到HttpClient对象
        HttpClient client = new DefaultHttpClient();

        //3,设置请求方式
        HttpGet get = new HttpGet("http://stock.gtimg.cn/data/get_hs_xls.php?id=ranka&type=1&metric=chr");

        //4,执行请求, 获取响应信息
        HttpResponse response = client.execute(get);

        if (response.getStatusLine().getStatusCode() == 200) {
            //得到实体
            HttpEntity entity = response.getEntity();

            byte[] data = EntityUtils.toByteArray(entity);

            //存入磁盘
            FileOutputStream fos = new FileOutputStream("E:/text/sharecertificate.xls");
            fos.write(data);
            fos.close();

            System.out.println("sharecertificate.xls文件下载成功!!!!");
        }
    }
}
