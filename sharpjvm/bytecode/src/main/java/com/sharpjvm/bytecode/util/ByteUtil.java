package com.sharpjvm.bytecode.util;

/**
 * 字节数组与各种基本类型之间转换的工具类。
 *
 * User: zhuguoyin
 * Date: 13-1-24
 * Time: 下午11:52
 * To change this template use File | Settings | File Templates.
 */
public class ByteUtil {

    public static byte[] short2ByteArray(short number) {
        byte[] bytes = new byte[2];
        bytes[0] = (byte) ((number >> 8) & 0xff);
        bytes[1] = (byte) (number & 0xff);
        return bytes;
    }

    public static short byteArray2Short(byte [] bytes) {
        if (bytes == null || bytes.length <= 0) {
            return 0;
        }
        if (bytes.length == 1) {
            return (short)(bytes[0] & 0xff);
        }
        return (short ) ((short) bytes[0] << 8 | ((short)bytes[1] & 0xff));
    }

    public static byte[] int2ByteArray(int  number) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte ) (number >> 24 );
        bytes[1] = (byte ) (number >> 16 );
        bytes[2] = (byte ) (number >> 8 );
        bytes[3] = (byte ) (number);
        return bytes;
    }

    public static int byteArray2Int(byte[] bytes) {
        int result;
        if (bytes == null || bytes.length == 0) {
            throw new RuntimeException("不能把空字节数组转换为int");
        }
        if (bytes.length == 1) {
            return bytes[0];
        }
        if (bytes.length == 2) {
            result = (bytes[0] & 0xff) << 8;
            result |= (bytes[1] & 0xff);
            return result;
        }
        if (bytes.length == 3) {
            result = (bytes[0] & 0xff) << 16;
            result |= (bytes[1] & 0xff) << 8;
            result |= (bytes[2] & 0xff);
            return result;
        }
        result = (bytes[0] & 0xff) << 24;
        result |= (bytes[1] & 0xff) << 16;
        result |= (bytes[2] & 0xff) << 8;
        result |= (bytes[3] & 0xff);
        return result;
    }

    public static float byteArray2Float(byte[] bytes) {
        int i =  byteArray2Int(bytes);
        return Float.intBitsToFloat(i);
    }

    public static byte[] float2ByteArray(float f) {
        return int2ByteArray(Float.floatToRawIntBits(f));
    }

    public static byte[] long2ByteArray(long l) {
        byte[] bytes = new byte[8];
        for (int i = 1; i < 9; i++) {
            bytes[i - 1] = (byte ) (l >> 64 - (i * 8));
        }
        return bytes;
    }

    public static long byteArray2Long(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            throw new RuntimeException("不能把空字节数组转换为long");
        }
        long result = 0;
        int lshCount = (bytes.length - 1) * 8;
        for (int i = 0; i < bytes.length; i++) {
            if (i == 0) {
                result = (((long) bytes[i] & 0xff) << lshCount);
            } else {
                result |= (((long) bytes[i] & 0xff) << lshCount);
            }
            lshCount -= 8;
        }
//        for (int i = 0; i < 8; i++) {
//            if (7 - i >= bytes.length) {
//                continue;
//            }
//            result |= (((long) bytes[7 - i] & 0xff) << (i * 8));
////            result = (((long) bytes[0] & 0xff) << 56)
////                    | (((long) bytes[1] & 0xff) << 48)
////                    | (((long) bytes[2] & 0xff) << 40)
////                    | (((long) bytes[3] & 0xff) << 32)
////                    | (((long) bytes[4] & 0xff) << 24)
////                    | (((long) bytes[5] & 0xff) << 16)
////                    | (((long) bytes[6] & 0xff) << 8)
////                    | (((long) bytes[7] & 0xff) << 0);
//        }
        return result;
    }

    public static byte[] double2ByteArray(double d) {
        long l = Double.doubleToLongBits(d);
        return long2ByteArray(l);
    }

    public static double byteArray2Double(byte[] bytes) {
        return Double.longBitsToDouble(byteArray2Long(bytes));
    }

    /**
     * 判断两个字节数组内容是否相同
     *
     * @param byteArray1
     * @param byteArray2
     * @return
     */
    public static boolean contentEquals(byte [] byteArray1, byte [] byteArray2) {
        if (byteArray1 == null || byteArray2 == null) {
            return false;
        }
        if (byteArray1.length != byteArray2.length) {
            return false;
        }
        for (int i = 0; i < byteArray1.length; i++) {
            if (byteArray1[i] != byteArray2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        byte[] bytes = {78, 126, 0, 0};
        System.out.println(byteArray2Float(bytes));
    }

}
