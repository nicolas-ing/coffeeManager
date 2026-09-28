package com.coffeemanager.coffeemanager.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VentaRequestDTO {

    @NotNull(message = "El cliente es obligatorio")
    private Integer idCliente;

    @NotNull(message = "El usuario es obligatorio")
    private Integer idUsuario;

    private Integer idPedido;

    private LocalDateTime fecha;

    @NotBlank(message = "El número de documento es obligatorio")
    @Size(max = 50,
            message = "El número de documento no puede superar los 50 caracteres")
    private String numeroDocumento;

    @NotBlank(message = "El tipo de pago es obligatorio")
    @Size(max = 30,
            message = "El tipo de pago no puede superar los 30 caracteres")
    private String tipoPago;

    @NotBlank(message = "El estado de la venta es obligatorio")
    @Size(max = 30,
            message = "El estado no puede superar los 30 caracteres")
    private String estado;

    @NotNull(message = "El subtotal es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true,
            message = "El subtotal no puede ser negativo")
    private BigDecimal subtotal;

    @NotNull(message = "El impuesto es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true,
            message = "El impuesto no puede ser negativo")
    private BigDecimal impuesto;

    @NotNull(message = "El total es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true,
            message = "El total no puede ser negativo")
    private BigDecimal total;

    public VentaRequestDTO() {
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(BigDecimal impuesto) {
        this.impuesto = impuesto;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}