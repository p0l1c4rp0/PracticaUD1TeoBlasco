package com.teobl.ferreteria.base;

import java.time.LocalDate;

public class HerramientaManual extends ArticuloFerreteria{
    public double peso;

    public HerramientaManual() { super(); }

    public HerramientaManual(String codigo, String modelo,
                             String marca, double precio,
                             LocalDate fechaCompra,
                             double peso) {
        super(codigo, modelo, marca, fechaCompra, precio);
        this.peso = peso;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "HerramientaManual{" +
                "peso=" + peso +
                ", codigo='" + codigo + '\'' +
                ", modelo='" + modelo + '\'' +
                ", marca='" + marca + '\'' +
                ", precio='" + precio + '\'' +
                ", fechaCompra=" + fechaCompra +
                '}';
    }
}
