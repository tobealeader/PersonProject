package jsp.com.dto;

/**
 * Created by lenovo on 2016/11/4.
 */
public class DataTables{
    private Integer draw;//需要回写的参数
    private Integer start;
    private Integer length;
    private String search;//搜索框参数

    public DataTables() {
        super();
    }
    public DataTables(Integer draw, Integer start, Integer length, String search) {
        super();
        this.draw = draw;
        this.start = start;
        this.length = length;
        this.search = search;
    }
    public Integer getDraw() {
        return draw;
    }
    public void setDraw(Integer draw) {
        this.draw = draw;
    }
    public Integer getStart() {
        return start;
    }
    public void setStart(Integer start) {
        this.start = start;
    }
    public Integer getLength() {
        return length;
    }
    public void setLength(Integer length) {
        this.length = length;
    }
    public String getSearch() {
        return search;
    }
    public void setSearch(String search) {
        this.search = search;
    }
}