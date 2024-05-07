package com.tantely.petrostationmanager.rest.controllers;

import com.tantely.petrostationmanager.entities.Station;
import com.tantely.petrostationmanager.services.StationService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/stations")
public class StationRestController {
    private final StationService stationService;

    public StationRestController(StationService stationService) {
        this.stationService = stationService;
    }

    @PostMapping("/create")
    public Station createStation(@RequestBody Station station){
        return stationService.createStation(station);
    }

    @PutMapping("/{id}")
    public Station updateStation(@PathVariable Integer id, @RequestBody Station station){
        return stationService.updateStation(id, station);
    }
}
