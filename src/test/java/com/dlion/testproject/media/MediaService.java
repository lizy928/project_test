package com.dlion.testproject.media;

import com.dlion.testproject.model.WaveHeader;
import com.dlion.testproject.service.HttpService;
import it.sauronsoftware.jave.AudioUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Objects;

/**
 * @author 李正元
 * @date 2019/9/4
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MediaService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HttpService httpService;

    @Test
    public void test() {
        String fileUrl = "http://file.api.weixin.qq.com/cgi-bin/media/get?media_id=zF87pJfla_XHwHmUrHSCX3X7SM4ak8YfAGNDtE1HWD4ejlcNl612cMloI7L6qL-s&access_token=25_nujn4kE9vWzRGCqa4uNTPZyoAm9FOf6uxJ1Hh_hOSYZDk-ZjrXL4tG83J89yVjGl8zmxl-EQiOs4SGQcjmjmNLzmSP5HhUGKNF7ug8PUwzbX4WQ1sW_zM4nq7futOMtgpNwfyeq6osJd7YBhSDQdADDITG";

        try {
            CloseableHttpResponse response = httpService.getFile(fileUrl);
            HttpEntity httpEntity = response.getEntity();


            int statusCode = response.getStatusLine().getStatusCode();

            if (Objects.equals(statusCode, HttpStatus.OK.value())) {

                InputStream inputStream = httpEntity.getContent();

                String fileOriginalName = "";
                String ext = "jpg";

                Header[] headers = response.getHeaders("Content-disposition");
                for (Header header : headers) {
                    String headerName = header.getName();
                    if (StringUtils.equalsIgnoreCase(headerName, "Content-disposition")) {
                        fileOriginalName = header.getValue().split(";")[1].split("\"")[1];
                        ext = fileOriginalName.substring(fileOriginalName.lastIndexOf(".") + 1);
                        break;
                    }
                }

                //File source = new File("E:\\gtGEHFWPLYxlsJmjaMJJYwuvvQxh6qHcZxzV-FuvCtkWM9XFimee0L2Zi9sCepqS.amr");

                File source = new File("D://test.amr");

                OutputStream os = null;
                try {
                    os = new FileOutputStream(source);
                    int len = 0;
                    byte[] buffer = new byte[8192];

                    while ((len = inputStream.read(buffer)) != -1) {
                        os.write(buffer, 0, len);
                    }
                } finally {
                    os.close();
                    inputStream.close();
                }


                File target = new File("D:\\1381370093615.mp3");

              /*  AudioAttributes audio = new AudioAttributes();
                Encoder encoder = new Encoder();
                audio.setCodec("libmp3lame");
                EncodingAttributes attrs = new EncodingAttributes();
                attrs.setFormat("mp3");
                attrs.setAudioAttributes(audio);

                try {
                    encoder.encode(source, target, attrs);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (InputFormatException e) {
                    e.printStackTrace();
                } catch (EncoderException e) {
                    e.printStackTrace();
                }
*/
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void test2() throws Exception {


        String src = "D://test.amr";
        FileInputStream fis = new FileInputStream(src);
        FileOutputStream fos = new FileOutputStream("D://result.wav");

        //计算长度
        int PCMSize = 0;
        byte[] buf = new byte[1024 * 4];
        int size = fis.read(buf);

        while (size != -1) {
            PCMSize += size;
            size = fis.read(buf);
        }
        fis.close();

        //填入参数，比特率等等。这里用的是16位单声道 8000 hz
        WaveHeader header = new WaveHeader(100);
        //长度字段 = 内容的大小（PCMSize) + 头部字段的大小(不包括前面4字节的标识符RIFF以及fileLength本身的4字节)
        header.fileLength = PCMSize + (44 - 8);
        header.fmtHdrLeth = 16;
        header.bitsPerSample = 16;
        header.channels = 1;
        header.formatTag = 0x0001;
        header.avgBytesPerSec = 8000;
        header.blockAlign = (short) (header.channels * header.bitsPerSample / 8);
        header.avgBytesPerSec = header.blockAlign * header.avgBytesPerSec;
        header.dataHdrLeth = PCMSize;

        byte[] h = header.getHeader();

        assert h.length == 44; //WAV标准，头部应该是44字节
        //write header
        fos.write(h, 0, h.length);
        //write data stream
        fis = new FileInputStream(src);
        size = fis.read(buf);
        while (size != -1) {
            fos.write(buf, 0, size);
            size = fis.read(buf);
        }
        fis.close();
        fos.close();
        System.out.println("Convert OK!");
    }

    @Test
    public void test3() {

        File source = new File("D://test.amr");
        File target = new File("D://testAudio.mp3");
        AudioUtils.amrToMp3(source, target);
    }

    @Test
    public void test31() throws Exception {

        File source = new File("D://tem/voice/test.amr");

        File target = new File("D://tem/voice/testAudio11.mp3");

       /* AudioAttributes audio = new AudioAttributes();
        audio.setChannels(new Integer(1));
        audio.setSamplingRate(new Integer(8000));
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("mp3");
        attrs.setAudioAttributes(audio);
        Encoder encoder = new Encoder();

        try {
            encoder.encode(source, target, attrs);
        } catch (Exception e) {
            System.out.println("error>>>" + e);
        }
        System.out.println(source);
        System.out.println(target);
        System.out.println("secc");*/

    }

    @Test
    public void test4() {

       /* Resource resource = new ClassPathResource("resource/temp");
        try {
            File sourceFile =  resource.getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        try {
            String path = ResourceUtils.getURL("classpath:").getPath();

            File rootFile = new File(path);
            if (!rootFile.exists()) {
                logger.info("根目录不存在, 重新创建");
                rootFile = new File("");
                logger.info("重新创建的根目录: " + rootFile.getAbsolutePath());
            }
            logger.debug("项目根目录: " + rootFile.getAbsolutePath());
            System.out.println("rootFile.getAbsolutePath()" + rootFile.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void test5() {

        String fileName = "WIdA0QqxrAQVFt9Yqqg4zON-pedJO0jx_5sGswCwulCenNC2BwieamHAqOug_KXP.amr";

        String replace = StringUtils.replace(fileName, ".amr", ".wav");
        System.out.println();


    }

    @Test
    public void test6() {

        File source = new File("D://tem/voice/test.amr");
        File target = new File("D://tem/voice/testAudio11.wav");

       /* AudioAttributes audio = new AudioAttributes();

        //设置解码格式，比特率，位数，声道等信息
        audio.setCodec("pcm_s16le");
        audio.setBitRate(new Integer(705000));
        audio.setChannels(new Integer(1));
        audio.setSamplingRate(new Integer(44100));
        audio.setVolume(new Integer(500));

        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("wav");
        attrs.setAudioAttributes(audio);
        //attrs.setDuration(duration);

        Encoder encoder = new Encoder();

        try {
            encoder.encode(source, target, attrs);
        } catch (Exception e) {
            logger.error("音频转码异常", e);
        }
        System.out.println(source);
        System.out.println(target);
        System.out.println("secc");*/

    }


    @Test
    public void test7() {


    }


}



