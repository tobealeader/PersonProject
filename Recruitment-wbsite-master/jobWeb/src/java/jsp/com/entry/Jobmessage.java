package jsp.com.entry;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by lenovo on 2016/10/20.
 * table:招聘信息表
 */
@Entity
public class Jobmessage {
    //招聘ID
    private Integer id;
    //企业id
    private User user;
    //招聘职位
    private String position;
    //招聘信息
    private String duty;
    //联系电话
    private String tell;
    //地址
    private String address;
    //时间
    private String date;

    private String type;

    private Set<Joblink> joblinks;

    @OneToMany(mappedBy = "jobmessage",fetch = FetchType.EAGER)
    public Set<Joblink> getJoblinks() {
        return joblinks;
    }

    public void setJoblinks(Set<Joblink> joblinks) {
        this.joblinks = joblinks;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(length = 250)
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Column(length = 250)
    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }

    @Column(length = 250)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(length = 250)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Column(length = 1024)
    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    @Column(length = 250)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
