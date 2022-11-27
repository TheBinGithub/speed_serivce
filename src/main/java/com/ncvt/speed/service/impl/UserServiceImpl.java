package com.ncvt.speed.service.impl;

import com.ncvt.speed.entity.AccountEntity;
import com.ncvt.speed.entity.UserEntity;
import com.ncvt.speed.mapper.AccountMapper;
import com.ncvt.speed.mapper.UserMapper;
import com.ncvt.speed.service.UserService;
import com.ncvt.speed.util.Md5;
import com.ncvt.speed.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private AccountMapper accountMapper;

    @Resource
    private UserMapper userMapper;

    // 查询
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

    // 昵称修改
    @Override
    public Result updateNickname(String userId, String nickname) {
        try {
            int uResult = userMapper.updateNickname(nickname, userId);
            if (uResult != 1) return Result.fail("修改过程出现未知异常！");
            return Result.ok("修改成功！");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！");
        }
    }

    // 修改登录密码
    @Override
    public Result updatePassword(String oldPassword, String newPassword, String userId) {
        try {
            AccountEntity accountEntity = accountMapper.queryAccountByPassword(userId);
            if (Md5.getMd5Password(oldPassword,accountEntity.getSalt()).equals(accountEntity.getPassword())){
                int result = accountMapper.updateUserByPassword(Md5.getMd5Password(newPassword, accountEntity.getSalt()), userId);
                if (result != 1) return Result.fail("修改登录密码出现未知异常！");
                return Result.ok("修改登录密码成功！");
            }
            return Result.ok(300,"原密码错误！");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！");
        }
    }

    // 二级密码校验
    @Override
    public Result CheckSePassword(String sePassword, String userId) {
        try {
            UserEntity user = userMapper.querySecondPassword(userId);
            if (Md5.getMd5Password(sePassword,user.getSSalt()).equals(user.getSecondPassword())) return Result.ok("密码正确！");
            return Result.fail(300,"密码错误！");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！",e.getMessage());
        }
    }

    // 设置二级密码
    @Override
    public Result addSepasswrod(String sePassword, String userId) {
        try {
            String salt = UUID.randomUUID().toString().toUpperCase();
            int result = userMapper.updateSecondPassword(Md5.getMd5Password(sePassword, salt), userId);
            if (result != 1) return Result.fail("设置二级密码出现未知异常！");
            return Result.ok("设置二级密码成功！");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！");
        }
    }

    // 修改二级密码
    @Override
    public Result updateSecondPassword(String oldPasswrod, String newPassword, String userId) {
        try {
            UserEntity user = userMapper.querySecondPassword(userId);
            if (Md5.getMd5Password(oldPasswrod,user.getSSalt()).equals(user.getSecondPassword())){
                int result = userMapper.updateSecondPassword(Md5.getMd5Password(newPassword,user.getSSalt()), userId);
                if (result != 1) return Result.fail("修改二级密码出现未知异常！");
                return Result.ok("修改二级密码成功！");
            }
            return Result.fail(300,"原密码错误");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！",e.getMessage());
        }
    }

}
