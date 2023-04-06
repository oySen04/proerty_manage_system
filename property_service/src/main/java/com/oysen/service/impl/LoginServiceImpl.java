package com.oysen.service.impl;

import com.oysen.bean.TblUserRecord;
import com.oysen.mapper.TblUserRecordMapper;
import com.oysen.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private TblUserRecordMapper tblUserRecordMapper;

    @Override
    public TblUserRecord login(String username, String password) {
        TblUserRecord tblUserRecord = tblUserRecordMapper.login(username, password);
        return tblUserRecord;
    }
}
