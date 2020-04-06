package cn.brownqi.model;

import java.math.BigDecimal;

public class Good {
    private String goodId;
    private String goodName;
    private BigDecimal goodPrice;
    private Integer goodCount;

    public Good() {
    }

    public Good(String goodId, String goodName, BigDecimal goodPrice, Integer goodCount) {
        this.goodId = goodId;
        this.goodName = goodName;
        this.goodPrice = goodPrice;
        this.goodCount = goodCount;
    }

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public BigDecimal getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(BigDecimal goodPrice) {
        this.goodPrice = goodPrice;
    }

    public Integer getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(Integer goodCount) {
        this.goodCount = goodCount;
    }


}
