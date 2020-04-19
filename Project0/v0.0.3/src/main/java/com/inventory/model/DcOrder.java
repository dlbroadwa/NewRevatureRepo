package com.inventory.model;

import java.time.LocalDate;

public class DcOrder implements SQL {
    public DcOrder(int warehouseId, int dcId, LocalDate date, int id) {
        this.warehouseId = warehouseId;
        this.dcId = dcId;
        this.date = date;
        this.id = id;
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
        return "(\"warehouseId\", \"dcId\" , \"date\", \"id\") ";
    }

    @Override
    public String toSQLString() {
        return "(" + warehouseId + ", " + dcId + ", " +  "TO_DATE('"+ date.toString() +  "', 'YYYY-MM-DD')," + id + ") ";
    }
}
