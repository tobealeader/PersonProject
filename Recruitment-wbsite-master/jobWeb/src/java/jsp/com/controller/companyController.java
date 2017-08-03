package jsp.com.controller;

import jsp.com.dto.*;
import jsp.com.entry.*;
import jsp.com.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/11/5.
 */
@Controller
@RequestMapping(value = "/company")
public class companyController {
    @Resource
    private JoblinkService joblinkService;
    @Resource
    private JobmessageService jobmessageService;
    @Resource
    private CompanyService companyService;
    @Resource
    private UserService userService;
    @Resource
    private ResumeService resumeService;
    @RequestMapping(value = "/message")
    @ResponseBody
    public List<UsermessageDto> message(HttpServletRequest request){
        HttpSession session=request.getSession();
        Integer id=(Integer)session.getAttribute("userid");
        List<Jobmessage> jobmessages=jobmessageService.getList("user.userid",id);
        int i;
        int j;
        int k=0;
        List<UsermessageDto> usermessages=new ArrayList<UsermessageDto>();
        for(j=0;j<jobmessages.size();j++){
            List<Joblink> joblinks=joblinkService.getList("jobmessage.id",jobmessages.get(j).getId());
            for(i=0;i<joblinks.size();i++){
                Integer cid = joblinks.get(i).getResume().getUser().getUserid();
                User user= userService.get(cid);
                UsermessageDto usermessage=new UsermessageDto(joblinks.get(i).getSatues(),joblinks.get(i).getTime(),user.getUsername());
                usermessages.add(usermessage);
                k++;
            }
        }
        return usermessages;
    }
    @RequestMapping(value = "/showJobMessage")
    @ResponseBody
    public jsonDto showJobMessage(HttpServletRequest request, HttpServletResponse response, DataTables dataTables) throws IOException {

        List<JobMessageDto> jobMessageDtos=new ArrayList<JobMessageDto>();
        HttpSession session=request.getSession();
        Integer id=(Integer)session.getAttribute("userid");
        Company company=companyService.get("user.userid",id);
        List<Jobmessage> jobmessages=jobmessageService.getList("from Jobmessage where user.userid="+id,dataTables.getStart(),dataTables.getLength());
        int i;
        for(i=0;i<jobmessages.size();i++){
            JobMessageDto jobMessageDto=new JobMessageDto();
            jobMessageDto.setId(jobmessages.get(i).getId());
            jobMessageDto.setDate(jobmessages.get(i).getDate());
            jobMessageDto.setAddress(jobmessages.get(i).getAddress());
            jobMessageDto.setDuty(jobmessages.get(i).getDuty());
            jobMessageDto.setPosition(jobmessages.get(i).getPosition());
            jobMessageDto.setTell(jobmessages.get(i).getTell());
            jobMessageDto.setUserid(id);
            jobMessageDto.setName(company.getName());
            jobMessageDto.setCompanytype(company.getType());
            jobMessageDtos.add(jobMessageDto);
        }
        jsonDto<JobMessageDto>  jsonObject= new jsonDto<JobMessageDto>();
        jsonObject.setDraw(dataTables.getDraw());
        Long count = jobmessageService.getCount("select count(*) from Jobmessage where user.userid="+id);
        jsonObject.setRecordsFiltered(count);
        jsonObject.setRecordsTotal(count);
        jsonObject.setData(jobMessageDtos);
        return jsonObject;
    }
    @RequestMapping(value = "/isJobMessage")
    @ResponseBody
    public boolean isJobMessage(HttpServletRequest request){
        HttpSession session=request.getSession();
        Integer id=(Integer)session.getAttribute("userid");
        Long count = jobmessageService.getCount("select count(*) from Jobmessage where user.userid="+id);
        if(count>0){
            return true;
        }
        return false;
    }
    @RequestMapping(value = "/JobMessageDelete")
    @ResponseBody
    public boolean JobMessageDelete(Integer id,HttpServletRequest request){
        try {
            jobmessageService.delete(id);

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    @RequestMapping(value = "/jobMessageGet")
    @ResponseBody
    public JobMessageDto jobMessageGet(Integer id){
        Jobmessage jobmessage=jobmessageService.get(id);
        JobMessageDto jobMessageDto=new JobMessageDto();
        jobMessageDto.setId(jobmessage.getId());
        jobMessageDto.setDate(jobmessage.getDate());
        jobMessageDto.setAddress(jobmessage.getAddress());
        jobMessageDto.setDuty(jobmessage.getDuty());
        jobMessageDto.setPosition(jobmessage.getPosition());
        jobMessageDto.setTell(jobmessage.getTell());
        Integer userid=jobmessage.getUser().getUserid();
        Company company=companyService.get("user.userid",userid);
        jobMessageDto.setUserid(userid);
        jobMessageDto.setName(company.getName());
        jobMessageDto.setCompanytype(company.getType());
        Long count=companyService.getCount("select count(*) from Joblink where jobmessage.id="+id);
        jobMessageDto.setCount(count);
        return jobMessageDto;
    }
    @RequestMapping(value = "/jobMessagechange")
    @ResponseBody
    public boolean jobMessagechange(Integer id,String tell,String address,String duty,String position,String date,Integer userid){
        try {
            Jobmessage jobmessage=jobmessageService.get(id);
            jobmessage.setTell(tell);
            jobmessage.setAddress(address);
            jobmessage.setDuty(duty);
            jobmessage.setPosition(position);
            jobmessage.setDate(date);
            jobmessage.setId(id);
            jobmessage.setUser(userService.get(userid));
            jobmessageService.update(jobmessage);
        }catch (Exception e){
            return false;
        }
        return true;
    }
    @RequestMapping(value = "/jobMessageSave")
    @ResponseBody
    public boolean jobMessageSave(String tell,String address,String duty,String position,String date,String type,HttpServletRequest request){
        try {
            Jobmessage jobmessage=new Jobmessage();
            jobmessage.setTell(tell);
            jobmessage.setAddress(address);
            jobmessage.setDuty(duty);
            jobmessage.setPosition(position);
            jobmessage.setDate(date);
            jobmessage.setType(type);
            HttpSession session=request.getSession();
            Integer userid=(Integer)session.getAttribute("userid");
            System.out.println(userid);
            jobmessage.setUser(userService.get(userid));
            jobmessageService.save(jobmessage);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }
    @RequestMapping(value = "/iscompanyMessageGet")
    @ResponseBody
    public boolean iscompanyMessageGet(HttpServletRequest request){
        HttpSession session=request.getSession();
        Integer id=(Integer)session.getAttribute("userid");
        if(companyService.getCount("select count(*) from Company where user.userid="+id)==0){
            return false;
        }
        else{
            Company company=companyService.get("user.userid",id);
            if(company.getStatus().equals("true")){
                return true;
            }
            else{
                return false;
            }
        }
    }
    @RequestMapping(value = "/messageCheckSave")
    @ResponseBody
    public boolean messageCheckSave(String name,String number,String email,String legalname,String legalnumber,String type,HttpServletRequest request){
        try {
            Company company=new Company();
            company.setName(name);
            company.setNumber(number);
            company.setEmail(email);
            company.setLegalname(legalname);
            company.setLegalnumber(legalnumber);
            company.setType(type);
            company.setStatus("on");
            HttpSession session = request.getSession();
            company.setUser(userService.get((Integer)session.getAttribute("userid")));
            companyService.save(company);
        }catch (Exception e){
            return false;
        }
        return true;
    }
    @RequestMapping(value = "/messageCheckDelete")
    @ResponseBody
    public boolean messageCheckDelete(HttpServletRequest request){
        try{
            HttpSession session=request.getSession();
            Company company=companyService.get("user.userid",(Integer)session.getAttribute("userid"));
            companyService.delete(company.getId());
        }catch (Exception e){
            return false;
        }
        return true;
    }
    @RequestMapping(value = "/companyMessageGet")
    @ResponseBody
    public CompanyDto companyMessageGet(HttpServletRequest request){
        HttpSession session=request.getSession();
        Integer id=(Integer)session.getAttribute("userid");
        CompanyDto companyDto=new CompanyDto();
        Company company=companyService.get("user.userid",id);
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
    @RequestMapping(value = "resumeMessageGet")
    @ResponseBody
    public jsonDto<ResumeAndJobMessageDto> jobMessageGet(HttpServletRequest request, HttpServletResponse response, DataTables dataTables) throws IOException{
        HttpSession session=request.getSession();
        Integer id=(Integer)session.getAttribute("userid");
        List<Jobmessage> jobmessages=jobmessageService.getList("user.userid",id);
        List<ResumeAndJobMessageDto> resumeAndJobMessageDtos=new ArrayList<ResumeAndJobMessageDto>();
        int a=0;
        int l=0;
        int flag=1;
        Long count=0L;
        for(int i=0;i<jobmessages.size();i++){
            Jobmessage jobmessage=jobmessages.get(i);
            List<Joblink> joblinks=joblinkService.getList("jobmessage.id",jobmessage.getId());
            String position=jobmessage.getPosition();
            count+=joblinks.size();
            for(int j=0;j<joblinks.size()&&flag==1;j++){
                a++;
                if(a<dataTables.getStart()){
                    continue;
                }
                Joblink joblink=joblinks.get(j);
                ResumeAndJobMessageDto resumeAndJobMessageDto=new ResumeAndJobMessageDto();
                Resume resume=resumeService.get(joblink.getResume().getId());
                resumeAndJobMessageDto.setId(joblink.getLinkid());
                resumeAndJobMessageDto.setPosition(position);
                resumeAndJobMessageDto.setName(resume.getName());
                resumeAndJobMessageDto.setSex(resume.getSex());
                resumeAndJobMessageDto.setBirth(resume.getBirth());
                resumeAndJobMessageDto.setUniversity(resume.getUniversity());
                resumeAndJobMessageDto.setEducation(resume.getEducation());
                resumeAndJobMessageDto.setMajor(resume.getRemajor());
                resumeAndJobMessageDto.setEnglish(resume.getEnglish());
                resumeAndJobMessageDto.setTell(resume.getTell());
                resumeAndJobMessageDto.setEmail(resume.getRemail());
                resumeAndJobMessageDto.setAddress(resume.getAddress());
                resumeAndJobMessageDto.setProject(resume.getProject());
                resumeAndJobMessageDto.setDate(joblink.getTime());
                String type="";
                if(joblink.getSatues().equals("on")){
                    type="未审核";
                }
                else if(joblink.getSatues().equals("true")){
                    type="同意";
                }
                else if(joblink.getSatues().equals("false")){
                    type="不同意";
                }
                resumeAndJobMessageDto.setSatues(type);
                resumeAndJobMessageDtos.add(resumeAndJobMessageDto);
                l++;
                if(l==dataTables.getLength()){
                    flag=0;
                    break;
                }
            }
        }
        jsonDto<ResumeAndJobMessageDto>  jsonObject= new jsonDto<ResumeAndJobMessageDto>();
        jsonObject.setDraw(dataTables.getDraw());
        jsonObject.setRecordsFiltered(count);
        jsonObject.setRecordsTotal(count);
        jsonObject.setData(resumeAndJobMessageDtos);
        return jsonObject;
    }
    @RequestMapping(value = "getresumeid")
    @ResponseBody
    public Integer getresumeid(Integer id){
        return joblinkService.get(id).getResume().getId();
    }
    @RequestMapping(value = "resumeMessageAcceptOrRefuse")
    @ResponseBody
    public boolean resumeMessageAcceptOrRefuse(Integer id,String status){
        try{
            Joblink joblink=joblinkService.get(id);
            joblink.setSatues(status);
            joblinkService.update(joblink);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
