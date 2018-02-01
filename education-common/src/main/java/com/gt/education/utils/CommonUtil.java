package com.gt.education.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.gt.api.util.KeysUtil;
import com.gt.education.constant.Constants;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA
 * User : yangqian
 * Date : 2017/7/18 0018
 * Time : 16:22
 */
public class CommonUtil {

    private static final Logger log = Logger.getLogger( CommonUtil.class );

    /**
     * 判断对象是否为空
     */
    public static boolean isEmpty( Object obj ) {
	boolean b = false;
	try {
	    if ( obj == null || "".equals( obj ) || "null".equals( obj ) ) {
		b = true;
	    } else {
		b = false;
	    }
	} catch ( Exception e ) {
	    b = false;
	    e.printStackTrace();
	}
	return b;
    }

    /**
     * 判断对象是否不为空
     */
    public static boolean isNotEmpty( Object obj ) {
	boolean b = false;
	try {
	    if ( obj == null || "".equals( obj ) || "null".equals( obj ) ) {
		b = false;
	    } else {
		b = true;
	    }
	} catch ( Exception e ) {
	    b = false;
	    e.printStackTrace();
	}
	return b;
    }

    /**
     * 转Integer
     */
    public static Integer toInteger( Object obj ) {
	try {
	    if ( !isEmpty( obj ) ) {
		return Integer.parseInt( obj.toString() );
	    } else {
		throw new Exception( "对象为空，转换失败！" );
	    }
	} catch ( Exception e ) {
	    e.printStackTrace();
	}
	return null;
    }

    public static Integer toIntegerByDouble( double obj ) {
	try {
	    return (int) obj;
	} catch ( Exception e ) {
	    e.printStackTrace();
	}
	return null;
    }

    /**
     * 转String
     */
    public static String toString( Object obj ) {
	try {
	    if ( !isEmpty( obj ) ) {
		return obj.toString();
	    } else {
		throw new Exception( "对象为空，转换失败！" );
	    }
	} catch ( Exception e ) {
	    e.printStackTrace();
	}
	return null;
    }

    /**
     * 转Double
     */
    public static Double toDouble( Object obj ) {
	try {
	    if ( !isEmpty( obj ) ) {
		return Double.parseDouble( obj.toString() );
	    } else {
		throw new Exception( "对象为空，转换失败！" );
	    }
	} catch ( Exception e ) {
	    e.printStackTrace();
	}
	return null;
    }

    /**
     * 校验是否是double数据
     */
    public static boolean isDouble( Object obj ) {
	try {
	    Double.parseDouble( obj.toString() );
	} catch ( Exception e ) {
	    e.printStackTrace();
	    return false;
	}
	return true;
    }

    /**
     * 转BigDecimal
     */
    public static BigDecimal toBigDecimal( Object obj ) {
	try {
	    if ( isNotEmpty( obj ) ) {
		if ( isDouble( obj ) ) {
		    return BigDecimal.valueOf( toDouble( obj ) );
		} else {
		    throw new Exception( "对象不是double数据，不能转换成BigDecimal" );
		}
	    } else {
		throw new Exception( "对象为空，转换失败" );
	    }
	} catch ( Exception e ) {
	    e.printStackTrace();
	}
	return null;
    }

    private static Pattern pattern = Pattern.compile( "^[-\\+]?[\\d]*$" );

    /**
     * 是否为正整数
     */
    public static boolean isInteger( String str ) {

	return pattern.matcher( str ).matches();
    }

    public static String blob2String( Object obj ) {
	String string = null;
	try {
	    if ( obj == null || "".equals( obj ) ) {
		return "";
	    }
	    byte[] bytes = (byte[]) obj;
	    string = new String( bytes, "UTF-8" );
	} catch ( UnsupportedEncodingException e ) {
	    e.printStackTrace();
	}
	return string;
    }

    public static String getBytes( String str ) {
	try {
	    if ( str.equals( new String( str.getBytes( "iso8859-1" ), "iso8859-1" ) ) ) {
		str = new String( str.getBytes( "iso8859-1" ), "utf-8" );
	    }
	} catch ( UnsupportedEncodingException e ) {
	    e.printStackTrace();
	}
	return str;
    }

    /**
     * url中文参数乱码
     */
    public static String urlEncode( String str ) {

	try {

	    return URLDecoder.decode( str, "UTF-8" );
	} catch ( UnsupportedEncodingException e ) {
	    e.printStackTrace();
	    return "";
	}
    }

    /**
     * 获取卡号 截取一位 是生成条形码13位
     */
    public static String getCode() {
	Long date = new Date().getTime();
	return date.toString().substring( 1 );
    }

    public static String getpath( HttpServletRequest request ) {
	String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()  // 服务器地址
			+ request.getContextPath() // 项目名称
			+ request.getServletPath() // 请求页面或其他地址
			+ ( CommonUtil.isEmpty( request.getQueryString() ) ? "" : "?" + request.getQueryString() ); // 参数
	return url;
    }

    /**
     * 格式化字符串
     *
     * @param format
     * @param args
     *
     * @return
     */
    public static String format( String format, Object... args ) {
	String str = null;
	str = String.format( format, args );
	return str;
    }

    /**
     * 精确的减法运算
     *
     * @param v1 v1
     * @param v2 v2
     *
     * @return v1-v2
     */
    public static double subtract( double v1, double v2 ) {
	BigDecimal b1 = new BigDecimal( Double.toString( v1 ) );
	BigDecimal b2 = new BigDecimal( Double.toString( v2 ) );
	return b1.subtract( b2 ).doubleValue();
    }

    /**
     * 精确的乘法运算
     *
     * @param v1 v1
     * @param v2 v2
     *
     * @return v1+v2
     */
    public static double multiply( double v1, double v2 ) {
	BigDecimal b1 = new BigDecimal( Double.toString( v1 ) );
	BigDecimal b2 = new BigDecimal( Double.toString( v2 ) );
	return b1.multiply( b2 ).doubleValue();
    }

    /**
     * 精确的加法运算
     *
     * @param v1 v1
     * @param v2 v2
     *
     * @return v1*v2
     */
    public static double add( double v1, double v2 ) {
	BigDecimal b1 = new BigDecimal( Double.toString( v1 ) );
	BigDecimal b2 = new BigDecimal( Double.toString( v2 ) );
	return b1.add( b2 ).doubleValue();
    }

    /**
     * 除法
     *
     * @param v1    v1
     * @param v2    v2
     * @param scale 保留小数
     *
     * @return v1/v2
     */
    public static double div( double v1, double v2, int scale ) {
	if ( scale < 0 ) {
	    return 0;
	}
	BigDecimal b1 = new BigDecimal( Double.toString( v1 ) );
	BigDecimal b2 = new BigDecimal( Double.toString( v2 ) );
	return b1.divide( b2, scale, BigDecimal.ROUND_HALF_UP ).doubleValue();
    }

    private static final int DEF_DIV_SCALE = 10;

    /**
     * 除法返回 整数
     *
     * @param d1 d1
     * @param d2 d2
     *
     * @return 整数
     */
    public static Integer divInteger( double d1, double d2 ) {
	return (int) div( d1, d2, DEF_DIV_SCALE );

    }

    /**
     * 保留2位小数（四舍五入）
     */
    public static Double getDecimal( Double d ) {
	if ( d != null ) {
	    if ( d.toString().split( "\\." )[1].length() > 2 ) {
		BigDecimal bg = new BigDecimal( d );
		return bg.setScale( 2, BigDecimal.ROUND_HALF_UP ).doubleValue();
	    } else {
		return d;
	    }
	} else {
	    return null;
	}

    }

    public static Double getDecimalStr( Double d, int len ) {
	if ( d != null ) {
	    if ( d.toString().split( "\\." )[1].length() > 2 ) {
		len = len == 0 ? 2 : len;
		if ( len > 0 ) {
		    len = len + 1;
		}
		//		BigDecimal bg = new BigDecimal( d );
		int index = d.toString().indexOf( "." ) + len;
		return CommonUtil.toDouble( d.toString().substring( 0, index ) );
	    }
	}
	return d;
    }

    private final static double PI = 3.14159265358979323; // 圆周率
    private final static double R  = 6371229; // 地球的半径

    /**
     * 获取两点间的距离
     *
     * @param longt1 经度1
     * @param lat1   纬度1
     * @param longt2 经度2
     * @param lat2   纬度2
     *
     * @return
     */
    public static double getDistance( double longt1, double lat1, double longt2, double lat2 ) {
	double x, y, distance;
	x = ( longt2 - longt1 ) * PI * R * Math.cos( ( ( lat1 + lat2 ) / 2 ) * PI / 180 ) / 180;
	y = ( lat2 - lat1 ) * PI * R / 180;
	distance = Math.hypot( x, y );
	if ( distance > 0 ) {
	    return distance;
	} else {
	    return 0.0;
	}
    }

    /**
     * 获取推荐码 6位
     *
     * @return
     */
    public static String getPhoneCode() {
	StringBuffer buf = new StringBuffer( "1,2,3,4,5,6,7,8,9,0" );
	String[] arr = buf.toString().split( "," );
	StringBuffer sb = new StringBuffer();
	Random random = new Random();
	for ( int i = 0; i < 6; i++ ) {
	    Integer count = arr.length;
	    int a = random.nextInt( count );
	    sb.append( arr[a] );
	}
	return sb.toString();
    }

    /**
     * 判断浏览器
     *
     * @return 1:微信浏览器,99:其他浏览器
     */
    public static Integer judgeBrowser( HttpServletRequest request ) {
	Integer result = null;
	String ua = request.getHeader( "user-agent" ).toLowerCase();
	if ( ua.indexOf( "micromessenger" ) > 0 ) {// 微信-1
	    result = 1;
	} else {//其他 -99
	    result = 99;
	}
	return result;
    }

    /**
     * 返回json数据到客户端
     *
     * @param response
     * @param obj
     *
     * @throws IOException
     */
    public static void write( HttpServletResponse response, Object obj ) throws IOException {
	if ( obj instanceof List || obj instanceof Object[] ) {
	    response.getWriter().print( JSONArray.toJSON( obj ) );
	} else if ( obj instanceof Boolean || obj instanceof Integer || obj instanceof String || obj instanceof Double ) {
	    Map< String,Object > result = new HashMap< String,Object >();
	    result.put( "status", obj );
	    response.getWriter().print( JSONObject.fromObject( result ) );
	} else {
	    response.getWriter().print( com.alibaba.fastjson.JSONObject.parseObject( JSON.toJSONString( obj ) ) );
	}
	response.getWriter().flush();
	response.getWriter().close();
    }

    /**
     * 获取IP
     *
     * @param request
     *
     * @return
     */
    public static String getIpAddr( HttpServletRequest request ) {
	String ip = request.getHeader( "x-forwarded-for" );
	if ( ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase( ip ) ) {
	    ip = request.getHeader( "Proxy-Client-IP" );
	}
	if ( ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase( ip ) ) {
	    ip = request.getHeader( "WL-Proxy-Client-IP" );
	}
	if ( ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase( ip ) ) {
	    ip = request.getRemoteAddr();
	}
	if ( "0:0:0:0:0:0:0:1".equals( ip ) ) {
	    ip = "127.0.0.1";
	}
	return ip;
    }

    /**
     * 获取会员的支付方式
     *
     * @param payWay   支付方式  传 order.getOrderPayWay
     * @param isWallet 是否使用钱包支付   1已使用  0未使用 -1正在支付  传 order.getIsWallet
     *
     * @return @return 支付方式（调用 memberPayService.paySuccess用的，不适用与其他的接口）
     */
    public static int getMemberPayType( int payWay, int isWallet ) {
	int payType = 0;//现金支付
	switch ( payWay ) {
	    case 1://微信支付
		payType = 1;
		break;
	    case 3://储值卡支付
		payType = 5;
		break;
	    case 4://积分支付
		payType = 11;
		break;
	    case 5://扫码支付
		payType = 1;
		break;
	    case 7://找人代付
		payType = 1;
		break;
	    case 8://粉币支付
		payType = 12;
		break;
	    case 9://支付宝
		payType = 0;
		break;
	    case 10://小程序支付
		payType = 1;
		break;
	    case 11://多粉钱包支付
		payType = 15;
		break;
	    default:
		payType = 10;//现金支付
		break;
	}
	if ( isWallet == 1 ) {
	    payType = 1;
	}
	return payType;
    }

    /**
     * 预售获取会员的支付方式
     *
     * @param payWay   支付方式  传 order.getOrderPayWay
     * @param isWallet 是否使用钱包支付   1已使用  0未使用 -1正在支付  传 order.getIsWallet
     *
     * @return @return 支付方式（调用 memberPayService.paySuccess用的，不适用与其他的接口）
     */
    public static int getMemberPayTypeByPresale( int payWay, int isWallet ) {
	int payType = 0;//现金支付
	switch ( payWay ) {
	    case 1://微信支付
		payType = 1;
		break;
	    case 2://储值卡支付
		payType = 5;
		break;
	    case 3://支付宝
		payType = 0;
		break;
	    case 4://多粉钱包支付
		payType = 15;
		break;
	    default:
		payType = 1;//微信支付
		break;
	}
	if ( isWallet == 1 ) {
	    payType = 1;
	}
	return payType;
    }

    /**
     * 获取会员的消费方式
     *
     * @return 消费方式 （调用 memberPayService.paySuccess用的，不适用与其他的接口）
     */
    public static int getMemberUcType( int orderType ) {
	int uctype = 104;//商城下单

	switch ( orderType ) {
	    case 1:
		uctype = 105;//团购
		break;
	    case 2:
		uctype = 110;//积分
		break;
	    case 3:
		uctype = 106;//秒杀
		break;
	    case 4:
		uctype = 107;//拍卖
		break;
	    case 5:
		uctype = 111;//粉币
		break;
	    case 6:
		uctype = 108;//预售
		break;
	    case 7:
		uctype = 109;//批发
		break;
	    default:
		uctype = 104;//商城下单
		break;
	}
	return uctype;
    }


    /**
     * 把用户集合改成用逗号隔开的字符串
     */
    public static String getMememberIds( List< Integer > memberList, Integer memberId ) {
	StringBuilder memberIds = new StringBuilder();
	if ( memberList != null && memberList.size() > 0 ) {
	    for ( Integer id : memberList ) {
		memberIds.append( id ).append( "," );
	    }
	    memberIds = new StringBuilder( memberIds.substring( 0, memberIds.length() - 1 ) );
	} else if ( CommonUtil.isNotEmpty( memberId ) ) {
	    memberIds = new StringBuilder( memberId.toString() );
	}
	return memberIds.toString();
    }

    /**
     * 手机号码验证正则表达式
     */
    private static Pattern phonePattern = Pattern.compile( "(^(13[0123456789][0-9]{8}|15[0123456789][0-9]{8}|18[0123456789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$)" );

    /**
     * 手机号验证
     */
    public static boolean isPhone( String phone ) {
	if ( StringUtils.isEmpty( phone ) ) {
	    return false;
	}

	Matcher matcher = phonePattern.matcher( phone );
	return matcher.matches();
    }

    public static String getPifaErrorMsg( int pfStatus ) {
	String errorMsg = "";
	if ( pfStatus == -2 ) {
	    errorMsg = "您还没申请批发商，是否前往我的批发进行申请";
	} else if ( pfStatus == -1 ) {
	    errorMsg = "您的批发商申请不通过,是否前往我的批发进行重新申请";
	} else if ( pfStatus == 0 ) {
	    errorMsg = "您的批发商申请在审核中请耐心等待1-3个工作日";
	}
	return errorMsg;
    }

    public static String getSellerErrorMsg( int sellerStatus, double consumeMoney, double minCosumeMoney ) {
	String errorMsg = "";
	if ( sellerStatus == -2 || sellerStatus == -4 ) {
	    if ( ( consumeMoney > 0 || minCosumeMoney > 0 ) && consumeMoney < minCosumeMoney ) {
		errorMsg = "加入超级销售员消费额必须要达到" + minCosumeMoney + "元，您的消费额只有" + consumeMoney + "元";
	    }
	} else if ( sellerStatus == 0 ) {
	    errorMsg = "您的超级销售员申请在审核中请耐心等待1-3个工作日";
	} else if ( sellerStatus == -1 ) {
	    if ( ( consumeMoney > 0 || minCosumeMoney > 0 ) && consumeMoney < minCosumeMoney ) {
		errorMsg = "您的超级销售员申请不通过，且消费额没有达到" + minCosumeMoney + "元，不能继续申请，您的消费额只有" + consumeMoney + "元";
	    } else {
		//		errorMsg = "您的超级销售员申请不通过，确认要重新申请？";
		errorMsg = "您的审核不通过不可重复发起申请";
	    }
	} else if ( sellerStatus == -3 ) {
	    errorMsg = "您的超级销售员已经被暂停了，不能继续使用";
	}
	return errorMsg;
    }

    /**
     * 在提交订单页面运费
     *
     * @param storeMap        店铺对象
     * @param memberLangitude 纬度
     * @param memberLongitude 经度
     *
     * @return 运费
     */
    public static double getRaill( Map< String,Object > storeMap, Double memberLangitude, Double memberLongitude ) {
	if ( CommonUtil.isEmpty( memberLangitude ) || CommonUtil.isEmpty( memberLongitude ) || CommonUtil.isEmpty( storeMap ) ) {
	    return 0;
	}
	double shopLongitude = CommonUtil.toDouble( storeMap.get( "stoLongitude" ) );//店铺经度
	double shopLangitude = CommonUtil.toDouble( storeMap.get( "stoLatitude" ) );//店铺纬度
	double raill = 0;//粉丝到店铺的距离
	if ( shopLangitude > 0 && shopLongitude > 0 && memberLangitude > 0 && memberLongitude > 0 ) {
	    raill = CommonUtil.getDistance( memberLongitude, memberLangitude, shopLongitude, shopLangitude );
	    raill = raill / 1000;
	}
	return raill;
    }

    /**
     * 小数处理（格式化）
     */
    public static Double formatDoubleNumber( Double number ) {
	DecimalFormat df = new DecimalFormat( "######0.00" );
	return CommonUtil.toDouble( df.format( number ) );
    }

    /**
     * 获取联盟的支付方式
     *
     * @param payWay 订单的支付方式
     */
    public static int getUnionType( int payWay ) {
	int orderType = payWay;
	if ( payWay == 9 ) {//支付宝
	    orderType = 2;
	} else if ( payWay == 2 ) {//货到付款
	    orderType = 3;
	} else if ( payWay == 6 ) {//到店支付
	    orderType = 0;
	}
	return orderType;
    }

    /**
     * 获取增值服务的模块（增值服务用到）
     *
     * @param type 1.团购商品 2积分 3.秒杀商品 4.拍卖商品 5 粉币商品 6预售商品 7批发商品 8 销售员
     *
     * @return 增值服务模块
     */
    public static String getAddedStyle( String type ) {
	String model_style = "";
	switch ( type ) {
	    case "1": //团购
		model_style = "Z002";
		break;
	    case "2": //积分商城
		model_style = "Z005";
		break;
	    case "3": //秒杀
		model_style = "Z001";
		break;
	    case "4": //拍卖
		model_style = "Z009";
		break;
	    case "6": //预售
		model_style = "Z006";
		break;
	    case "7": //批发
		model_style = "Z007";
		break;
	    case "8": //超级销售员
		model_style = "Z003";
		break;
	    case "报价":
		model_style = "Z008";
		break;
	    case "H5商城":
		model_style = "Z004";
		break;
	    default:
		break;
	}
	return model_style;
    }

}
