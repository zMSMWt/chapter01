package com.zp.chapter01.common.entity;import com.zp.chapter01.common.errors.ServiceErrors;import java.io.Serializable;public class Result<D> implements Serializable {    private D data;    private boolean success;    private String code;    private String message;    private Integer type;    public Result() {    }    public static <D> Result<D> wrapSuccessfulResult(D data) {        Result<D> result = new Result<D>();        result.data = data;        result.success = true;        result.code = "000000";        return result;    }    public static <T> Result<T> wrapSuccessfulResult(String message, T data) {        Result<T> result = new Result<T>();        result.data = data;        result.success = true;        result.code = "000000";        result.message = message;        return result;    }    public static <T> Result<T> wrapSuccessfulResultt(Integer type, T data) {        Result<T> result = new Result<T>();        result.data = data;        result.success = true;        result.code = "000000";        result.type = type;        return result;    }    public static <D> Result<D> wrapErrorResult(ServiceErrors error) {        Result<D> result = new Result<D>();        result.success = false;        result.code = error.getCode();        result.message = error.getMessage();        return result;    }    public static <D> Result<D> wrapErrorResult(ServiceErrors error, Object... extendMsg) {        Result<D> result = new Result<D>();        result.success = false;        result.code = error.getCode();        result.message = String.format(error.getMessage(), extendMsg);        return result;    }    public static <D> Result<D> wrapErrorResult(String code, String message) {        Result<D> result = new Result<D>();        result.success = false;        result.code = code;        result.message = message;        return result;    }    public static <D> Result<D> wrapErrorStringResult(String error) {        Result<D> result = new Result<D>();        result.success = false;        result.code = "999999";        result.message = error;        return result;    }    public D getData() {        return this.data;    }    public Result<D> setData(D data) {        this.data = data;        return this;    }    public boolean isSuccess() {        return this.success;    }    public Result<D> setSuccess(boolean success) {        this.success = success;        return this;    }    public String getCode() {        return this.code;    }    public Result<D> setCode(String code) {        this.code = code;        return this;    }    public String getMessage() {        return this.message;    }    public Result<D> setMessage(String message) {        this.message = message;        return this;    }    @Override    public String toString() {        StringBuilder sb = new StringBuilder();        sb.append("{");        sb.append("success=");        sb.append(this.success);        sb.append(",");        sb.append("code=");        sb.append(this.code);        sb.append(",");        sb.append("message=");        sb.append(this.message);        sb.append(",");        sb.append("data=");        sb.append(this.data);        sb.append(",");        sb.append("type=");        sb.append(this.type);        sb.append("}");        return sb.toString();    }}