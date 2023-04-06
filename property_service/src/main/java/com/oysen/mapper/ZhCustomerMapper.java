package com.oysen.mapper;

import com.oysen.bean.ZhCustomer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 业主信息表 Mapper 接口
 * </p>
 *
 * @author yang
 * @since 2023-03-03
 */
public interface ZhCustomerMapper extends BaseMapper<ZhCustomer> {

    /**
     * 查询全部业主信息
     * @return 查询到的业主信息结果
     */

    List<ZhCustomer> selectAll();
}
