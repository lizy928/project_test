package com.dlion.testproject;

import com.dlion.testproject.html.RobotContent;
import org.apache.commons.lang3.StringUtils;
import org.apache.oro.text.perl.Perl5Util;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 李正元
 * @date 2019/8/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonTest {

    @Test
    public void test() {

        String imgPath = "http://cms-bucket.ws.126.net/2019/08/26/f8c9a2fcd45f4a8cb24c7bc43c4ac4a7.jpeg";
        StringUtils.substringBeforeLast(imgPath, ".");
    }

    @Test
    public void test1() {

        String htmlStr = "<div style=\"margin-top:20px\"><strong>美国空中国民警卫队在夏威夷演习</strong></div><div><img src=\"http://cms-bucket.ws.126.net/2019/08/24/bb2bf55e7fac41fb85bcc8e2cffeae69.jpeg\" style=\"width:100%\"/></div><p></p><div style=\"margin-top:20px\"><strong>美军B-52轰炸机挂载水雷 展示布雷能力</strong></div><div><img src=\"http://pic-bucket.ws.126.net/photo/0001/2019-08-24/ENB9FV344T8E0001NOS.jpg\" style=\"width:100%\"/></div><p></p><div style=\"margin-top:20px\"><strong>传统不能丢!空军歼-10C战机打火箭弹</strong></div><div><img src=\"http://pic-bucket.ws.126.net/photo/0001/2019-08-24/ENB95K7O4T8E0001NOS.jpg\" style=\"width:100%\"/></div><p></p><div style=\"margin-top:20px\"><strong>解放军高海拔训练 突击车卡车炮开火</strong></div><div><img src=\"http://pic-bucket.ws.126.net/photo/0001/2019-08-24/ENB7H9QP4T8E0001NOS.jpg\" style=\"width:100%\"/></div><p></p><div style=\"margin-top:20px\"><strong>俄总统专用直升机曝光 用安-124空运</strong></div><div><img src=\"http://pic-bucket.ws.126.net/photo/0001/2019-08-24/ENB72KJP4T8E0001NOS.jpg\" style=\"width:100%\"/></div><p></p>";

        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式
        String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式

        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); //过滤script标签

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); //过滤style标签

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); //过滤html标签

        System.out.println(htmlStr.trim()); //返回文本字符串

    }

    public void test2() {
        String html = "<div style=\"margin-top:20px\"><strong>美国空中国民警卫队在夏威夷演习</strong></div><div><img src=\"http://cms-bucket.ws.126.net/2019/08/24/bb2bf55e7fac41fb85bcc8e2cffeae69.jpeg\" style=\"width:100%\"/></div><p></p><div style=\"margin-top:20px\"><strong>美军B-52轰炸机挂载水雷 展示布雷能力</strong></div><div><img src=\"http://pic-bucket.ws.126.net/photo/0001/2019-08-24/ENB9FV344T8E0001NOS.jpg\" style=\"width:100%\"/></div><p></p><div style=\"margin-top:20px\"><strong>传统不能丢!空军歼-10C战机打火箭弹</strong></div><div><img src=\"http://pic-bucket.ws.126.net/photo/0001/2019-08-24/ENB95K7O4T8E0001NOS.jpg\" style=\"width:100%\"/></div><p></p><div style=\"margin-top:20px\"><strong>解放军高海拔训练 突击车卡车炮开火</strong></div><div><img src=\"http://pic-bucket.ws.126.net/photo/0001/2019-08-24/ENB7H9QP4T8E0001NOS.jpg\" style=\"width:100%\"/></div><p></p><div style=\"margin-top:20px\"><strong>俄总统专用直升机曝光 用安-124空运</strong></div><div><img src=\"http://pic-bucket.ws.126.net/photo/0001/2019-08-24/ENB72KJP4T8E0001NOS.jpg\" style=\"width:100%\"/></div><p></p>";


        StringBuffer buffer = new StringBuffer();
        Perl5Util preg = new Perl5Util();
        preg.substitute(buffer, "s/<script[^>]*?>.*?<\\/script>//gmi", html);
//过滤script标签
        html = buffer.toString();
        buffer.setLength(0);
        preg.substitute(buffer, "s#<[/]*?(?!p|img|span)[^<>]*?>##gmi", html);
//( p/img/span ...标签之外,都删除)
        html = buffer.toString();
        System.out.println(html);

    }


    @Test
    public void test3() {

        RobotContent robotContent = new RobotContent();

        robotContent.setText("您好！我是茹来实习小波特。请问有什么可以帮您的吗？\uD83D\uDE42");
        List list = new ArrayList();
        list.add("导航");
        list.add("笑话");
        robotContent.setOptions(list);

        Integer msgType = 6;
        String text = robotContent.getText();
        List<String> options = robotContent.getOptions();
        Whitelist whitelist = new Whitelist();

        if (Objects.equals(5, msgType)) {
            if (StringUtils.contains(text, "img")) {
                whitelist.addAttributes("img", "src");
                String docStr = Jsoup.clean(text, whitelist);
                String[] docArr = docStr.split("<img ");
                for (String str : docArr) {
                    if (StringUtils.contains(str, "src")) {
                        String[] texts = str.split(">");
                        for (String txt : texts) {
                            if (StringUtils.contains(txt, "src")) {
                                String fileUrl = txt.replace("src=", "").replace("\"", "");
                                String fileName = StringUtils.substringAfterLast(fileUrl, "/");
                                //发送图片消息
                                System.out.println("img:" + fileUrl + ":" + fileName);
                            } else {
                                System.out.println("text" + txt);
                            }
                        }
                    } else {
                        System.out.println("text:" + str);
                    }
                }
            } else {
                if (StringUtils.isNotEmpty(text)) {
                    System.out.println("text" + text);
                }
            }

        }
        if (Objects.equals(6, msgType)) {
            System.out.println("options:" + options);
        }
    }

    @Test
    public void test4() {

        String str = "sfe";

        Optional<Object> cnoObj = Optional.ofNullable(str);
        if (cnoObj.isPresent()) {
            System.out.println("打印了：" + str);
        }
    }

    @Test
    public void test5() {
        RobotContent robotContent = new RobotContent();

        robotContent.setText("消息+按钮。<a href=\"http://baidu.com\" target=\"_blank\">百度</a> 显示一个图片 <img src=\"https://www.baidu.com/img/bd_logo1.png\" width='100%'><br>消息完毕。");
        List list = new ArrayList();
        list.add("导航");
        list.add("笑话");
        robotContent.setOptions(list);

        Integer msgType = 5;
        String text = robotContent.getText();
        List<String> options = robotContent.getOptions();
        Whitelist whitelist = new Whitelist();

        if (Objects.equals(5, msgType)) {
            if (StringUtils.contains(text, "href")) {
                //whitelist.addAttributes("img", "src");
                whitelist.addAttributes("a", "href");
                String docStr = Jsoup.clean(text, whitelist);
                String[] docArr = docStr.split("<a ");

                for (String str : docArr) {

                    String[] strArr = str.split("<a ");
                    StringBuilder stringBuilder = new StringBuilder();
                    for (String textContent : strArr) {
                        if (StringUtils.contains(str, "href")) {
                            textContent = StringUtils.remove(textContent, "</a>");
                            textContent = StringUtils.remove(textContent, "\">");
                            textContent = StringUtils.remove(textContent, "href=\"");
                            stringBuilder.append(textContent);
                        } else {
                            stringBuilder.append(textContent);
                        }
                    }
                    str = stringBuilder.toString();
                    System.out.println(str);
                }
            } else {
                if (StringUtils.isNotEmpty(text)) {
                    System.out.println("text" + text);
                }
            }

        }
        if (Objects.equals(6, msgType)) {
            System.out.println("options:" + options);
        }
    }

    @Test
    public void test6() {
        //System.out.println(new BCryptPasswordEncoder().encode("life1234@#"));
    }

    @Test
    public void test7() {
        //System.out.println(new BCryptPasswordEncoder().encode("Haph123@#"));
    }

}

