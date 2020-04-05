package cn.brownqi.dao.impl;

import cn.brownqi.dao.BaseDao;
import cn.brownqi.dao.GoodDao;
import cn.brownqi.model.Good;

import java.util.List;

public class GoodDaoImpl extends BaseDao implements GoodDao {
    @Override
    public List<Good> queryAllGood() {
        String sql = "select * from t_good";
        List<Good> goods = queryAll(Good.class, sql);
        return goods;
    }

    @Override
    public List<Good> queryGoodByName(String goodName) {
        String sql = "SELECT * FROM t_good WHERE INSTR(goodname,?)>0;";
        List<Good> goods = queryForList(Good.class,sql,goodName);
        return goods;
    }
}
