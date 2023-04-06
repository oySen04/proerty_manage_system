package com.oysen.service;

import com.oysen.bean.TblUserRecord;


public interface LoginService {
    TblUserRecord login(String username, String password);
}
