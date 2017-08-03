package jsp.com.service;

import jsp.com.dao.UserDao;
import jsp.com.entry.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by lenovo on 2016/10/27.
 */
@Service
public class UserService extends BaseService<User,Integer>{
    @Resource
    private UserDao userdao;
    @Resource
    public void setUserDao(UserDao userdao) {
        super.setBaseDao(userdao);
    }
}
