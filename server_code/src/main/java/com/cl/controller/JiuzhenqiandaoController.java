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

import com.cl.entity.JiuzhenqiandaoEntity;
import com.cl.entity.view.JiuzhenqiandaoView;

import com.cl.service.JiuzhenqiandaoService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;

/**
 * 就诊签到
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-27 15:44:15
 */
@RestController
@RequestMapping("/jiuzhenqiandao")
public class JiuzhenqiandaoController {
    @Autowired
    private JiuzhenqiandaoService jiuzhenqiandaoService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JiuzhenqiandaoEntity jiuzhenqiandao,
                                                                                                                                        HttpServletRequest request){
                    String tableName = request.getSession().getAttribute("tableName").toString();
                                                                        if(tableName.equals("yisheng")) {
                    jiuzhenqiandao.setYishengzhanghao((String)request.getSession().getAttribute("username"));
                                    }
                                                                                                                                                                    if(tableName.equals("yonghu")) {
                    jiuzhenqiandao.setZhanghao((String)request.getSession().getAttribute("username"));
                                    }
                                                                                            EntityWrapper<JiuzhenqiandaoEntity> ew = new EntityWrapper<JiuzhenqiandaoEntity>();
                                                                                                                                                                                                            
        
        
        PageUtils page = jiuzhenqiandaoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiuzhenqiandao), params), params));
        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,JiuzhenqiandaoEntity jiuzhenqiandao,
		HttpServletRequest request){
        EntityWrapper<JiuzhenqiandaoEntity> ew = new EntityWrapper<JiuzhenqiandaoEntity>();

		PageUtils page = jiuzhenqiandaoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiuzhenqiandao), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JiuzhenqiandaoEntity jiuzhenqiandao){
       	EntityWrapper<JiuzhenqiandaoEntity> ew = new EntityWrapper<JiuzhenqiandaoEntity>();
      	ew.allEq(MPUtil.allEQMapPre( jiuzhenqiandao, "jiuzhenqiandao")); 
        return R.ok().put("data", jiuzhenqiandaoService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JiuzhenqiandaoEntity jiuzhenqiandao){
        EntityWrapper< JiuzhenqiandaoEntity> ew = new EntityWrapper< JiuzhenqiandaoEntity>();
 		ew.allEq(MPUtil.allEQMapPre( jiuzhenqiandao, "jiuzhenqiandao")); 
		JiuzhenqiandaoView jiuzhenqiandaoView =  jiuzhenqiandaoService.selectView(ew);
		return R.ok("查询就诊签到成功").put("data", jiuzhenqiandaoView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JiuzhenqiandaoEntity jiuzhenqiandao = jiuzhenqiandaoService.selectById(id);
		jiuzhenqiandao = jiuzhenqiandaoService.selectView(new EntityWrapper<JiuzhenqiandaoEntity>().eq("id", id));
        return R.ok().put("data", jiuzhenqiandao);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JiuzhenqiandaoEntity jiuzhenqiandao = jiuzhenqiandaoService.selectById(id);
		jiuzhenqiandao = jiuzhenqiandaoService.selectView(new EntityWrapper<JiuzhenqiandaoEntity>().eq("id", id));
        return R.ok().put("data", jiuzhenqiandao);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增就诊签到")
    public R save(@RequestBody JiuzhenqiandaoEntity jiuzhenqiandao, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(jiuzhenqiandao);
        jiuzhenqiandaoService.insert(jiuzhenqiandao);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增就诊签到")
    @RequestMapping("/add")
    public R add(@RequestBody JiuzhenqiandaoEntity jiuzhenqiandao, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(jiuzhenqiandao);
        jiuzhenqiandaoService.insert(jiuzhenqiandao);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改就诊签到")
    public R update(@RequestBody JiuzhenqiandaoEntity jiuzhenqiandao, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jiuzhenqiandao);
        jiuzhenqiandaoService.updateById(jiuzhenqiandao);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除就诊签到")
    public R delete(@RequestBody Long[] ids){
        jiuzhenqiandaoService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
