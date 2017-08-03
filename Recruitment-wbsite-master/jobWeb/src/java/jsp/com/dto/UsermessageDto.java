package jsp.com.dto;

import java.util.Set;

/**
 * Created by lenovo on 2016/11/3.
 */
public class UsermessageDto {
    private String status;
    private String time;
    private String company;

    public UsermessageDto(String status, String time, String company) {
        this.status = status;
        this.time = time;
        this.company = company;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
