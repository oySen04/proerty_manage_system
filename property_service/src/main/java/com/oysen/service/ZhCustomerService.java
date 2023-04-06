package com.oysen.service;

import com.oysen.bean.ZhCustomer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oysen.bean.ZhCustomerEstate;
import com.oysen.vo.CustomerMessage;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


public interface ZhCustomerService extends IService<ZhCustomer> {
    /**
     * 新增业主信息
     * @param zhCustomer 前端传递的业主信息
     * @return 业主信息是否新增成功
     */
    Integer insertCustomer(ZhCustomer zhCustomer);

    /**
     * 查询全部业主信息
     * @return
     */
    List<ZhCustomer> selectAllCustomer();

    /**
     * 根据用户的选择的值查询业主信息
     * @param column 对应查询的列
     * @param value 对应列的值
     * @return 业主信息
     */
    List<ZhCustomer> selectCustomerByColumnAndValue(String column, String value);

    /**
     * 根据业主类型查询业主信息
     * @param customerType 业主类型
     * @return 业主信息
     */
    List<ZhCustomer> selectByCustomerByCustomerType(String customerType);

    /**
     *根据前端传递的业主编码信息修改业主状态
     * @param customerCodes 业主编码,如出现多个业主编码会通过"|"分割
     * @param status 状态码: 1启用,0禁用
     * @return 修改业主状态信息结果
     */
    Integer UpdateCustomerStatusByCustomerCode(String customerCodes,String status);

    /**
     * 统一查询功能 通过用户所传递的参数进行对应发查询
     * @param customerMessage 用户传递的参数
     * @return 查询的结果: 业主信息
     */
    List<ZhCustomer> selectCustomer(CustomerMessage customerMessage);

    /**
     * 接收并解析Excel表格的所有数据，以实体类ZhCustomer集合类型接收，并根据公司来新增业主数据
     * @param customers 解析后的集合类型业主信息
     * @param company 公司id
     * @return
     */
    Integer insertAll(List<ZhCustomer> customers,String company);

    /**
     * 新增业主入住信息
     * @param zhCustomerEstate 业主与房产关系数据
     * @return 是否添加成功
     */
    Integer insertCustomerOrEstate(ZhCustomerEstate zhCustomerEstate);
}
