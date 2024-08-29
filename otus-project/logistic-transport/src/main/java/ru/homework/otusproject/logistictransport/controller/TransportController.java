package ru.homework.otusproject.logistictransport.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.homework.otusproject.entity.Transport;
import ru.homework.otusproject.logistictransport.service.TransportService;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/transport")
public class TransportController {
    public final TransportService transportService;

    public TransportController(TransportService transportService) {this.transportService = transportService;}

    @GetMapping("/{id}")
    public ResponseEntity<Transport> findById(@PathVariable("id") Long id) {
        Transport transport = null;
        try {
            transport = transportService.findById(id);
        } catch (NoSuchElementException e) { // если объект не будет найден
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(transport);
    }

    @PostMapping("/add")
    public ResponseEntity<Transport> add(@RequestBody Transport transport) {
        if (transport.getId() != null) {
            return new ResponseEntity("redudant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(transportService.add(transport));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable("id") Long id) {
        Transport transport = transportService.findById(id);
        if (transport.getId() == null) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }
        transportService.update(transport);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {

        try {
            transportService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/free-track")
    public ResponseEntity<Transport> findFreeTrack(@RequestBody Long count) {
        Transport transport = transportService.findByParams(count);
        return ResponseEntity.ok(transport);
    }
    @GetMapping("/gettrack/{count}")
    public ResponseEntity<Transport> getFreeTrack(@PathVariable("count") Long count) {
        Transport transport = transportService.findByParams(count);
        return ResponseEntity.ok(transport);
    }
}
