package cn.com.weixin.sto;

/**
 * 图片消息体
 * Created by lenovo on 2017/3/11.
 */
public class ImageMessageSto extends MainMessageSto{
    Image Image;

    public Image getImage() {
        return Image;
    }

    public void setImage(Image image) {
        this.Image = image;
    }
}
