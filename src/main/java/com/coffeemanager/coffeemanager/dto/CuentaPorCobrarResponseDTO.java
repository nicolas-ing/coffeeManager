package com.coffeemanager.coffeemanager.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CuentaPorCobrarResponseDTO {

    private Integer idCuentaCobrar;
    private Integer idVenta;
    private Integer idCliente;
    private LocalDate fechaEmision;
    private LocalDate fechaVencimiento;
    private BigDecimal monto;
    private BigDecimal saldo;
    private String estado;

    public CuentaPorCobrarResponseDTO() {
    }

    public CuentaPorCobrarResponseDTO(
            Integer idCuentaCobrar,
            Integer idVenta,
            Integer idCliente,
            LocalDate fechaEmision,
            LocalDate fechaVencimiento,
            BigDecimal monto,
            BigDecimal saldo,
            String estado) {

        this.idCuentaCobrar = idCuentaCobrar;
        this.idVenta = idVenta;
        this.idCliente = idCliente;
        this.fechaEmision = fechaEmision;
        this.fechaVencimiento = fechaVencimiento;
        this.monto = monto;
        this.saldo = saldo;
        this.estado = estado;
    }

    public Integer getIdCuentaCobrar() {
        return idCuentaCobrar;
    }

    public void setIdCuentaCobrar(Integer idCuentaCobrar) {
        this.idCuentaCobrar = idCuentaCobrar;
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}