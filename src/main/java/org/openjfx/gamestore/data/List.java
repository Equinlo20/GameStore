/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.gamestore.data;

/**
 * @param <Type>
 */
public class List<Type> {

    private Node<Type> pointer;
    private long size;

    public List() {
        this.pointer = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.pointer == null;
    }

    public long getSize() {
        return this.size;
    }

    public void addElementToEnd(Type node) {
        Node<Type> newNode = new Node<>(node);

        if (this.isEmpty()) {

            this.pointer = newNode;

        } else {

            Node<Type> aux = this.pointer;

            while (aux.getNext() != null) {
                aux = aux.getNext();
            }

            aux.setNext(newNode);
        }

        this.size++;
    }

    public void addElementToStart(Type node) {
        Node<Type> newNode = new Node<>(node);

        if (this.isEmpty()) {

            this.pointer = newNode;

        } else {

            newNode.setNext(this.pointer);
            this.pointer = newNode;
        }
        this.size++;
    }
    
    public boolean remove(long location){
        if(location >= 0 && location < this.size){
            
            if(location == 0){
                
                this.pointer = this.pointer.getNext();
            }else{
                
                Node<Type> aux = this.pointer;
                
                for (int i = 0; i < location-1; i++) {
                    aux = aux.getNext();
                }
                
                Node<Type> next = aux.getNext();
                aux.setNext(next.getNext());
            }
            this.size--;
            return true;
        }else{
            return false;
        }
        
    }
    
    public Type get(long location) throws Exception{
        if(location>=0 && location<this.size){
            if (location == 0) {
                return this.pointer.getData();
            }else{
                Node<Type> aux = this.pointer;
                for (int i = 0; i < location; i++) {
                    aux = aux.getNext();
                }
                return aux.getData();
            }
        } else {
            throw new Exception("Location not exist in the list");
        }
    }
    
    public boolean removeAll(){
        this.pointer = null;
        this.size = 0;
        
        return true;
    }

}
