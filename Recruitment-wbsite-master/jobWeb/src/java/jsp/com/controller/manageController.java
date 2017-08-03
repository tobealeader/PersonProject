package jsp.com.controller;

import jsp.com.dao.CompanyDao;
import jsp.com.dto.CompanyDto;
import jsp.com.dto.DataTables;
import jsp.com.dto.UserDto;
import jsp.com.dto.jsonDto;
import jsp.com.entry.Company;
import jsp.com.entry.User;
import jsp.com.service.CompanyService;
import jsp.com.service.UserService;
import org.aspectj.apache.bcel.generic.Select;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/11/6.
 */
@Controller
@RequestMapping(value = "/manage")
public class manageController {
    @Resource
    private UserService userService;
    @Resource
    private CompanyService companyService;
    @RequestMapping(value = "allperson")
    @ResponseBody
    public jsonDto allperson(HttpServletRequest request, HttpServletResponse response, DataTables dataTables) throws IOException {

        List<UserDto> userDtos=new ArrayList<UserDto>();
        List<User> users=new ArrayList<User>();
        users=userService.getList("from User where usertype='person'",dataTables.getStart(),dataTables.getLength());
        int i;
        for(i=0;i<users.size();i++){
            UserDto userDto=new UserDto(users.get(i).getUserid(),users.get(i).getUsername(),users.get(i).getUserpwd(),users.get(i).getUseremail(),users.get(i).getUsertype(),users.get(i).getUserdate(),users.get(i).getPhoto());
            userDtos.add(userDto);
        }
        jsonDto<UserDto>  jsonObject= new jsonDto<UserDto>();
        jsonObject.setDraw(dataTables.getDraw());
        Long count = userService.getCount("select count(*) from User where usertype='person'");
        jsonObject.setRecordsFiltered(count);
        jsonObject.setRecordsTotal(count);
        jsonObject.setData(userDtos);
        return jsonObject;
    }
    @RequestMapping(value = "allcompany")
    @ResponseBody
    public jsonDto allcompany(HttpServletRequest request, HttpServletResponse response, DataTables dataTables) throws IOException {

        List<UserDto> userDtos=new ArrayList<UserDto>();
        List<User> users=new ArrayList<User>();
        users=userService.getList("from User where usertype='company'",dataTables.getStart(),dataTables.getLength());
        int i;
        for(i=0;i<users.size();i++){
            UserDto userDto=new UserDto(users.get(i).getUserid(),users.get(i).getUsername(),users.get(i).getUserpwd(),users.get(i).getUseremail(),users.get(i).getUsertype(),users.get(i).getUserdate(),users.get(i).getPhoto());
            userDtos.add(userDto);
        }
        jsonDto<UserDto>  jsonObject= new jsonDto<UserDto>();
        jsonObject.setDraw(dataTables.getDraw());
        Long count = userService.getCount("select count(*) from User where usertype='company'");
        jsonObject.setRecordsFiltered(count);
        jsonObject.setRecordsTotal(count);
        jsonObject.setData(userDtos);
        return jsonObject;
    }
    @RequestMapping(value = "companyCheck")
    @ResponseBody
    public jsonDto<CompanyDto> companyCheck(HttpServletRequest request, HttpServletResponse response, DataTables dataTables) throws IOException{
        List<CompanyDto> companyDtos=new ArrayList<CompanyDto>();
        List<Company> companies=new ArrayList<Company>();
        companies=companyService.getList("from Company where status='on'",dataTables.getStart(),dataTables.getLength());
        int i;
        for(i=0;i<companies.size();i++){
            if(companies.get(0).getStatus().equals("on")){
                CompanyDto companyDto=new CompanyDto();
                companyDto.setId(companies.get(i).getId());
                companyDto.setEmail(companies.get(i).getEmail());
                companyDto.setLegalname(companies.get(i).getLegalname());
                companyDto.setLegalnumber(companies.get(i).getLegalnumber());
                companyDto.setName(companies.get(i).getName());
                companyDto.setNumber(companies.get(i).getNumber());
                companyDto.setStatus("未审核");
                companyDto.setType(companies.get(i).getType());
                companyDto.setUserid(companies.get(i).getUser().getUserid());
                companyDtos.add(companyDto);
            }
        }
        jsonDto<CompanyDto>  jsonObject= new jsonDto<CompanyDto>();
        jsonObject.setDraw(dataTables.getDraw());
        Long count = userService.getCount("select count(*) from Company where status='on'");
        jsonObject.setRecordsFiltered(count);
        jsonObject.setRecordsTotal(count);
        jsonObject.setData(companyDtos);
        return jsonObject;
    }
    @RequestMapping(value = "userGet")
    @ResponseBody
    public UserDto userGet(Integer id){
        User user=userService.get(id);
        UserDto userDto=new UserDto(user.getUserid(),user.getUsername(),user.getUserpwd(),user.getUseremail(),user.getUsertype(),user.getUserdate(),user.getPhoto());
        userDto.setResumeNum(userService.getCount("select count(*) from Joblink where resume.id in (select id from Resume where user.userid="+user.getUserid()+")"));
        return userDto;
    }
    @RequestMapping(value = "userDelete")
    @ResponseBody
    public boolean userDelete(Integer id){
        try {
            userService.delete(id);
        }catch (Exception e){
            return  false;
        }
        return true;
    }
    @RequestMapping(value = "userChange")
    @ResponseBody
    public boolean userChange(Integer id,String username,String password,String useremail){
        System.out.println("id");
        try{
            User user=userService.get(id);
            user.setUseremail(useremail);
            user.setUserpwd(password);
            user.setUsername(username);
            userService.update(user);
        }catch (Exception e){
            return false;
        }
        return true;
    }
    @RequestMapping(value = "companyCheckGet")
    @ResponseBody
    public CompanyDto companyCheckGet(Integer id){
        CompanyDto companyDto=new CompanyDto();
        Company company=companyService.get(id);
        companyDto.setId(company.getId());
        companyDto.setEmail(company.getEmail());
        companyDto.setLegalname(company.getLegalname());
        companyDto.setLegalnumber(company.getLegalnumber());
        companyDto.setName(company.getName());
        companyDto.setNumber(company.getNumber());
        companyDto.setStatus(company.getStatus());
        companyDto.setType(company.getType());
        companyDto.setUserid(company.getUser().getUserid());
        return companyDto;
    }
    @RequestMapping(value = "companyMessageGet")
    @ResponseBody
    public CompanyDto companyMessageGet(Integer id){
        Integer cid=userService.get(id).getUserid();
        CompanyDto companyDto=new CompanyDto();
        Company company=companyService.get("user.userid",cid);
        companyDto.setId(company.getId());
        companyDto.setEmail(company.getEmail());
        companyDto.setLegalname(company.getLegalname());
        companyDto.setLegalnumber(company.getLegalnumber());
        companyDto.setName(company.getName());
        companyDto.setNumber(company.getNumber());
        if(company.getStatus().equals("true")){
            companyDto.setStatus("审核通过");
        }
        else if(company.getStatus().equals("false")){
            companyDto.setStatus("审核未通过");
        }
        else if(company.getStatus().equals("on")){
            companyDto.setStatus("未审核");
        }
        companyDto.setType(company.getType());
        companyDto.setUserid(company.getUser().getUserid());
        companyDto.setJobNum(companyService.getCount("select count(*) from Jobmessage where user.userid="+cid));
        System.out.println(companyDto.getJobNum());
        return companyDto;
    }
    @RequestMapping(value = "companyCheckWrite")
    @ResponseBody
    public boolean companyCheckWrite(Integer id,String status){
        try{
            Company company=companyService.get(id);
            company.setStatus(status);
            companyService.update(company);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
