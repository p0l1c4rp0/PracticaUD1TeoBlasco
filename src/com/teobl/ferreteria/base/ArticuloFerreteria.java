package com.teobl.ferreteria.base;

import java.time.LocalDate;

public abstract class ArticuloFerreteria {
    public String codigo;
    public String modelo;
    public String marca;
    public LocalDate fechaCompra;

    public ArticuloFerreteria() {}

    public ArticuloFerreteria(String codigo, String modelo, String marca, LocalDate fechaCompra) {
        this.codigo = codigo;
        this.modelo = modelo;
        this.marca = marca;
        this.fechaCompra = fechaCompra;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
}
