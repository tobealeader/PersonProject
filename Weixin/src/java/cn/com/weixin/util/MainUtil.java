package cn.com.weixin.util;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * 一个测试类
 * Created by lenovo on 2017/3/11.
 */
public class MainUtil {
    public static void main(String[] args)
    {
        System.out.println(WeixinUtil.getAgeAndSex("d:/dj.jpg"));
        /*try {
           *//* AccessToken accessToken=WeixinUtil.getAccessToken();
            System.out.println("票据："+accessToken.getAccess_token());
            System.out.println("有效时间："+accessToken.getExpires_in());
            String path="d:/jj.jpg";
            String mediaId=WeixinUtil.uploadImage(path,accessToken.getAccess_token(),"image");
            System.out.println(mediaId);*//*
            URL url = ClassLoader.getSystemResource("image/bokeyuang.png");
            System.out.println(url.getPath());
            File file = new File(url.getPath());
            System.out.println(file.exists());
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
