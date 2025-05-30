package com.ingsoftware.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingsoftware.backend.model.ParametroGeneral;
import com.ingsoftware.backend.repository.ParametroGeneralRepository;

@Service
public class ParametroGeneralImpl implements ParametroGeneralService {

    @Autowired
    private ParametroGeneralRepository parametroGeneralRepository;

    @Override
    public List<ParametroGeneral> getParametrosGenerales() {
        return this.parametroGeneralRepository.findAll();
    }

    @Override
    public Optional<ParametroGeneral> getParametroGeneral(Long id) {
        return this.parametroGeneralRepository.findById(id);
    }

    @Override
    public ParametroGeneral createParametroGeneral(ParametroGeneral parametro) {
        return this.parametroGeneralRepository.save(parametro);
    }

    @Override
    public ParametroGeneral updateParametroGeneral(ParametroGeneral parametro) {
        return this.parametroGeneralRepository.save(parametro);
    }

    @Override
    public void deleteParametroGeneral(Long id) {
        this.parametroGeneralRepository.deleteById(id);
    }
}
