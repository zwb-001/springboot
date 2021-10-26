package com.demo.mapper;

import com.demo.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 根据用户名称查询用户信息
     * @param username
     * @return
     */
    @Select( "select * from user where username = #{username}" )
    public List<User> findByUsername(String username);

    @Select( "select username from user where username=#{username}" )
    public String selectUsername(@Param ( "username" ) String username );

    /**
     * 登录（用户名和密码）
     * @param username
     * @param password
     * @return
     */
    @Select( "select username,password from user where username=#{username} and password=#{password}" )
    public User findByUsernameAndPassword(@Param ( "username" ) String username , @Param( "password" ) String password);
    /**
     * 注册
     * @param username
     * @param password
     * @param sex
     * @param birthday
     * @param myself
     * @param QQ
     */
    @Insert( "insert into user(username,password,sex,birthday,myself,QQ) values(#{username},#{password},#{sex},#{birthday},#{myself},#{QQ})" )
    public void save(@Param( "username" ) String username , @Param( "password" ) String password , @Param( "sex" ) String sex, @Param("birthday") String birthday , @Param( "myself" ) String myself, @Param( "QQ" ) String QQ);
    //多字段更新，只需要在单字段后面用逗号分隔来书写就行

    /**
     * 更新用户信息
     * @param sex
     * @param QQ
     * @param birthday
     * @param myself
     * @param username
     */
    @Update( "update user set sex = #{sex},QQ=#{QQ},birthday=#{birthday},myself=#{myself}  where username=#{username}" )
    public void update(@Param( "sex" ) String sex,
                       @Param( "QQ" ) String QQ,
                       @Param( "birthday" ) String birthday,
                       @Param( "myself" ) String myself,
                       @Param( "username" ) String username);

}
