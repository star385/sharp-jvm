package com.sharpjvm.jvm.test.complex;

/**
 * 校验结果
 * 
 * User: zhuguoyin
 * Date: 13-3-31
 * To change this template use File | Settings | File Templates.
 */
public class ValidateResult {

	// 是否成功
    private boolean success;

    // 失败消息
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
