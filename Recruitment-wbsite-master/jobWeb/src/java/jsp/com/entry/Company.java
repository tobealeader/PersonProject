package jsp.com.entry;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by lenovo on 2016/10/20.
 * table:企业基本信息表
 */
@Entity
public class Company {
    //企业id
    private Integer id;
    //用户id
    private User user;
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


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(length = 50)
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Column(length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(length = 50)
    public String getLegalname() {
        return legalname;
    }

    public void setLegalname(String legalname) {
        this.legalname = legalname;
    }

    @Column(length = 50)
    public String getLegalnumber() {
        return legalnumber;
    }

    public void setLegalnumber(String legalnumber) {
        this.legalnumber = legalnumber;
    }

    @Column(length = 250)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(length = 250)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
