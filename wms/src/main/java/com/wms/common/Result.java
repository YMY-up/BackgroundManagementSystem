package com.wms.common;

import lombok.Data;


// @Data注解相当于自动生成Getter和Setter、toString方法
@Data
public class Result {
    private int code;           // 200、400
    private String message;     // 成功、失败
    private Long total;         // 总记录数量
    private Object data;        // 数据

    /**
     * 添加失败，没有记录数据和记录条数
     * @return  返回结果对象
     */
    public static Result fail(){
        return result(400,"失败",0L,null);
    }

    /**
     *  添加成功1
     * @return 然后结果对象
     */
    public static Result suc(){
        return result(200,"成功",0L,null);
    }

    /**
     *  添加成功2
     * @param data  存储的数据
     * @return  result成功的对象
     */
    public static Result suc(Object data){
        return result(200,"成功",0L,data);
    }

    /**
     * 添加成功3
     * @param data  存储的数据
     * @param total 记录的总数量
     * @return  返回结果对象
     */
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
