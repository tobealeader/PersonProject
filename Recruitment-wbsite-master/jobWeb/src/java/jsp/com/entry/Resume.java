package jsp.com.entry;

import javax.persistence.*;
import java.util.Set;

/*
*
 * Created by lenovo on 2016/10/20.
 * table:求职简历表
 * name:姓名
 * sex:性别
 * photo:照片
 * birth:出生年份
 * university:毕业院校
 * education:学历
 * major:专业
 * english:英语水平
 * tell:电话
 * mail:电子邮件
 * address:家庭住址
 * project:项目经验
*/
@Entity
public class Resume {
    private Integer id;
    private User user;
    private String name;
    private String sex;
    private String photo;
    private String birth;
    private String university;
    private String education;
    private String remajor;
    private String english;
    private String tell;
    private String remail;
    private String address;
    private String project;
    private String date;

    private Set<Joblink> joblink;

    @OneToMany(mappedBy = "resume",fetch = FetchType.EAGER)
    public Set<Joblink> getJoblink() {
        return joblink;
    }

    public void setJoblink(Set<Joblink> joblink) {
        this.joblink = joblink;
    }

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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(length = 50)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Column(length = 50)
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Column(length = 50)
    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    @Column(length = 50)
    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    @Column(length = 50)
    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Column(length = 50)
    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    @Column(length = 50)
    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }

    @Column(length = 200)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(length = 1024)
    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    @Column(length = 200)
    public String getRemajor() {
        return remajor;
    }

    public void setRemajor(String remajor) {
        this.remajor = remajor;
    }

    @Column(length = 200)
    public String getRemail() {
        return remail;
    }

    public void setRemail(String remail) {
        this.remail = remail;
    }

    @Column(length = 200)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
