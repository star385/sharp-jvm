package com.sharpjvm.jvm.option;

import java.util.ArrayList;
import java.util.List;

import com.sharpjvm.memory.option.Options;

/**
 * 默认的参数解析器
 *
 * User: zhuguoyin
 * Date: 13-2-17
 * Time: 上午11:53
 * To change this template use File | Settings | File Templates.
 */
public class DefaultOptionParser implements OptionParser {

    public Options parseOption(String[] parameters) {
        Options options = new Options();
        if (parameters == null || parameters.length == 0) {
            return options;
        }
        int startIndex = 0;
        while (startIndex < parameters.length) {
            int optionLength = parseOneOption(options, parameters, startIndex);
            startIndex += optionLength;
        }
        return options;
    }

    private int parseOneOption(Options options, String[] parameters, int startIndex) {
        String parameterName = parameters[startIndex];
        if (parameters[startIndex].toLowerCase().startsWith("-xms")) {
            int xmsCountStart = 4;
            List<Integer> countList = new ArrayList<Integer>();
            while ('0' <= parameterName.charAt(xmsCountStart) && parameterName.charAt(xmsCountStart) <= '9') {
                countList.add(parameterName.charAt(xmsCountStart) - '0');
                xmsCountStart++;
            }

            int number = countNumberByList(countList);

            String unit = parameterName.substring(xmsCountStart);
            long totalCount;
            if (unit == null || "".equals(unit)) {
                totalCount = number;
            } else if ("b".equalsIgnoreCase(unit)) {
                totalCount = number;
            } else if ("k".equalsIgnoreCase(unit)) {
                totalCount = number * (2 << 9);
            } else if ("m".equalsIgnoreCase(unit)) {
                totalCount = number * (2 << 19);
            } else if ("g".equalsIgnoreCase(unit)) {
                totalCount = number * (2 << 29);
            } else {
                System.err.println("不识别的参数！");
                return 1;
            }

            if (totalCount <= 0 || totalCount > (2 << 29)) {
                System.err.println("xmx必须在0~2G之间");
                return 1;
            }
            options.setXms((int) totalCount);
        } else if (parameters[startIndex].toLowerCase().startsWith("-xmx")) {
            int xmsCountStart = 4;
            List<Integer> countList = new ArrayList<Integer>();
            while ('0' <= parameterName.charAt(xmsCountStart) && parameterName.charAt(xmsCountStart) <= '9') {
                countList.add(parameterName.charAt(xmsCountStart) - '0');
                xmsCountStart++;
            }

            int number = countNumberByList(countList);

            String unit = parameterName.substring(xmsCountStart);
            long totalCount;
            if (unit == null || "".equals(xmsCountStart)) {
                totalCount = number;
            } else if ("b".equalsIgnoreCase(unit)) {
                totalCount = number;
            } else if ("k".equalsIgnoreCase(unit)) {
                totalCount = number * (2 << 9);
            } else if ("m".equalsIgnoreCase(unit)) {
                totalCount = number * (2 << 19);
            } else if ("g".equalsIgnoreCase(unit)) {
                totalCount = number * (2 << 29);
            } else {
                System.err.println("不识别的参数！");
                return 1;
            }

            if (totalCount <= 0 || totalCount > (2 << 29)) {
                System.err.println("xmx必须在0~2G之间");
                return 1;
            }
            options.setXmx((int) totalCount);
            return 1;
        } else if (parameters[startIndex].toLowerCase().startsWith("-XX:PermSize=")) {
            int xmsCountStart = "-XX:PermSize=".length();
            List<Integer> countList = new ArrayList<Integer>();
            while ('0' <= parameterName.charAt(xmsCountStart) && parameterName.charAt(xmsCountStart) <= '9') {
                countList.add(parameterName.charAt(xmsCountStart) - '0');
                xmsCountStart++;
            }

            int number = countNumberByList(countList);

            String unit = parameterName.substring(xmsCountStart);
            long totalCount;
            if (unit == null || "".equals(xmsCountStart)) {
                totalCount = number;
            } else if ("b".equalsIgnoreCase(unit)) {
                totalCount = number;
            } else if ("k".equalsIgnoreCase(unit)) {
                totalCount = number * (2 << 9);
            } else if ("m".equalsIgnoreCase(unit)) {
                totalCount = number * (2 << 19);
            } else if ("g".equalsIgnoreCase(unit)) {
                totalCount = number * (2 << 29);
            } else {
                System.err.println("不识别的参数！");
                return 1;
            }

            if (totalCount <= 0 || totalCount > (2 << 29)) {
                System.err.println("xmx必须在0~2G之间");
                return 1;
            }
            options.setPermSize((int) totalCount);
            return 1;
        } else if (parameters[startIndex].toLowerCase().startsWith("-XX:MaxPermSize=")) {
            int xmsCountStart = "-XX:MaxPermSize=".length();
            List<Integer> countList = new ArrayList<Integer>();
            while ('0' <= parameterName.charAt(xmsCountStart) && parameterName.charAt(xmsCountStart) <= '9') {
                countList.add(parameterName.charAt(xmsCountStart) - '0');
                xmsCountStart++;
            }

            int number = countNumberByList(countList);

            String unit = parameterName.substring(xmsCountStart);
            long totalCount;
            if (unit == null || "".equals(xmsCountStart)) {
                totalCount = number;
            } else if ("b".equalsIgnoreCase(unit)) {
                totalCount = number;
            } else if ("k".equalsIgnoreCase(unit)) {
                totalCount = number * (2 << 9);
            } else if ("m".equalsIgnoreCase(unit)) {
                totalCount = number * (2 << 19);
            } else if ("g".equalsIgnoreCase(unit)) {
                totalCount = number * (2 << 29);
            } else {
                System.err.println("不识别的参数！");
                return 1;
            }

            if (totalCount <= 0 || totalCount > (2 << 29)) {
                System.err.println("xmx必须在0~2G之间");
                return 1;
            }
            options.setMaxPermSize((int) totalCount);
            return 1;
        } else if (parameterName.toLowerCase().equals("-classpath")) {
            options.setClassPath(parameters[startIndex + 1]);
            return 2;
        } else if (!parameterName.startsWith("-")) {
            options.setMainClass(parameterName);
            return 1;
        }
        return 1;
    }

    private int countNumberByList(List<Integer> countList) {
        int result = 0;
        int degrade = 1;
        for (int i = countList.size() - 1; i > -1; i--) {
            result += countList.get(i) * degrade;
            degrade *= 10;
        }
        return result;
    }

    public static void main(String[] args) {
        String[] parameters = {"-Xms60M", "-Xmx80G", "-XX:MaxPermSize=40K"};
        Options options = new DefaultOptionParser().parseOption(parameters);
        System.out.println(options);
    }
}
