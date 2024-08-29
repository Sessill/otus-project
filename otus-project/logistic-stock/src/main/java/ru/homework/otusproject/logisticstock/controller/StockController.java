package ru.homework.otusproject.logisticstock.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.homework.otusproject.entity.Stock;
import ru.homework.otusproject.logisticstock.service.CargoService;
import ru.homework.otusproject.logisticstock.service.StockService;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/stock")
public class StockController {
    public final StockService stockService;
    public final CargoService cargoService;

    public StockController(StockService stockService, CargoService cargoService) {
        this.stockService = stockService;
        this.cargoService = cargoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> findById(@PathVariable("id") Long id) {
        Stock stock = null;
        try {
            stock = stockService.findById(id);
        } catch (NoSuchElementException e) { // если объект не будет найден
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(stock);
    }

    @PostMapping("/add")
    public ResponseEntity<Stock> add(@RequestBody Stock stock) {
        if (stock.getId() != null) {
            return new ResponseEntity("redudant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (cargoService.findById(stock.getCargoId())== null){
            return new ResponseEntity("there is no cargo: id "+stock.getCargoId(), HttpStatus.NOT_ACCEPTABLE);
        }else {

        }
        return ResponseEntity.ok(stockService.add(stock));
    }

    @PutMapping("/update/{id}/{count}")
    public ResponseEntity update(@PathVariable("id") Long id, @PathVariable("count") Long count) {
        Stock stock = stockService.findById(id);
        if (stock.getId() == 0) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }
        count = count + stock.getCount();
        stock.setCount(count);
        stockService.update(stock);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {

        try {
            stockService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
