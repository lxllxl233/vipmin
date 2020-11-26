package com.vipmin.resp;

import org.springframework.web.bind.annotation.ControllerAdvice;

//全局统一异常处理类
@ControllerAdvice
public class RException {

    //其他异常
//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public R error(Exception e){
//        e.printStackTrace();
//        return new R(false,ResultCode.GLOBAL,"服务器出现了异常",e.getMessage());
//    }
//
//    //特定异常
//    @ExceptionHandler(ArithmeticException.class)
//    @ResponseBody
//    public R tError(ArithmeticException e){
//        e.printStackTrace();
//        return new R(false,ResultCode.GLOBAL,"服务器出现了 by zero 异常",e.getMessage());
//    }
//
//    //特定异常-处理自定义异常
//    @ExceptionHandler(YouNaiException.class)
//    @ResponseBody
//    public R myError(YouNaiException e){
//        e.printStackTrace();
//        return new R(false,ResultCode.GLOBAL,"服务器出现了YouNai异常",e.getMessage());
//    }
}
