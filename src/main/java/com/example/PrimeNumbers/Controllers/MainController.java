package com.example.PrimeNumbers.Controllers;

import com.example.PrimeNumbers.Entity.PrimeNumber;
import com.example.PrimeNumbers.PrimeNumberInput;
import com.example.PrimeNumbers.PrimeNumberNotFoundException;
import com.example.PrimeNumbers.Repository.PrimeNumberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.UUID;


@RestController
public class MainController {

    @Autowired
    AmqpTemplate template;

    private final PrimeNumberRepository repository;
    MainController(PrimeNumberRepository repository){
        this.repository = repository;
    }




    @PostMapping("/calc/prime")
    public UUID createPrimeNumber(@RequestBody PrimeNumberInput primeNumberInput) throws IOException {
        primeNumberInput.setGuid(UUID.randomUUID());
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writeValueAsString(primeNumberInput);
        template.convertAndSend("myQueue", jsonPayload);
        return primeNumberInput.getGuid();
    }
    /*@GetMapping("/calc/prime/{guid}")
    public PrimeNumber getPrimeNumber(@PathVariable UUID guid){
        return repository.findByGuid(guid);

    }

     */

    @GetMapping("/calc/prime/{guid}")
    public ResponseEntity<PrimeNumber> getPrimeNumber(@PathVariable UUID guid){
        PrimeNumber primeNumber = repository.findByGuid(guid);
        if (primeNumber == null) {
            throw new PrimeNumberNotFoundException("Prime number not found with guid: " + guid);
        }
        return ResponseEntity.ok(primeNumber);
    }


}



