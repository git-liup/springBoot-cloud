package com.fh.shop.apigateway.config;

import com.fh.shop.apigateway.filter.GatewayFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName: shop-cloud
 * @Package: com.fh.shop.apigateway.config
 * @ClassName: SysConfig
 * @Description: java类作用描述
 * @Author: LiuPeng mll0616@163.com
 * @CreateDate: 2018/11/25 18:58
 * @UpdateUser: LiuPeng mll0616@163.com
 * @UpdateDate: 2018/11/25 18:58
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Configuration
public class SysConfig {
    @Bean
    public GatewayFilter fatewayFilter(){
        return new GatewayFilter();
    }
}
