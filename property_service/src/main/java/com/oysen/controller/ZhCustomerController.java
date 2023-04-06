package com.oysen.controller;


import com.oysen.bean.ZhCustomer;
import com.oysen.bean.ZhCustomerEstate;
import com.oysen.result.R;
import com.oysen.service.ZhCustomerService;
import com.oysen.util.ExcelUtil;
import com.oysen.vo.CustomerMessage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 业主信息表 前端控制器
 * </p>
 *
 * @author yang
 * @since 2023-03-03
 */
@RestController
@RequestMapping("/zhCustomer")
public class ZhCustomerController {
    @Resource
    private ZhCustomerService customerService;

    /**
     * 新增业主信息
     * @param zhCustomer 前端传递的业主信息
     * @return 业主信息是否新增成功
     */
    @PostMapping("/insertCustomer")
    public R insertCustomer(ZhCustomer zhCustomer) {
        System.out.println("insertCustomer");
        Integer result = customerService.insertCustomer(zhCustomer);
        if (result == 0) {
            return new R("业主信息已存在");
        }
        return new R("业主信息添加成功");
    }

    /**
     * 查询全部业主信息
     * @return
     */
    @GetMapping("/selectCustomer")
    public R selectAllCustomer() {
        System.out.println("selectAllCustomer");
        List<ZhCustomer> zhCustomers = customerService.selectAllCustomer();
        return new R(zhCustomers);
    }

    /**
     * 根据用户的选择的值查询业主信息
     * @param column 对应查询的列
     * @param value 对应列的值
     * @return 业主信息
     */
    @PostMapping("/selectCustomerByColumnAndValue")
    public R selectCustomerByColumnAndValue(@RequestParam("column") String column, @RequestParam("value") String value) {
        System.out.println("selectCustomerByColumnAndValue");
        List<ZhCustomer> zhCustomers = customerService.selectCustomerByColumnAndValue(column, value);
        return new R(zhCustomers);
    }

    /**
     * 根据业主类型查询业主信息
     * @param customerType 业主类型
     * @return 业主信息
     */
    @PostMapping("/selectByCustomerByCustomerType")
    public R selectByCustomerByCustomerType(String customerType) {
        System.out.println("selectByCustomerByCustomerType");
        List<ZhCustomer> zhCustomers = customerService.selectByCustomerByCustomerType(customerType);
        return new R(zhCustomers);
    }

    /**
     * 根据前端传递的业主编码信息修改业主状态
     * @param map 前端传递的参数
     *            customerCodes: 业主编码,如出现多个业主编码会通过"|"分割
     *            status: 状态码: 1启用,0禁用
     * @return 修改业主状态信息结果
     */
    @PostMapping("/UpdateCustomerStatusByCustomerCode")
    public R UpdateCustomerStatusByCustomerCode(@RequestBody Map map) {
        System.out.println("UpdateCustomerStatusByCustomerCode");
        String customerCodes = (String) map.get("customerCodes");
        String status = (String) map.get("status");
        System.out.println(customerCodes + ":" + status);
        Integer result = customerService.UpdateCustomerStatusByCustomerCode(customerCodes, status);
        if (result == 1) {
            return new R("修改成功");
        }else {
            return new R("修改失败");
        }
    }

    /**
     * 统一查询功能 通过用户所传递的参数进行对应发查询
     * @return 查询的结果: 业主信息
     */
    @PostMapping("/selectCustomer")
    public R selectCustomer(@RequestBody CustomerMessage customerMessage) {
        System.out.println("selectCustomer");
        List<ZhCustomer> zhCustomers = customerService.selectCustomer(customerMessage);
        return new R(zhCustomers);
    }

    /**
     * 根据前端传递的Excel文件和对应的公司信息解析业主信息并存入数据库
     * @param file Excel表格文件 (固定格式)
     * @param company 对应公司编号
     * @return 是否新增成功
     */
    @PostMapping("/uploadExcel")
    public R uploadExcel(MultipartFile file, String company) {
        System.out.println("uploadExcel");
        if (file != null && file.getSize() > 0) {
            //调用readExcel方法解析Excel表格
            try {
                List<ZhCustomer> customers = ExcelUtil.readExcel((FileInputStream) file.getInputStream(),ZhCustomer.class);
                for (ZhCustomer customer : customers) {
                    System.out.println(customer);
                }
                //调用业务层传递实体类
                customerService.insertAll(customers,company);
                return new R("批量增加成功");
            }catch (Exception e) {
                e.printStackTrace();
                return new R("批量增加失败");
            }
        }
        return new R("文件无法接收");
    }

    /**
     * 新增业主入住信息
     * @param zhCustomerEstate 业主与房产关系数据
     * @return 是否添加成功
     */
    @PostMapping("/insertCustomerOrEstate")
    public R insertCustomerOrEstate(@RequestBody ZhCustomerEstate zhCustomerEstate) {
        System.out.println("insertCustomerOrEstate");
        Integer result = customerService.insertCustomerOrEstate(zhCustomerEstate);
        if (result == 1) {
            return new R("1");
        }else {
            return new R("2");
        }
    }

}

