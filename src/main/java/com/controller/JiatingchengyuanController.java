
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
 * 家庭成员流动
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/jiatingchengyuan")
public class JiatingchengyuanController {
    private static final Logger logger = LoggerFactory.getLogger(JiatingchengyuanController.class);

    @Autowired
    private JiatingchengyuanService jiatingchengyuanService;


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
        PageUtils page = jiatingchengyuanService.queryPage(params);

        //字典表数据转换
        List<JiatingchengyuanView> list =(List<JiatingchengyuanView>)page.getList();
        for(JiatingchengyuanView c:list){
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
        JiatingchengyuanEntity jiatingchengyuan = jiatingchengyuanService.selectById(id);
        if(jiatingchengyuan !=null){
            //entity转view
            JiatingchengyuanView view = new JiatingchengyuanView();
            BeanUtils.copyProperties( jiatingchengyuan , view );//把实体数据重构到view中

                //级联表
                YonghuEntity yonghu = yonghuService.selectById(jiatingchengyuan.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
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
    public R save(@RequestBody JiatingchengyuanEntity jiatingchengyuan, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,jiatingchengyuan:{}",this.getClass().getName(),jiatingchengyuan.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            jiatingchengyuan.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<JiatingchengyuanEntity> queryWrapper = new EntityWrapper<JiatingchengyuanEntity>()
            .eq("yonghu_id", jiatingchengyuan.getYonghuId())
            .eq("jiatingchengyuan_name", jiatingchengyuan.getJiatingchengyuanName())
            .eq("jiatingchengyuan_phone", jiatingchengyuan.getJiatingchengyuanPhone())
            .eq("jiatingchengyuan_address", jiatingchengyuan.getJiatingchengyuanAddress())
            .eq("jiatingchengyuan_date", jiatingchengyuan.getJiatingchengyuanDate())
            .eq("jiatingchengyuan_text", jiatingchengyuan.getJiatingchengyuanText())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JiatingchengyuanEntity jiatingchengyuanEntity = jiatingchengyuanService.selectOne(queryWrapper);
        if(jiatingchengyuanEntity==null){
            jiatingchengyuan.setInsertTime(new Date());
            jiatingchengyuan.setCreateTime(new Date());
            jiatingchengyuanService.insert(jiatingchengyuan);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody JiatingchengyuanEntity jiatingchengyuan, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,jiatingchengyuan:{}",this.getClass().getName(),jiatingchengyuan.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            jiatingchengyuan.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<JiatingchengyuanEntity> queryWrapper = new EntityWrapper<JiatingchengyuanEntity>()
            .notIn("id",jiatingchengyuan.getId())
            .andNew()
            .eq("yonghu_id", jiatingchengyuan.getYonghuId())
            .eq("jiatingchengyuan_name", jiatingchengyuan.getJiatingchengyuanName())
            .eq("jiatingchengyuan_phone", jiatingchengyuan.getJiatingchengyuanPhone())
            .eq("jiatingchengyuan_address", jiatingchengyuan.getJiatingchengyuanAddress())
            .eq("jiatingchengyuan_date", jiatingchengyuan.getJiatingchengyuanDate())
            .eq("jiatingchengyuan_text", jiatingchengyuan.getJiatingchengyuanText())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JiatingchengyuanEntity jiatingchengyuanEntity = jiatingchengyuanService.selectOne(queryWrapper);
        if(jiatingchengyuanEntity==null){
            jiatingchengyuanService.updateById(jiatingchengyuan);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        jiatingchengyuanService.deleteBatchIds(Arrays.asList(ids));
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
            List<JiatingchengyuanEntity> jiatingchengyuanList = new ArrayList<>();//上传的东西
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
                            JiatingchengyuanEntity jiatingchengyuanEntity = new JiatingchengyuanEntity();
//                            jiatingchengyuanEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            jiatingchengyuanEntity.setJiatingchengyuanName(data.get(0));                    //外出人员名称 要改的
//                            jiatingchengyuanEntity.setJiatingchengyuanPhone(data.get(0));                    //外出人员联系方式 要改的
//                            jiatingchengyuanEntity.setJiatingchengyuanAddress(data.get(0));                    //外出地址 要改的
//                            jiatingchengyuanEntity.setJiatingchengyuanDate(data.get(0));                    //外出时间 要改的
//                            jiatingchengyuanEntity.setJiatingchengyuanText(data.get(0));                    //外出详情 要改的
//                            jiatingchengyuanEntity.setInsertTime(date);//时间
//                            jiatingchengyuanEntity.setCreateTime(date);//时间
                            jiatingchengyuanList.add(jiatingchengyuanEntity);


                            //把要查询是否重复的字段放入map中
                                //外出人员联系方式
                                if(seachFields.containsKey("jiatingchengyuanPhone")){
                                    List<String> jiatingchengyuanPhone = seachFields.get("jiatingchengyuanPhone");
                                    jiatingchengyuanPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> jiatingchengyuanPhone = new ArrayList<>();
                                    jiatingchengyuanPhone.add(data.get(0));//要改的
                                    seachFields.put("jiatingchengyuanPhone",jiatingchengyuanPhone);
                                }
                        }

                        //查询是否重复
                         //外出人员联系方式
                        List<JiatingchengyuanEntity> jiatingchengyuanEntities_jiatingchengyuanPhone = jiatingchengyuanService.selectList(new EntityWrapper<JiatingchengyuanEntity>().in("jiatingchengyuan_phone", seachFields.get("jiatingchengyuanPhone")));
                        if(jiatingchengyuanEntities_jiatingchengyuanPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(JiatingchengyuanEntity s:jiatingchengyuanEntities_jiatingchengyuanPhone){
                                repeatFields.add(s.getJiatingchengyuanPhone());
                            }
                            return R.error(511,"数据库的该表中的 [外出人员联系方式] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        jiatingchengyuanService.insertBatch(jiatingchengyuanList);
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
        PageUtils page = jiatingchengyuanService.queryPage(params);

        //字典表数据转换
        List<JiatingchengyuanView> list =(List<JiatingchengyuanView>)page.getList();
        for(JiatingchengyuanView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        JiatingchengyuanEntity jiatingchengyuan = jiatingchengyuanService.selectById(id);
            if(jiatingchengyuan !=null){


                //entity转view
                JiatingchengyuanView view = new JiatingchengyuanView();
                BeanUtils.copyProperties( jiatingchengyuan , view );//把实体数据重构到view中

                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(jiatingchengyuan.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
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
    public R add(@RequestBody JiatingchengyuanEntity jiatingchengyuan, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,jiatingchengyuan:{}",this.getClass().getName(),jiatingchengyuan.toString());
        Wrapper<JiatingchengyuanEntity> queryWrapper = new EntityWrapper<JiatingchengyuanEntity>()
            .eq("yonghu_id", jiatingchengyuan.getYonghuId())
            .eq("jiatingchengyuan_name", jiatingchengyuan.getJiatingchengyuanName())
            .eq("jiatingchengyuan_phone", jiatingchengyuan.getJiatingchengyuanPhone())
            .eq("jiatingchengyuan_address", jiatingchengyuan.getJiatingchengyuanAddress())
            .eq("jiatingchengyuan_date", jiatingchengyuan.getJiatingchengyuanDate())
            .eq("jiatingchengyuan_text", jiatingchengyuan.getJiatingchengyuanText())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JiatingchengyuanEntity jiatingchengyuanEntity = jiatingchengyuanService.selectOne(queryWrapper);
        if(jiatingchengyuanEntity==null){
            jiatingchengyuan.setInsertTime(new Date());
            jiatingchengyuan.setCreateTime(new Date());
        jiatingchengyuanService.insert(jiatingchengyuan);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


}
