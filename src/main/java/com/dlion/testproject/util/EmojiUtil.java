package com.dlion.testproject.util;

import com.dlion.testproject.constant.EmoticonEnum;
import org.apache.commons.lang3.StringUtils;

/**
 * 微信emoji表情转换工具
 *
 * @author 李正元
 * @date 2019/6/25
 */
public class EmojiUtil {

    //private static final EmojiConverter emojiConverter = EmojiConverter.getInstance();

    /**
     * 将emojiStr转为带有表情的字符
     *
     * @param emojiStr 带有emoji表情的文本
     * @return
     */
   // public static String emojiConverterUnicodeStr(String emojiStr) {
        //return emojiConverter.toUnicode(emojiStr);
    //}

    /**
     * 带有表情的字符串转换为编码
     *
     * @param str 表情的字符串
     * @return
     */
  //  public static String emojiConverterToAlias(String str) {
        //return emojiConverter.toAlias(str);
   // }

    /**
     * 处理微信发送的表情
     *
     * @param wxMsg 微信发送的消息内容
     * @return
     */
    public static String handleWxEmoticon(String wxMsg) {

        wxMsg = "";// emojiConverterToAlias(wxMsg);

        for (EmoticonEnum emoticonEnum : EmoticonEnum.values()) {
            if (StringUtils.contains(wxMsg, emoticonEnum.wxEmoticon)) {
                if (StringUtils.isEmpty(emoticonEnum.twEmotion)) {
                    wxMsg = StringUtils.replace(wxMsg, emoticonEnum.wxEmoticon,
                            "【" + emoticonEnum.desc + "】");
                } else {
                    String targetMsg = "[:" + emoticonEnum.twEmotion + ":]";
                    wxMsg = StringUtils.replace(wxMsg, emoticonEnum.wxEmoticon, targetMsg);
                }
            }
        }
        return wxMsg;
    }

    /**
     * 处理座席发送的表情
     *
     * @param agentMsg 座席发送的消息
     * @return
     */
    public static String handleTwEmoticon(String agentMsg) {

        String[] strArr = agentMsg.split(":]");
        StringBuilder stringBuilder = new StringBuilder();

        for (String item : strArr) {
            if (StringUtils.contains(item, "[:")) {
                String emoji = StringUtils.substringAfterLast(item, "[:");
                String text = StringUtils.substringBeforeLast(item, "[:");
                stringBuilder.append(text);
                for (EmoticonEnum emoticonEnum : EmoticonEnum.values()) {
                    if (StringUtils.equals(emoji, emoticonEnum.twEmotion)) {
                        emoji = ""; //EmojiUtil.emojiConverterUnicodeStr(emoticonEnum.wxEmoticon);
                        stringBuilder.append(emoji);
                    }
                }
            } else {
                stringBuilder.append(item);
            }
        }
        return stringBuilder.toString();
    }

}
