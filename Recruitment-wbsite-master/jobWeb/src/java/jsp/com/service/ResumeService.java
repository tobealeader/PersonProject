package jsp.com.service;

import jsp.com.dao.ResumeDao;
import jsp.com.dao.UserDao;
import jsp.com.entry.Resume;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by lenovo on 2016/10/29.
 */
@Service
public class ResumeService extends BaseService<Resume,Integer> {
    @Resource
    private ResumeDao resumeDao;
    @Resource
    public void setResumeDao(ResumeDao resumeDao) {
        super.setBaseDao(resumeDao);
    }
}
