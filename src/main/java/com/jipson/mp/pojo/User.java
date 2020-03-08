package com.jipson.mp.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_user")
public class User {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String userName;
    //设置TableField为false，保护密码，不被查询出来
    @TableField(select = false)
    private String password;
    private String name;
    private Integer age;
    @TableField(value = "email")//当属性名和数据库字段名不一样时，指定数据库字段名
    private String mail;

    //数据库不存在字段名
    @TableField(exist = false)
    private String address;

}
