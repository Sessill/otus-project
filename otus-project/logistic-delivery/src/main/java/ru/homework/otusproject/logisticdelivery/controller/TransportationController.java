package ru.homework.otusproject.logisticdelivery.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.homework.otusproject.entity.Transport;
import ru.homework.otusproject.entity.Transportation;
import ru.homework.otusproject.logisticdelivery.service.TransportationService;
import ru.homework.otusproject.utils.rest.webclient.TransportWebClientBuilder;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/delivery")
public class TransportationController {
    public final TransportationService transportationService;
    private TransportWebClientBuilder transportWebClientBuilder;

    public TransportationController(TransportationService transportationService,
                                    TransportWebClientBuilder transportWebClientBuilder)
    {
        this.transportationService = transportationService;
        this.transportWebClientBuilder = transportWebClientBuilder;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transportation> findById(@PathVariable("id") Long id) {
        Transportation transportation = null;
        try {
            transportation = transportationService.findById(id);
        } catch (NoSuchElementException e) { // если объект не будет найден
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(transportation);
    }

    @PostMapping("/add")
    public ResponseEntity<Transportation> add(@RequestBody Transportation transportation,@AuthenticationPrincipal Jwt jwt) {
        if (transportation.getId() != null) {
            return new ResponseEntity("redudant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }
        Transport transport = transportWebClientBuilder.getTrack(Long.valueOf(transportation.getTransportId()),jwt).block();
        transportation.setTransportId(String.valueOf(transport.getId()));
//        return ResponseEntity.ok(transportationService.add(transportation));
        
        return ResponseEntity.ok(transportationService.add(transportation));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable("id") Long id) {
        Transportation transportation = transportationService.findById(id);
        if (transportation.getId() == null) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }
        transportationService.update(transportation);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {

        try {
            transportationService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/damage")
    public ResponseEntity findDamage(){
        String damages = transportationService.findDamage();
        return new ResponseEntity(damages,HttpStatus.OK);
    }
}
