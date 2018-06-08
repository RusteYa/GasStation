package com.kpfu.itis.gasstation.service.entities_service;

import com.kpfu.itis.gasstation.entities.EngineOil;
import com.kpfu.itis.gasstation.forms.EngineOilForm;
import com.kpfu.itis.gasstation.repositories.EngineOilRepository;
import com.kpfu.itis.gasstation.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Rustem.
 */
@Service
public class EngineOilService {
    private final EngineOilRepository engineOilRepository;
    private final UploadService uploadService;

    @Autowired
    public EngineOilService(EngineOilRepository engineOilRepository, UploadService uploadService) {
        this.engineOilRepository = engineOilRepository;
        this.uploadService = uploadService;
    }

    public List<EngineOil> getAllEngineOils() {
        return engineOilRepository.findAll();
    }

    public List<EngineOil> getAllEngineOilsWithManafacturer(String filter) {
        return engineOilRepository.findAllByManafacturer(filter);
    }

    public List<String> getAllManafacturers() {
        return engineOilRepository.findAllManafacturers();
    }

    public EngineOil saveEngineOilFromEngineOilForm(EngineOilForm engineOilForm) {
        String photoPath = uploadService.upload(engineOilForm.getFileDatas());

        EngineOil engineOil = new EngineOil();
        engineOil.setName(engineOilForm.getName());
        engineOil.setPrice(engineOilForm.getPrice());
        engineOil.setManafacturer(engineOilForm.getManafacturer());
        engineOil.setPhotoPath(photoPath);

        return engineOilRepository.save(engineOil);
    }

    public int deleteEngineOilWithId(int id) {
        return engineOilRepository.deleteEngineOilById(id);
    }

    public EngineOilForm createEngineOilFormFromEngineOilById(int id) {
        EngineOil engineOil = engineOilRepository.findById(id);
        EngineOilForm engineOilForm = new EngineOilForm();
        engineOilForm.setName(engineOil.getName());
        engineOilForm.setPrice(engineOil.getPrice());
        engineOilForm.setManafacturer(engineOil.getManafacturer());

        return engineOilForm;
    }

    public EngineOil updateEngineOilFromEngineOilFormById(int id, EngineOilForm engineOilForm) {
        EngineOil engineOil = engineOilRepository.findById(id);
        String photoPath = uploadService.upload(engineOilForm.getFileDatas());

        engineOil.setName(engineOilForm.getName());
        engineOil.setPrice(engineOilForm.getPrice());
        engineOil.setManafacturer(engineOilForm.getManafacturer());
        if (!"".equals(photoPath)) {
            engineOil.setPhotoPath(photoPath);
        }

        return engineOilRepository.save(engineOil);
    }
}
