package cn.com.weixin.util;

import cn.com.weixin.sto.AccessToken;
import cn.com.weixin.sto.TransSto;

import java.io.*;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.weixin.sto.WeatherSto;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

/**
 * 重要类，处理多个功能请求
 * Created by lenovo on 2017/3/10.
 */
public class WeixinUtil {
    private static final String APPID="wx43a3c8d67a280944";
    private static final String APPSECRET="d95a6774e7d2f12bd7434964a32cfea8";
    //private static final String APPID="wx4e8485029447c9dc";
    //private static final String APPSECRET="281c8e0ff3d4caa9ac3f6692dbfc1d53";
    private static final String AK="2XlAIrLiR6N9PFPZWde8f95rNlGEoBEe";

    private static final String ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    private static final String WEATHER_URL="http://api.map.baidu.com/telematics/v3/weather?location=LOCATION&output=json&ak=11ffd27d38deda622f51c9d314d46b17";
    private static final String BAIDUMAP_URL="http://api.map.baidu.com/geocoder/v2/?location=LOCATION&output=json&pois=1&ak=AK";
    private static final String UPLOADIMAGE_URL="https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
    /**
     * http get请求
     * @param url
     * @return
     * @throws IOException
     */
    public static JSONObject doGetStr(String url) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        //httpURLConnection.setRequestProperty("Content-type", "application/x-java-serialized-object");
        httpURLConnection.connect();
        InputStream inputStream=httpURLConnection.getInputStream();
        InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"utf-8");
        BufferedReader  bufferedReader=new BufferedReader (inputStreamReader);
        String str =null;
        StringBuffer stringBuffer=new StringBuffer();
        while((str=bufferedReader.readLine())!=null)
        {
            stringBuffer.append(str);
        }
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
        inputStream=null;
        httpURLConnection.disconnect();
        return JSONObject.fromObject(stringBuffer.toString());
    }

    /**
     * http post请求
     * @param url
     * @param outStr
     * @return
     * @throws IOException
     */
    public static JSONObject doPostStr(String url,String outStr) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        //httpURLConnection.setRequestProperty("Content-type", "application/x-java-serialized-object");
        httpURLConnection.connect();
        if(outStr!=null)
        {
            OutputStream outputStream=httpURLConnection.getOutputStream();
            outputStream.write(outStr.getBytes("utf-8"));
            outputStream.flush();
            outputStream.close();
        }
        InputStream inputStream=httpURLConnection.getInputStream();
        InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"utf-8");
        BufferedReader  bufferedReader=new BufferedReader (inputStreamReader);
        String str =null;
        StringBuffer stringBuffer=new StringBuffer();
        while((str=bufferedReader.readLine())!=null)
        {
            stringBuffer.append(str);
        }
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
        inputStream=null;
        httpURLConnection.disconnect();
        return JSONObject.fromObject(stringBuffer.toString());
    }

    /**
     * 获取access_token
     * @return
     * @throws IOException
     */
    public static AccessToken getAccessToken() throws IOException {
        AccessToken accessToken=new AccessToken();
        String url=ACCESS_TOKEN_URL.replace("APPID",APPID).replace("APPSECRET",APPSECRET);
        JSONObject jsonObject = doGetStr(url);
        if (jsonObject != null)
        {
            accessToken.setAccess_token(jsonObject.getString("access_token"));
            accessToken.setExpires_in(jsonObject.getInt("expires_in"));
        }
        return accessToken;
    }

    /**
     * 翻译query
     * @param query
     * @return
     * @throws IOException
     */
    public static TransSto translate(String query) throws IOException {
        TransSto transSto=new TransSto();
        transSto.setEn(TranslateUtil.getTransResult(query,"auto","en"));
        transSto.setZh(TranslateUtil.getTransResult(query,"auto","zh"));
        return transSto;
    }

    /**
     * 根据城市获得天气
     * @param LOCATION
     * @return
     */
    public static String GetWeather(String LOCATION){
        StringBuffer stringBuffer=new StringBuffer();
        try {
            String url= WEATHER_URL.replace("LOCATION",TranslateUtil.encode(LOCATION));
            JSONObject jsonObject = doGetStr(url);
            String results=jsonObject.getString("results");
            results=results.substring(1,results.length()-1);
            jsonObject=JSONObject.fromObject(results);
            results=jsonObject.getString("weather_data");
            JSONArray jsonArray=JSONArray.fromString(results);
            List<WeatherSto> list=(List<WeatherSto>)JSONArray.toList(JSONArray.fromObject(jsonArray), WeatherSto.class);

            if(list.size()>0)
            {
                stringBuffer.append("今日天气：\n");
                stringBuffer.append("date："+list.get(0).getDate()+"\n");
                stringBuffer.append("weather："+list.get(0).getWeather()+"\n");
                stringBuffer.append("temperature："+list.get(0).getTemperature()+"\n");
                stringBuffer.append("wind："+list.get(0).getWind()+"\n\n");
            }
            if(list.size()>1)
            {
                stringBuffer.append("明日天气：\n");
                stringBuffer.append("date："+list.get(1).getDate()+"\n");
                stringBuffer.append("weather："+list.get(1).getWeather()+"\n");
                stringBuffer.append("temperature："+list.get(1).getTemperature()+"\n");
                stringBuffer.append("wind："+list.get(1).getWind());
            }
        }catch (Exception e)
        {
            stringBuffer.append("false");
        }
        return stringBuffer.toString();
    }

    /**
     * 根据坐标获得城市地址
     * @param LOCATION
     * @return
     */
    public static String getCity(String LOCATION)
    {
        String city=null;
        String url=BAIDUMAP_URL.replace("AK",AK).replace("LOCATION",LOCATION);
        try {
            JSONObject jsonObject=doGetStr(url);
            String result=jsonObject.getString("result");
            jsonObject=JSONObject.fromString(result);
            String addressComponent=jsonObject.getString("addressComponent");
            jsonObject=JSONObject.fromString(addressComponent);
            city=jsonObject.getString("city");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return city;
    }

    /**
     * 上传图片，需要微信认证
     * @param filePath
     * @param accessToken
     * @param type
     * @return
     * @throws IOException
     */
    public static String uploadImage(String filePath,String accessToken,String type) throws IOException {
        File file=new File(filePath);
        if(!file.exists()||!file.isFile())
        {
            System.out.println("none");
            throw new IOException("文件不存在");
        }
        String url=UPLOADIMAGE_URL.replace("ACCESS_TOKEN",accessToken).replace("TYPE",type);
        HttpURLConnection httpURLConnection=(HttpURLConnection)new URL(url).openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        // 设置请求头信息
        httpURLConnection.setRequestProperty("Connection","Keep-Alive");
        httpURLConnection.setRequestProperty("Charset","UTF-8");
        // 设置边界,这里的boundary是http协议里面的分割符
        String boundary="----------"+System.currentTimeMillis();
        httpURLConnection.setRequestProperty("Content-Type","multipart/form-data; boundary="+boundary);
        StringBuilder sb = new StringBuilder();
        //这块是post提交type的值也就是文件对应的mime类型值
        sb.append("--"); // 必须多两道线 这里说明下，这两个横杠是http协议要求的，用来分隔提交的参数用的，不懂的可以看看http 协议头
        sb.append(boundary);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data;name=\"file\";filename=\""+file.getName()+"\"\r\n"); //这里是参数名，参数名和值之间要用两次
        sb.append("Content-Type:application/octet-stream\r\n\r\n");
        byte[] head=sb.toString().getBytes("utf-8");
        //获得输出流
        OutputStream outputStream=new DataOutputStream(httpURLConnection.getOutputStream());
        // 输出表头
        outputStream.write(head);
        //文件正文部分
        // 把文件已流文件的方式 推入到url中
        DataInputStream dataInputStream=new DataInputStream(new FileInputStream(file));
        int bytes=0;
        byte[] bufferOut=new byte[1024];
        while((bytes=dataInputStream.read(bufferOut))!=-1)
        {
            outputStream.write(bufferOut,0,bytes);
        }
        dataInputStream.close();
        // 结尾部分，这里结尾表示整体的参数的结尾，结尾要用"--"作为结束，这些都是http协议的规定
        byte[] foot = ("\r\n--" + boundary + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
        outputStream.write(foot);
        outputStream.flush();
        outputStream.close();
        StringBuffer stringBuffer=new StringBuffer();
        BufferedReader bufferedReader=null;
        String result=null;
        try {
            bufferedReader=new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line=null;
            while((line=bufferedReader.readLine())!=null)
            {
                stringBuffer.append(line);
            }
            if(result==null)
            {
                result=stringBuffer.toString();
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }finally {
            if(bufferedReader!=null)
            {
                bufferedReader.close();
            }
        }
        JSONObject jsonObject=JSONObject.fromString(result);
        System.out.println(result);
        String mediaId=jsonObject.getString("media_id");
        return mediaId;
    }

    /**
     * 从结果提取出年龄
     * @param outputs
     * @return
     */
    public static String GetAgeFromResult(String outputs)
    {
        String age = null;
        try {
            JSONObject jsonObject = JSONObject.fromObject(outputs);
            outputs=jsonObject.getString("outputs");
            outputs=outputs.substring(1,outputs.length()-1);
            jsonObject=JSONObject.fromObject(outputs);
            String outputValue=jsonObject.getString("outputValue");
            jsonObject=JSONObject.fromString(outputValue);
            String dataValue =jsonObject.getString("dataValue");
            jsonObject=JSONObject.fromString(dataValue);
            age = jsonObject.getString("age");
            age = age.substring(1,age.length()-1);
        }catch (Exception e)
        {
            age=null;
            //e.printStackTrace();
        }
        return age;
    }

    /**
     * 从结果中提取出性别
     * @param outputs
     * @return
     */
    public static String GetSexFromResult(String outputs)
    {
        String sex = null;
        try {
            JSONObject jsonObject = JSONObject.fromObject(outputs);
            outputs=jsonObject.getString("outputs");
            outputs=outputs.substring(1,outputs.length()-1);
            jsonObject=JSONObject.fromObject(outputs);
            String outputValue=jsonObject.getString("outputValue");
            jsonObject=JSONObject.fromString(outputValue);
            String dataValue =jsonObject.getString("dataValue");
            jsonObject=JSONObject.fromString(dataValue);
            sex = jsonObject.getString("gender");
            sex = sex.substring(1,sex.length()-1);
        }catch (Exception e)
        {
            sex=null;
            //e.printStackTrace();
        }
        return sex;
    }

    /**
     * 获得图片的年龄和性别
     * @param imgFile
     * @return
     */
    public static String getAgeAndSex(String imgFile)
    {
        String strImg = Base64Util.GetImageStr(imgFile);
        String ageHost = "https://dm-23.data.aliyun.com";
        String sexHost = "https://dm-22.data.aliyun.com";
        String agePath = "/rest/160601/face/age_detection.json";
        String sexPath = "/rest/160601/face/gender_detection.json";
        String method = "POST";
        String APPCODE = "fa25e13bc0624871b27a19628d77147e";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "APPCODE "+APPCODE);
        Map<String, String> querys = new HashMap<String, String>();
        String ageBodys="{\"inputs\":[" +
                "          {\"image\":{\"dataType\":50,\"dataValue\":\""+strImg+"\"}," +
                "           \"type\":{\"dataType\":10,\"dataValue\":3}}]" +
                "}";
        String sexBodys="{\"inputs\":[" +
                "          {\"image\":{\"dataType\":50,\"dataValue\":\""+strImg+"\"}," +
                "           \"type\":{\"dataType\":10,\"dataValue\":2}}]" +
                "}";
        String text;
        try {
            HttpResponse response = HttpUtils.doPost(ageHost, agePath, method, headers, querys, ageBodys);
            String outputs=EntityUtils.toString(response.getEntity());
            String age= GetAgeFromResult(outputs);
            response = HttpUtils.doPost(sexHost, sexPath, method, headers, querys, sexBodys);
            outputs=EntityUtils.toString(response.getEntity());
            String sex= GetSexFromResult(outputs);
            if(age.equals(""))
            {
                age="无法识别年龄";
            }
            else
            {
                age=age+" 岁";
            }
            if(sex.equals(""))
            {
                sex="无法识别性别";
            }
            else
            {
                if(sex.equals("1"))
                {
                    sex="男性";
                }
                else if(sex.equals("0"))
                {
                    sex="女性";
                }
            }
            text="年龄: "+age+"\n性别: "+sex;
        } catch (Exception e) {
            text="此图片识别出错，这是一张假头像";
            e.printStackTrace();
        }
        return text;
    }
}
