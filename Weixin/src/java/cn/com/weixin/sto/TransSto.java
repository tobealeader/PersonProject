package cn.com.weixin.sto;

/**
 * 中英翻译Sto
 * Created by lenovo on 2017/3/11.
 */
public class TransSto {
    private String en;
    private String zh;

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getZh() {
        return zh;
    }

    public void setZh(String zh) {
        this.zh = zh;
    }

    @Override
    public String toString() {
        return "中文："+zh+"\n"+
                "英文："+en;
    }
}
