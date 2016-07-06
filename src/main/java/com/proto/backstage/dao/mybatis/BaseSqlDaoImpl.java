package com.proto.backstage.dao.mybatis;


import com.proto.backstage.dao.BaseSqlDao;
import com.proto.backstage.entity.BaseDataEntity;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * mybatis基类，支持注解
 * Created by yitao on 2016/5/19.
 */
public class BaseSqlDaoImpl<T extends BaseDataEntity, PK extends String> extends SqlSessionDaoSupport implements BaseSqlDao<T, PK> {

    public static final String SQL_GET_BY_ID = "getById";
    public static final String SQL_GET_BY_MAP = "getByMap";
    public static final String SQL_FIND_ALL = "findAll";
    public static final String SQL_FIND_BY_MAP = "findByMap";
    public static final String SQL_INSERT = "insert";
    public static final String SQL_UPDATE = "update";
    public static final String SQL_UPDATE_BY_MAP = "updateByMap";
    public static final String SQL_DELETE_BY_ID = "deleteById";
    public static final String SQL_SOFT_DELETE_BY_ID = "softDeleteById";
    public static final String SQL_COUNT = "count";
    public static final String SQL_COUNT_BY_MAP = "countByMap";
    // 批量操作：修改和假删除
    public static final String SQL_BATCH_OPT = "batchOpt";
    // 批量插入
    public static final String SQL_BATCH_INSERT = "batchInsert";

    public static final String SQL_INSERT_RELATION = "insertRelation";

    protected String className;

    public String getClassName() {
        return className;
    }

    protected Class<T> clazz;

    @PostConstruct
    public void init() {
        clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        className = clazz.getName();
    }

    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public T get(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        return getSqlSession().selectOne(className + "." + SQL_GET_BY_ID, id);
    }

    public T get(Map<String, Object> query) {
        return getSqlSession().selectOne(className + "." + SQL_GET_BY_MAP, query);
    }

    public List<T> findAll() {
        return findAll(false);
    }

    public List<T> findAll(Boolean deleted) {
        return getSqlSession().selectList(className + "." + SQL_FIND_ALL, deleted);
    }

    public void save(T entity) {
        if (entity.isNew()) {
            entity.setId(UUID.randomUUID().toString());
            insert(entity);
        } else {
            update(entity);
        }
    }

    public void insert(T entity) {
        getSqlSession().insert(className + "." + SQL_INSERT, entity);
    }

    public void update(T entity) {
        getSqlSession().update(className + "." + SQL_UPDATE, entity);
    }

    public void delete(PK id, boolean isHardDelete) {
        if (!isHardDelete) {
            delete(id);
        } else {
            getSqlSession().delete(className + "." + SQL_DELETE_BY_ID, id);
        }
    }

    public Long count(Boolean deleted) {
        return null;
    }

    public void delete(PK id) {
        getSqlSession().update(className + "." + SQL_SOFT_DELETE_BY_ID, id);
    }

    public Long count() {
        return getSqlSession().selectOne(className + "." + SQL_COUNT);
    }

    public Long count(Map<String, Object> query) {
        return getSqlSession().selectOne(className + "." + SQL_COUNT_BY_MAP, query);
    }

    /**
     * 仅用于后台分页管理查询
     *
     * @param query
     * @return
     */
    public List<T> find(Map<String, Object> query) {
        return getSqlSession().selectList(className + "." + SQL_FIND_BY_MAP, query);
    }

}
