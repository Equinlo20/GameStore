
package org.openjfx.gamestore.models.domain;

public class Purchase {
    private long id;
    private String date;
    private int numItems; //numItems se va a calcular luego a partir del tamaño de la lista de items que se debe reemplazar aquí.
    private double total; //El total luego se va a calcular con un metodo en esta clase, el cual recorra la pila o lista donde esten almacenados los items e ir sacando los valores de costos para sumarlos

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

    public int getNumItems() {
        return numItems;
    }

    public void setNumItems(int numItems) {
        this.numItems = numItems;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
}
