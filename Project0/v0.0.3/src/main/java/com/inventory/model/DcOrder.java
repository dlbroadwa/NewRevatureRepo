package com.inventory.model;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

public class DcOrder implements SQL, Comparable<DcOrder> {
    public DcOrder(int id, int warehouseId, int dcId, LocalDate date) {
        this.id = id;
        this.warehouseId = warehouseId;
        this.dcId = dcId;
        this.date = date;
    }

    private final int warehouseId;
    private final int dcId;
    private final java.time.LocalDate date;
    private final int id;

    public int getWarehouseId() {
        return warehouseId;
    }

    public int getDcId() {
        return dcId;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "DcOrder{" +
                "warehouseId=" + warehouseId +
                ", dcId=" + dcId +
                ", date=" + date +
                ", id=" + id +
                '}';
    }

    @Override
    public String getSQLColumnFormat() {
        return "(\"id\", \"warehouseId\" , \"dcId\", \"date\") ";
    }

    @Override
    public String toSQLString() {
        return "(" + id + ", " + warehouseId + ", " +  dcId + ", " + "TO_DATE('"+ date.toString() +  "', 'YYYY-MM-DD')) ";
    }

    @Override
    public int compareTo(@NotNull DcOrder dcOrder) {
        int first = Integer.compare(id, dcOrder.id);
        if(first != 0)
            return first;

        int second = Integer.compare(warehouseId, dcOrder.warehouseId);
        if(second != 0)
            return second;

        int third = Integer.compare(dcId, dcOrder.dcId);
        if(third != 0)
            return third;

        return date.toString().compareTo(dcOrder.date.toString());
    }
}
