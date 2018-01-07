package jsp.com.controller;

import jsp.com.dto.*;
import jsp.com.entry.*;
import jsp.com.service.CompanyService;
import jsp.com.service.JoblinkService;
import jsp.com.service.ResumeService;
import jsp.com.service.UserService;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/10/27.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private ResumeService resumeService;
    @Resource
    private JoblinkService joblinkService;
    @Resource
    private CompanyService companyService;
    @RequestMapping(value = "/register")
    @ResponseBody
    public MessageAndStatus register(String username, String password, String email, String type, String date){
        User user=new User();
        user.setUsername(username);
        user.setUserpwd(password);
        user.setUseremail(email);
        user.setUsertype(type);
        user.setUserdate(date);
        userService.save(user);
        String message="success";
        return new MessageAndStatus("success");
    }
    @RequestMapping(value = "/resume")
    @ResponseBody
    public MessageAndStatus resume(String username, String sex, String birth, String tell, String email,
    String address, String major, String education, String english, String university, String project,String position,String date,HttpServletRequest request){
        HttpSession session=request.getSession();
        Integer userid=(Integer)session.getAttribute("userid");
        User user=userService.get(userid);
        Resume resume =new Resume();
        resume.setAddress(address);
        resume.setBirth(birth);
        resume.setEducation(education);
        resume.setEnglish(english);
        resume.setName(username);
        resume.setRemail(email);
        resume.setProject(project);
        resume.setSex(sex);
        resume.setUniversity(university);
        resume.setRemajor(major);
        resume.setUser(user);
        resume.setTell(tell);
        resume.setPhoto(position);
        resume.setDate(date);
        resumeService.save(resume);
        return new MessageAndStatus("success");
    }
    @RequestMapping(value = "/checkname")
    @ResponseBody
    public MessageAndStatus checkname(String username){
        String message="no";
        if(userService.isExist("username",username)){
            message="yes";
        }
        return new MessageAndStatus(message);
    }
    @RequestMapping(value = "/login")
    @ResponseBody
    public MessageAndStatus login(HttpServletRequest request, String username, String password){
        String message="no";
        if(userService.isExist("username",username)==false){
            User user=userService.get("username",username);
            if(user.getUserpwd().equals(password)){
                HttpSession session=request.getSession();
                session.setAttribute("userid",user.getUserid());

                message="yes";
            }
            else{
                message="no";
            }
        }
        else{
            message="not exit";
        }
        return new MessageAndStatus(message);
    }
    @RequestMapping(value = "/isLogin")
    @ResponseBody
    public CheckLogin isLogin(HttpServletRequest request, String username, String password){
        HttpSession session=request.getSession();
        CheckLogin check=new CheckLogin();
        if(session == null){
            check.setMessage("no");
        }else if(session.getAttribute("userid") == null){
            check.setMessage("no");
        }else{
            Integer id=(Integer)session.getAttribute("userid");
            User user=userService.get(id);
            check.setMessage("yes");
            check.setId(id);
            check.setUsername(user.getUsername());
            check.setType(user.getUsertype());
        }
        return check;
    }
    @RequestMapping(value = "/tounLogin")
    @ResponseBody
    public MessageAndStatus tounLogin(HttpServletRequest request){
        HttpSession session=request.getSession();
        session.removeAttribute("userid");
        return new MessageAndStatus("yes");
    }
    @RequestMapping(value = "/showMessage")
    @ResponseBody
    public UserDto showMessage(HttpServletRequest request){
        HttpSession session=request.getSession();
        Integer userid=(Integer)session.getAttribute("userid");
        User user=userService.get(userid);
        UserDto userDto=new UserDto(userid,user.getUsername(),user.getUserpwd(),user.getUseremail(),user.getUsertype(),user.getUserdate(),user.getPhoto());
        return userDto;
    }
    @RequestMapping(value = "/saveMessage")
    @ResponseBody
    public MessageAndStatus saveMessage(String username,String userpwd,String useremail,HttpServletRequest request){
        HttpSession session=request.getSession();
        Integer userid=(Integer)session.getAttribute("userid");
        User user=userService.get(userid);
        user.setUsername(username);
        user.setUserpwd(userpwd);
        user.setUseremail(useremail);
        userService.update(user);
        return new MessageAndStatus("yes");
    }
    @RequestMapping(value = "/imgUpload")
    @ResponseBody
    public synchronized String imgUpload(@RequestParam("file") MultipartFile file,HttpServletRequest request) throws IOException {
        String tishi="no";
        System.out.println("arrive here");
        if(!file.isEmpty()) {
            //System.out.println(file.getOriginalFilename());
            String message = System.currentTimeMillis() + file.getOriginalFilename();
            String realPath = request.getSession().getServletContext().getRealPath("/upload/");
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, message));
            message="upload/"+message;
            HttpSession session=request.getSession();
            Integer id=(Integer)session.getAttribute("userid");
            User user=userService.get(id);
            user.setPhoto(message);
            userService.update(user);
            tishi="yes";
        }
        return tishi;
    }
    @RequestMapping(value = "/userMessage")
    @ResponseBody
    public List<UsermessageDto> userMessage(HttpServletRequest request){
        HttpSession session=request.getSession();
        Integer id=(Integer)session.getAttribute("userid");
        List<Resume> resumes=resumeService.getList("user.userid",id);
        int i;
        int j;
        int k=0;
        List<UsermessageDto> usermessages=new ArrayList<UsermessageDto>();
        for(j=0;j<resumes.size();j++){
            List<Joblink> joblinks=joblinkService.getList("resume.id",resumes.get(j).getId());

            for(i=0;i<joblinks.size();i++){
                Integer cid = joblinks.get(i).getJobmessage().getUser().getUserid();
                System.out.println(cid);
                Company company=companyService.get("user.userid",cid);
                UsermessageDto usermessage=new UsermessageDto(joblinks.get(i).getSatues(),joblinks.get(i).getTime(),company.getName());
                usermessages.add(usermessage);
                k++;
            }
        }
        return usermessages;
    }
    @RequestMapping(value = "/isuserResumeMessage")
    @ResponseBody
    public boolean isuserResumeMessage(HttpServletRequest request){
        HttpSession session=request.getSession();
        Integer id=(Integer)session.getAttribute("userid");
        if(resumeService.getList("user.id",id).size()==0){
            return false;
        }
        return true;
    }
    @RequestMapping(value = "/userResumeMessage")
    @ResponseBody
    public jsonDto userResumeMessage(HttpServletRequest request, HttpServletResponse response, DataTables dataTables) throws IOException{
        HttpSession session=request.getSession();
        Integer id=(Integer)session.getAttribute("userid");
        List<Resume> resumes=new ArrayList<Resume>();
        resumes=resumeService.getList("from Resume where user.userid="+id+" order by id desc",dataTables.getStart(),dataTables.getLength());
        List<ResumeDto> resumeDtos=new ArrayList<ResumeDto>();
        int i;
        for(i=0;i<resumes.size();i++){
            ResumeDto resumeDto=new ResumeDto();
            resumeDto.setDate(resumes.get(i).getDate());
            resumeDto.setPhoto(resumes.get(i).getPhoto());
            resumeDto.setEnglish(resumes.get(i).getEnglish());
            resumeDto.setId(resumes.get(i).getId());
            resumeDto.setEducation(resumes.get(i).getEducation());
            resumeDto.setAddress(resumes.get(i).getAddress());
            resumeDto.setBirth(resumes.get(i).getBirth());
            resumeDto.setMail(resumes.get(i).getRemail());
            resumeDto.setUniversity(resumes.get(i).getUniversity());
            resumeDto.setTell(resumes.get(i).getTell());
            resumeDto.setSex(resumes.get(i).getSex());
            resumeDto.setMajor(resumes.get(i).getRemajor());
            resumeDto.setName(resumes.get(i).getName());
            resumeDto.setProject(resumes.get(i).getProject());
            resumeDtos.add(resumeDto);
        }
        jsonDto<ResumeDto>  jsonObject= new jsonDto<ResumeDto>();
        jsonObject.setDraw(dataTables.getDraw());

        Long count =(long) resumeService.getList("user.id",id).size();
        jsonObject.setRecordsFiltered(count);
        jsonObject.setRecordsTotal(count);
        jsonObject.setData(resumeDtos);
        return jsonObject;
    }
    @RequestMapping(value = "CopyResume")
    @ResponseBody
    public boolean CopyResume(Integer id)
    {
        try {
            Resume resume=resumeService.get(id);
            resume.setId(null);
            resumeService.save(resume);
        }catch (Exception e)
        {
            return false;
        }
        return true;
    }
    @RequestMapping(value = "userResumeMessageDelete")
    @ResponseBody
    public boolean userResumeMessageDelete(Integer id){
        try {
            resumeService.delete(id);

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    @RequestMapping(value = "userResumeGet")
    @ResponseBody
    public ResumeDto userResumeGet(Integer id){
        Resume resume=resumeService.get(id);
        ResumeDto resumeDto=new ResumeDto();
        resumeDto.setDate(resume.getDate());
        resumeDto.setPhoto(resume.getPhoto());
        resumeDto.setEnglish(resume.getEnglish());
        resumeDto.setId(resume.getId());
        resumeDto.setEducation(resume.getEducation());
        resumeDto.setAddress(resume.getAddress());
        resumeDto.setBirth(resume.getBirth());
        resumeDto.setMail(resume.getRemail());
        resumeDto.setUniversity(resume.getUniversity());
        resumeDto.setTell(resume.getTell());
        resumeDto.setSex(resume.getSex());
        resumeDto.setMajor(resume.getRemajor());
        resumeDto.setName(resume.getName());
        resumeDto.setProject(resume.getProject());
        resumeDto.setUserid(resume.getUser().getUserid());
        return resumeDto;
    }
    @RequestMapping(value = "/resumechange")
    @ResponseBody
    public MessageAndStatus resumechange(Integer id,String username, String sex, String birth, String tell, String email,
                                   String address, String major, String education, String english, String university, String project,String position,String date,Integer userid){
        User user=userService.get(userid);
        Resume resume =new Resume();
        resume.setAddress(address);
        resume.setBirth(birth);
        resume.setEducation(education);
        resume.setEnglish(english);
        resume.setName(username);
        resume.setRemail(email);
        resume.setProject(project);
        resume.setSex(sex);
        resume.setUniversity(university);
        resume.setRemajor(major);
        resume.setTell(tell);
        resume.setPhoto(position);
        resume.setId(id);
        resume.setDate(date);
        resume.setUser(user);
        resumeService.update(resume);
        return new MessageAndStatus("yes");
    }
    @RequestMapping(value = "alluser")
    @ResponseBody
    public jsonDto alluser(HttpServletRequest request, HttpServletResponse response, DataTables dataTables) throws IOException{

        List<UserDto> userDtos=new ArrayList<UserDto>();
        List<User> users=new ArrayList<User>();
        System.out.println("daochi");
        users=userService.getList("from User",dataTables.getStart(),dataTables.getLength());
        int i;
        for(i=0;i<users.size();i++){
            UserDto userDto=new UserDto(users.get(i).getUserid(),users.get(i).getUsername(),users.get(i).getUserpwd(),users.get(i).getUseremail(),users.get(i).getUsertype(),users.get(i).getUserdate(),users.get(i).getPhoto());
            userDtos.add(userDto);
        }
        jsonDto<UserDto>  jsonObject= new jsonDto<UserDto>();
        jsonObject.setDraw(dataTables.getDraw());
        Long count = userService.getTotalCount();
        jsonObject.setRecordsFiltered(count);
        jsonObject.setRecordsTotal(count);
        jsonObject.setData(userDtos);
        return jsonObject;
    }
    @RequestMapping(value = "comapnyMessageRead")
    @ResponseBody
    public jsonDto<CompanyMessageDto> comapnyMessageRead(HttpServletRequest request, HttpServletResponse response, DataTables dataTables) throws IOException{
        HttpSession session=request.getSession();
        Integer id=(Integer)session.getAttribute("userid");
        List<Resume> resumes=resumeService.getList("user.userid",id);
        List<CompanyMessageDto> companyMessageDtos=new ArrayList<CompanyMessageDto>();
        Long count=0L;

        int a=0;
        int l=0;
        int flag=1;
        for(int i=0;i<resumes.size();i++){
            Resume resume=resumes.get(i);
            List<Joblink> joblinks=joblinkService.getList("resume.id",resume.getId());
            count+=joblinks.size();
            for(int j=0;j<joblinks.size()&&flag==1;j++){
                a++;
                if(a<dataTables.getStart()){
                    continue;
                }
                CompanyMessageDto companyMessageDto=new CompanyMessageDto();
                Company company=companyService.get("user.userid",joblinks.get(j).getJobmessage().getUser().getUserid());
                companyMessageDto.setDate(joblinks.get(j).getTime());
                companyMessageDto.setId(joblinks.get(j).getJobmessage().getId());
                companyMessageDto.setCompanyname(company.getName());
                companyMessageDto.setResumename(resume.getPhoto());
                String status="";
                if(joblinks.get(j).getSatues().equals("on")){
                    status="投递成功";
                }
                else if(joblinks.get(j).getSatues().equals("false")){
                    status="被婉拒";
                }
                else if(joblinks.get(j).getSatues().equals("true")){
                    status="恭喜通过";
                }
                companyMessageDto.setStatus(status);
                companyMessageDtos.add(companyMessageDto);
                l++;
                if(l==dataTables.getLength()){
                    flag=0;
                    break;
                }
            }
        }
        jsonDto<CompanyMessageDto>  jsonObject= new jsonDto<CompanyMessageDto>();
        jsonObject.setDraw(dataTables.getDraw());
        jsonObject.setRecordsFiltered(count);
        jsonObject.setRecordsTotal(count);
        jsonObject.setData(companyMessageDtos);
        return jsonObject;
    }
}
