
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 资料上传
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/ziliaoshangchuan")
public class ZiliaoshangchuanController {
    private static final Logger logger = LoggerFactory.getLogger(ZiliaoshangchuanController.class);

    @Autowired
    private ZiliaoshangchuanService ziliaoshangchuanService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service

    @Autowired
    private YonghuService yonghuService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = ziliaoshangchuanService.queryPage(params);

        //字典表数据转换
        List<ZiliaoshangchuanView> list =(List<ZiliaoshangchuanView>)page.getList();
        for(ZiliaoshangchuanView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ZiliaoshangchuanEntity ziliaoshangchuan = ziliaoshangchuanService.selectById(id);
        if(ziliaoshangchuan !=null){
            //entity转view
            ZiliaoshangchuanView view = new ZiliaoshangchuanView();
            BeanUtils.copyProperties( ziliaoshangchuan , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody ZiliaoshangchuanEntity ziliaoshangchuan, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,ziliaoshangchuan:{}",this.getClass().getName(),ziliaoshangchuan.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<ZiliaoshangchuanEntity> queryWrapper = new EntityWrapper<ZiliaoshangchuanEntity>()
            .eq("ziliaoshangchuan_name", ziliaoshangchuan.getZiliaoshangchuanName())
            .eq("ziliaoshangchuan_types", ziliaoshangchuan.getZiliaoshangchuanTypes())
            .eq("ziliaoshangchuan_yesno_types", ziliaoshangchuan.getZiliaoshangchuanYesnoTypes())
            .eq("ziliaoshangchuan_yesno_text", ziliaoshangchuan.getZiliaoshangchuanYesnoText())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZiliaoshangchuanEntity ziliaoshangchuanEntity = ziliaoshangchuanService.selectOne(queryWrapper);
        if(ziliaoshangchuanEntity==null){
            ziliaoshangchuan.setInsertTime(new Date());
            ziliaoshangchuan.setZiliaoshangchuanYesnoTypes(1);
            ziliaoshangchuan.setCreateTime(new Date());
            ziliaoshangchuanService.insert(ziliaoshangchuan);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ZiliaoshangchuanEntity ziliaoshangchuan, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,ziliaoshangchuan:{}",this.getClass().getName(),ziliaoshangchuan.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<ZiliaoshangchuanEntity> queryWrapper = new EntityWrapper<ZiliaoshangchuanEntity>()
            .notIn("id",ziliaoshangchuan.getId())
            .andNew()
            .eq("ziliaoshangchuan_name", ziliaoshangchuan.getZiliaoshangchuanName())
            .eq("ziliaoshangchuan_types", ziliaoshangchuan.getZiliaoshangchuanTypes())
            .eq("insert_time", ziliaoshangchuan.getInsertTime())
            .eq("ziliaoshangchuan_yesno_types", ziliaoshangchuan.getZiliaoshangchuanYesnoTypes())
            .eq("ziliaoshangchuan_yesno_text", ziliaoshangchuan.getZiliaoshangchuanYesnoText())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZiliaoshangchuanEntity ziliaoshangchuanEntity = ziliaoshangchuanService.selectOne(queryWrapper);
        if("".equals(ziliaoshangchuan.getZiliaoshangchuanFile()) || "null".equals(ziliaoshangchuan.getZiliaoshangchuanFile())){
                ziliaoshangchuan.setZiliaoshangchuanFile(null);
        }
        if(ziliaoshangchuanEntity==null){
            ziliaoshangchuanService.updateById(ziliaoshangchuan);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


    /**
    * 审核
    */
    @RequestMapping("/shenhe")
    public R shenhe(@RequestBody ZiliaoshangchuanEntity ziliaoshangchuan, HttpServletRequest request){
        logger.debug("shenhe方法:,,Controller:{},,ziliaoshangchuan:{}",this.getClass().getName(),ziliaoshangchuan.toString());

//        if(ziliaoshangchuan.getZiliaoshangchuanYesnoTypes() == 2){//通过
//            ziliaoshangchuan.setZiliaoshangchuanTypes();
//        }else if(ziliaoshangchuan.getZiliaoshangchuanYesnoTypes() == 3){//拒绝
//            ziliaoshangchuan.setZiliaoshangchuanTypes();
//        }
        ziliaoshangchuanService.updateById(ziliaoshangchuan);//审核
        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        ziliaoshangchuanService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<ZiliaoshangchuanEntity> ziliaoshangchuanList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            ZiliaoshangchuanEntity ziliaoshangchuanEntity = new ZiliaoshangchuanEntity();
//                            ziliaoshangchuanEntity.setZiliaoshangchuanName(data.get(0));                    //资料名称 要改的
//                            ziliaoshangchuanEntity.setZiliaoshangchuanTypes(Integer.valueOf(data.get(0)));   //资料类型 要改的
//                            ziliaoshangchuanEntity.setZiliaoshangchuanFile(data.get(0));                    //资料 要改的
//                            ziliaoshangchuanEntity.setInsertTime(date);//时间
//                            ziliaoshangchuanEntity.setZiliaoshangchuanContent("");//详情和图片
//                            ziliaoshangchuanEntity.setZiliaoshangchuanYesnoTypes(Integer.valueOf(data.get(0)));   //审核状态 要改的
//                            ziliaoshangchuanEntity.setZiliaoshangchuanYesnoText(data.get(0));                    //审核结果 要改的
//                            ziliaoshangchuanEntity.setCreateTime(date);//时间
                            ziliaoshangchuanList.add(ziliaoshangchuanEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        ziliaoshangchuanService.insertBatch(ziliaoshangchuanList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }





    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        // 没有指定排序字段就默认id倒序
        if(StringUtil.isEmpty(String.valueOf(params.get("orderBy")))){
            params.put("orderBy","id");
        }
        PageUtils page = ziliaoshangchuanService.queryPage(params);

        //字典表数据转换
        List<ZiliaoshangchuanView> list =(List<ZiliaoshangchuanView>)page.getList();
        for(ZiliaoshangchuanView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ZiliaoshangchuanEntity ziliaoshangchuan = ziliaoshangchuanService.selectById(id);
            if(ziliaoshangchuan !=null){


                //entity转view
                ZiliaoshangchuanView view = new ZiliaoshangchuanView();
                BeanUtils.copyProperties( ziliaoshangchuan , view );//把实体数据重构到view中

                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody ZiliaoshangchuanEntity ziliaoshangchuan, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,ziliaoshangchuan:{}",this.getClass().getName(),ziliaoshangchuan.toString());
        Wrapper<ZiliaoshangchuanEntity> queryWrapper = new EntityWrapper<ZiliaoshangchuanEntity>()
            .eq("ziliaoshangchuan_name", ziliaoshangchuan.getZiliaoshangchuanName())
            .eq("ziliaoshangchuan_types", ziliaoshangchuan.getZiliaoshangchuanTypes())
            .eq("ziliaoshangchuan_yesno_types", ziliaoshangchuan.getZiliaoshangchuanYesnoTypes())
            .eq("ziliaoshangchuan_yesno_text", ziliaoshangchuan.getZiliaoshangchuanYesnoText())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZiliaoshangchuanEntity ziliaoshangchuanEntity = ziliaoshangchuanService.selectOne(queryWrapper);
        if(ziliaoshangchuanEntity==null){
            ziliaoshangchuan.setInsertTime(new Date());
            ziliaoshangchuan.setZiliaoshangchuanYesnoTypes(1);
            ziliaoshangchuan.setCreateTime(new Date());
        ziliaoshangchuanService.insert(ziliaoshangchuan);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


}
