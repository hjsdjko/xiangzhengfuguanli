package com.entity.view;

import com.entity.ZiliaoshangchuanEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 资料上传
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("ziliaoshangchuan")
public class ZiliaoshangchuanView extends ZiliaoshangchuanEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 资料类型的值
		*/
		private String ziliaoshangchuanValue;
		/**
		* 审核状态的值
		*/
		private String ziliaoshangchuanYesnoValue;



	public ZiliaoshangchuanView() {

	}

	public ZiliaoshangchuanView(ZiliaoshangchuanEntity ziliaoshangchuanEntity) {
		try {
			BeanUtils.copyProperties(this, ziliaoshangchuanEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 资料类型的值
			*/
			public String getZiliaoshangchuanValue() {
				return ziliaoshangchuanValue;
			}
			/**
			* 设置： 资料类型的值
			*/
			public void setZiliaoshangchuanValue(String ziliaoshangchuanValue) {
				this.ziliaoshangchuanValue = ziliaoshangchuanValue;
			}
			/**
			* 获取： 审核状态的值
			*/
			public String getZiliaoshangchuanYesnoValue() {
				return ziliaoshangchuanYesnoValue;
			}
			/**
			* 设置： 审核状态的值
			*/
			public void setZiliaoshangchuanYesnoValue(String ziliaoshangchuanYesnoValue) {
				this.ziliaoshangchuanYesnoValue = ziliaoshangchuanYesnoValue;
			}












}
