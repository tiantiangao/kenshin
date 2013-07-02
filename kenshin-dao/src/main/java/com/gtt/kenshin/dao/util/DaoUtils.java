package com.gtt.kenshin.dao.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

import com.gtt.kenshin.dao.annotation.DaoAction;
import com.gtt.kenshin.dao.annotation.DaoParam;
import com.gtt.kenshin.dao.annotation.DaoParamType;
import com.gtt.kenshin.dao.model.DaoMethod;

/**
 * DAO工具类
 * 
 * @author tiantian.gao
 * @date 2011-5-24
 * 
 */
public class DaoUtils {

	/**
	 * 根据{@link MethodInvocation}创建{@link DaoMethod}实例
	 */
	public static DaoMethod createDaoMethod(final Method method, final Object[] arguments) {

		DaoAction DaoAction = getDaoAction(method, arguments);

		String methodName = getMethodName(method, DaoAction);

		DaoMethod daoMethod = new DaoMethod(methodName, DaoAction.action());

		parseParameters(method, arguments, daoMethod);

		return daoMethod;
	}

	/**
	 * 从注解中抽取DaoAction
	 * 
	 * @param method
	 * @param arguments
	 * @return
	 */
	private static DaoAction getDaoAction(final Method method, final Object[] arguments) {
		if (method == null || arguments == null) {
			throw new IllegalArgumentException("Both the method and arguments cannot be null.");
		}

		DaoAction DaoAction = method.getAnnotation(DaoAction.class);

		if (DaoAction == null) {
			throw new IllegalArgumentException("The method " + method.getName() + " must be annotated by "
					+ DaoAction.class.getName());
		}
		return DaoAction;
	}

	/**
	 * 获取方法名
	 * 
	 * @param method
	 * @param DaoAction
	 * @return
	 */
	private static String getMethodName(final Method method, DaoAction DaoAction) {
		// 如果方法声明未指定名称，默认使用java方法名
		String methodName = method.getName();
		if (!DaoAction.method().equals("")) {
			methodName = DaoAction.method();
		}
		return methodName;
	}

	/**
	 * 解析参数
	 * 
	 * @param method
	 * @param arguments
	 * @param daoMethod
	 */
	private static void parseParameters(Method method, Object[] arguments, DaoMethod daoMethod) {
		final Map<String, Object> paramValues = new HashMap<String, Object>();

		Class<?>[] paramTypes = method.getParameterTypes();
		Annotation[][] paramAnnos = method.getParameterAnnotations();

		for (int i = 0; i < paramTypes.length; i++) {

			Annotation[] annos = paramAnnos[i];
			boolean resolvedName = false;

			// 尝试从注解中抽取参数名称
			for (Annotation anno : annos) {
				if (anno instanceof DaoParam) {
					DaoParam DaoParam = (DaoParam) anno;

					if (DaoParam.type() == DaoParamType.NORMAL) {
						paramValues.put(DaoParam.value(), arguments[i]);
						resolvedName = true;
					}
				}
			}

			// 注解中未指定，从java方法中抽取参数名称
			if (!resolvedName) {
				String paramName = resolveParamName(method, i);
				// if param is null, this means that the java compiler is not
				// debug mode, we should ignore this param setting!
				if (paramName != null) {
					paramValues.put(paramName, arguments[i]);
				}
			}

			daoMethod.addParams(paramValues);
		}
	}

	/**
	 * 从java类文件中抽取参数名称<br>
	 * 如果java编译器开启了debug模式则可取到，否则返回null
	 */
	private static String resolveParamName(Method method, int paramIndex) {
		String[] params = new LocalVariableTableParameterNameDiscoverer().getParameterNames(method);
		// If the java compiler is not debug mode, the params object is null
		return params == null ? null : params[paramIndex];
	}

}
