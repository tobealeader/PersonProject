package jsp.com.service;

import jsp.com.dao.CompanyDao;
import jsp.com.entry.Company;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by lenovo on 2016/11/3.
 */
@Service
public class CompanyService extends BaseService<Company,Integer> {
    @Resource
    CompanyDao companyDao;
    @Resource
    public void setCompanyDao(CompanyDao companyDao){
        super.setBaseDao(companyDao);
    }
}
