package com.fh.shop.apigateway.filter;

import com.fh.IEeum;

/**
 * @ProjectName: shop-cloud
 * @Package: com.fh.shop.apigateway.filter
 * @ClassName: GatewayEnum
 * @Description: java类作用描述
 * @Author: LiuPeng mll0616@163.com
 * @CreateDate: 2018/11/25 19:38
 * @UpdateUser: LiuPeng mll0616@163.com
 * @UpdateDate: 2018/11/25 19:38
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public enum GatewayEnum implements IEeum {

    ERROR_USER_NAME(10001, "用户名不存在"),
    ERROR_USER_PWD(10002, "密码错误"),
    ERRPT_FAIL_LOGIN(20001,"非法登录"),
    ERROR_USER_CODE(10003, "验证码错误"),
    ERROR_NOT_EXIST(10000, "信息错误"),
    ERROR_USER_LOCK(1004,"用户已锁定"),

    SIGN_IS_ERROR(2004,"无效签名"),
    TIME_OUT(2002,"请求超时"),
    APPSCRECT_IS_EMPTY(2003,"appscrect为空"),
    NONCE_IS_TWO(2004,"请求被攻击"),
    CURRENT_LIMITING(2005,"请求被限流"),
    HEDERS_IS_EMPTY(2001,"请求头信息不完整");

    private  Integer code;

    private  String message;


    private GatewayEnum() {

    }

    private GatewayEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
