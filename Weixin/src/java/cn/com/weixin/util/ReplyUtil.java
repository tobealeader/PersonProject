package cn.com.weixin.util;

import cn.com.weixin.sto.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 消息的各种回复
 * Created by lenovo on 2017/3/10.
 */
public class ReplyUtil {
    private static String Image_url="http://d3a9c564.ngrok.io/image/";
    /**
     * 主菜单
     * @return
     */
    public static String mainText()
    {
        return "感谢您的关注，请按以下提示操作：\n\n" +
                "回复数字【1】: 关于我\n" +
                "回复数字【2】: 我要翻译\n"+
                "回复数字【3】: 查看天气\n"+
                "回复数字【4】: 头像识别\n"+
                "回复“？”跳出提示菜单\n";
    }

    /**
     * 封装text消息
     * @param toUserName
     * @param fromUserName
     * @param content
     * @return
     */
    public static String initTextMessage(String toUserName,String fromUserName,String content)
    {
        String message=null;
        TextMessageSto textMessageSto=new TextMessageSto();
        textMessageSto.setFromUserName(toUserName);
        textMessageSto.setToUserName(fromUserName);
        textMessageSto.setMsgType(MsgType.MESSAGE_TEXT);
        textMessageSto.setCreateTime(new Date().getTime());
        textMessageSto.setContent(content);
        message=TransformUtil.textMessageToXml(textMessageSto);
        return message;
    }

    /**
     * 封装图文消息
     * @param toUserName
     * @param fromUserName
     * @return
     */
    public static String initNewsMessage(String toUserName,String fromUserName)
    {
        String message = null;
        NewsMessageSto newsMessageSto=new NewsMessageSto();
        List<News> newsList=new ArrayList<News>();
        News news=new News();
        news.setTitle("个人介绍");
        news.setDescription("大家好，请叫我DJ，我是一名大三学生！");
        news.setPicUrl("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=95400049,1800698204&fm=23&gp=0.jpg");
        news.setUrl("http://www.djtime.com.cn/");
        News news1=new News();
        news1.setTitle("我的博客\n代码改变世界");
        news1.setDescription("里面有些我写的博文");
        news1.setPicUrl("https://mmbiz.qlogo.cn/mmbiz_png/RCJILlZrSSNBiaoM43eaIu7qoicZV8Y2lQRWeicDnaFRdWmwfQFgbtZ3l1oNxIyA9p8rydyWLbAy95We6Sl3lb2Fw/0?wx_fmt=png");
       // news1.setPicUrl(Image_url+"bokeyuang.png");
        news1.setUrl("http://www.cnblogs.com/dj3839/");
        News news2=new News();
        news2.setTitle("我的github\n代码改变世界");
        news2.setDescription("里面有些我做过的小项目");
        news2.setPicUrl("https://mmbiz.qlogo.cn/mmbiz_png/RCJILlZrSSNBiaoM43eaIu7qoicZV8Y2lQcV9tYv9X2v3g1VDqAAdMusqTFr4WzSNRSSiaTyYKzoJOhGiahDzTRF3w/0?wx_fmt=png");
        //news2.setPicUrl(Image_url+"github.png");
        news2.setUrl("https://github.com/tobealeader");
        newsList.add(news);
        newsList.add(news1);
        newsList.add(news2);
        newsMessageSto.setToUserName(fromUserName);
        newsMessageSto.setFromUserName(toUserName);
        newsMessageSto.setCreateTime(new Date().getTime());
        newsMessageSto.setMsgType(MsgType.MESSAGE_NEWS);
        newsMessageSto.setArticleCount(newsList.size());
        newsMessageSto.setArticles(newsList);
        message=TransformUtil.NewsMessageToXml(newsMessageSto);
        return message;
    }

    /**
     * 封装图片消息
     */
    public static String initImageMessage(String toUserName,String fromUserName,String mediaId)
    {
        Image image=new Image();
        image.setMediaId(mediaId);
        ImageMessageSto imageMessageSto=new ImageMessageSto();
        imageMessageSto.setMsgType(MsgType.MESSAGE_IMAGE);
        imageMessageSto.setToUserName(fromUserName);
        imageMessageSto.setFromUserName(toUserName);
        imageMessageSto.setCreateTime(new Date().getTime());
        imageMessageSto.setImage(image);
        return TransformUtil.ImageMessageToXml(imageMessageSto);
    }
}
