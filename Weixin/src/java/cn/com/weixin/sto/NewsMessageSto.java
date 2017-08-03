package cn.com.weixin.sto;

import java.util.List;

/**
 * 图文消息
 * Created by lenovo on 2017/3/10.
 */
public class NewsMessageSto extends MainMessageSto{
    private int ArticleCount;
    private List<News> Articles;

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public List<News> getArticles() {
        return Articles;
    }

    public void setArticles(List<News> articles) {
        Articles = articles;
    }
}
