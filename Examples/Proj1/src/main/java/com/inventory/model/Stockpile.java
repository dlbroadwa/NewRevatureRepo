package com.inventory.model;

import org.jetbrains.annotations.NotNull;

public class Stockpile implements SQL, Comparable<Stockpile>{
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

    @Override
    public String getSQLColumnFormat() {
        return "(\"warehouseId\", \"itemId\" , \"quantity\") ";
    }

    @Override
    public String toSQLString() {
        return "(" + warehouseId + ", " + itemId + ", " + quantity + ") ";
    }

    @Override
    public int compareTo(@NotNull Stockpile stockpile) {
        int first = Integer.compare(warehouseId, stockpile.warehouseId);
        if(first != 0)
            return first;

        int second = Integer.compare(itemId, stockpile.itemId);
        if(second != 0)
            return second;

        return Integer.compare(quantity, stockpile.quantity);
    }
}
