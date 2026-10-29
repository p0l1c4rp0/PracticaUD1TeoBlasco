package com.teobl.ferreteria.base;

import java.time.LocalDate;

public class HerramientaElectrica extends ArticuloFerreteria {
    private int potencia;
    private int numBaterias;

    public HerramientaElectrica() { super(); }

    public HerramientaElectrica(String codigo, String modelo, String marca, LocalDate fechaCompra, int potencia, int numBaterias) {
        super(codigo, modelo, marca, fechaCompra);
        this.potencia = potencia;
        this.numBaterias = numBaterias;
    }

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public int getNumBaterias() {
        return numBaterias;
    }

    public void setNumBaterias(int numBaterias) {
        this.numBaterias = numBaterias;
    }

    @Override
    public String toString() {
        return "HerramientaElectrica{" +
                "potencia=" + potencia +
                ", numBaterias=" + numBaterias +
                ", codigo='" + codigo + '\'' +
                ", modelo='" + modelo + '\'' +
                ", marca='" + marca + '\'' +
                ", fechaCompra=" + fechaCompra +
                '}';
    }
}
