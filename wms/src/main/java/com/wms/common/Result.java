package com.wms.common;

import lombok.Data;

// getter和setter方法@Dara注解
@Data
public class Result {
    private int code;           // 200、400
    private String message;     // 成功、失败
    private Long total;         // 总记录数量
    private Object data;        // 数据

    public static Result fail(){
        return result(400,"失败",0L,null);
    }
    public static Result suc(){
        return result(200,"成功",0L,null);
    }
    public static Result suc(Object data){
        return result(200,"成功",0L,data);
    }
    public static Result suc(Object data,Long total){
        return result(200,"成功",total,data);
    }
    public static  Result result(int code,String msg, Long total,Object data){
        Result res = new Result();
        res.setData(data);
        res.setMessage(msg);
        res.setCode(code);
        res.setTotal(total);
        return res;
    }

}
