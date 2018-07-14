package com.pixivx.www.Controller;

import com.pixivx.www.Entity.*;
import com.pixivx.www.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.pixivx.www.Helper.AliyunOssHelper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/User")
public class UserManageController {
    @Autowired private UserManageService userManageService;
    @Autowired private PicGroupService picGroupService;
    @Autowired private PicService picService;
    @Autowired private TopicService topicService;
    @Autowired private PicCollectServcie picCollectServcie;
    @Autowired private UserFollowerService userFollowerService;
    @Autowired private OfficalMessageService officalMessageService;
    @Autowired private FeedService feedService;
    @Autowired private FeedCommentService feedCommentService;

    //用户作品列表
    private List<PicGroup> userPicGroupList;
    private List<Pic> userPicList;

    //用户话题列表
    private List<Topic> userTopicList;

    //用户收藏图片列表
    List<PicCollect> userCollectList;
    List<Pic> userPicCollectList;

    //用户关注人列表
    List<User> userFollowerList;

    //用户动态和评论列表
    List<Feed> userFeedList ;
    List<List<FeedComment>> userFeedCommentList;

    @RequestMapping(value="/toRegister")
    public String toRegister(){
        return "login_register";
    }

    @RequestMapping(value="/toLogin")
    public String toLogin(){
        return "login_log";
    }

    @RequestMapping(value="/toHome")
    public String toHome(ModelMap model, @RequestParam(value = "phone")String phone, HttpSession httpSession){
        User user = null;
        user = userManageService.selectPhone(phone);
        httpSession.setAttribute("user_id",user.getUser_id());
        //model.addAttribute("nickname", user.getNickname());
        return "redirect:/home";
    }

    @RequestMapping(value="/register")
    public String userRegister(HttpServletRequest request,ModelMap model,HttpSession httpSession)throws IOException {
        request.setCharacterEncoding("UTF-8");
        User user = new User();
        user.setPhone(request.getParameter("phone"));
        user.setPassword(request.getParameter("inputPassword"));
        user.setNickname(request.getParameter("nickname"));
        user.setGender(request.getParameter("customRadioInline1"));
        String birthday = request.getParameter("selectYear")+request.getParameter("selectMonth")+request.getParameter("selectDay");
        user.setBirthday(birthday);
        long seconds = System.currentTimeMillis()/1000;
        user.setRegister_time(seconds);
        userManageService.insertUser(user);
        User res = userManageService.selectPhone(user.getPhone());
        //model.addAttribute("nickname", user.getNickname());
        httpSession.setAttribute("user_id",res.getUser_id());
        return "redirect:/home";
    }

    @RequestMapping(value="/login")
    @ResponseBody
    public String userLogin(@RequestParam(value = "phone")String phone,@RequestParam(value = "password")String password){
        User user = null;
        user = userManageService.selectUserByPhone(phone,password);
        if(user == null)
            return "{\"num\":\"0\"}";
        else
            return "{\"num\":\"1\"}";
    }

    @RequestMapping(value="/toReminder")
    public String reminderPwd(){
        return "reminder";
    }

    @RequestMapping(value="/toReminder2")
    public String reminderPwd2(HttpServletRequest request,HttpSession httpSession){
        httpSession.setAttribute("phone",request.getParameter("phone"));
        return "reminder2";
    }

    //获取点击的公告详情
    @RequestMapping(value="/browsing_announcement")
    public String browsingAnn(HttpServletRequest request,ModelMap model)throws IOException{
        request.setCharacterEncoding("UTF-8");
        String offical_message_id = request.getParameter("offical_message_id");
        OfficalMessage officalMessage = null;
        officalMessage = officalMessageService.getOfficalMessageById(offical_message_id);
        model.addAttribute("officalMessage",officalMessage);
        return "browsing_announcement";
    }

    //用户忘记密码，转向登录界面
    @RequestMapping(value="/modifyPassword")
    public String userModifyPassword(HttpSession httpSession,@RequestParam(value = "inputPassword")String password){
        String phone = (String)httpSession.getAttribute("phone");
        userManageService.updateUserPassword(phone,password);
        return "login_log";
    }

    //判断手机号是否已存在
    @RequestMapping(value="/selectPhone")
    @ResponseBody
    public String  selectPhone(@RequestParam(value = "phone")String phone){
        User user = null;
        user = userManageService.selectPhone(phone);
        if(user == null)
            return "{\"num\":\"0\"}";
        else
            return "{\"num\":\"1\"}";
    }

    //判断用户名是否已存在
    @RequestMapping(value="/selectNickname")
    @ResponseBody
    public String  selectNickname(@RequestParam(value = "nickname")String nickname){
        User user = null;
        user = userManageService.selectNickname(nickname);
        if(user == null)
            return "{\"num\":\"0\"}";
        else
            return "{\"num\":\"1\"}";
    }

    //获得用户作品
    @RequestMapping(value="/getUserAllWorks")
    @ResponseBody
    public void getUserWorks(ModelMap modelMap,@RequestParam("nickname")String nickname,@RequestParam("method")String method){
        //用户作品列表
        userPicGroupList = new ArrayList<>();
        userPicList = new ArrayList<>();

        //根据类别获取图片组
        User user = userManageService.selectNickname(nickname);
        if(method.equals("1"))
        {
            //获取动图
            userPicGroupList = picGroupService.selectGifPicGroupByUserid(user.getUser_id());
        }
        else if(method.equals("0"))
        {
            //获取插画
            userPicGroupList = picGroupService.selectIconPicGroupByUserid(user.getUser_id());
        }
        else
            userPicGroupList = picGroupService.selectPicGroupByUserid(user.getUser_id());

        //图片组的第一张图
        for (PicGroup picGroup : userPicGroupList) {
            userPicList.add(picService.findPicAllByPicGroupId(picGroup.getPic_group_id()).get(0));
        }
        modelMap.addAttribute("userPicGroupList", userPicGroupList);
        modelMap.addAttribute("userPicList", userPicList);
        System.out.println(userPicGroupList);
        System.out.println(userPicList);
    }

    //获取用户话题列表
    @RequestMapping(value="/getUserTopics")
    @ResponseBody
    public void getUserTopics(ModelMap modelMap,@RequestParam("nickname")String nickname){
        userTopicList = new ArrayList<>();
        User user = userManageService.selectNickname(nickname);
        int user_id = Integer.valueOf(user.getUser_id()).intValue();
        userTopicList = topicService.selectTopicByUserid(user_id);
        modelMap.addAttribute("userTopicList", userTopicList);
        System.out.println(userTopicList);
    }

    //获取用户收藏图片列表
    @RequestMapping(value="/getUserPicCollect")
    @ResponseBody
    public void getUserPicCollect(ModelMap modelMap,@RequestParam("nickname")String nickname){
        userCollectList = new ArrayList<>();
        userPicCollectList  = new ArrayList<>();

        User user = userManageService.selectNickname(nickname);
        int user_id = Integer.valueOf(user.getUser_id()).intValue();

        userCollectList = picCollectServcie.selectPicCollectByUserid(user_id);

        for (PicCollect picCollect : userCollectList) {
            userPicCollectList.add(picService.findPicAllByPicGroupId(picCollect.getPic_group_id()).get(0));
        }

        modelMap.addAttribute("userCollectList", userCollectList);
        modelMap.addAttribute("userPicCollectList", userPicCollectList);

        System.out.println(userCollectList);
        System.out.println(userPicCollectList);
    }

    //获取用户关注人列表
    @RequestMapping(value="/getUserFollower")
    @ResponseBody
    public void getUserFollower(ModelMap modelMap,@RequestParam("nickname")String nickname){
        userFollowerList = new ArrayList<>();
        List<String> followerIdList = new ArrayList<>();

        User user = userManageService.selectNickname(nickname);
        int user_id = Integer.valueOf(user.getUser_id()).intValue();

        followerIdList = userFollowerService.getUserFollowerByUserid(user_id);

        for (String followerId : followerIdList) {
             int tempFollowerId = Integer.valueOf(followerId).intValue();
            userFollowerList.add(userManageService.findUserById(tempFollowerId));
        }

        modelMap.addAttribute("userFollowerList", userFollowerList);
        System.out.println(userFollowerList);
    }


    //获取用户发布的动态列表
    @RequestMapping(value="/getUserFeed")
    @ResponseBody
    public void getUserFeed(ModelMap modelMap,@RequestParam("nickname")String nickname){
        User user = userManageService.selectNickname(nickname);
        userFeedList = feedService.selectFeedByUserid(user.getUser_id());
        userFeedCommentList = new ArrayList<>();
        for (Feed feed : userFeedList) {
               userFeedCommentList.add(feedCommentService.selectFeedCommentByFeedid(feed.getFeed_id()));
        }
        modelMap.addAttribute("userFeedList", userFeedList);
        modelMap.addAttribute("userFeedCommentList", userFeedCommentList);
        System.out.println(userFeedList);
        System.out.println(userFeedCommentList);
    }

    //上传头像到数据库
    @RequestMapping(value="/uploadHeadpic")
    @ResponseBody
    public String uploadHeadpic(ModelMap modelMap,@RequestParam("nickname")String nickname,@RequestParam("file")MultipartFile file)throws FileNotFoundException,IOException {
        String fileUrl = null;
        String endpoint = "oss-cn-beijing.aliyuncs.com";
        String bucketName = "pixivx";
        if(file != null)
        {
            String filename = file.getOriginalFilename();
            if(!"".equals(filename.trim())){
                File newFile = new File(filename);
                FileOutputStream os = new FileOutputStream(newFile);
                os.write(file.getBytes());
                os.close();
                file.transferTo(newFile);
                fileUrl = AliyunOssHelper.upload(newFile);
                fileUrl = "https://"+bucketName+"."+endpoint+"/"+fileUrl;
                userManageService.updateUserHeadpic(nickname,fileUrl);
            }
            return fileUrl;
        }
        else
            return "error";
    }
}
