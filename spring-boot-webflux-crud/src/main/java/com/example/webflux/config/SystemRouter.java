//package com.example.webflux.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.reactive.function.server.RouterFunction;
//import org.springframework.web.reactive.function.server.ServerResponse;
//
//import static org.springframework.http.MediaType.APPLICATION_JSON;
//import static org.springframework.web.reactive.function.server.RequestPredicates.*;
//import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
//import static org.springframework.web.reactive.function.server.RouterFunctions.route;
//
///**
// * 接口路由表
// *
// * @author hejunlin@skyworth.com
// * @version 1.0
// * @since 2019/6/13 18:11
// */
//@Configuration
//public class SystemRouter {
//
//
//    /**
//     * 隐私信息路由
//     *
//     * @param handler 隐私信息操作类
//     * @return router
//     */
//    @Bean
//    public RouterFunction<ServerResponse> privacyRouter(PrivacyHandler handler) {
//        return nest(path("/api/v1/privacy"),
//                nest(accept(APPLICATION_JSON),
//                        route(POST("/captcha"), handler::sendCaptcha)
//                ));
//    }
//}
