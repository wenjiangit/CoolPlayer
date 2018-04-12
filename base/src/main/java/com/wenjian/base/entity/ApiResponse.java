package com.wenjian.base.entity;

import com.google.gson.annotations.Expose;

/**
 * Description: ApiResponse
 * Date: 2018/1/8
 *
 * @author jian.wen@ubtrobot.com
 */

public class ApiResponse<T> {

    private static final String HTTP_OK = "200";

    @Expose
    private String msg;

    @Expose
    private String code;

    @Expose
    private T ret;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getRet() {
        return ret;
    }

    /**
     * 判断请求是否成功
     *
     * @return 成功与否
     */
    public boolean isSuccess() {
        return HTTP_OK.equals(code);
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                ", ret=" + ret +
                '}';
    }
}
