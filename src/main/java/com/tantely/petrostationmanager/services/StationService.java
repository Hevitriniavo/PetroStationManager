package com.tantely.petrostationmanager.services;

import com.tantely.petrostationmanager.entities.Station;

import java.util.List;

public interface StationService {
    Station updateStation(Integer id, Station station);

    Station createStation(Station station);

    Station findStationById(Integer id);

    Station destroyStationById(Integer id);

    List<Station> findAll();
}
