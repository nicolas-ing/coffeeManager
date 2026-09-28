package com.coffeemanager.coffeemanager.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class IngresoResponseDTO {

    private Integer idIngreso;
    private Integer idVenta;
    private Integer idUsuario;
    private LocalDateTime fecha;
    private String concepto;
    private BigDecimal monto;
    private String metodoPago;
    private String estado;

    public IngresoResponseDTO() {
    }

    public IngresoResponseDTO(
            Integer idIngreso,
            Integer idVenta,
            Integer idUsuario,
            LocalDateTime fecha,
            String concepto,
            BigDecimal monto,
            String metodoPago,
            String estado) {

        this.idIngreso = idIngreso;
        this.idVenta = idVenta;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.concepto = concepto;
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.estado = estado;
    }

    public Integer getIdIngreso() {
        return idIngreso;
    }

    public void setIdIngreso(Integer idIngreso) {
        this.idIngreso = idIngreso;
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}