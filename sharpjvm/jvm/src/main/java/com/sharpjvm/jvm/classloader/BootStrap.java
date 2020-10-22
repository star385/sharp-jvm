package com.sharpjvm.jvm.classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.sharpjvm.interpreter.util.FileUtil;
import com.sharpjvm.memory.classloader.ClassInfoLoader;
import com.sharpjvm.memory.runtime.RuntimeContext;

/**
 * 引导类加载器。
 * <p/>
 * User: zhuguoyin
 * Date: 13-2-17
 * Time: 上午9:31
 * To change this template use File | Settings | File Templates.
 */
public class BootStrap extends ClassInfoLoader {

    private List<File> classPathFiles;

    /**
     * 从类名获取字节数据，整体思路：
     * 1.从系统的环境变量中获取类路径
     * 2.从执行参数中获取类路径
     * 3.分别判断每个类路径下是不是存在该类，存在则将二进制数据读入内存，不存在，则继续寻找下个类路径。
     * 4.如果所有类路径都没找着指定的类，抛出ClassNotFoundException
     *
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    protected byte[] getByteCodeFromClassName(String name) throws ClassNotFoundException {
        if (name == null || name.length() == 0) {
            throw new RuntimeException("class name can not be null!");
        }
        // 如果类路径没有
        if (classPathFiles == null) {
            // 从系统环境变量和执行参数中获取类路径
            parseClassPathFiles();
        }
        if (classPathFiles == null || classPathFiles.isEmpty()) {
            throw new ClassNotFoundException("no class path found!");
        }

        // 将类名转为路径
        String path = classNameToPath(name);
        for (File classPathFile : classPathFiles) {
            byte[] byteCode;
            //该类路径是一个目录时
            if (classPathFile.isDirectory()) {
                byteCode = getByteCodeFromDirectory(classPathFile, path);
                // 类路径不是目录是单个文件
            } else {
                byteCode = getByteCodeFromJarFile(classPathFile, path);
            }
            if (byteCode != null) {
                return byteCode;
            }
        }
        throw new ClassNotFoundException("didn't found class" + name);
    }

    /**
     * 从类目录中获取类文件。
     *
     * @param classPathFile
     * @param path
     * @return
     */
    private byte[] getByteCodeFromDirectory(File classPathFile, String path) {
        String dir = classPathFile.getAbsolutePath();
        String fullPath = FileUtil.join(dir, path);
        File fullPathFile = new File(fullPath);
        if (!fullPathFile.exists()) {
            return null;
        }
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(fullPathFile);
            byte[] byteCode = new byte[fis.available()];
            fis.read(byteCode);
            return byteCode;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	try {
            	fis.close();
            } catch (Exception e1) {
            	
            }
        }
    }

    /**
     * 从jar获取类文件。
     *
     * @param classPathFile
     * @param path
     * @return
     */
    private byte[] getByteCodeFromJarFile(File classPathFile, String path) {
        InputStream in = null;
        try {
            JarFile jarFile = new JarFile(classPathFile);
            Enumeration<JarEntry> enumeration = jarFile.entries();
            while (enumeration.hasMoreElements()) {
                JarEntry jarEntry = enumeration.nextElement();
                if (jarEntry.isDirectory()) {
                    continue;
                }
                String jarEntryName = jarEntry.getName();
                if (jarEntryName.endsWith(path)) {
                    in = jarFile.getInputStream(jarEntry);
                    byte[] byteCode = new byte[in.available()];
                    read(byteCode, in);
//                    in.read(byteCode);
                    in.close();
                    return byteCode;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private void read(byte[] byteCode, InputStream in) throws IOException {
        int onceByteLength = 8192;
        int totalCount = in.available();
        int readCount = 0;
        while (totalCount - readCount > 0) {
            int readLength;
            if (totalCount - readCount > onceByteLength) {
                readLength = in.read(byteCode, readCount, onceByteLength);
            } else {
                readLength = in.read(byteCode, readCount, (totalCount - readCount));
            }
            readCount += readLength;
        }
    }

    private String classNameToPath(String name) {
        String path = name.replace(".", File.separator);
        return path + ".class";
    }

    private void parseClassPathFiles() {
        classPathFiles = new ArrayList<File>();
        String classPathEvn = FileUtil.getClassPathEvn();
        List<File> classPathListFromEvn = FileUtil.parseClassPathFromString(classPathEvn);
        if (classPathListFromEvn != null) {
            for (File file : classPathListFromEvn) {
                addFileToClassPathFiles(file);
            }
        }

        String rtJarPath = FileUtil.getRtJarPath();
        String toolsJarPath = FileUtil.getRtJarPath();
        if (rtJarPath != null && toolsJarPath != null) {
            String classPathString = rtJarPath + File.pathSeparator + toolsJarPath;
            List<File> classPathListFromJavaHome = FileUtil.parseClassPathFromString(classPathString);
            for (File file : classPathListFromJavaHome) {
                addFileToClassPathFiles(file);
            }
        }

        String optionClassPath = RuntimeContext.getInstance().getOptions().getClassPath();
        List<File> optionClassFiles = FileUtil.parseClassPathFromString(optionClassPath);
        if (optionClassFiles != null) {
            for (File file : optionClassFiles) {
                addFileToClassPathFiles(file);
            }
        }
        Collections.sort(classPathFiles, new ClassPathComparator());
    }

    private void addFileToClassPathFiles(File file) {
    	if (file == null) {
    		return;
    	}
    	if (!file.exists()) {
    		return;
    	}
        // 去重
        if (classPathFiles != null && !classPathFiles.contains(file)) {
            classPathFiles.add(file);
        }
    }

}
