package com.tantely.petrostationmanager.repositories.impl;

import com.tantely.petrostationmanager.entities.Station;
import com.tantely.petrostationmanager.repositories.StationRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StationRepositoryImpl implements StationRepository {
    private final Connection connection;

    public StationRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Station> findById(Integer id) throws SQLException {
        final var query = "SELECT * FROM stations WHERE id = ?";
        try (final var stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (final var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToStation(rs));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Station update(Station toUpdate) throws SQLException {
        final String query = "UPDATE stations SET name = ?, address = ?, max_volume_diesel = ?, max_volume_gasoline = ?, max_volume_petrol = ? WHERE id = ?";
        try (final var stmt = connection.prepareStatement(query)) {
            setStationParameters(stmt, toUpdate);
            stmt.setInt(6, toUpdate.getId());
            final var rows = stmt.executeUpdate();
            if (rows > 0) {
                return toUpdate;
            }
        }
        return null;
    }

    @Override
    public Station create(Station toCreate) throws SQLException {
        final var query = "INSERT INTO stations (name, address, max_volume_diesel, max_volume_gasoline, max_volume_petrol) VALUES (?, ?, ?, ?, ?)";
        try (final var stmt = connection.prepareStatement(query)) {
            setStationParameters(stmt, toCreate);
            final var rows = stmt.executeUpdate();
            if (rows > 0) {
                try (final var generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        final var id = generatedKeys.getInt(1);
                        return this.findById(id).orElse(null);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Optional<Station> delete(Integer id) throws SQLException {
        final var foundStation = this.findById(id);
        final var query = "DELETE FROM stations WHERE id = ?";
        try (final var stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            final var rows = stmt.executeUpdate();
            if (rows > 0) {
                return foundStation;
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Station> findAll() throws SQLException {
        final var query = "SELECT * FROM stations";
        final var stations = new ArrayList<Station>();
        try (final var stmt = connection.prepareStatement(query);
             final var rs = stmt.executeQuery()) {
            while (rs.next()) {
                stations.add(mapResultSetToStation(rs));
            }
        }
        return stations;
    }

    private Station mapResultSetToStation(ResultSet rs) throws SQLException {
        return new Station()
                .setId(rs.getInt("id"))
                .setName(rs.getString("name"))
                .setAddress(rs.getString("address"))
                .setMaxVolumeDiesel(rs.getDouble("max_volume_diesel"))
                .setMaxVolumeGasoline(rs.getDouble("max_volume_gasoline"))
                .setMaxVolumePetrol(rs.getDouble("max_volume_petrol"));
    }

    private void setStationParameters(PreparedStatement stmt, Station station) throws SQLException {
        stmt.setString(1, station.getName());
        stmt.setString(2, station.getAddress());
        stmt.setDouble(3, station.getMaxVolumeDiesel());
        stmt.setDouble(4, station.getMaxVolumeGasoline());
        stmt.setDouble(5, station.getMaxVolumePetrol());
    }
}
