package com.ncvt.speed.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("tb_account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountPojo {

    @TableId
    private Long userId;
    private String userName;
    private String password;

}
