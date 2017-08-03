package jsp.com.entry;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

/**
 * Created by lenovo on 2016/10/20.
 * table:用户信息表
 * userid:用户Id
 * username:登陆名称
 * userpwd:登陆密码
 * useremail：用户邮箱
 * usertype:用户类型
 * userdate:注册时间
 */
@Entity
public class User {
    private Integer userid;
    private String username;
    private String userpwd;
    private String useremail;
    private String usertype;
    private String userdate;
    private String photo;
    private Set<Company> company;
    private Set<Resume> resume;
    private Set<Jobmessage> jobmessages;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
    @Column(length = 50)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Column(length = 50)
    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }
    @Column(length = 50)
    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    @Column(length = 50)
    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    @Column(length = 250)
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Column(length = 30)
    public String getUserdate() {
        return userdate;
    }

    public void setUserdate(String userdate) {
        this.userdate = userdate;
    }

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    public Set<Company> getCompany() {
        return company;
    }

    public void setCompany(Set<Company> company) {
        this.company = company;
    }

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    public Set<Resume> getResume() {
        return resume;
    }

    public void setResume(Set<Resume> resume) {
        this.resume = resume;
    }

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    public Set<Jobmessage> getJobmessages() {

        return jobmessages;
    }

    public void setJobmessages(Set<Jobmessage> jobmessages) {
        this.jobmessages = jobmessages;
    }
}
