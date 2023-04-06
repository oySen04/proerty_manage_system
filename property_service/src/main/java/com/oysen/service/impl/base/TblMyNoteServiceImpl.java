package com.oysen.service.impl.base;

import com.oysen.bean.TblMyNote;
import com.oysen.mapper.TblMyNoteMapper;
import com.oysen.service.base.TblMyNoteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 我的记事本 服务实现类
 * </p>
 *
 * @author yang
 * @since 2023-03-03
 */
@Service
public class TblMyNoteServiceImpl extends ServiceImpl<TblMyNoteMapper, TblMyNote> implements TblMyNoteService {

}
