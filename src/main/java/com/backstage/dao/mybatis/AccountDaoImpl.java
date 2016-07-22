package com.backstage.dao.mybatis;

import com.backstage.dao.AccountDao;
import com.backstage.entity.Account;
import org.springframework.stereotype.Repository;

/**
 * Created by yitao on 2016/5/19.
 */
@Repository("P_AccountDaoImpl")
public class AccountDaoImpl extends BaseSqlDaoImpl<Account,String> implements AccountDao {
}
