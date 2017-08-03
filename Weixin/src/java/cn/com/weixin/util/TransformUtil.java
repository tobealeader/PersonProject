package cn.com.weixin.util;

import cn.com.weixin.sto.ImageMessageSto;
import cn.com.weixin.sto.News;
import cn.com.weixin.sto.NewsMessageSto;
import cn.com.weixin.sto.TextMessageSto;
import com.thoughtworks.xstream.XStream;
import net.sf.json.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * xml与其它格式的相互转换
 * Created by lenovo on 2017/3/10.
 */
public class TransformUtil {
    /**
     * xml转为map
     * @param request
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public static Map<String,String> xmlToMap(HttpServletRequest request)throws IOException, DocumentException{
        Map<String,String> map=new HashMap<String, String>();
        SAXReader saxReader=new SAXReader();
        InputStream inputStream=request.getInputStream();
        Document document=saxReader.read(inputStream);
        Element element=document.getRootElement();
        List<Element> list=element.elements();
        for (Element item : list)
        {
            map.put(item.getName(),item.getText());
        }
        inputStream.close();
        return map;
    }
    /**
     * 将文本消息转为xml
     * @param textMessageSto
     * @return
     */
    public static String textMessageToXml(TextMessageSto textMessageSto)
    {
        XStream xStream = new XStream();
        xStream.alias("xml",textMessageSto.getClass());
        return xStream.toXML(textMessageSto);
    }

    /**
     * 将图文消息转为xml
     * @param newsMessageSto
     * @return
     */
    public static String NewsMessageToXml(NewsMessageSto newsMessageSto)
    {
        XStream xStream = new XStream();
        xStream.alias("xml",newsMessageSto.getClass());
        xStream.alias("item",new News().getClass());
        return xStream.toXML(newsMessageSto);
    }

    /**
     * 图片消息转为xml
     * @param imageMessageSto
     * @return
     */
    public static String ImageMessageToXml(ImageMessageSto imageMessageSto)
    {
        XStream xStream = new XStream();
        xStream.alias("xml",imageMessageSto.getClass());
        return xStream.toXML(imageMessageSto);
    }




}
