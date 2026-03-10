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

import com.cl.entity.ChukujiluEntity;
import com.cl.entity.view.ChukujiluView;

import com.cl.service.ChukujiluService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;

/**
 * 出库记录
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-27 15:44:15
 */
@RestController
@RequestMapping("/chukujilu")
public class ChukujiluController {
    @Autowired
    private ChukujiluService chukujiluService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ChukujiluEntity chukujilu,
                                                                                HttpServletRequest request){
                                    EntityWrapper<ChukujiluEntity> ew = new EntityWrapper<ChukujiluEntity>();
                                                                                                                    
        
        
        PageUtils page = chukujiluService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chukujilu), params), params));
        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ChukujiluEntity chukujilu,
		HttpServletRequest request){
        EntityWrapper<ChukujiluEntity> ew = new EntityWrapper<ChukujiluEntity>();

		PageUtils page = chukujiluService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chukujilu), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ChukujiluEntity chukujilu){
       	EntityWrapper<ChukujiluEntity> ew = new EntityWrapper<ChukujiluEntity>();
      	ew.allEq(MPUtil.allEQMapPre( chukujilu, "chukujilu")); 
        return R.ok().put("data", chukujiluService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ChukujiluEntity chukujilu){
        EntityWrapper< ChukujiluEntity> ew = new EntityWrapper< ChukujiluEntity>();
 		ew.allEq(MPUtil.allEQMapPre( chukujilu, "chukujilu")); 
		ChukujiluView chukujiluView =  chukujiluService.selectView(ew);
		return R.ok("查询出库记录成功").put("data", chukujiluView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ChukujiluEntity chukujilu = chukujiluService.selectById(id);
		chukujilu = chukujiluService.selectView(new EntityWrapper<ChukujiluEntity>().eq("id", id));
        return R.ok().put("data", chukujilu);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ChukujiluEntity chukujilu = chukujiluService.selectById(id);
		chukujilu = chukujiluService.selectView(new EntityWrapper<ChukujiluEntity>().eq("id", id));
        return R.ok().put("data", chukujilu);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增出库记录")
    public R save(@RequestBody ChukujiluEntity chukujilu, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(chukujilu);
        chukujiluService.insert(chukujilu);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增出库记录")
    @RequestMapping("/add")
    public R add(@RequestBody ChukujiluEntity chukujilu, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(chukujilu);
        chukujiluService.insert(chukujilu);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改出库记录")
    public R update(@RequestBody ChukujiluEntity chukujilu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(chukujilu);
        chukujiluService.updateById(chukujilu);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除出库记录")
    public R delete(@RequestBody Long[] ids){
        chukujiluService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
