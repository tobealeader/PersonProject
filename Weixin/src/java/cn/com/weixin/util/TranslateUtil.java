package cn.com.weixin.util;

import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 翻译请求类
 * Created by lenovo on 2017/3/11.
 */
public class TranslateUtil {
    private static final String APP_ID = "20170311000042051";
    private static final String SECURITY_KEY = "YMD6zy2R1vXE4hOuNEAR";
    public static String getTransResult(String query,String from,String to) throws IOException {
        StringBuffer stringBuffer=new StringBuffer("http://api.fanyi.baidu.com/api/trans/vip/translate?");
        stringBuffer.append("q="+encode(query));
        stringBuffer.append("&from="+encode(from));
        stringBuffer.append("&to="+encode(to));
        stringBuffer.append("&appid="+APP_ID);
        String salt=String.valueOf(System.currentTimeMillis());
        stringBuffer.append("&salt="+encode(salt));
        String sign = APP_ID + query + salt + SECURITY_KEY;
        stringBuffer.append("&sign="+encode(Md5Util.md5(sign)));
        JSONObject jsonObject=WeixinUtil.doGetStr(stringBuffer.toString());
        String trans_result=jsonObject.getString("trans_result");
        trans_result=trans_result.substring(1,trans_result.length()-1);
        jsonObject=JSONObject.fromObject(trans_result);
        return jsonObject.getString("dst");
    }
    public static String encode(String input) {
        if (input == null) {
            return "";
        }
        try {
            return URLEncoder.encode(input, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return input;
    }
}
