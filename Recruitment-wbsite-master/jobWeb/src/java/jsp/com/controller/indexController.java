package jsp.com.controller;


import jsp.com.dto.DataTables;
import jsp.com.dto.JobMessageDto;
import jsp.com.dto.ResumeDto;
import jsp.com.dto.jsonDto;
import jsp.com.entry.Company;
import jsp.com.entry.Joblink;
import jsp.com.entry.Jobmessage;
import jsp.com.entry.Resume;
import jsp.com.service.CompanyService;
import jsp.com.service.JoblinkService;
import jsp.com.service.JobmessageService;
import jsp.com.service.ResumeService;
import org.hibernate.annotations.Source;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
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
 * Created by lenovo on 2016/11/8.
 */
@Controller
@RequestMapping(value = "/index")
public class indexController {
    @Resource
    private ResumeService resumeService;
    @Resource
    private JoblinkService joblinkService;
    @Resource
    private JobmessageService jobmessageService;
    @Resource
    private CompanyService companyService;
    @RequestMapping(value = "/resumeget")
    @ResponseBody
    public List<ResumeDto> userResumeMessage(HttpServletRequest request) throws IOException {
        HttpSession session=request.getSession();
        Integer id=(Integer)session.getAttribute("userid");
        List<Resume> resumes=resumeService.getList("user.userid",id);
        List<ResumeDto> resumeDtos=new ArrayList<ResumeDto>();
        int i;
        for(i=0;i<resumes.size();i++){
            ResumeDto resumeDto=new ResumeDto();
            resumeDto.setPhoto(resumes.get(i).getPhoto());
            resumeDto.setId(resumes.get(i).getId());
            resumeDtos.add(resumeDto);
        }
        return resumeDtos;
    }
    @RequestMapping(value = "upresume")
    @ResponseBody
    public boolean upresume(Integer id,Integer cid,String date){
        try{
            Joblink joblink=new Joblink();
            joblink.setSatues("on");
            joblink.setJobmessage(jobmessageService.get(id));
            joblink.setResume(resumeService.get(cid));
            joblink.setTime(date);
            joblinkService.save(joblink);
        }catch (Exception e){
            return false;
        }
        return true;
    }
    @RequestMapping(value = "/jobMessageGet")
    @ResponseBody
    public List<JobMessageDto> jobMessageGet(){
        List<Jobmessage> jobmessages=jobmessageService.getList("from Jobmessage");
        List<JobMessageDto> jobMessageDtos=new ArrayList<JobMessageDto>();
        int j=0;
        for(int i=jobmessages.size()-1;i>=0;i--){
            Jobmessage jobmessage=jobmessages.get(i);
            JobMessageDto jobMessageDto=new JobMessageDto();
            jobMessageDto.setId(jobmessage.getId());
            jobMessageDto.setDate(jobmessage.getDate());
            jobMessageDto.setAddress(jobmessage.getAddress());
            jobMessageDto.setPosition(jobmessage.getPosition());
            Integer userid=jobmessage.getUser().getUserid();
            Company company=companyService.get("user.userid",userid);
            jobMessageDto.setName(company.getName());
            jobMessageDto.setCompanytype(company.getType());
            jobMessageDtos.add(jobMessageDto);
            j++;
            if(j==5)
                break;
        }
        return jobMessageDtos;
    }
    @RequestMapping(value = "/jobmessageall")
    @ResponseBody
    public List<JobMessageDto> jobmessageall(){
        List<Jobmessage> jobmessages=jobmessageService.getList("from Jobmessage");
        List<JobMessageDto> jobMessageDtos=new ArrayList<JobMessageDto>();
        for(int i=jobmessages.size()-1;i>=0;i--){
            Jobmessage jobmessage=jobmessages.get(i);
            JobMessageDto jobMessageDto=new JobMessageDto();
            jobMessageDto.setId(jobmessage.getId());
            jobMessageDto.setDate(jobmessage.getDate());
            jobMessageDto.setAddress(jobmessage.getAddress());
            jobMessageDto.setPosition(jobmessage.getPosition());
            Integer userid=jobmessage.getUser().getUserid();
            Company company=companyService.get("user.userid",userid);
            jobMessageDto.setName(company.getName());
            jobMessageDto.setCompanytype(company.getType());
            jobMessageDtos.add(jobMessageDto);
        }
        return jobMessageDtos;
    }
    @RequestMapping(value = "/searchJob")
    @ResponseBody
    public List<JobMessageDto> searchJob(String search){
        List<Jobmessage> jobmessages=jobmessageService.getList("from Jobmessage where type like '%"+search+"%'");
        List<JobMessageDto> jobMessageDtos=new ArrayList<JobMessageDto>();
        for(int i=jobmessages.size()-1;i>=0;i--){
            Jobmessage jobmessage=jobmessages.get(i);
            JobMessageDto jobMessageDto=new JobMessageDto();
            Integer userid=jobmessage.getUser().getUserid();
            Company company=companyService.get("user.userid",userid);
            jobMessageDto.setName(company.getName());
            jobMessageDto.setCompanytype(company.getType());
            jobMessageDto.setId(jobmessage.getId());
            jobMessageDto.setDate(jobmessage.getDate());
            jobMessageDto.setAddress(jobmessage.getAddress());
            jobMessageDto.setPosition(jobmessage.getPosition());
            jobMessageDtos.add(jobMessageDto);
        }
        jobmessages=jobmessageService.getList("from Jobmessage where position like '%"+search+"%'");
        for(int i=jobmessages.size()-1;i>=0;i--){
            Jobmessage jobmessage=jobmessages.get(i);
            JobMessageDto jobMessageDto=new JobMessageDto();
            jobMessageDto.setId(jobmessage.getId());
            jobMessageDto.setDate(jobmessage.getDate());
            Integer userid=jobmessage.getUser().getUserid();
            Company company=companyService.get("user.userid",userid);
            jobMessageDto.setName(company.getName());
            jobMessageDto.setCompanytype(company.getType());
            jobMessageDto.setAddress(jobmessage.getAddress());
            jobMessageDto.setPosition(jobmessage.getPosition());
            jobMessageDtos.add(jobMessageDto);
        }
        return jobMessageDtos;
    }
}
