package ru.homework.otusproject.logisticstock.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.homework.otusproject.entity.Cargo;
import ru.homework.otusproject.logisticstock.service.CargoService;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/cargo")
public class CargoController {
    public final CargoService cargoService;

    public CargoController(CargoService cargoService) {this.cargoService = cargoService;}

    @GetMapping("/{id}")
    public ResponseEntity<Cargo> findById(@PathVariable("id") Long id) {
        Cargo cargo = null;
        try {
            cargo = cargoService.findById(id);
        } catch (NoSuchElementException e) { // если объект не будет найден
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(cargo);
    }

    @PostMapping("/add")
    public ResponseEntity<Cargo> add(@RequestBody Cargo cargo) {
        if (cargo.getId() != null) {
            return new ResponseEntity("redudant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(cargoService.add(cargo));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable("id") Long id) {
        Cargo cargo = cargoService.findById(id);
        if (cargo.getId() == 0) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }
        cargoService.update(cargo);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {

        try {
            cargoService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
