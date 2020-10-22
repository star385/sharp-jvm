package com.sharpjvm.interpreter.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sharpjvm.bytecode.bean.ClassInfo;
import com.sharpjvm.bytecode.bean.MethodInfo;
import com.sharpjvm.bytecode.util.ByteUtil;
import com.sharpjvm.interpreter.ExecuteException;
import com.sharpjvm.interpreter.common.DefaultMethodExecutor;
import com.sharpjvm.interpreter.common.MethodExecutor;
import com.sharpjvm.interpreter.common.MethodExecutorParameter;
import com.sharpjvm.interpreter.constant.Constants;
import com.sharpjvm.interpreter.method.MethodTypeInfo;
import com.sharpjvm.memory.model.config.SystemConfig;
import com.sharpjvm.memory.model.stack.StackFrame;
import com.sharpjvm.memory.model.util.TwoWayHashMap;
import com.sharpjvm.memory.runtime.RuntimeContext;

/**
 * ������
 *
 * User: zhuguoyin
 * Date: 13-2-16
 * Time: ����10:49
 * To change this template use File | Settings | File Templates.
 */
public class InterpreterUtil implements Constants {

    static TwoWayHashMap<String, Class<?>> abridge2ClassMap = new TwoWayHashMap<String, Class<?>>();

    static {
        abridge2ClassMap.put(BYTE_ABRIDGE, byte.class);
        abridge2ClassMap.put(CHAR_ABRIDGE, char.class);
        abridge2ClassMap.put(DOUBLE_ABRIDGE, double.class);
        abridge2ClassMap.put(FLOAT_ABRIDGE, float.class);
        abridge2ClassMap.put(INT_ABRIDGE, int.class);
        abridge2ClassMap.put(LONG_ABRIDGE, long.class);
        abridge2ClassMap.put(SHORT_ABRIDGE, short.class);
        abridge2ClassMap.put(BOOLEAN_ABRIDGE, boolean.class);
        abridge2ClassMap.put("[" + BYTE_ABRIDGE, byte[].class);
        abridge2ClassMap.put("[" + CHAR_ABRIDGE, char[].class);
        abridge2ClassMap.put("[" + DOUBLE_ABRIDGE, double[].class);
        abridge2ClassMap.put("[" + FLOAT_ABRIDGE, float[].class);
        abridge2ClassMap.put("[" + INT_ABRIDGE, int[].class);
        abridge2ClassMap.put("[" + LONG_ABRIDGE, long[].class);
        abridge2ClassMap.put("[" + SHORT_ABRIDGE, short[].class);
        abridge2ClassMap.put("[" + BOOLEAN_ABRIDGE, boolean[].class);
        abridge2ClassMap.put(VOID_ABRIDGE, void.class);
    }
    
    private static Map<String, MethodExecutor> methodExecutorMap;
    
    private static MethodExecutor defaultMethodExecutor = new DefaultMethodExecutor();

    /**
     * ���ݷ�����������Ϣ��ȡ�����Ĳ����ͷ���ֵ���͡�
     *
     * @param methodDescription
     * @return
     */
    public static MethodTypeInfo parseMethodParameterTypes(String methodDescription) {
        if (methodDescription == null || methodDescription.isEmpty()) {
            return null;
        }
        MethodTypeInfo methodTypeInfo = new MethodTypeInfo();
        String parameterString = methodDescription.substring(methodDescription.indexOf(METHOD_PARAMETERS_PREFIX) + 1, methodDescription.indexOf(METHOD_PARAMETERS_POSTFIX));
        if (parameterString == null || parameterString.isEmpty()) {
            methodTypeInfo.setParameterTypes(new Class[0]);
        } else {
            String[] parameterTypeStringArray = parameterString.split(PARAMETER_SEPARATOR);
            if (parameterTypeStringArray == null || parameterTypeStringArray.length == 0) {
                methodTypeInfo.setParameterTypes(new Class[0]);
            } else {
                List<Class<?>> parameterTypeList = getBaseTypeParametersByDescription(parameterTypeStringArray);
                methodTypeInfo.setParameterTypes(parameterTypeList.toArray(new Class<?>[0]));
            }
        }
        String returnTypeString = methodDescription.substring(methodDescription.indexOf(")") + 1);
        methodTypeInfo.setReturnType(getTypeByDescriptionString(returnTypeString));
        return methodTypeInfo;
    }

    private static List<Class<?>> getBaseTypeParametersByDescription(String[] parameterTypeStringArray) {
        List<Class<?>> parameterTypeList = new ArrayList<Class<?>>();
//                Class<?>[] parameterTypes = new Class[parameterTypeStringArray.length];
//                int i = 0;
        for (String parameterTypeString : parameterTypeStringArray) {
            if (parameterTypeString == null || parameterTypeString.isEmpty()) {
                continue;
            }
            if (parameterTypeString.startsWith(OBJECT_ABRIDGE)) {
                parameterTypeList.add(getTypeByDescriptionString(parameterTypeString));
            } else {
                String loopParameterString = parameterTypeString;
                do {
                    String tempParameterString;
                    if (loopParameterString.startsWith(ARRAY_PREFIX)) {
                        tempParameterString = loopParameterString.substring(0, 2);
                    } else if (loopParameterString.startsWith(OBJECT_ABRIDGE)) {
                        parameterTypeList.add(getTypeByDescriptionString(loopParameterString));
                        break;
                    } else {
                        tempParameterString = loopParameterString.substring(0, 1);
                    }
                    parameterTypeList.add(getTypeByDescriptionString(tempParameterString));
                    loopParameterString = loopParameterString.substring(tempParameterString.length());
                }
                while (loopParameterString != null && !loopParameterString.isEmpty());
            }
        }
        return parameterTypeList;
    }

    public static Class<?> getTypeByDescriptionString(String parameterString) {
        if (parameterString.startsWith(OBJECT_ABRIDGE)) {
            String className = parameterString.substring(1, parameterString.length());
            className = className.replace("/", ".");
            if (className.endsWith(";")) {
                className = className.substring(0, className.length() - 1);
            }
            try {
                return Class.forName(className);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("class not existed");
            }
        }
        Class<?> type = abridge2ClassMap.get(parameterString.substring(0, 1));
        if (type != null) {
            return type;
        }
        if (parameterString.startsWith(ARRAY_PREFIX)) {
            String parameterDescriptor = parameterString.substring(1);
            Class<?> t = getTypeByDescriptionString(parameterDescriptor);
//            String className = parameterString.substring(2, parameterString.length());
//            className = className.replace("/", ".");
//                Class<?> t = Class.forName(className);
                Object o = Array.newInstance(t, 0);
                return o.getClass();
        }
        throw new RuntimeException("unknown type");
    }

    public static Object getObjectByDescription(byte[] bytes, String fieldDescription) {
        Class<?> type = InterpreterUtil.getTypeByDescriptionString(fieldDescription);
        return getObjectByType(bytes, type);
    }

    /**
     * ����byte�����ȡ���󣬵�ȻҲ�����ǻ������ͣ�����ǻ������ͣ��򷵻����װ���͡�
     * Ӧ�ó�����PutStatic��ָ���ջ�л�ȡ�������ֽ����飬��Ҫ�õ���ʵ�Ķ��󣨻��߻������ͣ�
     * ����ǻ������ͣ�ֱ�Ӱ��ֽ�����ת��Ϊ��Ӧ���ͼ��ɡ�
     * ������������ͣ�����Ҫ�Ӷ��л�ȡ��Ӧ�Ķ���
     * 
     * @param bytes
     * @param type
     * @return
     */
    public static Object getObjectByType(byte[] bytes, Class<?> type) {
        if (type == int.class) {
            return ByteUtil.byteArray2Int(bytes);
        }
        if (type == char.class) {
            return (char) ByteUtil.byteArray2Short(bytes);
        }
        if (type == short.class) {
            return ByteUtil.byteArray2Short(bytes);
        }
        if (type == byte.class) {
            if (bytes == null || bytes.length == 0) {
                return null;
            }
            return bytes[0];
        }
        if (type == boolean.class) {
            int result = ByteUtil.byteArray2Int(bytes);
            return (result == com.sharpjvm.bytecode.constants.Constants.TRUE_BYTE) ? true : false;
        }
        if (type == float.class) {
            return ByteUtil.byteArray2Float(bytes);
        }
        if (type == double.class) {
            return ByteUtil.byteArray2Double(bytes);
        }
        return RuntimeContext.getInstance().getHeap().get(bytes);
    }
    
    /**
     * �������ͻ�ȡ�ֽڴ���
     * Ӧ�ó���������GetStaic�������ȡ����Ӧ�ı�������Ҫת��Ϊbyte����push��ջ�С�
     * ���ڻ������ͺ��������͵Ĳ�ͬ�������Ҫ�˷�����
     * �������ͽ���ȡ���Ķ���ֱ��ת��Ϊ�ֽ����顣
     * ������������Ҫ�Ӷ��л�ȡ����ֵ��
     * 
     * @param o ��Ϊ�ӷ����л�ȡ�����Ķ��Ƕ�������ǻ������ͣ���ת��Ϊ��Ӧ�ķ�װ���͡�
     * @param type �����������͡�
     * @return
     */
    public static byte[] getBytesByType(Object o, Class<?> type) {
    	if (type == int.class) {
            return ByteUtil.int2ByteArray((Integer) o);
        }
        if (type == char.class) {
            return ByteUtil.short2ByteArray((short)((int)((Character) o).charValue()));
        }
        if (type == short.class) {
            return ByteUtil.short2ByteArray((Short) o);
        }
        if (type == byte.class) {
//            if (bytes == null || bytes.length == 0) {
//                return null;
//            }
        	byte[] bytes = {(Byte)o};
            return bytes;
        }
        if (type == boolean.class) {
            Boolean boolValue = (Boolean) o;
            byte[] bytes = new byte[1];
            if (boolValue) {
            	bytes[0] = com.sharpjvm.bytecode.constants.Constants.TRUE_BYTE;
            } else {
            	bytes[0] = com.sharpjvm.bytecode.constants.Constants.FALSE_BYTE;
            }
            return bytes;
        }
        if (type == float.class) {
            return ByteUtil.float2ByteArray((Float) o);
        }
        if (type == double.class) {
            return ByteUtil.double2ByteArray((Double) o);
        }
        if (type == long.class) {
            return ByteUtil.long2ByteArray((Long) o);
        }
        return RuntimeContext.getInstance().getHeap().getReference(o);
    }

    /**
     * ����classInfo�ͷ��������Լ����������ҵ�ĳ���������п����������׵ġ�
     * 
     * @param classInfo
     * @param name
     * @param parameterTypes
     * @return
     */
    public static List<Object> findMethod(ClassInfo classInfo, String name, Class<?>[] parameterTypes) {
        ClassInfo loopClass = classInfo;
        do {
            List<MethodInfo> methodInfoList = loopClass.getMethodInfoList();
            if (methodInfoList == null) {
                return null;
            }
            String parameter = getMethodDescriptor(parameterTypes);
            for (MethodInfo methodInfo : methodInfoList) {
                if (!name.equals(methodInfo.getNameConstant().getValue())) {
                    continue;
                }
                if (methodInfo.getDescriptionConstant().getValue().startsWith(parameter)) {
                    List<Object> result = new ArrayList<Object>();
                    result.add(methodInfo);
                    result.add(loopClass);
                    return result;
                }
            }
            String superClassName = loopClass.getSuperClassConstant().getNameConstant().getValue();
            superClassName = superClassName.replace("/", ".");
            try {
                loopClass = RuntimeContext.getInstance().getMethodArea().getClassInfo(superClassName);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("didn't find class : " + superClassName);
            }
        } while (loopClass != null);
        return null;
    }

    private static String getMethodDescriptor(Class<?>[] parameterTypes) {
        StringBuilder parameterString = new StringBuilder();
        parameterString.append(METHOD_PARAMETERS_PREFIX);
        if (parameterTypes != null && parameterTypes.length > 0) {
            for (int i = 0; i < parameterTypes.length; i++) {
                Class<?> type = parameterTypes[i];
                if (type.isArray()) {
                    parameterString.append(ARRAY_PREFIX);
                    type = type.getComponentType();
                }
                if (type.isPrimitive()) {
                    String abridge = abridge2ClassMap.getKeyByValue(type);
                    parameterString.append(abridge);
                } else {
                    parameterString.append(OBJECT_ABRIDGE);
                    parameterString.append(type.getCanonicalName().replace(".", "/"));
                    parameterString.append(PARAMETER_SEPARATOR);
                }
            }
        }
        parameterString.append(METHOD_PARAMETERS_POSTFIX);
        return parameterString.toString();
    }

    public static StackFrame executeMethod(MethodExecutorParameter parameter)
            throws ExecuteException {
        String name = parameter.getMethodName();
        ClassInfo classInfo = parameter.getClassInfo();
        Class<?>[] parameterTypes = getParameterTypes(parameter);
        MethodExecutor methodExecutor;
        String key = getConfigKey(name, classInfo, parameterTypes);
        String configMethodExecutorClassName = SystemConfig.getInstance().getConfigByKey(key);
		if (configMethodExecutorClassName == null
				|| "".equals(configMethodExecutorClassName)) {
			methodExecutor = defaultMethodExecutor;
		} else {
			methodExecutor = getMethodExecutorMap().get(configMethodExecutorClassName);
			if (methodExecutor == null) {
				try {
					methodExecutor = (MethodExecutor) Class.forName(configMethodExecutorClassName).newInstance();
				} catch (Exception e) {
					methodExecutor = defaultMethodExecutor;
				}
				getMethodExecutorMap().put(configMethodExecutorClassName,
						methodExecutor);
			}
		}
        return methodExecutor.execute(parameter);
    }

    public static Class<?>[] getParameterTypes(MethodExecutorParameter parameter) {
        List<MethodExecutorParameter.Parameter> parameters = parameter.getParameters();
        if (parameters == null || parameters.size() == 0) {
            return new Class<?>[0];
        }
        Class<?>[] parameterTypes = new Class<?>[parameters.size()];
        int i = 0;
        for (MethodExecutorParameter.Parameter p : parameters) {
            parameterTypes[i++] = p.getType();
        }
        return parameterTypes;
    }

    private static String getConfigKey(String name, ClassInfo classInfo, Class<?>[] parameterTypes) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        String className = classInfo.getClassName();
        className = className.replace("/", ".");
        sb.append(className);
        sb.append("]");
        sb.append("[");
        sb.append(name);
        sb.append("]");
        sb.append("[");
        sb.append(getMethodDescriptor(parameterTypes));
        return sb.append("]").toString();
    }
    
    private static Map<String, MethodExecutor> getMethodExecutorMap() {
    	if (methodExecutorMap == null) {
    		methodExecutorMap = new HashMap<String, MethodExecutor>();
    	}
    	return methodExecutorMap;
    }

    public static void main(String[] args) {
        Class<?> clazz = int[].class;
        System.out.println(clazz);
        System.out.println(clazz.getComponentType());
    }
}
