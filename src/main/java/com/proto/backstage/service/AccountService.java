package com.proto.backstage.service;


import com.proto.backstage.dao.*;
import com.proto.backstage.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yitao on 2016/6/14.
 */
@Service
public class AccountService {
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private ARDao arDao;

    public IAccount insertAccount(IAccount account){
        accountDao.save(account);
        return account;
    }

    public IAccount updateAccount(IAccount account){
        accountDao.update(account);
        return account;
    }

    public void deleteAccount(String accountId){
        accountDao.delete(accountId,true);
        arDao.deleteByAccountId(accountId);
    }

    public List<IAccount> findAll(){
        return accountDao.findAll();
    }
}
