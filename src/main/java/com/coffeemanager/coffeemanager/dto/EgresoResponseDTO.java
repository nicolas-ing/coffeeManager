package com.coffeemanager.coffeemanager.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EgresoResponseDTO {

    private Integer idEgreso;
    private Integer idCompra;
    private Integer idUsuario;
    private LocalDateTime fecha;
    private String concepto;
    private BigDecimal monto;
    private String metodoPago;
    private String estado;

    public EgresoResponseDTO() {
    }

    public EgresoResponseDTO(
            Integer idEgreso,
            Integer idCompra,
            Integer idUsuario,
            LocalDateTime fecha,
            String concepto,
            BigDecimal monto,
            String metodoPago,
            String estado) {

        this.idEgreso = idEgreso;
        this.idCompra = idCompra;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.concepto = concepto;
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.estado = estado;
    }

    public Integer getIdEgreso() {
        return idEgreso;
    }

    public void setIdEgreso(Integer idEgreso) {
        this.idEgreso = idEgreso;
    }

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
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