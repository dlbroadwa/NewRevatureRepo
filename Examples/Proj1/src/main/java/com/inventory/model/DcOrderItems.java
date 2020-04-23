package com.inventory.model;

import org.jetbrains.annotations.NotNull;

public class DcOrderItems implements SQL, Comparable<DcOrderItems>{
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

    @Override
    public int compareTo(@NotNull DcOrderItems dcOrderItems) {
        int first = Integer.compare(orderId, dcOrderItems.orderId);
        if(first != 0)
            return first;

        int second = Integer.compare(itemId, dcOrderItems.itemId);
        if(second != 0)
            return second;

        return Integer.compare(quantity, dcOrderItems.quantity);
    }
}
