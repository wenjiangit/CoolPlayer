package com.wenjian.myplayer.data.network.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.Expose;

/**
 * Description: HttpResponse
 * Date: 2018/1/8
 *
 * @author jian.wen@ubtrobot.com
 */

public class HttpResponse {

    private static final Gson GSON;

    private static final String HTTP_OK = "200";

    static {
        GSON = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    @Expose
    private String msg;

    @Expose
    private String code;

    @Expose
    private Object ret;

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

    public Object getRet() {
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

    /**
     * 通过传递class类型获取bean对象
     *
     * @param clazz Class
     * @param <T>   泛型类型
     * @return 实例
     */
    public <T> T getResult(Class<T> clazz) {
        try {
            return GSON.fromJson(GSON.toJson(ret), clazz);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public String toString() {
        return "HttpResponse{" +
                "msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                ", ret=" + ret +
                '}';
    }
}
