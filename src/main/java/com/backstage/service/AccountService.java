package com.backstage.service;

import com.backstage.dao.ARDao;
import com.backstage.dao.AccountDao;
import com.backstage.entity.Account;
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

    public Account insertAccount(Account account) {
        accountDao.save(account);
        return account;
    }

    public Account updateAccount(Account account) {
        accountDao.update(account);
        return account;
    }

    public void deleteAccount(String accountId){
        accountDao.delete(accountId,true);
        arDao.deleteByAccountId(accountId);
    }

    public List<Account> findAll() {
        return accountDao.findAll();
    }
}
