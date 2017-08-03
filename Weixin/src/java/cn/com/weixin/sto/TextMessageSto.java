package cn.com.weixin.sto;

/**
 * Created by lenovo on 2017/3/4.
 */

/**
 * 文本消息
 * Content 文本消息内容
 * MsgId 消息id，64位整型
 */
public class TextMessageSto  extends MainMessageSto{

    private String Content;
    private String MsgId;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }
}
