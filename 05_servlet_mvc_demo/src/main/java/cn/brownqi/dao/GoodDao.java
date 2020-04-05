package cn.brownqi.dao;

import cn.brownqi.model.Good;

import java.util.List;

public interface GoodDao {
    List<Good> queryAllGood();
    List<Good> queryGoodByName(String goodName);
}
