package com.gt.education.config.interceptor;

import com.gt.api.bean.session.Member;
import com.gt.api.util.SessionUtils;
import com.gt.education.common.AuthorizeOrLoginController;
import com.gt.education.enums.ResponseEnums;
import com.gt.education.exception.BusinessException;
import com.gt.education.utils.CommonUtil;
import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PhoneInterceptor extends AuthorizeOrLoginController implements HandlerInterceptor {

    private Logger logger = Logger.getLogger( PhoneInterceptor.class );

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler ) throws Exception {
	//	logger.info( ">>>PhoneInterceptor>>>>>>>在请求处理之前进行调用（Controller方法调用之前）" );
	logger.info( ">>>PhoneInterceptor>>basePath = " + CommonUtil.getpath( request ) );

	long startTime = System.currentTimeMillis();
	request.setAttribute( "runStartTime", startTime );

	// 获得在下面代码中要用的request,response,session对象
	Map< String,Object > params = getParameterMap( request );
	Member member = null;
	if ( CommonUtil.isNotEmpty( params.get( "busId" ) ) ) {
	    Integer busId = CommonUtil.toInteger( params.get( "busId" ) );
	    member = SessionUtils.getLoginMember( request, busId );
	}
	if ( CommonUtil.isEmpty( member ) ) {
	    String returnStr = userLogin( request, response, params );
	    if ( CommonUtil.isNotEmpty( returnStr ) ) {
		throw new BusinessException( ResponseEnums.NEED_LOGIN.getCode(), ResponseEnums.NEED_LOGIN.getDesc(), returnStr );
	    }
	}
	return true;// 只有返回true才会继续向下执行，返回false取消当前请求*/
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后
     */
    @Override
    public void postHandle( HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView ) throws Exception {
	long startTime = (Long) request.getAttribute( "runStartTime" );

	long endTime = System.currentTimeMillis();

	long executeTime = endTime - startTime;

	HandlerMethod handlerMethod = (HandlerMethod) handler;
	Method method = handlerMethod.getMethod();
	/*if ( logger.isDebugEnabled() ) {*/
	logger.error( "方法:" + handlerMethod.getBean() + "." + method.getName() + "  ；  请求参数：" + handlerMethod.getMethodParameters() );
	logger.error( "访问的执行时间 : " + executeTime + "ms----页面：" + CommonUtil.getpath( request ) );
    }

    private Map< String,Object > getParameterMap( HttpServletRequest request ) {
	// 参数Map
	Map map = request.getParameterMap();
	// 返回值Map
	Map< String,Object > returnMap = new HashMap<>();
	Iterator entries = map.entrySet().iterator();
	Map.Entry entry;
	String name = "";
	String value = "";
	while ( entries.hasNext() ) {
	    entry = (Map.Entry) entries.next();
	    name = (String) entry.getKey();
	    Object valueObj = entry.getValue();
	    if ( null == valueObj ) {
		value = "";
	    } else if ( valueObj instanceof String[] ) {
		String[] values = (String[]) valueObj;
		for ( int i = 0; i < values.length; i++ ) {
		    value = values[i] + ",";
		}
		value = value.substring( 0, value.length() - 1 );
	    } else {
		value = valueObj.toString();
	    }
	    returnMap.put( name, value );
	}
	return returnMap;
    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作
     */
    @Override
    public void afterCompletion( HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex ) throws Exception {
    }

}
