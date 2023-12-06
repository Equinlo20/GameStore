/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.gamestore.utils;

public class ListenerProvider {

    private static ListenerProvider pv = new ListenerProvider();

    private SetViewListener stListener;

    private GoToViewGameListener gtvGameListener;
    
    private ViewPurchaseListener vPListener;

    private ListenerProvider() {
    }

    public static ListenerProvider getListenerProvider() {
        return pv;
    }

    public void setStListener(SetViewListener stListener) {
        this.stListener = stListener;
    }

    public SetViewListener getStListener() {
        return this.stListener;
    }

    public GoToViewGameListener getGtvGameListener() {
        return gtvGameListener;
    }

    public void setGtvGameListener(GoToViewGameListener gtvGameListener) {
        this.gtvGameListener = gtvGameListener;
    }

    public ViewPurchaseListener getvPListener() {
        return vPListener;
    }

    public void setvPListener(ViewPurchaseListener vPListener) {
        this.vPListener = vPListener;
    }

}
