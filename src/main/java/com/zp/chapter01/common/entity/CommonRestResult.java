package com.zp.chapter01.common.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * rest 请求通用返回结果数据:
 * <code>
 * {
 *     "content": [],
 *     "metadata": {},
 *     "status": "success",
 *     "code": "000000",
 *     "errors": []
 * }
 *
 * </code>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonRestResult<T> implements Serializable {

    public static final String RESULT_OP_SUCCESS = "操作成功！";

    public static final String RESULT_OP_FAIL = "操作失败！";

    public static final String FAIL = "fail";

    public static final String SUCCESS = "success";

    public static final String DEFAULT_SUCCESS_CODE = "000000";

    public static final String DEFAULT_ERROR_CODE = "999999";

    private static final long serialVersionUID = 1379254493335272705L;

    /**
     * 返回结果
     */
    private T content;

    /**
     * 对结果的元描述，比如分页等信息
     */
    private Map metadata = new HashMap();

    /**
     * 业务的处理结果
     */
    private String status = SUCCESS;

    private String code = DEFAULT_SUCCESS_CODE;

    private String message;

    /**
     * 更多错误
     */
    private List<?> errors = new ArrayList<>();

    /**
     * 填充meta对象信息
     *
     * @param key
     * @param value
     * @return
     */
    public CommonRestResult fillMetaData(String key, Object value) {
        this.metadata.put(key, value);
        return this;
    }

    /**
     * 默认构造函数
     */
    public CommonRestResult() {}

    public CommonRestResult(boolean success, String message, T content) {
        if (success) {
            status = SUCCESS;
            code = DEFAULT_SUCCESS_CODE;
        } else {
            status = FAIL;
            code = DEFAULT_ERROR_CODE;
        }
        this.message = message;
        this.content = content;
    }


    public CommonRestResult(String status, String message, T content) {

        if (status.equals(SUCCESS)) {
            code = DEFAULT_SUCCESS_CODE;
        } else {
            code = DEFAULT_ERROR_CODE;
        }
        this.status = status;
        this.message = message;
        this.content = content;
    }

    public CommonRestResult(final T content) {
        this();
        this.content = content;
    }

    public CommonRestResult(final String status, final String message) {
        this(null);
        this.status = status;
        this.message = message;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public Map getMetadata() {
        return metadata;
    }

    public void setMetadata(Map metadata) {
        this.metadata = metadata;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean success() {
        return "success".equals(status);
    }

    public List<?> getErrors() {
        return errors;
    }

    public void setErrors(List<?> errors) {
        this.errors = errors;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
