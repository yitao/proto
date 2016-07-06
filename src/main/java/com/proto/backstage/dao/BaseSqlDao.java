package com.proto.backstage.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by yitao on 2016/5/19.
 */
public interface BaseSqlDao<T, PK extends Serializable> {

    void insert(T entity);

    void delete(PK id);

    void delete(PK id, boolean isHardDelete);

    void update(T entity);

    void save(T entity);

    T get(PK id);

    T get(Map<String, Object> query);

    Long count();

    List<T> findAll();

    Long count(Boolean deleted);

    List<T> findAll(Boolean deleted);

    Long count(Map<String, Object> query);

    List<T> find(Map<String, Object> query);


}
