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

import com.cl.entity.RukujiluEntity;
import com.cl.entity.view.RukujiluView;

import com.cl.service.RukujiluService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;

/**
 * 入库记录
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-27 15:44:15
 */
@RestController
@RequestMapping("/rukujilu")
public class RukujiluController {
    @Autowired
    private RukujiluService rukujiluService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,RukujiluEntity rukujilu,
                                                                                            HttpServletRequest request){
                                    EntityWrapper<RukujiluEntity> ew = new EntityWrapper<RukujiluEntity>();
                                                                                                                                        
        
        
        PageUtils page = rukujiluService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, rukujilu), params), params));
        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,RukujiluEntity rukujilu,
		HttpServletRequest request){
        EntityWrapper<RukujiluEntity> ew = new EntityWrapper<RukujiluEntity>();

		PageUtils page = rukujiluService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, rukujilu), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( RukujiluEntity rukujilu){
       	EntityWrapper<RukujiluEntity> ew = new EntityWrapper<RukujiluEntity>();
      	ew.allEq(MPUtil.allEQMapPre( rukujilu, "rukujilu")); 
        return R.ok().put("data", rukujiluService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(RukujiluEntity rukujilu){
        EntityWrapper< RukujiluEntity> ew = new EntityWrapper< RukujiluEntity>();
 		ew.allEq(MPUtil.allEQMapPre( rukujilu, "rukujilu")); 
		RukujiluView rukujiluView =  rukujiluService.selectView(ew);
		return R.ok("查询入库记录成功").put("data", rukujiluView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        RukujiluEntity rukujilu = rukujiluService.selectById(id);
		rukujilu = rukujiluService.selectView(new EntityWrapper<RukujiluEntity>().eq("id", id));
        return R.ok().put("data", rukujilu);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        RukujiluEntity rukujilu = rukujiluService.selectById(id);
		rukujilu = rukujiluService.selectView(new EntityWrapper<RukujiluEntity>().eq("id", id));
        return R.ok().put("data", rukujilu);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增入库记录")
    public R save(@RequestBody RukujiluEntity rukujilu, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(rukujilu);
        rukujiluService.insert(rukujilu);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增入库记录")
    @RequestMapping("/add")
    public R add(@RequestBody RukujiluEntity rukujilu, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(rukujilu);
        rukujiluService.insert(rukujilu);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改入库记录")
    public R update(@RequestBody RukujiluEntity rukujilu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(rukujilu);
        rukujiluService.updateById(rukujilu);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除入库记录")
    public R delete(@RequestBody Long[] ids){
        rukujiluService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
