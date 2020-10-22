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
 * �������������
 * <p/>
 * User: zhuguoyin
 * Date: 13-2-17
 * Time: ����9:31
 * To change this template use File | Settings | File Templates.
 */
public class BootStrap extends ClassInfoLoader {

    private List<File> classPathFiles;

    /**
     * ��������ȡ�ֽ����ݣ�����˼·��
     * 1.��ϵͳ�Ļ��������л�ȡ��·��
     * 2.��ִ�в����л�ȡ��·��
     * 3.�ֱ��ж�ÿ����·�����ǲ��Ǵ��ڸ��࣬�����򽫶��������ݶ����ڴ棬�����ڣ������Ѱ���¸���·����
     * 4.���������·����û����ָ�����࣬�׳�ClassNotFoundException
     *
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    protected byte[] getByteCodeFromClassName(String name) throws ClassNotFoundException {
        if (name == null || name.length() == 0) {
            throw new RuntimeException("class name can not be null!");
        }
        // �����·��û��
        if (classPathFiles == null) {
            // ��ϵͳ����������ִ�в����л�ȡ��·��
            parseClassPathFiles();
        }
        if (classPathFiles == null || classPathFiles.isEmpty()) {
            throw new ClassNotFoundException("no class path found!");
        }

        // ������תΪ·��
        String path = classNameToPath(name);
        for (File classPathFile : classPathFiles) {
            byte[] byteCode;
            //����·����һ��Ŀ¼ʱ
            if (classPathFile.isDirectory()) {
                byteCode = getByteCodeFromDirectory(classPathFile, path);
                // ��·������Ŀ¼�ǵ����ļ�
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
     * ����Ŀ¼�л�ȡ���ļ���
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
     * ��jar��ȡ���ļ���
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
        // ȥ��
        if (classPathFiles != null && !classPathFiles.contains(file)) {
            classPathFiles.add(file);
        }
    }

}
