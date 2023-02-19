package jpabook.jpashop.Entity;

import jpabook.jpashop.Enum.StatusEnum;

import java.io.Serializable;

/*
* data return
* */
public class ResultEntity<T>{
    private int statusCode = StatusEnum.OK.getStatusCode();
    private String code = StatusEnum.OK.getCode();

    private T data;


    public ResultEntity() {}

    public ResultEntity(int statusCode, String code) {
        this.statusCode = statusCode;
        this.code = code;
    }

    public ResultEntity(T data) {
        this.data = data;
    }

    public ResultEntity(int statusCode, String code, T data) {
        this.statusCode = statusCode;
        this.code = code;
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
