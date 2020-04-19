package com.inventory.model;

public class DcOrderItems implements SQL{
    public DcOrderItems(int orderId, int itemId, int quantity) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.quantity = quantity;
    }

    private final int orderId;
    private final int itemId;
    private final int quantity;

    public int getOrderId() {
        return orderId;
    }

    public int getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String getSQLColumnFormat() {
        return "(\"orderId\", \"itemId\" , \"quantity\") ";
    }

    @Override
    public String toSQLString() {
        return "(" + orderId + ", " + itemId + ", " + quantity + ") ";
    }

    @Override
    public String toString() {
        return "DcOrdersItemsList{" +
                "orderId=" + orderId +
                ", itemId=" + itemId +
                ", quantity=" + quantity +
                '}';
    }
}
