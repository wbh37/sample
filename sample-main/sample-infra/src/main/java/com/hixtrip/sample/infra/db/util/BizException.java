package com.hixtrip.sample.infra.db.util;


public class BizException extends BaseException {

    private int error_code = 403;

    public BizException() {
        super("业务受阻或异常");
    }

    public BizException(String error) {
        super(error);
    }

    public BizException(int code, String error) {
        super(error);
        this.error_code = code;
    }

    @Override
    public int getErrorCode() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }


}
