package com.teobl.ferreteria.base;

import java.time.LocalDate;

public class Tornillos extends ArticuloFerreteria {
    private int cantidadPorCaja;
    private int tamanyo;

    public Tornillos() {
        super();
    }

    public Tornillos(String codigo, String modelo,
                     String marca, LocalDate fechaCompra,
                     double precio, int cantidadPorCaja,
                     int tamanyo) {
        super(codigo, modelo, marca, fechaCompra, precio);
        this.cantidadPorCaja = cantidadPorCaja;
        this.tamanyo = tamanyo;
    }

    public int getCantidadPorCaja() {
        return cantidadPorCaja;
    }

    public void setCantidadPorCaja(int cantidadPorCaja) {
        this.cantidadPorCaja = cantidadPorCaja;
    }

    public int getTamanyo() {
        return tamanyo;
    }

    public void setTamanyo(int tamanyo) {
        this.tamanyo = tamanyo;
    }

    @Override
    public String toString() {
        return "Tornillos{" +
                "cantidadPorCaja=" + cantidadPorCaja +
                ", tamanyo=" + tamanyo +
                ", codigo='" + codigo + '\'' +
                ", modelo='" + modelo + '\'' +
                ", marca='" + marca + '\'' +
                ", precio='" + marca + '\'' +
                ", fechaCompra=" + fechaCompra +
                '}';
    }
}
