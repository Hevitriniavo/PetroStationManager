package com.tantely.petrostationmanager.rest.controllers;

import com.tantely.petrostationmanager.entities.Station;
import com.tantely.petrostationmanager.services.StationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/stations")
public class StationRestController {
    private final StationService stationService;

    public StationRestController(StationService stationService) {
        this.stationService = stationService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Station createStation(@RequestBody Station station){
        return stationService.createStation(station);
    }

    @PutMapping("/{id}")
    public Station updateStation(@PathVariable Integer id, @RequestBody Station station){
        return stationService.updateStation(id, station);
    }


    @GetMapping("/{id}")
    public Station getStationById(@PathVariable Integer id) {
        return stationService.findStationById(id);
    }

    @GetMapping
    public List<Station> getAllStations() {
        return stationService.findAll();
    }


    @DeleteMapping("/{id}")
    public Station deleteStation(@PathVariable Integer id) {
        return stationService.destroyStationById(id);
    }
}
