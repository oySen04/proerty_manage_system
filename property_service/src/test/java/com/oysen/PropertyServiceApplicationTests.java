package com.oysen;

import com.oysen.bean.TblCompany;
import com.oysen.bean.TblUserRecord;
import com.oysen.bean.ZhCustomer;
import com.oysen.mapper.TblCompanyMapper;
import com.oysen.mapper.TblUserRecordMapper;
import com.oysen.service.EstateService;
import com.oysen.service.LoginService;
import com.oysen.service.ZhCustomerService;
import com.oysen.vo.CustomerMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class PropertyServiceApplicationTests {

    //@Autowired
    @Resource
    TblUserRecordMapper tblUserRecordMapper;

    @Resource
    LoginService loginService;

    @Resource
    TblCompanyMapper tblCompanyMapper;

    @Resource
    EstateService estateService;

    @Resource
    ZhCustomerService zhCustomerService;

    @Test
    void contextLoads() {
        //TblUserRecord record = tblUserRecordMapper.login("admin", "c4ca4238a0b923820dcc509a6f75849b");
        //TblUserRecord login = loginService.login("admin", "c4ca4238a0b923820dcc509a6f75849b");
        //List<TblCompany> list = tblCompanyMapper.selectCompany();
        List<TblCompany> list = estateService.selectCompany();
        System.out.println("查询数据 :" + list);
    }

}
