package com.kpfu.itis.gasstation.service.entities;

import com.kpfu.itis.gasstation.entities.Fuel;
import com.kpfu.itis.gasstation.repositories.FuelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Rustem.
 */
@Service
public class FuelService {
    private final FuelRepository fuelRepository;

    @Autowired
    public FuelService(FuelRepository fuelRepository) {
        this.fuelRepository = fuelRepository;
    }

    public List<Fuel> getAllFuels() {
        return fuelRepository.findAll();
    }
}
