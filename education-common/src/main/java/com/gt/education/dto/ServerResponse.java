package com.gt.education.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gt.education.enums.ResponseEnums;
import com.gt.education.utils.CommonUtil;
import com.gt.education.utils.PropertiesUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import static com.fasterxml.jackson.databind.annotation.JsonSerialize.Typing.DEFAULT_TYPING;

/**
 * 服务响应类
 * <pre>
 *     统一响应返回数据格式
 * </pre>
 *
 * @author zhangmz
 * @create 2017/6/16
 */
//保证序列化json的时候,如果是null的对象,key也会消失
@ApiModel( value = "ServerResponse", description = "统一响应返回数据格式" )
@JsonSerialize( typing = DEFAULT_TYPING )
public class ServerResponse< T > implements Serializable {

    /*状态码*/
    @ApiModelProperty( name = "code", value = "状态码：参考响应编码说明" )
    private int code;

    /*返回消息*/
    @ApiModelProperty( name = "msg", value = "返回消息" )
    private String msg;

    /*泛型数据*/
    @ApiModelProperty( name = "data", value = "返回数据" )
    private T data;

    protected ServerResponse( int code ) {
	this.code = code;
    }

    protected ServerResponse( int code, String msg ) {
	this.code = code;
	this.msg = msg;
    }

    public ServerResponse( int code, T data ) {
	this.code = code;
	this.data = data;
    }

    protected ServerResponse( int code, String msg, T data ) {
	this.code = code;
	this.msg = msg;
	this.data = data;
    }

    /**
     * 创建响应成功
     *
     * @return ServerResponse
     */
    public static < T > ServerResponse< T > createBySuccess() {
	return createBySuccessMessage( ResponseEnums.SUCCESS.getDesc() );
    }

    /**
     * 创建响应成功
     *
     * @return ServerResponse
     */
    public static < T > ServerResponse< T > createBySuccessCode() {
	return createBySuccessMessage( ResponseEnums.SUCCESS.getCode() );
    }

    /**
     * 创建响应成功
     *
     * @param data 数据包
     *
     * @return ServerResponse
     */
    public static < T > ServerResponse< T > createBySuccess( T data ) {
	return createBySuccess( null, data );
    }

    /**
     * 创建响应成功
     *
     * @param msg 返回消息
     *
     * @return ServerResponse
     */
    public static < T > ServerResponse< T > createBySuccessMessage( String msg ) {
	return createBySuccess( msg, null );
    }

    /**
     * 创建响应成功
     *
     * @param code 状态码
     *
     * @return ServerResponse
     */
    public static < T > ServerResponse< T > createBySuccessMessage( int code ) {
	return createBySuccessCodeMessage( code );
    }

    /**
     * 创建响应成功
     *
     * @param msg  消息
     * @param data 数据包
     *
     * @return ServerResponse
     */
    public static < T > ServerResponse< T > createBySuccess( String msg, T data ) {
	return createBySuccessCodeMessage( ResponseEnums.SUCCESS.getCode(), msg, data );
    }

    /**
     * 创建响应成功
     *
     * @param code 状态码
     * @param msg  消息
     * @param data 数据包
     *
     * @return ServerResponse
     */
    public static < T > ServerResponse< T > createBySuccessCodeMessage( int code, String msg, T data ) {
	return new ServerResponse<>( code, msg, data );
    }

    /**
     * 创建响应成功
     *
     * @param code 状态码
     *
     * @return ServerResponse
     */
    public static < T > ServerResponse< T > createBySuccessCodeMessage( int code ) {
	return new ServerResponse<>( code );
    }

    /**
     * 创建响应成功
     *
     * @param code 状态码
     * @param msg  消息
     *
     * @return ServerResponse
     */
    public static < T > ServerResponse< T > createBySuccessCodeMessage( int code, String msg ) {
	return new ServerResponse<>( code, msg );
    }

    /**
     * 创建响应成功
     *
     * @param code 状态吗
     * @param data 数据
     *
     * @return ServerResponse
     */
    public static < T > ServerResponse< T > createBySuccessCodeData( int code, T data ) {
	return new ServerResponse< T >( code, data );
    }

    /**
     * 创建响应失败
     *
     * @return ServerResponse
     */
    public static < T > ServerResponse< T > createByError() {
	return createByErrorCodeMessage( ResponseEnums.ERROR.getCode(), ResponseEnums.ERROR.getDesc() );
    }

    /**
     * 创建响应失败
     *
     * @param errorMessage 消息
     *
     * @return ServerResponse
     */
    public static < T > ServerResponse< T > createByErrorMessage( String errorMessage ) {
	return createByErrorCodeMessage( ResponseEnums.ERROR.getCode(), errorMessage );
    }

    /**
     * 创建响应失败
     *
     * @param errorCode    状态码
     * @param errorMessage 消息
     *
     * @return ServerResponse
     */
    public static < T > ServerResponse< T > createByErrorCodeMessage( int errorCode, String errorMessage ) {
	return new ServerResponse<>( errorCode, errorMessage );
    }

    /**
     * 创建响应失败
     *
     * @param errorCode    状态码
     * @param errorMessage 消息
     * @param data         返回数据
     *
     * @return ServerResponse
     */
    public static < T > ServerResponse< T > createByErrorCodeMessage( int errorCode, String errorMessage, T data ) {
	return new ServerResponse<>( errorCode, errorMessage, data );
    }

    //使之不在json序列化结果当中，作用用于判断
    @JsonIgnore
    public boolean isSuccess() {
	return this.code == ResponseEnums.SUCCESS.getCode();
    }

    public int getCode() {
	return code;
    }

    public T getData() {
	return data;
    }

    public String getMsg() {
	return msg;
    }
}

