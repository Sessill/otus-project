package ru.homework.otusproject.logisticclaim.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import ru.homework.otusproject.entity.Claims;
import ru.homework.otusproject.logisticclaim.service.ClaimService;
import ru.homework.otusproject.utils.rest.webclient.ClaimWebClientBuilder;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/claims")
public class ClaimController {
    public final ClaimService claimService;

    private ClaimWebClientBuilder claimWebClientBuilder;

    public ClaimController(ClaimService claimService,ClaimWebClientBuilder claimWebClientBuilder)
    {
        this.claimService = claimService;
        this.claimWebClientBuilder = claimWebClientBuilder;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Claims> findById(@PathVariable("id") Long id) {
        Claims claims = null;
        try {
            claims = claimService.findById(id);
        } catch (NoSuchElementException e) { // если объект не будет найден
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(claims);
    }

    @GetMapping("/damages")
    public ResponseEntity<String> getDamages(@AuthenticationPrincipal Jwt jwt){
        String damages = claimWebClientBuilder.getDamage(jwt).block();
        return ResponseEntity.ok(damages);
    }

    @PostMapping("/add")
    public ResponseEntity<Claims> add(@RequestBody Claims claims) {
        if (claims.getId() != null) {
            return new ResponseEntity("redudant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(claimService.add(claims));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable("id") Long id) {
        Claims claims = claimService.findById(id);
        if (claims.getId() == null) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }
        claimService.update(claims);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {

        try {
            claimService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
