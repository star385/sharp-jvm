package com.sharpjvm.interpreter.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 关于文件的工具类
 * <p/>
 * User: zhuguoyin
 * Date: 13-2-17
 * Time: 下午3:44
 * To change this template use File | Settings | File Templates.
 */
public class FileUtil {

    public static List<File> parseClassPathFromString(String classPathString) {
        if (classPathString == null || classPathString.length() == 0) {
            return new ArrayList<File>();
        }
        List<File> result = new ArrayList<File>();
        String[] classPathFileArray = classPathString.split(File.pathSeparator);
        for (String classPathFile : classPathFileArray) {
            File file = new File(classPathFile);
            if (file.exists()) {
                result.add(file);
            }
            if (!file.isDirectory()) {
                continue;
            }
            File[] subFiles = file.listFiles();
            if (subFiles == null) {
                continue;
            }
            for (File subFile : subFiles) {
                if (subFile.isDirectory()) {
                    continue;
                }
                String path = subFile.getAbsolutePath();
                if (path.toLowerCase().endsWith(".jar")) {
                    result.add(subFile);
                }
            }
        }
        return result;
    }
    
    public static String getRtJarPath() {
		String javaHomeEvn = getEnvVarialble("JAVA_HOME");
		if (javaHomeEvn == null || javaHomeEvn.isEmpty()) {
    		return null;
    	}
		String rtJarPath = join(javaHomeEvn, "jre", "lib", "rt.jar");
		return rtJarPath;
	}
    
    public static String getToolsJarPath() {
    	String javaHomeEvn = getEnvVarialble("JAVA_HOME");
    	if (javaHomeEvn == null || javaHomeEvn.isEmpty()) {
    		return null;
    	}
    	String toolsJarPath = join(javaHomeEvn, "lib", "tools.jar");
    	return toolsJarPath;
    }
    
    public static String getClassPathEvn() {
    	String classPathEvn = getEnvVarialble("CLASSPATH");
    	return classPathEvn;
    }
    
    public static String join(String basePath, String... paths) {
		if (basePath == null || basePath.isEmpty()) {
			throw new RuntimeException("base path can not be empty!");
		}
		if (paths == null || paths.length == 0) {
			while (basePath.endsWith(File.separator)) {
				basePath = basePath.substring(0, basePath.length() - 1);
			}
			return basePath;
		}
		String resultPath = basePath;
		for (String path : paths) {
			if (path == null || "".equals(path)) {
				throw new RuntimeException("path can not be empty!");
			}
			if (!resultPath.endsWith(File.separator)) {
				while (resultPath.endsWith("\\")) {
					resultPath = resultPath.substring(0, resultPath.length() - 1);
				}
			} else {
				while (resultPath.endsWith(File.separator)) {
					resultPath = resultPath.substring(0, resultPath.length() - 1);
				}
			}
			resultPath = resultPath + File.separator;
			while (path.startsWith(File.separator) || path.startsWith("\\")) {
				path = path.substring(1);
			}
			if (path == null || "".equals(path)) {
				throw new RuntimeException("path can not be all \"/\" and \"\\\"!");
			}
			resultPath += path;
		}
		while (resultPath.endsWith(File.separator) || resultPath.endsWith("\\")) {
			resultPath = resultPath.substring(0, resultPath.length() - 1);
		}
		return resultPath;
	}
    
    /**
     * 获取环境变量
     * 
     * @param evnName
     * @return
     */
    public static String getEnvVarialble(String evnName) {
        Map<String, String> m = System.getenv();
        for (String key : m.keySet()) {
            if (evnName.equalsIgnoreCase(key)) {
                return m.get(key);
            }
        }
        return "";
    }
}
