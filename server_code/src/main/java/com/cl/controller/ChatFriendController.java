package com.cl.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import com.cl.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cl.annotation.IgnoreAuth;
import com.cl.annotation.SysLog;

import com.cl.entity.ChatFriendEntity;
import com.cl.entity.view.ChatFriendView;

import com.cl.service.ChatFriendService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;

/**
 * 好友表
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-27 15:44:16
 */
@RestController
@RequestMapping("/chatfriend")
public class ChatFriendController {
    @Autowired
    private ChatFriendService chatFriendService;






     /**
     * 后台列表
     */
    @RequestMapping("/page2")
    @IgnoreAuth
    public R page2(@RequestParam Map<String, Object> params, HttpServletRequest request){
        PageUtils page = chatFriendService.queryFriendPage(params);
        return R.ok().put("data", page);
    }



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ChatFriendEntity chatFriend,
                                                                                                                                HttpServletRequest request){
                                    EntityWrapper<ChatFriendEntity> ew = new EntityWrapper<ChatFriendEntity>();
                                                                                                                                                                                                    
        
        
        PageUtils page = chatFriendService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chatFriend), params), params));
        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ChatFriendEntity chatFriend,
		HttpServletRequest request){
        EntityWrapper<ChatFriendEntity> ew = new EntityWrapper<ChatFriendEntity>();

		PageUtils page = chatFriendService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chatFriend), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ChatFriendEntity chatFriend){
       	EntityWrapper<ChatFriendEntity> ew = new EntityWrapper<ChatFriendEntity>();
      	ew.allEq(MPUtil.allEQMapPre( chatFriend, "chatFriend")); 
        return R.ok().put("data", chatFriendService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ChatFriendEntity chatFriend){
        EntityWrapper< ChatFriendEntity> ew = new EntityWrapper< ChatFriendEntity>();
 		ew.allEq(MPUtil.allEQMapPre( chatFriend, "chatFriend")); 
		ChatFriendView chatFriendView =  chatFriendService.selectView(ew);
		return R.ok("查询好友表成功").put("data", chatFriendView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ChatFriendEntity chatFriend = chatFriendService.selectById(id);
		chatFriend = chatFriendService.selectView(new EntityWrapper<ChatFriendEntity>().eq("id", id));
        return R.ok().put("data", chatFriend);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ChatFriendEntity chatFriend = chatFriendService.selectById(id);
		chatFriend = chatFriendService.selectView(new EntityWrapper<ChatFriendEntity>().eq("id", id));
        return R.ok().put("data", chatFriend);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增好友表")
    public R save(@RequestBody ChatFriendEntity chatFriend, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(chatFriend);
        chatFriendService.insert(chatFriend);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增好友表")
    @RequestMapping("/add")
    public R add(@RequestBody ChatFriendEntity chatFriend, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(chatFriend);
        chatFriendService.insert(chatFriend);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改好友表")
    public R update(@RequestBody ChatFriendEntity chatFriend, HttpServletRequest request){
        //ValidatorUtils.validateEntity(chatFriend);
        chatFriendService.updateById(chatFriend);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除好友表")
    public R delete(@RequestBody Long[] ids){
        chatFriendService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
