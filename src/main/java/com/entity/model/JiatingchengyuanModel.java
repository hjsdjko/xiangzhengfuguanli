package com.entity.model;

import com.entity.JiatingchengyuanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 家庭成员流动
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class JiatingchengyuanModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 外出人员名称
     */
    private String jiatingchengyuanName;


    /**
     * 外出人员联系方式
     */
    private String jiatingchengyuanPhone;


    /**
     * 外出地址
     */
    private String jiatingchengyuanAddress;


    /**
     * 外出时间
     */
    private String jiatingchengyuanDate;


    /**
     * 外出详情
     */
    private String jiatingchengyuanText;


    /**
     * 添加时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：用户
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：外出人员名称
	 */
    public String getJiatingchengyuanName() {
        return jiatingchengyuanName;
    }


    /**
	 * 设置：外出人员名称
	 */
    public void setJiatingchengyuanName(String jiatingchengyuanName) {
        this.jiatingchengyuanName = jiatingchengyuanName;
    }
    /**
	 * 获取：外出人员联系方式
	 */
    public String getJiatingchengyuanPhone() {
        return jiatingchengyuanPhone;
    }


    /**
	 * 设置：外出人员联系方式
	 */
    public void setJiatingchengyuanPhone(String jiatingchengyuanPhone) {
        this.jiatingchengyuanPhone = jiatingchengyuanPhone;
    }
    /**
	 * 获取：外出地址
	 */
    public String getJiatingchengyuanAddress() {
        return jiatingchengyuanAddress;
    }


    /**
	 * 设置：外出地址
	 */
    public void setJiatingchengyuanAddress(String jiatingchengyuanAddress) {
        this.jiatingchengyuanAddress = jiatingchengyuanAddress;
    }
    /**
	 * 获取：外出时间
	 */
    public String getJiatingchengyuanDate() {
        return jiatingchengyuanDate;
    }


    /**
	 * 设置：外出时间
	 */
    public void setJiatingchengyuanDate(String jiatingchengyuanDate) {
        this.jiatingchengyuanDate = jiatingchengyuanDate;
    }
    /**
	 * 获取：外出详情
	 */
    public String getJiatingchengyuanText() {
        return jiatingchengyuanText;
    }


    /**
	 * 设置：外出详情
	 */
    public void setJiatingchengyuanText(String jiatingchengyuanText) {
        this.jiatingchengyuanText = jiatingchengyuanText;
    }
    /**
	 * 获取：添加时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：添加时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
