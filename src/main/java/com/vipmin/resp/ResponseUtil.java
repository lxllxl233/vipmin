package com.vipmin.resp;

import java.util.HashMap;

public class ResponseUtil {
    private Boolean success;
    private Integer code;
    private String message;
    private HashMap<Object,Object> data = null;

    public ResponseUtil() {
    }

    private ResponseUtil(boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public static ResponseUtil ok(String message){
        return new ResponseUtil(true,ResponseCode.SUCCESS,message);
    }
    public static ResponseUtil error(String message){
        return new ResponseUtil(false,ResponseCode.ERROR,message);
    }
    public ResponseUtil data(Object k,Object v){
        if (null == data){
            data = new HashMap<>();
        }
        data.put(k, v);
        return this;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HashMap<Object, Object> getData() {
        return data;
    }

    public void setData(HashMap<Object, Object> data) {
        this.data = data;
    }
}
