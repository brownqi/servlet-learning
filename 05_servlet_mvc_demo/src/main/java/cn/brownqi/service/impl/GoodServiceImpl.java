package cn.brownqi.service.impl;

import cn.brownqi.dao.GoodDao;
import cn.brownqi.dao.impl.GoodDaoImpl;
import cn.brownqi.model.Good;
import cn.brownqi.service.GoodService;

import java.util.List;

public class GoodServiceImpl implements GoodService {

    private GoodDao goodDao = new GoodDaoImpl();

    @Override
    public List<Good> selectAllGoods() {
        return goodDao.queryAllGood();
    }

    @Override
    public List<Good> selectGoodsByName(String goodName) {
        return goodDao.queryGoodByName(goodName);
    }
}
