package com.oysen.service.impl.base;

import com.oysen.bean.TblTodo;
import com.oysen.mapper.TblTodoMapper;
import com.oysen.service.base.TblTodoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 待办事项 服务实现类
 * </p>
 *
 * @author yang
 * @since 2023-03-03
 */
@Service
public class TblTodoServiceImpl extends ServiceImpl<TblTodoMapper, TblTodo> implements TblTodoService {

}
