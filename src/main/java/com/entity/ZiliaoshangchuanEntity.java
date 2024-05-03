package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 资料上传
 *
 * @author 
 * @email
 */
@TableName("ziliaoshangchuan")
public class ZiliaoshangchuanEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ZiliaoshangchuanEntity() {

	}

	public ZiliaoshangchuanEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 资料名称
     */
    @TableField(value = "ziliaoshangchuan_name")

    private String ziliaoshangchuanName;


    /**
     * 资料类型
     */
    @TableField(value = "ziliaoshangchuan_types")

    private Integer ziliaoshangchuanTypes;


    /**
     * 资料
     */
    @TableField(value = "ziliaoshangchuan_file")

    private String ziliaoshangchuanFile;


    /**
     * 添加时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 资料详情
     */
    @TableField(value = "ziliaoshangchuan_content")

    private String ziliaoshangchuanContent;


    /**
     * 审核状态
     */
    @TableField(value = "ziliaoshangchuan_yesno_types")

    private Integer ziliaoshangchuanYesnoTypes;


    /**
     * 审核结果
     */
    @TableField(value = "ziliaoshangchuan_yesno_text")

    private String ziliaoshangchuanYesnoText;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }
    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：资料名称
	 */
    public String getZiliaoshangchuanName() {
        return ziliaoshangchuanName;
    }
    /**
	 * 获取：资料名称
	 */

    public void setZiliaoshangchuanName(String ziliaoshangchuanName) {
        this.ziliaoshangchuanName = ziliaoshangchuanName;
    }
    /**
	 * 设置：资料类型
	 */
    public Integer getZiliaoshangchuanTypes() {
        return ziliaoshangchuanTypes;
    }
    /**
	 * 获取：资料类型
	 */

    public void setZiliaoshangchuanTypes(Integer ziliaoshangchuanTypes) {
        this.ziliaoshangchuanTypes = ziliaoshangchuanTypes;
    }
    /**
	 * 设置：资料
	 */
    public String getZiliaoshangchuanFile() {
        return ziliaoshangchuanFile;
    }
    /**
	 * 获取：资料
	 */

    public void setZiliaoshangchuanFile(String ziliaoshangchuanFile) {
        this.ziliaoshangchuanFile = ziliaoshangchuanFile;
    }
    /**
	 * 设置：添加时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 获取：添加时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：资料详情
	 */
    public String getZiliaoshangchuanContent() {
        return ziliaoshangchuanContent;
    }
    /**
	 * 获取：资料详情
	 */

    public void setZiliaoshangchuanContent(String ziliaoshangchuanContent) {
        this.ziliaoshangchuanContent = ziliaoshangchuanContent;
    }
    /**
	 * 设置：审核状态
	 */
    public Integer getZiliaoshangchuanYesnoTypes() {
        return ziliaoshangchuanYesnoTypes;
    }
    /**
	 * 获取：审核状态
	 */

    public void setZiliaoshangchuanYesnoTypes(Integer ziliaoshangchuanYesnoTypes) {
        this.ziliaoshangchuanYesnoTypes = ziliaoshangchuanYesnoTypes;
    }
    /**
	 * 设置：审核结果
	 */
    public String getZiliaoshangchuanYesnoText() {
        return ziliaoshangchuanYesnoText;
    }
    /**
	 * 获取：审核结果
	 */

    public void setZiliaoshangchuanYesnoText(String ziliaoshangchuanYesnoText) {
        this.ziliaoshangchuanYesnoText = ziliaoshangchuanYesnoText;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Ziliaoshangchuan{" +
            "id=" + id +
            ", ziliaoshangchuanName=" + ziliaoshangchuanName +
            ", ziliaoshangchuanTypes=" + ziliaoshangchuanTypes +
            ", ziliaoshangchuanFile=" + ziliaoshangchuanFile +
            ", insertTime=" + insertTime +
            ", ziliaoshangchuanContent=" + ziliaoshangchuanContent +
            ", ziliaoshangchuanYesnoTypes=" + ziliaoshangchuanYesnoTypes +
            ", ziliaoshangchuanYesnoText=" + ziliaoshangchuanYesnoText +
            ", createTime=" + createTime +
        "}";
    }
}
