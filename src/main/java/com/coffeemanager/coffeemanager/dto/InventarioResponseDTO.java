package com.coffeemanager.coffeemanager.dto;

import java.time.LocalDateTime;

public class InventarioResponseDTO {

    private Integer idInventario;
    private Integer idProducto;
    private Integer cantidadActual;
    private Integer stockMinimo;
    private Integer stockMaximo;
    private LocalDateTime fechaActualizacion;

    public InventarioResponseDTO() {
    }

    public InventarioResponseDTO(
            Integer idInventario,
            Integer idProducto,
            Integer cantidadActual,
            Integer stockMinimo,
            Integer stockMaximo,
            LocalDateTime fechaActualizacion) {

        this.idInventario = idInventario;
        this.idProducto = idProducto;
        this.cantidadActual = cantidadActual;
        this.stockMinimo = stockMinimo;
        this.stockMaximo = stockMaximo;
        this.fechaActualizacion = fechaActualizacion;
    }

    public Integer getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(Integer idInventario) {
        this.idInventario = idInventario;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getCantidadActual() {
        return cantidadActual;
    }

    public void setCantidadActual(Integer cantidadActual) {
        this.cantidadActual = cantidadActual;
    }

    public Integer getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(Integer stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public Integer getStockMaximo() {
        return stockMaximo;
    }

    public void setStockMaximo(Integer stockMaximo) {
        this.stockMaximo = stockMaximo;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}