package com.oysen.mapper;

import com.oysen.bean.TblCompany;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 企业档案 Mapper 接口
 * </p>
 *
 * @author yang
 * @since 2023-03-03
 */
public interface TblCompanyMapper extends BaseMapper<TblCompany> {
    /**
     * 查询公司信息方法
     * @return 所查询到的公司信息
     */
    @Select("SELECT id,company_full_name FROM tbl_company")
    List<TblCompany> selectCompany();
}
