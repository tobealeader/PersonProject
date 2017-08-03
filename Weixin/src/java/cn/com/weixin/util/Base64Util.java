package cn.com.weixin.util;

import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
 * Created by lenovo on 2017/3/12.
 */
public class Base64Util {
    public static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while( (len=inStream.read(buffer)) != -1 ){
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }
    public static String GetImageStr(String imgFile)
    {
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try
        {
            URL url = new URL(imgFile);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            in = conn.getInputStream();
            data = readInputStream(in);
            //in.read(data);
            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);//返回Base64编码过的字节数组字符串
    }
}
