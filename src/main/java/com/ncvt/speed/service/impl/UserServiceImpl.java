package com.ncvt.speed.service.impl;

import com.ncvt.speed.entity.UserEntity;
import com.ncvt.speed.mapper.UserMapper;
import com.ncvt.speed.service.UserService;
import com.ncvt.speed.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Result queryUser(String userId) {
        try {
            UserEntity user = userMapper.queryUser(userId);
            if (user.getUserId() == null) return Result.ok("无结果");
            return Result.ok("查询成功！", user);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！");
        }
    }

    @Override
    public Result addUser(UserEntity user) {
        try {
            int result = userMapper.addUser(user);
            if (result == 0) return Result.fail("新增过程出现未知异常！");
            return Result.ok("添加成功！");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！");
        }
    }

    @Override
    public Result deleteUser(String userId) {
        try {
            int result = userMapper.deleteUser(userId);
            if (result == 0) return Result.fail("删除过程出现未知异常！");
            return Result.ok("删除成功！");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！");
        }
    }

    @Override
    public Result updateUser(UserEntity user) {
        try {
            int result = userMapper.updateUser(user);
            if (result == 0) return Result.fail("修改过程出现未知异常！");
            return Result.ok("修改成功！",user);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！");
        }
    }
}
