package jsp.com.dto;

/**
 * Created by lenovo on 2016/11/3.
 */
public class UserDto {
    private Integer userid;
    private String username;
    private String userpwd;
    private String useremail;
    private String usertype;
    private String userdate;
    private String photo;
    private Long resumeNum;

    public UserDto(Integer userid, String username, String userpwd, String useremail, String usertype, String userdate, String photo) {
        this.userid = userid;
        this.username = username;
        this.userpwd = userpwd;
        this.useremail = useremail;
        this.usertype = usertype;
        this.userdate = userdate;
        this.photo = photo;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getUserdate() {
        return userdate;
    }

    public void setUserdate(String userdate) {
        this.userdate = userdate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Long getResumeNum() {
        return resumeNum;
    }

    public void setResumeNum(Long resumeNum) {
        this.resumeNum = resumeNum;
    }
}
