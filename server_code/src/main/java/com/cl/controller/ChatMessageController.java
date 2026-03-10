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

import com.cl.entity.ChatMessageEntity;
import com.cl.entity.view.ChatMessageView;

import com.cl.service.ChatMessageService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;

/**
 * 消息表
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-27 15:44:16
 */
@RestController
@RequestMapping("/chatmessage")
public class ChatMessageController {
    @Autowired
    private ChatMessageService chatMessageService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ChatMessageEntity chatMessage,
                                                                        HttpServletRequest request){
                                    EntityWrapper<ChatMessageEntity> ew = new EntityWrapper<ChatMessageEntity>();
                                                                                                            
        
        
        PageUtils page = chatMessageService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chatMessage), params), params));
        return R.ok().put("data", page);
    }




    /**
     * 消息列表
     */
    @RequestMapping("/mlist")
    public R mlist(@RequestParam Map<String, Object> params,ChatMessageEntity chatmessage, HttpServletRequest request){
        EntityWrapper<ChatMessageEntity> ew = new EntityWrapper<ChatMessageEntity>();
        ew.eq("uid", chatmessage.getUid()).eq("fid", chatmessage.getFid()).orNew().eq("fid", chatmessage.getUid()).eq("uid", chatmessage.getFid());
        PageUtils page = chatMessageService.queryPage(params, ew);
        if(null!=chatmessage.getFid()){
        	chatMessageService.updateForSet("is_read=1", new EntityWrapper<ChatMessageEntity>().eq("is_read", 0).eq("fid", chatmessage.getUid()).eq("uid", chatmessage.getFid()));
        }
        return R.ok().put("data", page);
    }



    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ChatMessageEntity chatMessage,
		HttpServletRequest request){
        EntityWrapper<ChatMessageEntity> ew = new EntityWrapper<ChatMessageEntity>();

		PageUtils page = chatMessageService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chatMessage), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ChatMessageEntity chatMessage){
       	EntityWrapper<ChatMessageEntity> ew = new EntityWrapper<ChatMessageEntity>();
      	ew.allEq(MPUtil.allEQMapPre( chatMessage, "chatMessage")); 
        return R.ok().put("data", chatMessageService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ChatMessageEntity chatMessage){
        EntityWrapper< ChatMessageEntity> ew = new EntityWrapper< ChatMessageEntity>();
 		ew.allEq(MPUtil.allEQMapPre( chatMessage, "chatMessage")); 
		ChatMessageView chatMessageView =  chatMessageService.selectView(ew);
		return R.ok("查询消息表成功").put("data", chatMessageView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ChatMessageEntity chatMessage = chatMessageService.selectById(id);
		chatMessage = chatMessageService.selectView(new EntityWrapper<ChatMessageEntity>().eq("id", id));
        return R.ok().put("data", chatMessage);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ChatMessageEntity chatMessage = chatMessageService.selectById(id);
		chatMessage = chatMessageService.selectView(new EntityWrapper<ChatMessageEntity>().eq("id", id));
        return R.ok().put("data", chatMessage);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增消息表")
    public R save(@RequestBody ChatMessageEntity chatMessage, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(chatMessage);
        chatMessageService.insert(chatMessage);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增消息表")
    @RequestMapping("/add")
    public R add(@RequestBody ChatMessageEntity chatMessage, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(chatMessage);
        chatMessageService.insert(chatMessage);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改消息表")
    public R update(@RequestBody ChatMessageEntity chatMessage, HttpServletRequest request){
        //ValidatorUtils.validateEntity(chatMessage);
        chatMessageService.updateById(chatMessage);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除消息表")
    public R delete(@RequestBody Long[] ids){
        chatMessageService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
