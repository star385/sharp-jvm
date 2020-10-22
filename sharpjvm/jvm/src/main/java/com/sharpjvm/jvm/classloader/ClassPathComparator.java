package com.sharpjvm.jvm.classloader;

import java.io.File;
import java.util.Comparator;

import com.sharpjvm.interpreter.util.FileUtil;

/**
 * ��·���Ƚ�������Ҫ��rt.jar��tools.jar�ŵ���ǰ��
 * 
 * @author zhuguoyin
 *
 */
public class ClassPathComparator implements Comparator<File> {

	public int compare(File o1, File o2) {
		String rtJarFilePath = FileUtil.getRtJarPath();
		String toolsJarFilePath = FileUtil.getToolsJarPath();
 		String file1Name = o1.getPath();
		String file2Name = o2.getPath();
		if (file1Name.equals(rtJarFilePath)) {
			return -1;
		}
		
		if (file2Name.equals(rtJarFilePath)) {
			return 1;
		}
		
		if (file1Name.equals(toolsJarFilePath)) {
			return -1;
		}
		
		if (file2Name.equals(toolsJarFilePath)) {
			return 1;
		}
		
		return 0;
	}

}
