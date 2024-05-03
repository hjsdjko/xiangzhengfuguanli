package com.entity.model;

import com.entity.ZiliaoshangchuanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 资料上传
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class ZiliaoshangchuanModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 资料名称
     */
    private String ziliaoshangchuanName;


    /**
     * 资料类型
     */
    private Integer ziliaoshangchuanTypes;


    /**
     * 资料
     */
    private String ziliaoshangchuanFile;


    /**
     * 添加时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 资料详情
     */
    private String ziliaoshangchuanContent;


    /**
     * 审核状态
     */
    private Integer ziliaoshangchuanYesnoTypes;


    /**
     * 审核结果
     */
    private String ziliaoshangchuanYesnoText;


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
	 * 获取：资料名称
	 */
    public String getZiliaoshangchuanName() {
        return ziliaoshangchuanName;
    }


    /**
	 * 设置：资料名称
	 */
    public void setZiliaoshangchuanName(String ziliaoshangchuanName) {
        this.ziliaoshangchuanName = ziliaoshangchuanName;
    }
    /**
	 * 获取：资料类型
	 */
    public Integer getZiliaoshangchuanTypes() {
        return ziliaoshangchuanTypes;
    }


    /**
	 * 设置：资料类型
	 */
    public void setZiliaoshangchuanTypes(Integer ziliaoshangchuanTypes) {
        this.ziliaoshangchuanTypes = ziliaoshangchuanTypes;
    }
    /**
	 * 获取：资料
	 */
    public String getZiliaoshangchuanFile() {
        return ziliaoshangchuanFile;
    }


    /**
	 * 设置：资料
	 */
    public void setZiliaoshangchuanFile(String ziliaoshangchuanFile) {
        this.ziliaoshangchuanFile = ziliaoshangchuanFile;
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
	 * 获取：资料详情
	 */
    public String getZiliaoshangchuanContent() {
        return ziliaoshangchuanContent;
    }


    /**
	 * 设置：资料详情
	 */
    public void setZiliaoshangchuanContent(String ziliaoshangchuanContent) {
        this.ziliaoshangchuanContent = ziliaoshangchuanContent;
    }
    /**
	 * 获取：审核状态
	 */
    public Integer getZiliaoshangchuanYesnoTypes() {
        return ziliaoshangchuanYesnoTypes;
    }


    /**
	 * 设置：审核状态
	 */
    public void setZiliaoshangchuanYesnoTypes(Integer ziliaoshangchuanYesnoTypes) {
        this.ziliaoshangchuanYesnoTypes = ziliaoshangchuanYesnoTypes;
    }
    /**
	 * 获取：审核结果
	 */
    public String getZiliaoshangchuanYesnoText() {
        return ziliaoshangchuanYesnoText;
    }


    /**
	 * 设置：审核结果
	 */
    public void setZiliaoshangchuanYesnoText(String ziliaoshangchuanYesnoText) {
        this.ziliaoshangchuanYesnoText = ziliaoshangchuanYesnoText;
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
