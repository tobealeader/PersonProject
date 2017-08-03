package jsp.com.entry;

import javax.persistence.*;

/**
 * Created by lenovo on 2016/10/20.
 */
@Entity
public class Joblink {
    private Integer linkid;
    private Jobmessage jobmessage;
    private Resume resume;
    private String time;
    private String satues;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getLinkid() {
        return linkid;
    }

    public void setLinkid(Integer linkid) {
        this.linkid = linkid;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public Jobmessage getJobmessage() {
        return jobmessage;
    }

    public void setJobmessage(Jobmessage jobmessage) {
        this.jobmessage = jobmessage;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    @Column(length = 50)
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Column(length = 50)
    public String getSatues() {
        return satues;
    }

    public void setSatues(String satues) {
        this.satues = satues;
    }
}
