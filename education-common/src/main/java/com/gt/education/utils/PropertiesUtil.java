package com.gt.education.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Properties 读取工具（注解方式获取application.yml文件的配置参数）
 * User : yangqian
 * Date : 2017/7/19 0019
 * Time : 15:11
 */
@Configuration
public class PropertiesUtil {

    private static String resourceUrl;//访问资源URL

    private static String wxmpDomain;//主项目域名（登陆。。）

    private static String wxmpSignKey;//调用主项目接口的前面key

    private static String redisHost;// redis IP

    private static String redisPort;// redis 端口

    private static String redisPassword;// redis 密码

    private static Integer redisDataBase;//redis Database

    private static String staticSourceFtpIp;//图片资源Ftp IP

    private static String staticSourceFtpPort;//图片资源Ftp 端口

    private static String staticSourceFtpUser;//图片资源Ftp 用户

    private static String staticSourceFtpPwd;  //图片资源Ftp 密码

    @Value( "${resource.url.prefix}" )
    public void setResourceUrl( String resourceUrl ) {
	PropertiesUtil.resourceUrl = resourceUrl;
    }

    @Value( "${http.wxmp.domain}" )
    public void setWxmpDomain( String wxmpDomain ) {
	PropertiesUtil.wxmpDomain = wxmpDomain;
    }

    @Value( "${http.wxmp.key}" )
    public void setWxmpSignKey( String wxmpSignKey ) {
	PropertiesUtil.wxmpSignKey = wxmpSignKey;
    }

    @Value( "${redis.host}" )
    public void setRedisHost( String redisHost ) {
	PropertiesUtil.redisHost = redisHost;
    }

    @Value( "${redis.port}" )
    public void setRedisPort( String redisPort ) {
	PropertiesUtil.redisPort = redisPort;
    }

    @Value( "${redis.password}" )
    public void setRedisPassword( String redisPassword ) {
	PropertiesUtil.redisPassword = redisPassword;
    }

    @Value( "${redis.database}" )
    public void setRedisDataBase( Integer redisDataBase ) {
	PropertiesUtil.redisDataBase = redisDataBase;
    }

    @Value( "${static.source.ftp.ip}" )
    public void setStaticSourceFtpIp( String staticSourceFtpIp ) {
	PropertiesUtil.staticSourceFtpIp = staticSourceFtpIp;
    }

    @Value( "${static.source.ftp.port}" )
    public void setStaticSourceFtpPort( String staticSourceFtpPort ) {
	PropertiesUtil.staticSourceFtpPort = staticSourceFtpPort;
    }

    @Value( "${static.source.ftp.user}" )
    public void setStaticSourceFtpUser( String staticSourceFtpUser ) {
	PropertiesUtil.staticSourceFtpUser = staticSourceFtpUser;
    }

    @Value( "${static.source.ftp.password}" )
    public void setStaticSourceFtpPwd( String staticSourceFtpPwd ) {
	PropertiesUtil.staticSourceFtpPwd = staticSourceFtpPwd;
    }

    public static String getResourceUrl() {
	return resourceUrl;
    }

    public static String getWxmpDomain() {
	return wxmpDomain;
    }

    public static String getWxmpSignKey() {
	return wxmpSignKey;
    }

    public static String getRedisHost() {
	return redisHost;
    }

    public static String getRedisPort() {
	return redisPort;
    }

    public static String getRedisPassword() {
	return redisPassword;
    }

    public static Integer getRedisDataBase() {
	return redisDataBase;
    }

    public static String getStaticSourceFtpIp() {
	return staticSourceFtpIp;
    }

    public static String getStaticSourceFtpPort() {
	return staticSourceFtpPort;
    }

    public static String getStaticSourceFtpUser() {
	return staticSourceFtpUser;
    }

    public static String getStaticSourceFtpPwd() {
	return staticSourceFtpPwd;
    }
}
