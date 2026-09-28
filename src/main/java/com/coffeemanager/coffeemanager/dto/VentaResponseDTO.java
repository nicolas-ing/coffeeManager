package com.coffeemanager.coffeemanager.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VentaResponseDTO {

    private Integer idVenta;
    private Integer idCliente;
    private Integer idUsuario;
    private Integer idPedido;
    private LocalDateTime fecha;
    private String numeroDocumento;
    private String tipoPago;
    private String estado;
    private BigDecimal subtotal;
    private BigDecimal impuesto;
    private BigDecimal total;

    public VentaResponseDTO() {
    }

    public VentaResponseDTO(
            Integer idVenta,
            Integer idCliente,
            Integer idUsuario,
            Integer idPedido,
            LocalDateTime fecha,
            String numeroDocumento,
            String tipoPago,
            String estado,
            BigDecimal subtotal,
            BigDecimal impuesto,
            BigDecimal total) {

        this.idVenta = idVenta;
        this.idCliente = idCliente;
        this.idUsuario = idUsuario;
        this.idPedido = idPedido;
        this.fecha = fecha;
        this.numeroDocumento = numeroDocumento;
        this.tipoPago = tipoPago;
        this.estado = estado;
        this.subtotal = subtotal;
        this.impuesto = impuesto;
        this.total = total;
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