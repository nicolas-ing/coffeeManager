package com.coffeemanager.coffeemanager.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CompraResponseDTO {

    private Integer idCompra;
    private Integer idProveedor;
    private Integer idUsuario;
    private LocalDateTime fecha;
    private String numeroDocumento;
    private String estado;
    private BigDecimal subtotal;
    private BigDecimal impuesto;
    private BigDecimal total;

    public CompraResponseDTO() {
    }

    public CompraResponseDTO(
            Integer idCompra,
            Integer idProveedor,
            Integer idUsuario,
            LocalDateTime fecha,
            String numeroDocumento,
            String estado,
            BigDecimal subtotal,
            BigDecimal impuesto,
            BigDecimal total) {

        this.idCompra = idCompra;
        this.idProveedor = idProveedor;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.numeroDocumento = numeroDocumento;
        this.estado = estado;
        this.subtotal = subtotal;
        this.impuesto = impuesto;
        this.total = total;
    }

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
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

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
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