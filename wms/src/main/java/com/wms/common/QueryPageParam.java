package com.wms.common;

import lombok.Data;

import java.util.HashMap;

@Data
public class QueryPageParam {
    /*分页的实现*/

    // 常量页码默认为20页
    private static int PAGE_SIZE = 20;
    // 默认页码从第一页开始
    private static int PAGE_NUM = 1;

    private int pageSize = PAGE_SIZE;
    private int pageNum = PAGE_NUM;

    // 定义HashMap容器,用于存放其他内容的集合
    private HashMap param = new HashMap<>();
}
