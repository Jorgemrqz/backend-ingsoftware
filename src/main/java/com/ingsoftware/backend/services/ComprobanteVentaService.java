package com.ingsoftware.backend.services;

import java.util.List;
import java.util.Optional;

import com.ingsoftware.backend.model.ComprobanteVenta;

public interface ComprobanteVentaService {

    List<ComprobanteVenta> getComprobantesVenta();

    Optional<ComprobanteVenta> getComprobanteVenta(Long id);

    ComprobanteVenta createComprobanteVenta(ComprobanteVenta comprobante);

    ComprobanteVenta updateComprobanteVenta(ComprobanteVenta comprobante);

    void deleteComprobanteVenta(Long id);
}
