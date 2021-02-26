package com.cvaiedu.template.exception;

import com.cvaiedu.template.result.ResultEnum;

public class FrameworkException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String msg;
    private int code = ResultEnum.ERROR_500.getCode();

    public FrameworkException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public FrameworkException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.msg = resultEnum.getMsg();
        this.code = resultEnum.getCode();
    }

    public FrameworkException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public FrameworkException(int code, String msg) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public FrameworkException(int code, String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
