package com.study.bean;

/**
 * @author 朱天乐
 */
public class Result<T> {
    private String code;
    private String msg;
    private T data;

    public Result() {

    }

    public Result(T data) {
        this.data = data;
    }

    public static Result success() {
        Result<Object> result = new Result<>();
        result.setCode("200");
        result.setMsg("成功");

        return result;
    }

    //静态方法中使用泛型需要在static后加 <T>
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>(data);
        result.setCode("200");
        result.setMsg("成功");

        return result;
    }


    public static Result error(String code, String msg) {
        Result<Object> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);

        return result;
    }

    public static <T> Result<T> error(String code, String msg, T data) {
        Result<T> result = new Result<>(data);
        result.setCode(code);
        result.setMsg(msg);

        return result;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
