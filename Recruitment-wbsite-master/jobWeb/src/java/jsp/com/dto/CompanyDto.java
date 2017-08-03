package jsp.com.dto;

/**
 * Created by lenovo on 2016/11/6.
 */
public class CompanyDto {
    //企业id
    private Integer id;
    //用户id
    private Integer userid;
    //企业编号
    private String number;
    //企业名称
    private String name;
    //企业邮件
    private String email;
    //法人姓名
    private String legalname;
    //公司性质
    private String type;
    //法人身份证号
    private String legalnumber;
    //是否通过审核
    private String status;
    private Long jobNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLegalname() {
        return legalname;
    }

    public void setLegalname(String legalname) {
        this.legalname = legalname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLegalnumber() {
        return legalnumber;
    }

    public void setLegalnumber(String legalnumber) {
        this.legalnumber = legalnumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getJobNum() {
        return jobNum;
    }

    public void setJobNum(Long jobNum) {
        this.jobNum = jobNum;
    }
}
