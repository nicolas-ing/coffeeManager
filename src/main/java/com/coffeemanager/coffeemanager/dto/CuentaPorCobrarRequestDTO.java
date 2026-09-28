package com.coffeemanager.coffeemanager.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CuentaPorCobrarRequestDTO {

    @NotNull(message = "La venta es obligatoria")
    private Integer idVenta;

    @NotNull(message = "El cliente es obligatorio")
    private Integer idCliente;

    @NotNull(message = "La fecha de emisión es obligatoria")
    private LocalDate fechaEmision;

    @NotNull(message = "La fecha de vencimiento es obligatoria")
    private LocalDate fechaVencimiento;

    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(
            value = "0.0",
            inclusive = true,
            message = "El monto no puede ser negativo"
    )
    private BigDecimal monto;

    @NotNull(message = "El saldo es obligatorio")
    @DecimalMin(
            value = "0.0",
            inclusive = true,
            message = "El saldo no puede ser negativo"
    )
    private BigDecimal saldo;

    @NotBlank(message = "El estado es obligatorio")
    @Size(
            max = 30,
            message = "El estado no puede superar los 30 caracteres"
    )
    private String estado;

    public CuentaPorCobrarRequestDTO() {
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