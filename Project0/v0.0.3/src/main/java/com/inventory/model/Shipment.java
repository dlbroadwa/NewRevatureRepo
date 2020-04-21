package com.inventory.model;

import org.jetbrains.annotations.NotNull;

public class Shipment implements Comparable<Shipment>{
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

    @Override
    public int compareTo(@NotNull Shipment o) {
        int first = stockpile.compareTo(o.stockpile);
        if(first != 0)
            return first;

        int second = dcOrder.compareTo(o.dcOrder);
        if(second != 0)
            return second;

        return dcOrderItems.compareTo(o.dcOrderItems);
    }
}
