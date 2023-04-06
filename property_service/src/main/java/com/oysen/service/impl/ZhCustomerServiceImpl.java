package com.oysen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.oysen.bean.ZhCustomer;
import com.oysen.bean.ZhCustomerEstate;
import com.oysen.mapper.ZhCustomerEstateMapper;
import com.oysen.mapper.ZhCustomerMapper;
import com.oysen.service.ZhCustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oysen.vo.CustomerMessage;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 业主信息表 服务实现类
 * </p>
 *
 * @author yang
 * @since 2023-03-03
 */
@Service
public class ZhCustomerServiceImpl extends ServiceImpl<ZhCustomerMapper, ZhCustomer> implements ZhCustomerService {

    @Resource
    private ZhCustomerMapper zhCustomerMapper;
    @Resource
    private ZhCustomerEstateMapper zhCustomerEstateMapper;
    @Override
    public Integer insertCustomer(ZhCustomer zhCustomer) {
        Integer result = 0;
        QueryWrapper<ZhCustomer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("customer_code",zhCustomer.getCustomerCode());
        ZhCustomer customer = zhCustomerMapper.selectOne(queryWrapper);
        if (customer == null) {
            result = zhCustomerMapper.insert(zhCustomer);
        }
        return result;
    }

    @Override
    public List<ZhCustomer> selectAllCustomer() {
        List<ZhCustomer> zhCustomers = zhCustomerMapper.selectAll();
        return zhCustomers;
    }

    @Override
    public List<ZhCustomer> selectCustomerByColumnAndValue(String column, String value) {
        QueryWrapper<ZhCustomer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(column,value);
        List<ZhCustomer> zhCustomers = zhCustomerMapper.selectList(queryWrapper);
        return zhCustomers;
    }

    @Override
    public List<ZhCustomer> selectByCustomerByCustomerType(String customerType) {
        List<ZhCustomer> zhCustomers;
        if (customerType.equals("")) {
            return selectAllCustomer();
        }else {
            QueryWrapper<ZhCustomer> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("customer_type",customerType);
            zhCustomers = zhCustomerMapper.selectList(queryWrapper);
        }
        return zhCustomers;
    }

    @Override
    public Integer UpdateCustomerStatusByCustomerCode(String customerCodes, String status) {
        Integer result = 0;
        //如果customerCodes包含"|"就为true,步包含就为false
        if (customerCodes.contains("|")) {
            //拆分具体业主编号   123|213|232
            String[] codes = customerCodes.split("[|]");
            for (int i = 0; i < codes.length; i++) {
                //必须重新创建UpdateWrapper否则查询条件会累加
                UpdateWrapper<ZhCustomer> updateWrapper = new UpdateWrapper<>();
                updateWrapper.set("customer_status",status).eq("customer_code",codes[i]);
                result = zhCustomerMapper.update(null,updateWrapper);
            }
            return result;
        }else {
            //只选择一个业主信息
            UpdateWrapper<ZhCustomer> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set("customer_status",status).eq("customer_code",customerCodes);
            result = zhCustomerMapper.update(null,updateWrapper);
            return result;
        }
    }

    /**
     * 查询优化，统一查询入口
     * @param customerMessage 用户传递的参数
     * @return
     */
    @Override
    public List<ZhCustomer> selectCustomer(CustomerMessage customerMessage) {
        QueryWrapper<ZhCustomer> queryWrapper = new QueryWrapper<>();
        //保证column和value都有值
        if (!StringUtils.isEmpty(customerMessage.getColumn()) && !StringUtils.isEmpty(customerMessage.getValue())) {
            queryWrapper.eq(customerMessage.getColumn(),customerMessage.getValue());
        }
        //保证getCustomerType有值
        if (!StringUtils.isEmpty(customerMessage.getCustomerType())) {
            queryWrapper.eq("getCustomer_type", customerMessage.getCustomerType());
        }
        List<ZhCustomer> customers = zhCustomerMapper.selectList(queryWrapper);
        return customers;
    }

    @Override
    public Integer insertAll(List<ZhCustomer> customers, String company) {
        Integer result = 0;
        if (customers.size() > 0) {
            for (ZhCustomer customer : customers) {
                customer.setCompany(company);
                result = zhCustomerMapper.insert(customer);
            }
        }
        return result;
    }

    @Override
    public Integer insertCustomerOrEstate(ZhCustomerEstate zhCustomerEstate) {
        Integer result = 0;
        if (zhCustomerEstate != null) {
            result = zhCustomerEstateMapper.insert(zhCustomerEstate);
            return result;
        }
        return result;
    }
}
