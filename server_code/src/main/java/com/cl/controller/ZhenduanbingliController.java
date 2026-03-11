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

import com.cl.entity.ZhenduanbingliEntity;
import com.cl.entity.view.ZhenduanbingliView;

import com.cl.service.ZhenduanbingliService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;

/**
 * 诊断病历
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-27 15:44:15
 */
@RestController
@RequestMapping("/zhenduanbingli")
public class ZhenduanbingliController {
    @Autowired
    private ZhenduanbingliService zhenduanbingliService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ZhenduanbingliEntity zhenduanbingli,
                                                                                                                                                    HttpServletRequest request){
                    String tableName = request.getSession().getAttribute("tableName").toString();
                                                                        if(tableName.equals("yisheng")) {
                    zhenduanbingli.setYishengzhanghao((String)request.getSession().getAttribute("username"));
                                    }
                                                                                                                                                                    if(tableName.equals("yonghu")) {
                    zhenduanbingli.setZhanghao((String)request.getSession().getAttribute("username"));
                                    }
                                                                                                                        EntityWrapper<ZhenduanbingliEntity> ew = new EntityWrapper<ZhenduanbingliEntity>();
                                                                                                                                                                                                                                
        
        
        PageUtils page = zhenduanbingliService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhenduanbingli), params), params));
        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ZhenduanbingliEntity zhenduanbingli,
		HttpServletRequest request){
        EntityWrapper<ZhenduanbingliEntity> ew = new EntityWrapper<ZhenduanbingliEntity>();

		PageUtils page = zhenduanbingliService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhenduanbingli), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ZhenduanbingliEntity zhenduanbingli){
       	EntityWrapper<ZhenduanbingliEntity> ew = new EntityWrapper<ZhenduanbingliEntity>();
      	ew.allEq(MPUtil.allEQMapPre( zhenduanbingli, "zhenduanbingli")); 
        return R.ok().put("data", zhenduanbingliService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ZhenduanbingliEntity zhenduanbingli){
        EntityWrapper< ZhenduanbingliEntity> ew = new EntityWrapper< ZhenduanbingliEntity>();
 		ew.allEq(MPUtil.allEQMapPre( zhenduanbingli, "zhenduanbingli")); 
		ZhenduanbingliView zhenduanbingliView =  zhenduanbingliService.selectView(ew);
		return R.ok("查询诊断病历成功").put("data", zhenduanbingliView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ZhenduanbingliEntity zhenduanbingli = zhenduanbingliService.selectById(id);
		zhenduanbingli = zhenduanbingliService.selectView(new EntityWrapper<ZhenduanbingliEntity>().eq("id", id));
        return R.ok().put("data", zhenduanbingli);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ZhenduanbingliEntity zhenduanbingli = zhenduanbingliService.selectById(id);
		zhenduanbingli = zhenduanbingliService.selectView(new EntityWrapper<ZhenduanbingliEntity>().eq("id", id));
        return R.ok().put("data", zhenduanbingli);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增诊断病历")
    public R save(@RequestBody ZhenduanbingliEntity zhenduanbingli, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(zhenduanbingli);
        zhenduanbingliService.insert(zhenduanbingli);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增诊断病历")
    @RequestMapping("/add")
    public R add(@RequestBody ZhenduanbingliEntity zhenduanbingli, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(zhenduanbingli);
        zhenduanbingliService.insert(zhenduanbingli);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改诊断病历")
    public R update(@RequestBody ZhenduanbingliEntity zhenduanbingli, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zhenduanbingli);
        zhenduanbingliService.updateById(zhenduanbingli);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除诊断病历")
    public R delete(@RequestBody Long[] ids){
        zhenduanbingliService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
