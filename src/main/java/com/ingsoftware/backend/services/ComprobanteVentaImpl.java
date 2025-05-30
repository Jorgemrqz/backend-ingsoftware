package com.ingsoftware.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingsoftware.backend.model.ComprobanteVenta;
import com.ingsoftware.backend.repository.ComprobanteVentaRepository;

@Service
public class ComprobanteVentaImpl implements ComprobanteVentaService {

    @Autowired
    private ComprobanteVentaRepository comprobanteVentaRepository;

    @Override
    public List<ComprobanteVenta> getComprobantesVenta() {
        return this.comprobanteVentaRepository.findAll();
    }

    @Override
    public Optional<ComprobanteVenta> getComprobanteVenta(Long id) {
        return this.comprobanteVentaRepository.findById(id);
    }

    @Override
    public ComprobanteVenta createComprobanteVenta(ComprobanteVenta comprobante) {
        return this.comprobanteVentaRepository.save(comprobante);
    }

    @Override
    public ComprobanteVenta updateComprobanteVenta(ComprobanteVenta comprobante) {
        return this.comprobanteVentaRepository.save(comprobante);
    }

    @Override
    public void deleteComprobanteVenta(Long id) {
        this.comprobanteVentaRepository.deleteById(id);
    }
}
