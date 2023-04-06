package com.oysen.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 欧阳
 * @Date: 2023/3/4
 * @Description: com.oysen.vo
 * sen
 * version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor //自动生成有参构造
public class CustomerMessage {
    private String column;
    private String value;
    private String customerType;

    /*public CustomerMessage() {

    }*/
}
