package com.demo.service;

import com.demo.mapper.UserMapper;
import com.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 注册，保存用户
     * @param username
     * @param password
     * @param sex
     * @param birthday
     * @param myself
     * @param QQ
     */
    public void save( String username, String password, String sex, String birthday, String myself, String QQ){
        this.userMapper.save( username, password,  sex,
                birthday,  myself,  QQ );
    }

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    //登陆功能（通过账号和密码查询数据库进行登陆）
    public User findByUsernameAndPassword(String username,String password){
        return this.userMapper.findByUsernameAndPassword( username,password );
    }

    /**
     * 查询所有用户信息
     * @param username
     * @return
     */
    //查询学生信息功能（通过用户名查询学生所有信息）
    public List<User> findByUsername(String username){
        return this.userMapper.findByUsername( username );
    }

    /**
     * 更改用户信息
     * @param sex
     * @param QQ
     * @param birthday
     * @param myself
     * @param username
     */
    //学生修改信息功能
    public void update(String sex,String QQ,String birthday,String myself,String username){
       this.userMapper.update( sex, QQ,birthday,myself,username);
    }

    /**
     * 验证用户名是否重复
     * @param username
     * @return
     */
    //注册验证用户名是否重复
    public   String selectUsername(String username){
        return this.userMapper.selectUsername( username );
    }
}
