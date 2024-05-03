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
 * 活动信息
 *
 * @author 
 * @email
 */
@TableName("huodong")
public class HuodongEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public HuodongEntity() {

	}

	public HuodongEntity(T t) {
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
     * 活动标题
     */
    @TableField(value = "huodong_name")

    private String huodongName;


    /**
     * 活动类型
     */
    @TableField(value = "huodong_types")

    private Integer huodongTypes;


    /**
     * 活动封面
     */
    @TableField(value = "huodong_photo")

    private String huodongPhoto;


    /**
     * 活动时间
     */
    @TableField(value = "huodong_date")

    private String huodongDate;


    /**
     * 活动详情
     */
    @TableField(value = "huodong_content")

    private String huodongContent;


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
	 * 设置：活动标题
	 */
    public String getHuodongName() {
        return huodongName;
    }
    /**
	 * 获取：活动标题
	 */

    public void setHuodongName(String huodongName) {
        this.huodongName = huodongName;
    }
    /**
	 * 设置：活动类型
	 */
    public Integer getHuodongTypes() {
        return huodongTypes;
    }
    /**
	 * 获取：活动类型
	 */

    public void setHuodongTypes(Integer huodongTypes) {
        this.huodongTypes = huodongTypes;
    }
    /**
	 * 设置：活动封面
	 */
    public String getHuodongPhoto() {
        return huodongPhoto;
    }
    /**
	 * 获取：活动封面
	 */

    public void setHuodongPhoto(String huodongPhoto) {
        this.huodongPhoto = huodongPhoto;
    }
    /**
	 * 设置：活动时间
	 */
    public String getHuodongDate() {
        return huodongDate;
    }
    /**
	 * 获取：活动时间
	 */

    public void setHuodongDate(String huodongDate) {
        this.huodongDate = huodongDate;
    }
    /**
	 * 设置：活动详情
	 */
    public String getHuodongContent() {
        return huodongContent;
    }
    /**
	 * 获取：活动详情
	 */

    public void setHuodongContent(String huodongContent) {
        this.huodongContent = huodongContent;
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
        return "Huodong{" +
            "id=" + id +
            ", huodongName=" + huodongName +
            ", huodongTypes=" + huodongTypes +
            ", huodongPhoto=" + huodongPhoto +
            ", huodongDate=" + huodongDate +
            ", huodongContent=" + huodongContent +
            ", createTime=" + createTime +
        "}";
    }
}
