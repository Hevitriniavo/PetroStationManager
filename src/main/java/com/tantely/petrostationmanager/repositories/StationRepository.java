package com.tantely.petrostationmanager.repositories;

import com.tantely.petrostationmanager.entities.Station;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface StationRepository {
    Optional<Station> findById(Integer id) throws SQLException;

    Station update(Station toUpdate) throws SQLException;

    Station create(Station toCreate) throws SQLException;

    Optional<Station> delete(Integer id) throws SQLException;

    List<Station> findAll() throws SQLException;
}
