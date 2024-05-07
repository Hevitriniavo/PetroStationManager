package com.tantely.petrostationmanager.services;

import com.tantely.petrostationmanager.entities.Station;

public interface StationService {
    Station updateStation(Integer id, Station station);

    Station createStation(Station station);
}
