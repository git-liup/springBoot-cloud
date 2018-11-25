package com.fh.shop.apigateway.filter;

import com.fh.CheckSumBuilder;
import com.fh.RedisUtil;
import com.fh.ServerResponse;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: shop-cloud
 * @Package: com.fh.shop.apigateway.filter
 * @ClassName: GatewayFilter
 * @Description: java类作用描述
 * @Author: LiuPeng mll0616@163.com
 * @CreateDate: 2018/11/25 18:23
 * @UpdateUser: LiuPeng mll0616@163.com
 * @UpdateDate: 2018/11/25 18:23
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class GatewayFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 5;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    private static final long times=30*1000l;
    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String appkey =  request.getHeader("appkey");
        String time =  request.getHeader("time");
        String sign =  request.getHeader("sign");
        String nonce =  request.getHeader("nonce");
        //判断头信息
        if (StringUtils.isEmpty(appkey) || StringUtils.isEmpty(time) || StringUtils.isEmpty(sign)){
            return ServerResponse.error(GatewayEnum.HEDERS_IS_EMPTY);
        }
        //是否超时
        long parselong = Long.parseLong(time);
        if (parselong + times < System.currentTimeMillis()){
            return  ServerResponse.error(GatewayEnum.TIME_OUT);
        }
        //验证nonce
        Boolean success = RedisUtil.setExpireNX(nonce, "1", 60);
        if(!success){
            return ServerResponse.error(GatewayEnum.NONCE_IS_TWO);
        }
        /*String appScrect = appService.findAppScrectByAppkey(appkey);
        if (StringUtils.isEmpty(appScrect)){
            return ServerResponse.error(GatewayEnum.APPSCRECT_IS_EMPTY);
        }
        String checkSum = CheckSumBuilder.getCheckSum("appScrect", "", "time");
        if (!checkSum.equals(sign)){
            return ServerResponse.error(GatewayEnum.SIGN_IS_ERROR);
        }*/

        return null;
    }
}
