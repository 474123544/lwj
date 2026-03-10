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

import com.cl.entity.FriendshipLinkEntity;
import com.cl.entity.view.FriendshipLinkView;

import com.cl.service.FriendshipLinkService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;

/**
 * 友情链接
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-27 15:44:15
 */
@RestController
@RequestMapping("/friendshiplink")
public class FriendshipLinkController {
    @Autowired
    private FriendshipLinkService friendshipLinkService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,FriendshipLinkEntity friendshipLink,
                                                                    HttpServletRequest request){
                                    EntityWrapper<FriendshipLinkEntity> ew = new EntityWrapper<FriendshipLinkEntity>();
                                                                                                
        
        
        PageUtils page = friendshipLinkService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, friendshipLink), params), params));
        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,FriendshipLinkEntity friendshipLink,
		HttpServletRequest request){
        EntityWrapper<FriendshipLinkEntity> ew = new EntityWrapper<FriendshipLinkEntity>();

		PageUtils page = friendshipLinkService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, friendshipLink), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( FriendshipLinkEntity friendshipLink){
       	EntityWrapper<FriendshipLinkEntity> ew = new EntityWrapper<FriendshipLinkEntity>();
      	ew.allEq(MPUtil.allEQMapPre( friendshipLink, "friendshipLink")); 
        return R.ok().put("data", friendshipLinkService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(FriendshipLinkEntity friendshipLink){
        EntityWrapper< FriendshipLinkEntity> ew = new EntityWrapper< FriendshipLinkEntity>();
 		ew.allEq(MPUtil.allEQMapPre( friendshipLink, "friendshipLink")); 
		FriendshipLinkView friendshipLinkView =  friendshipLinkService.selectView(ew);
		return R.ok("查询友情链接成功").put("data", friendshipLinkView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        FriendshipLinkEntity friendshipLink = friendshipLinkService.selectById(id);
		friendshipLink = friendshipLinkService.selectView(new EntityWrapper<FriendshipLinkEntity>().eq("id", id));
        return R.ok().put("data", friendshipLink);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        FriendshipLinkEntity friendshipLink = friendshipLinkService.selectById(id);
		friendshipLink = friendshipLinkService.selectView(new EntityWrapper<FriendshipLinkEntity>().eq("id", id));
        return R.ok().put("data", friendshipLink);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增友情链接")
    public R save(@RequestBody FriendshipLinkEntity friendshipLink, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(friendshipLink);
        friendshipLinkService.insert(friendshipLink);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增友情链接")
    @RequestMapping("/add")
    public R add(@RequestBody FriendshipLinkEntity friendshipLink, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(friendshipLink);
        friendshipLinkService.insert(friendshipLink);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改友情链接")
    public R update(@RequestBody FriendshipLinkEntity friendshipLink, HttpServletRequest request){
        //ValidatorUtils.validateEntity(friendshipLink);
        friendshipLinkService.updateById(friendshipLink);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除友情链接")
    public R delete(@RequestBody Long[] ids){
        friendshipLinkService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
