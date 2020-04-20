package com.inventory.model;

public class Shipment {
    public Shipment(Stockpile stockpile, DcOrder dcOrder,DcOrderItems dcOrderItems){
        this.stockpile = stockpile;
        this.dcOrder = dcOrder;
        this.dcOrderItems = dcOrderItems;
    }

    private final Stockpile stockpile;
    private final DcOrder dcOrder;
    private final DcOrderItems dcOrderItems;

    public Stockpile getStockpile() {
        return stockpile;
    }

    public DcOrder getDcOrder() {
        return dcOrder;
    }

    public DcOrderItems getDcOrderItems() {
        return dcOrderItems;
    }

    @Override
    public String toString() {
        return "Shipment{" +
                "stockpile=" + stockpile.toString() +
                ", dcOrder=" + dcOrder.toString() +
                ", dcOrderItems=" + dcOrderItems.toString() +
                '}';
    }
}
