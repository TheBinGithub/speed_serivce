package com.ncvt.speed.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("tb_account")
@Data
public class AccountPojo {

    @TableId
    private Long userId;
    private String userName;
    private String password;

}
