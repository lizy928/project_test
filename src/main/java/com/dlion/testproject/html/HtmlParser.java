package com.dlion.testproject.html;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 李正元
 * @date 2019/8/24
 */
public class HtmlParser {

    protected List<List<String>> data = new LinkedList<List<String>>();

    /**
     * 获取value值
     *
     * @param e
     * @return
     */
    public static String getValue(Element e) {
        return e.attr("value");
    }

    /**
     * 获取
     * <tr>
     * 和
     * </tr>
     * 之间的文本
     *
     * @param e
     * @return
     */
    public static String getText(Element e) {
        return e.text();
    }

    /**
     * 识别属性id的标签,一般一个html页面id唯一
     *
     * @param body
     * @param id
     * @return
     */
    public static Element getID(String body, String id) {
        Document doc = Jsoup.parse(body);
        // 所有#id的标签
        Elements elements = doc.select("#" + id);
        // 返回第一个
        return elements.first();
    }

    /**
     * 识别属性class的标签
     *
     * @param body
     * @param class
     * @return
     */
    public static Elements getClassTag(String body, String classTag) {
        Document doc = Jsoup.parse(body);
        // 所有#id的标签
        return doc.select("." + classTag);
    }

    /**
     * 获取tr标签元素组
     *
     * @param e
     * @return
     */
    public static Elements getTR(Element e) {
        return e.getElementsByTag("tr");
    }

    /**
     * 获取td标签元素组
     *
     * @param e
     * @return
     */
    public static Elements getTD(Element e) {
        return e.getElementsByTag("td");
    }

    /**
     * 获取表元组
     *
     * @param table
     * @return
     */
    public static List<List<String>> getTables(Element table) {
        List<List<String>> data = new ArrayList<>();

        for (Element etr : table.select("tr")) {
            List<String> list = new ArrayList<>();
            for (Element etd : etr.select("td")) {
                String temp = etd.text();
                //增加一行中的一列
                list.add(temp);
            }
            //增加一行
            data.add(list);
        }
        return data;
    }

    /**
     * 读html文件
     *
     * @param fileName
     * @return
     */
    public static String readHtml(String fileName) {
        FileInputStream fis = null;
        StringBuffer sb = new StringBuffer();
        try {
            fis = new FileInputStream(fileName);
            byte[] bytes = new byte[1024];
            while (-1 != fis.read(bytes)) {
                sb.append(new String(bytes));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // String url = "http://www.baidu.com";
        // String body = HtmlBody.getBody(url);
        // System.out.println(body);

        String html = "<div style=\"margin-top:20px\"><strong>美国空中国民警卫队在夏威夷演习</strong></div><div><img src=\"http://cms-bucket.ws.126.net/2019/08/24/bb2bf55e7fac41fb85bcc8e2cffeae69.jpeg\" style=\"width:100%\"/></div><p></p><div style=\"margin-top:20px\"><strong>美军B-52轰炸机挂载水雷 展示布雷能力</strong></div><div><img src=\"http://pic-bucket.ws.126.net/photo/0001/2019-08-24/ENB9FV344T8E0001NOS.jpg\" style=\"width:100%\"/></div><p></p><div style=\"margin-top:20px\"><strong>传统不能丢!空军歼-10C战机打火箭弹</strong></div><div><img src=\"http://pic-bucket.ws.126.net/photo/0001/2019-08-24/ENB95K7O4T8E0001NOS.jpg\" style=\"width:100%\"/></div><p></p><div style=\"margin-top:20px\"><strong>解放军高海拔训练 突击车卡车炮开火</strong></div><div><img src=\"http://pic-bucket.ws.126.net/photo/0001/2019-08-24/ENB7H9QP4T8E0001NOS.jpg\" style=\"width:100%\"/></div><p></p><div style=\"margin-top:20px\"><strong>俄总统专用直升机曝光 用安-124空运</strong></div><div><img src=\"http://pic-bucket.ws.126.net/photo/0001/2019-08-24/ENB72KJP4T8E0001NOS.jpg\" style=\"width:100%\"/></div><p></p>";

        //Document doc = Jsoup.parse(html);
        //System.out.println(Jsoup.isValid(html, Whitelist.none()));

      /*  Whitelist whitelist=new Whitelist();  //通过设置白名单留下想要的html标签
        whitelist.addAttributes("img","src");
        String docStr = Jsoup.clean(html,whitelist);
        System.out.println(docStr);*/
      String tes = "发送消息后和客服回复消息时间间隔的平均值，每个平均响应时长不为零的对话的和的平均值";

        Whitelist whitelist = new Whitelist();
        whitelist.addAttributes("img", "src");
        String docStr = Jsoup.clean(html, whitelist);
        if (StringUtils.contains(docStr, "img")) {
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
        }

        Document doc = Jsoup.parse(html);
        Elements strong = doc.select("div strong");
        strong.forEach(e -> {
            System.out.println("图文的标题是：" + e.text());
        });

        Elements img = doc.select("img");
        for (Element e : img) {
            System.out.println("图片的地址是：" + e.attr("src"));
        }


        // 获取html的标题
        String title = doc.select("title").text();
        System.out.println(title);
        // 获取按钮的文本
        String btnText = doc.select("div").select("strong").attr("value");
        System.out.println(btnText);
        // 获取导航栏文本
        Elements elements = doc.select(".head_wrapper").select("#u1").select("a");
        for (Element e : elements) {
            System.out.println(e.text());
        }
        Document doc2 = Jsoup.parse(html);
        Element table = doc2.select("strong").first();
        List<List<String>> list = getTables(table);
        for (List<String> list2 : list) {
            for (String string : list2) {
                System.out.print(string + ",");
            }
            System.out.println();
        }
    }

}
