package jsp.com.service;

import jsp.com.dao.JobmessageDao;
import jsp.com.entry.Jobmessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by lenovo on 2016/11/3.
 */
@Service
public class JobmessageService extends BaseService<Jobmessage,Integer>{
    @Resource
    private JobmessageDao jobmessageDao;
    @Resource
    public void setJobmessageDao(JobmessageDao jobmessageDao){
        super.setBaseDao(jobmessageDao);
    }
}
