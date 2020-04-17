package com.inventory.model;

import java.time.LocalDate;

public class DcOrder {
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

    @Override
    public String toString() {
        return "DcOrder{" +
                "warehouseId=" + warehouseId +
                ", dcId=" + dcId +
                ", date=" + date +
                ", id=" + id +
                '}';
    }
}
