package cn.com.weixin.controller;

import cn.com.weixin.sto.MsgType;
import cn.com.weixin.sto.TextMessageSto;
import cn.com.weixin.util.ReplyUtil;
import cn.com.weixin.util.Sha1Util;
import cn.com.weixin.util.TransformUtil;
import cn.com.weixin.util.WeixinUtil;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.parsing.SourceExtractor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by lenovo on 2017/3/4.
 */
@Controller
public class WeiXinController {


    /**
     * 验证消息的确来自微信服务器
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    @RequestMapping(value = "/link",method = RequestMethod.GET)
    @ResponseBody
    public String Link(String signature, String timestamp, String nonce, String echostr)
    {
        //排序
        String string[]=new String[]{MsgType.token,timestamp,nonce};
        Arrays.sort(string);

        //连接字符串
        StringBuffer stringBuffer=new StringBuffer();
        for (String item : string)
        {
            stringBuffer.append(item);
        }

        //sha1加密
        String tmp = Sha1Util.getSha1(stringBuffer.toString());

        if(tmp.equals(signature))
        {
            return echostr;
        }
        else
        {
            return "error";
        }
    }

    @RequestMapping(value = "link",method = RequestMethod.POST,produces = "application/text; charset=utf-8")
    @ResponseBody
    public String link_post(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException {
        Map<String,String> map= TransformUtil.xmlToMap(request);
        String toUserName=map.get("ToUserName");
        String fromUserName=map.get("FromUserName");
        String msgType=map.get("MsgType");
        String content=map.get("Content");
        String message=null;
        if(MsgType.MESSAGE_TEXT.equals(msgType))
        {
            if(content.equals("?")||content.equals("？"))
            {
                message=ReplyUtil.initTextMessage(toUserName,fromUserName,ReplyUtil.mainText());
            }
            else if(content.equals("1"))
            {
                message=ReplyUtil.initNewsMessage(toUserName,fromUserName);
            }
            else if(content.equals("2"))
            {
                String text="翻译举例:\n"+
                        "翻译你好\n"+
                        "翻译hello\n"+
                        "翻译こんにちは\n";
                message=ReplyUtil.initTextMessage(toUserName,fromUserName,text);
            }
            else if(content.equals("3"))
            {
                String text="查看天气举例:\n"+
                        "宁波天气\n"+
                        "上海天气\n"+
                        "或者通过发送位置来获取所在地天气";
                message=ReplyUtil.initTextMessage(toUserName,fromUserName,text);
            }
            else if(content.equals("4"))
            {
                String text="也许你对一个人的年龄、性别\n"+
                        "极为好奇\n"+
                        "        那么\n"+
                        "快他的头像发来给我识别吧\n"+
                        "（来自阿里云的人脸识别技术）";
                message=ReplyUtil.initTextMessage(toUserName,fromUserName,text);
            }
            else if(content.length()>2&&content.substring(0,2).equals("翻译")) {
                String text = content.substring(2, content.length());
                text = WeixinUtil.translate(text).toString();
                message = ReplyUtil.initTextMessage(toUserName, fromUserName, text);
            }
            else if(content.length()>2&&content.substring(content.length()-2,content.length()).equals("天气"))
            {
                String text = content.substring(0, content.length()-2);
                text = WeixinUtil.GetWeather(text);
                if(text.equals("false"))
                {
                    text="无此地天气信息";
                }
                message = ReplyUtil.initTextMessage(toUserName, fromUserName, text);
            }
        }
        else if(MsgType.MESSAGE_EVENT.equals(msgType))
        {
            if(MsgType.MESSAGE_SUBSCRIBE.equals(map.get("Event")))
            {
                message=ReplyUtil.initTextMessage(toUserName,fromUserName,ReplyUtil.mainText());
            }
        }
        else if(MsgType.MESSAGE_LOCATION.equals(msgType))
        {
            String location_X=map.get("Location_X");
            String location_Y=map.get("Location_Y");
            String text=WeixinUtil.getCity(location_X+","+location_Y);
            text=WeixinUtil.GetWeather(text);
            message = ReplyUtil.initTextMessage(toUserName, fromUserName, text);
        }
        else if (MsgType.MESSAGE_IMAGE.equals(msgType))
        {
            //上传图片，需要微信认证
            //String mediaId="p5V6lzzpCjZrJhJ3mJm3wKOlTnG4RoHdKNWvG0uzM-kS4Ijni4jASjONsnfNf_TD";
            //message=ReplyUtil.initImageMessage(toUserName,fromUserName,mediaId);
            String url=map.get("PicUrl");
            String text=WeixinUtil.getAgeAndSex(url);
            message=ReplyUtil.initTextMessage(toUserName,fromUserName,text);
        }
        else if(MsgType.MESSAGE_VOICE.equals(msgType))
        {
            String text="请不要发语音消息给我，我现在还无法识别";
            message=ReplyUtil.initTextMessage(toUserName,fromUserName,text);
        }
        else if(MsgType.MESSAGE_LINK.equals(msgType))
        {
            String text="这是条奇怪的链接";
            message=ReplyUtil.initTextMessage(toUserName,fromUserName,text);
        }
        return message;
    }


}
