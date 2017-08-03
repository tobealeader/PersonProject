package jsp.com.service;

import jsp.com.dao.JoblinkDao;
import jsp.com.entry.Joblink;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by lenovo on 2016/11/3.
 */
@Service
public class JoblinkService extends BaseService<Joblink,Integer> {
     @Resource
    private JoblinkDao joblinkDao;
    @Resource
    public void setJoblinkDao(JoblinkDao joblinkDao){
        super.setBaseDao(joblinkDao);
    }
}
