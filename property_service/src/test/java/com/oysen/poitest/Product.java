package com.oysen.poitest;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 此类型用于测试用户利用poi读取Excel表格功能
 * 将读取的数据存入Product中
 * Excel中的每一条数据为一个Product实列
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Integer id;
    private String name;
    private Double price;
    private Integer count;

}
