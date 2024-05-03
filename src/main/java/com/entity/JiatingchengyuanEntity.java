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
 * 家庭成员流动
 *
 * @author 
 * @email
 */
@TableName("jiatingchengyuan")
public class JiatingchengyuanEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public JiatingchengyuanEntity() {

	}

	public JiatingchengyuanEntity(T t) {
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
     * 用户
     */
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 外出人员名称
     */
    @TableField(value = "jiatingchengyuan_name")

    private String jiatingchengyuanName;


    /**
     * 外出人员联系方式
     */
    @TableField(value = "jiatingchengyuan_phone")

    private String jiatingchengyuanPhone;


    /**
     * 外出地址
     */
    @TableField(value = "jiatingchengyuan_address")

    private String jiatingchengyuanAddress;


    /**
     * 外出时间
     */
    @TableField(value = "jiatingchengyuan_date")

    private String jiatingchengyuanDate;


    /**
     * 外出详情
     */
    @TableField(value = "jiatingchengyuan_text")

    private String jiatingchengyuanText;


    /**
     * 添加时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


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
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }
    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：外出人员名称
	 */
    public String getJiatingchengyuanName() {
        return jiatingchengyuanName;
    }
    /**
	 * 获取：外出人员名称
	 */

    public void setJiatingchengyuanName(String jiatingchengyuanName) {
        this.jiatingchengyuanName = jiatingchengyuanName;
    }
    /**
	 * 设置：外出人员联系方式
	 */
    public String getJiatingchengyuanPhone() {
        return jiatingchengyuanPhone;
    }
    /**
	 * 获取：外出人员联系方式
	 */

    public void setJiatingchengyuanPhone(String jiatingchengyuanPhone) {
        this.jiatingchengyuanPhone = jiatingchengyuanPhone;
    }
    /**
	 * 设置：外出地址
	 */
    public String getJiatingchengyuanAddress() {
        return jiatingchengyuanAddress;
    }
    /**
	 * 获取：外出地址
	 */

    public void setJiatingchengyuanAddress(String jiatingchengyuanAddress) {
        this.jiatingchengyuanAddress = jiatingchengyuanAddress;
    }
    /**
	 * 设置：外出时间
	 */
    public String getJiatingchengyuanDate() {
        return jiatingchengyuanDate;
    }
    /**
	 * 获取：外出时间
	 */

    public void setJiatingchengyuanDate(String jiatingchengyuanDate) {
        this.jiatingchengyuanDate = jiatingchengyuanDate;
    }
    /**
	 * 设置：外出详情
	 */
    public String getJiatingchengyuanText() {
        return jiatingchengyuanText;
    }
    /**
	 * 获取：外出详情
	 */

    public void setJiatingchengyuanText(String jiatingchengyuanText) {
        this.jiatingchengyuanText = jiatingchengyuanText;
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
        return "Jiatingchengyuan{" +
            "id=" + id +
            ", yonghuId=" + yonghuId +
            ", jiatingchengyuanName=" + jiatingchengyuanName +
            ", jiatingchengyuanPhone=" + jiatingchengyuanPhone +
            ", jiatingchengyuanAddress=" + jiatingchengyuanAddress +
            ", jiatingchengyuanDate=" + jiatingchengyuanDate +
            ", jiatingchengyuanText=" + jiatingchengyuanText +
            ", insertTime=" + insertTime +
            ", createTime=" + createTime +
        "}";
    }
}
