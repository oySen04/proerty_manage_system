package com.oysen.mapper;

import com.oysen.bean.FcEstate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 楼盘信息 Mapper 接口
 * </p>
 *
 * @author yang
 * @since 2023-03-03
 */
public interface FcEstateMapper extends BaseMapper<FcEstate> {

    /**
     * 查询全部房产信息
     * @return
     */

    List<FcEstate> selectAllEstate();
}
