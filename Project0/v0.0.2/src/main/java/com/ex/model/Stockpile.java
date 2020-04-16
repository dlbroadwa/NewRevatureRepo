package com.ex.model;

public class Stockpile {
    public Stockpile(int warehouseId, int itemId, int quantity) {
        this.warehouseId = warehouseId;
        this.itemId = itemId;
        this.quantity = quantity;
    }

    private final int warehouseId;
    private final int itemId;
    private final int quantity;

    public int getWarehouseId() {
        return warehouseId;
    }

    public int getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Stockpile{" +
                "warehouseId=" + warehouseId +
                ", itemId=" + itemId +
                ", quantity=" + quantity +
                '}';
    }
}
