package org.openjfx.gamestore.models.domain;

import org.openjfx.gamestore.data.LList;

public class Purchase {

    private long id;
    private String date;
    private LList<ItemGame> items;

    public Purchase(long id, String date, LList<ItemGame> items) {
        this.id = id;
        this.date = date;
        this.items = items;
    }

    public Purchase(long id, String date) {
        this.id = id;
        this.date = date;
        this.items = new LList<>();
    }

    public Purchase() {
        this.items = new LList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public LList<ItemGame> getItems() {
        return items;
    }

    public void setItems(LList<ItemGame> items) {
        this.items = items;
    }

    public long getNumItems() {
        return this.items.getSize();
    }

    public double getTotal() {
        double totalPay = 0;
        for (int i = 0; i < this.items.getSize(); i++) {
            try {
                totalPay += this.items.get(i).getTotalItemPrice();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return totalPay;
    }

    @Override
    public String toString() {
        String data = "";
        long size = this.items.getSize();
        for (int i = 0; i < size; i++) {
            if (i < size - 1) {
                try {
                    data += String.format("%s;%s;%s\n", this.id, this.date, this.items.get(i).toString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                try {
                    data += String.format("%s;%s;%s", this.id, this.date, this.items.get(i).toString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return data;
    }
}
