package com.hssh.spring.cloud.persistent.entity.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

/**
 * Created by hssh on 2017/2/25.
 */
@Data
public class UserDto {

    public Integer id;

    @Length(max = 10, message = "姓名长度最大20")
    public String userName;

    public Integer age;

    @Length(max = 20, message = "电话号码长度最大为20")
    public String phone;

    @Range(min=1, max=2, message = "性别不能为人妖")
    public Integer sex;

}
