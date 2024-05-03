package com.dao;

import com.entity.JiatingchengyuanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.JiatingchengyuanView;

/**
 * 家庭成员流动 Dao 接口
 *
 * @author 
 */
public interface JiatingchengyuanDao extends BaseMapper<JiatingchengyuanEntity> {

   List<JiatingchengyuanView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
