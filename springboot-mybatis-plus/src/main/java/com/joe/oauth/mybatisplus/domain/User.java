package com.joe.oauth.mybatisplus.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper=true)
@Accessors(chain = true)
@TableName(value = "t_user")
public class User extends Model<User> {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String name;
    private Integer age;
    private String email;
    @TableField(value = "last_name")
    private String lastName;
    @TableField(value="data_time")
    private Timestamp dataTime;

    @Override
    protected Serializable pkVal() {
        /**
         * AR 模式这个必须有，否则 xxById 的方法都将失效！
         * 另外 xxxMapper 也必须 AR 依赖该层注入，可有可无 XML
         */
        return this.id;
    }
}