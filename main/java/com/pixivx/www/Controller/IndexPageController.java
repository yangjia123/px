package com.pixivx.www.Controller;

import com.pixivx.www.Entity.*;
import com.pixivx.www.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class IndexPageController {
    @Autowired private IndexRecService indexRecService;
    @Autowired private PicGroupService picGroupService;
    @Autowired private PicService picService;
    @Autowired private OfficalMessageService officalMessageService;
    @Autowired private UserManageService userManageService;
    @Autowired private TopicService topicService;
    //推荐
    private List<IndexRec> indexRecList;
    private List<PicGroup> indexRecPicGroupslist;
    private List<Pic> indexRecFirstPicList;
    private List<User> indexRecUserList;
    //公告
    private List<OfficalMessage> officalMessageList;
    //排行榜
    private List<PicGroup> rankPicGroupslist;
    private List<Pic> rankFirstPicList;
    private List<User> rankUserList;
    //图片标签
    private List<PicGroup> rankPicLabelPicGroupslist_1;
    private List<Pic> rankPicLabelFirstPicList_1;
    private List<User> rankPicLabelUserList_1;
    private List<PicGroup> rankPicLabelPicGroupslist_2;
    private List<Pic> rankPicLabelFirstPicList_2;
    private List<User> rankPicLabelUserList_2;
    //性别排行
    private List<PicGroup> rankGengerPicGroupslist_female;
    private List<Pic> rankGengerFirstPicList_female;
    private List<User> rankGengerRecUserList_female;
    private List<PicGroup> rankGengerPicGroupslist_male;
    private List<Pic> rankGengerFirstPicList_male;
    private List<User> rankGengerRecUserList_male;
    //最新作品
    private List<PicGroup> newestPicGroupslist;
    private List<Pic> newestFirstPicList;
    private List<User> newestUserList;
    //话题
    private List<Topic> topicList;
    private List<User> topicUserList;
    //发现
    private List<PicGroup> discoveryPicGroupslist;
    private List<Pic> discoveryFirstPicList;
    private List<User> discoveryUserList;
    //登录状态
    private boolean is_user_login = true;
    //排行榜状态
    private int pic_label_rank = 0;
    private int gender_rank = 0;

    @RequestMapping("/home")
    public String Welcome(ModelMap modelMap, HttpSession httpSession){
        //登录状态
        //String user_id_tp = (String) httpSession.getAttribute("user_id");
        String user_id_tp = "31";
        if (user_id_tp != null) {
            int user_id = Integer.valueOf(user_id_tp).intValue();
            User user = userManageService.findUserById(user_id);
            modelMap.addAttribute("user", user);
            is_user_login = true;
        }else{
            is_user_login = false;
        }
        try {
            //公告数据
            getOfficalMessageData(modelMap);
            //首页推荐数据
            getIdexRecData(modelMap);
            //排行榜
            getRankData(modelMap);
            //最新作品
            getNewestData(modelMap);

            if (is_user_login){
                //发现
                getDiscoveryData(modelMap);
                //话题
                getTopicData(modelMap);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        String userid = (String) httpSession.getAttribute("user_id");
        int user_id = Integer.valueOf(userid).intValue();
        User user = userManageService.findUserById(user_id);
        modelMap.addAttribute("user",user);
        return "home";
    }
    @RequestMapping("/rank")
    @ResponseBody
    public String Rank(@RequestParam(value = "label")String label){
        Map<Integer, String> map = new LinkedHashMap<>();
        System.out.println("=============RANK============");
        switch (label){
            case "0":


                rankPicGroupslist = picGroupService.findOrder(0,"click_times", 3);
                rankUserList = new ArrayList<>();
                rankFirstPicList = new ArrayList<>();
                searchUserAndFirstPicByPicGroup( rankPicGroupslist, rankFirstPicList, rankUserList);
                setMapForRank(map, rankPicGroupslist, rankFirstPicList,rankUserList,"rank_1");
                break;
            case "1":

                rankPicLabelPicGroupslist_1 = picGroupService.findOrder(1,"click_times", 3);
                rankPicLabelUserList_1 = new ArrayList<>();
                rankPicLabelFirstPicList_1 = new ArrayList<>();
                searchUserAndFirstPicByPicGroup( rankPicLabelPicGroupslist_1, rankPicLabelFirstPicList_1, rankPicLabelUserList_1);
                setMapForRank(map,rankPicLabelPicGroupslist_1,rankPicLabelFirstPicList_1,rankPicLabelUserList_1,"rank_1");
                break;
            case "2":

                rankPicLabelPicGroupslist_2 = picGroupService.findOrder(1,"click_times", 3);
                rankPicLabelUserList_2 = new ArrayList<>();
                rankPicLabelFirstPicList_2 = new ArrayList<>();
                searchUserAndFirstPicByPicGroup( rankPicLabelPicGroupslist_2, rankPicLabelFirstPicList_2, rankPicLabelUserList_2);
                setMapForRank(map,rankPicLabelPicGroupslist_2,rankPicLabelFirstPicList_2,rankPicLabelUserList_2,"rank_1");
                break;
            case "3":

                rankGengerPicGroupslist_male = picGroupService.findOrder(3,"click_times", 3);
                rankGengerFirstPicList_male = new ArrayList<>();
                rankGengerRecUserList_male = new ArrayList<>();
                searchUserAndFirstPicByPicGroup( rankGengerPicGroupslist_male, rankGengerFirstPicList_male, rankGengerRecUserList_male);

                setMapForRank(map,rankGengerPicGroupslist_male,rankGengerFirstPicList_male,rankGengerRecUserList_male,"rank_2");
                break;
            case "4":

                rankGengerPicGroupslist_female = picGroupService.findOrder(4,"click_times", 3);
                rankGengerFirstPicList_female = new ArrayList<>();
                rankGengerRecUserList_female = new ArrayList<>();
                searchUserAndFirstPicByPicGroup( rankGengerPicGroupslist_female, rankGengerFirstPicList_female, rankGengerRecUserList_female);
                setMapForRank(map,rankGengerPicGroupslist_female,rankGengerFirstPicList_female,rankGengerRecUserList_female,"rank_2");
                break;
        }
        String result="[";
        int i = 1;
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            int flag = i % 4;
            if (flag == 1) {
                result += "{";
            }
            switch (flag){
                case 1:
                    result += "\"" + "urls" + "\":\"" + entry.getValue() + "\"";
                    break;
                case 2:
                    result += "\"" + "title" + "\":\"" + entry.getValue() + "\"";
                    break;
                case 3:
                    result += "\"" + "nickname" + "\":\"" + entry.getValue() + "\"";
                    break;
                case 0:
                    result += "\"" + "div_id" + "\":\"" + entry.getValue() + "\"";
                    break;
            }

            if (flag != 0) {
                result += ",";
            }
            if (flag == 0){
                result+="},";
            }
            i++;
        }
        result = result.substring(0,result.length()-1);
        result+="]";
        System.out.println("==============JSON================\n"+result);
        return result;
    }
    private void setMapForRank(Map map, List<PicGroup> picGroups, List<Pic> firstPics, List<User> users,String div_id){
        for (int i = 0,j=0; i < picGroups.size(); i++){

            map.put( j++, firstPics.get(i).getUrl());
            map.put( j++, picGroups.get(i).getTitle());
            map.put( j++, users.get(i).getNickname());
            map.put( j++, div_id);
        }
    }
    private void getNewestData(ModelMap modelMap) {
        newestPicGroupslist = picGroupService.findOrder(0,"submit_time", 6);
        newestUserList = new ArrayList<>();
        newestFirstPicList = new ArrayList<>();
        //用户
        searchUserAndFirstPicByPicGroup(newestPicGroupslist, newestFirstPicList, newestUserList);
        modelMap.addAttribute("newest_pic_group_list", newestPicGroupslist);
        modelMap.addAttribute("newest_user_list", newestUserList);
        modelMap.addAttribute("newest_first_pic_list", newestFirstPicList);
    }

    private void getRankData(ModelMap modelMap) {
        switch (pic_label_rank){
            case 0:
                rankPicGroupslist = picGroupService.findOrder(0,"click_times", 3);
                rankUserList = new ArrayList<>();
                rankFirstPicList = new ArrayList<>();
                searchUserAndFirstPicByPicGroup( rankPicGroupslist, rankFirstPicList, rankUserList);
                modelMap.addAttribute("rank_pic_group_list", rankPicGroupslist);
                modelMap.addAttribute("rank_user_list", rankUserList);
                modelMap.addAttribute("rank_first_pic_list", rankFirstPicList);
                break;
            case 1:
                rankPicLabelPicGroupslist_1 = picGroupService.findOrder(1,"click_times", 3);
                rankPicLabelUserList_1 = new ArrayList<>();
                rankPicLabelFirstPicList_1 = new ArrayList<>();
                searchUserAndFirstPicByPicGroup( rankPicLabelPicGroupslist_1, rankPicLabelFirstPicList_1, rankPicLabelUserList_1);
                modelMap.addAttribute("rank_pic_label_pic_group_list_1", rankPicLabelPicGroupslist_1);
                modelMap.addAttribute("rank_pic_label_user_list_1", rankPicLabelUserList_1);
                modelMap.addAttribute("rank_pic_label_first_pic_list_1", rankPicLabelFirstPicList_1);
                break;
            case 2:
                rankPicLabelPicGroupslist_2 = picGroupService.findOrder(1,"click_times", 3);
                rankPicLabelUserList_2 = new ArrayList<>();
                rankPicLabelFirstPicList_2 = new ArrayList<>();
                searchUserAndFirstPicByPicGroup( rankPicLabelPicGroupslist_2, rankPicLabelFirstPicList_2, rankPicLabelUserList_2);
                modelMap.addAttribute("rank_pic_label_pic_group_list_2", rankPicLabelPicGroupslist_2);
                modelMap.addAttribute("rank_pic_label_user_list_2", rankPicLabelUserList_2);
                modelMap.addAttribute("rank_pic_label_first_pic_list_2", rankPicLabelFirstPicList_2);
                break;
        }

        switch (gender_rank){
            case 0:
                rankGengerPicGroupslist_male = picGroupService.findOrder(3,"click_times", 3);
                rankGengerFirstPicList_male = new ArrayList<>();
                rankGengerRecUserList_male = new ArrayList<>();
                searchUserAndFirstPicByPicGroup( rankGengerPicGroupslist_male, rankGengerFirstPicList_male, rankGengerRecUserList_male);
                modelMap.addAttribute("rank_gender_pic_group_list_male", rankGengerPicGroupslist_male);
                modelMap.addAttribute("rank_gender_user_list_male", rankGengerRecUserList_male);
                modelMap.addAttribute("rank_gender_first_pic_list_male", rankGengerFirstPicList_male);

                break;
            case 1:
                rankGengerPicGroupslist_female = picGroupService.findOrder(4,"click_times", 3);
                rankGengerFirstPicList_female = new ArrayList<>();
                rankGengerRecUserList_female = new ArrayList<>();
                searchUserAndFirstPicByPicGroup( rankGengerPicGroupslist_female, rankGengerFirstPicList_female, rankGengerRecUserList_female);
                modelMap.addAttribute("rank_gender_pic_group_list_female", rankGengerPicGroupslist_female);
                modelMap.addAttribute("rank_gender_user_list_female", rankGengerRecUserList_female);
                modelMap.addAttribute("rank_gender_first_pic_list_female", rankGengerFirstPicList_female);
                break;
        }
    }

    private void getTopicData(ModelMap modelMap) {
        topicList = topicService.findTopicOrderBySubmitTime(6);
        topicUserList = new ArrayList<>();
        for (Topic topic : topicList){
            topicUserList.add(userManageService.findUserById(topic.getUser_id()));
        }
        modelMap.addAttribute("topic_list", topicList);
        modelMap.addAttribute("topic_user_list",topicUserList);
    }

    private void getDiscoveryData(ModelMap modelMap) {
        discoveryPicGroupslist = picGroupService.findRadom(6);
        discoveryFirstPicList = new ArrayList<>();
        discoveryUserList = new ArrayList<>();
        searchUserAndFirstPicByPicGroup(discoveryPicGroupslist, discoveryFirstPicList, discoveryUserList);
        modelMap.addAttribute("discovery_pic_group_list", discoveryPicGroupslist);
        modelMap.addAttribute("discovery_user_list", discoveryUserList);
        modelMap.addAttribute("discovery_first_pic_list", discoveryFirstPicList);
    }

    private void getIdexRecData(ModelMap modelMap){
       //推荐部分
       indexRecList = indexRecService.findAll();
       indexRecPicGroupslist = new ArrayList<>();
       indexRecFirstPicList = new ArrayList<>();
       indexRecUserList = new ArrayList<>();
       //图片组
       for (IndexRec indexRec : indexRecList) {
           indexRecPicGroupslist.add(picGroupService.findPicGroupById(indexRec.getPic_group_id()));
       }
       searchUserAndFirstPicByPicGroup(indexRecPicGroupslist,indexRecFirstPicList,indexRecUserList);
       //推荐部分
       modelMap.addAttribute("index_rec_list", indexRecList);
       modelMap.addAttribute("index_rec_pic_group_list", indexRecPicGroupslist);
       modelMap.addAttribute("index_rec_first_pic_list", indexRecFirstPicList);
       modelMap.addAttribute("index_rec_user_list", indexRecUserList);
    }

   private void getOfficalMessageData(ModelMap modelMap){
        officalMessageList = officalMessageService.findAll();
        modelMap.addAttribute("offical_message_list", officalMessageList);
   }

    /**
     *
     * @param picGrouplist 图片组
     * @param firstPicList 图片组的第一张图
     * @param userList 图片组的作者
     */
   private void searchUserAndFirstPicByPicGroup(List<PicGroup> picGrouplist, List<Pic> firstPicList, List<User> userList){
       for (PicGroup picGroup : picGrouplist){
           User user = userManageService.findUserById(picGroup.getUser_id());
           userList.add(user);
           Pic pic = picService.findPicAllByPicGroupId(picGroup.getPic_group_id()).get(0);
           firstPicList.add(pic);
       }
   }

}
