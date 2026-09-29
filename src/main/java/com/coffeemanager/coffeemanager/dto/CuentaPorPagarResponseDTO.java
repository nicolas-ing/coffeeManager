package com.coffeemanager.coffeemanager.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CuentaPorPagarResponseDTO {

    private Integer idCuentaPagar;
    private Integer idCompra;
    private LocalDate fechaEmision;
    private LocalDate fechaVencimiento;
    private BigDecimal monto;
    private BigDecimal saldo;
    private String estado;

    public CuentaPorPagarResponseDTO() {
    }

    public CuentaPorPagarResponseDTO(
            Integer idCuentaPagar,
            Integer idCompra,
            LocalDate fechaEmision,
            LocalDate fechaVencimiento,
            BigDecimal monto,
            BigDecimal saldo,
            String estado) {

        this.idCuentaPagar = idCuentaPagar;
        this.idCompra = idCompra;
        this.fechaEmision = fechaEmision;
        this.fechaVencimiento = fechaVencimiento;
        this.monto = monto;
        this.saldo = saldo;
        this.estado = estado;
    }

    public Integer getIdCuentaPagar() {
        return idCuentaPagar;
    }

    public void setIdCuentaPagar(Integer idCuentaPagar) {
        this.idCuentaPagar = idCuentaPagar;
    }

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
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