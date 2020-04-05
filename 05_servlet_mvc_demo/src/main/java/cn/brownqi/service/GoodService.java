package cn.brownqi.service;

import cn.brownqi.model.Good;

import java.util.List;

public interface GoodService {
    List<Good> selectAllGoods();

    List<Good> selectGoodsByName(String goodName);

    Good searchGoodDetail(String goodId);
}
