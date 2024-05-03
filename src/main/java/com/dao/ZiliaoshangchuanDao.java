package com.dao;

import com.entity.ZiliaoshangchuanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ZiliaoshangchuanView;

/**
 * 资料上传 Dao 接口
 *
 * @author 
 */
public interface ZiliaoshangchuanDao extends BaseMapper<ZiliaoshangchuanEntity> {

   List<ZiliaoshangchuanView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
