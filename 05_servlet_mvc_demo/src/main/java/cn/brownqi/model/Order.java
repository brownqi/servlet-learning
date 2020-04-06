package cn.brownqi.model;

public class Order {

    private String orderId;
    private Integer userId;
    private String goodId;
    private Integer orderCount;
    private Integer orderState;

    public Order() {
    }

    public Order(String orderId, Integer userId, String goodId, Integer orderCount, Integer orderState) {
        this.orderId = orderId;
        this.userId = userId;
        this.goodId = goodId;
        this.orderCount = orderCount;
        this.orderState = orderState;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }
}
