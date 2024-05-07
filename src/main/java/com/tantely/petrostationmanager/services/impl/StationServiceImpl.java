package com.tantely.petrostationmanager.services.impl;

import com.tantely.petrostationmanager.entities.Station;
import com.tantely.petrostationmanager.exceptions.BadRequestException;
import com.tantely.petrostationmanager.exceptions.InternalServerException;
import com.tantely.petrostationmanager.exceptions.NotFoundException;
import com.tantely.petrostationmanager.repositories.StationRepository;
import com.tantely.petrostationmanager.services.StationService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class StationServiceImpl implements StationService {
    private final StationRepository stationRepository;

    public StationServiceImpl(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Override
    public Station updateStation(Integer id, Station station) {
        try {
            final var foundStationOptional = stationRepository.findById(id);
            if (foundStationOptional.isPresent()){
                var foundStation = foundStationOptional.get();
                foundStation.setName(station.getName());
                foundStation.setAddress(station.getAddress());
                foundStation.setMaxVolumeDiesel(station.getMaxVolumeDiesel());
                foundStation.setMaxVolumeGasoline(station.getMaxVolumeGasoline());
                foundStation.setMaxVolumePetrol(station.getMaxVolumePetrol());
                return stationRepository.update(foundStation);
            }
            throw new NotFoundException("Station not found");
        } catch (SQLException e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @Override
    public Station createStation(Station station) {
        try {
            if (station.getId() == null){
                return stationRepository.create(station);
            }
            throw new BadRequestException("Station ID must be null for creation");
        } catch (SQLException e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @Override
    public Station findStationById(Integer id) {
        try {
            return stationRepository.findById(id).orElseThrow(() -> new NotFoundException("Station not found"));
        } catch (SQLException e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @Override
    public Station destroyStationById(Integer id) {
        try {
            return stationRepository.delete(id).orElseThrow(() -> new NotFoundException("Station not found"));
        } catch (SQLException e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @Override
    public List<Station> findAll() {
        try {
            return stationRepository.findAll();
        } catch (SQLException e) {
            throw new InternalServerException(e.getMessage());
        }
    }
}
