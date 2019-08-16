package com.dlion.testproject.constant;

/**
 * 微信和tw表情映射
 *
 * @author 李正元
 * @date 2019/6/24
 */
public enum EmoticonEnum {

    //微笑
    SMELL("/::)", "1f31d", "微笑"),

    GRIEVED("/::~", "1f974", "伤心"),

    BEAUTY("/::B", "1f63b", "美女"),

    DAZE("/::|", "1f644", "发呆"),

    SUNGLASSES("/:8-)", "1f576", "墨镜"),

    CRY("/::<", "1f62d", "大哭"),

    SHY("/::$", "263a", "羞"),

    DUMB("/::X", "1f64a", "哑"),

    SLEEP("/::Z", "1f634", "睡"),

    WEEP("/::'(", "", "哭"),

    OOPS("/::-|", "1f61f", "囧"),

    ANGRY("/::@", "1f621", "怒"),

    NAUGHTY("/::P", "1f61b", "调皮"),

    LICKING("/::D", "1f62c", "呲牙笑"),

    SURPRISED("/::O", "1f62f", "惊讶"),

    SAD("/::(", "2639", "难过"),

    COOL("/::+", "1f913", "酷"),

    SWEAT("/::L", "1f625", "汗"),

    CRAZY("/::Q", "1f631", "抓狂"),

    THREWUP("/::T", "1f92e", "吐"),

    GRIN("/:,@P", "", "捂嘴笑"),

    HAPPY("/:,@-D", "1f63a", "愉快"),

    WHITEEYE("/::d", "", "白眼"),

    ARROGANT("/:,@o", "", "傲慢"),

    HUNGRY("/::g", "", "饿"),

    TIRED("/:|-)", "", "困"),

    SCARE("/::!", "1f632", "吓"),

    GLAD("/::>", "1f638", "高兴"),

    LAIDBACK("/::,@", "", "悠闲"),

    WORKHARD("/:,@f", "", "奋斗"),

    CURSE("/::-S", "", "咒骂"),

    DOUBT("/:?", "", "疑问"),

    SECRET("/:,@x", "1f910", "秘密"),

    GOSH("/:,@@", "", "晕"),

    crazy("/::8","","疯了"),

    SORROW("/:,@!", "", "哀"),

    BONE("/:!!!", "", "骷髅"),

    BEAT("/:xx", "", "敲打"),

    BYE("/:bye", "", "再见"),

    WIPE("/:wipe", "1f613", "汗"),

    DIP("/:dig", "", "抠鼻"),

    HANDCLA("/:handclap", "", "鼓掌"),

    BAD("/:&-(", "", "溴大了"),

    SPOOF("/:B-)", "", "坏笑"),

    LEFTHEN("/:<@", "", "左哼哼"),

    RIGHTHEN("/:@>", "", "右哼哼"),

    YAWN("/::-O", "1f971", "哈欠"),

    DESPISE("/:>-|", "", "鄙视"),

    WRONGED("/:P-(", "1f641", "委屈"),

    WALMOSTCRY("/::'|", "1f629", "快哭了"),

    SINISTER("/:X-)", "", "阴险"),

    KISS("/::*", "1f61a", "亲亲"),

    HORRIFY("/:@x", "", "吓"),

    POOR("/:8*", "1f97a", "可怜"),

    KNIFE("/:pd", "1f52a", "刀"),

    WATERMELON("/:<W>", "1f349", "西瓜"),

    BEER("/:beer", "1f942", "啤酒"),

    BASKETBALL("/:basketb", "1f3c0", "篮球"),

    PINGPONG("/:oo", "1f3d3", "乒乓"),

    COFFEE("/:coffee", "2615", "咖啡"),

    EAT("/:eat", "", "米饭"),

    PIG("/:pig", "", "猪头"),

    ROSE("/:rose", "1f339", "玫瑰"),

    FADE("/:fade", "1f940", "枯"),

    SHOWLOVE("/:showlove", "", "唇"),

    HEART("/:heart", "1f493", "爱心"),

    HEARTBREAK("/:break", "1f494", "心碎"),

    CAKE("/:cake", "1f382", "生日"),

    ELECTRICITY("/:li", "26a1", "电"),

    BOME("/:bome","","炸弹"),

    SMALL_KNIFE("/:kn","1f52a","刀"),

    SHIT("/:shit","","便便"),

    MOON("/:moon","","月亮"),

    BLESSING("[Blessing]", "", "福"),

    MAHJONG("[發]", "", "麻将"),

    PACKET("[Packet]", "", "红包"),

    SMART("[Smart]", "", "机智"),

    YEAH("[Yeah!]", "", "耶"),

    SMIRK("[Smirk]", "", "奸笑"),

    FACEPALM("[Facepalm]", "", "捂脸笑"),

    HEY("[Hey]", "", "嘿哈"),

    JUMP("/:jump", "", "跳跳"),

    SHAKE("/:shake", "", "发抖"),

    OFIRE("/:<O>", "", "怄火"),

    CIRCLE("/:circle", "", "转圈"),

    STRONG("/:strong", "", "强"),

    WEAK("/:weak", "", "弱"),

    SHARE("/:share", "", "握手"),

    VICTORY("/:v", "", "胜利"),

    HOLDING("/:@)", "", "抱拳"),

    SEDUCE("/:jj", "1f91e", "勾引"),

    FIST("/:@@", "", "拳头"),

    OK("/:ok", "1f44c", "OK"),

    SUN("/:sun", "1f505", "太阳"),

    HUG("/:hug", "", "拥抱"),

    EMOJI_BEAMING(":smile:", "1f601", "微笑"),

    EMOJI_Medical(":mask:", "1f637", "生病"),

    EMOJI_TEARS_OF_JOY(":joy:", "1f602", "笑哭"),

    EMOJI_STUCK_OUT_TONGUE(":stuck_out_tongue_closed_eyes:", "1f61b", "吐舌"),

    EMOJI_FLUSHED(":flushed:", "", "瞪眼"),

    EMOJI_SCREAM(":scream:", "", "尖叫"),

    CONCERNED("[Concerned]","","皱眉"),

    GHOST(":ghost:","","鬼魂"),

    EMOJI_PENSIVE(":pensive:", "1f614", "忧郁"),

    EMOJI_UNAMUSED(":unamused:", "1f615", "垂头丧气"),

    EMOJI_PRAY(":pray:", "", "谢谢"),

    EMOJI_MUSCLE(":muscle:", "1f4aa_1f3fd", "肌肉"),

    EMOJI_TADA(":tada:", "1f389", "鲜花"),

    EMOJI_GIFT(":gift:", "", "礼物");


    public String wxEmoticon;

    public String twEmotion;

    public String desc;

    EmoticonEnum(String wxEmoticon, String twEmotion, String desc) {
        this.wxEmoticon = wxEmoticon;
        this.twEmotion = twEmotion;
        this.desc = desc;
    }

}
