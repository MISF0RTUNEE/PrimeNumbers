package com.example.PrimeNumbers;

import com.example.PrimeNumbers.Entity.PrimeNumber;
import com.example.PrimeNumbers.Repository.PrimeNumberRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;




@EnableRabbit
@Component
public class RabbitMqListener {

    private final PrimeNumberRepository repository;
    RabbitMqListener(PrimeNumberRepository repository){
        this.repository = repository;
    }

    @RabbitListener(queues = "myQueue")
    public void processMyQueue(String jsonPayload) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        PrimeNumberInput primeNumberInput = objectMapper.readValue(jsonPayload, PrimeNumberInput.class);

       int count = 0;
        int first = 0;
        int last = 0;
        boolean isFirst = true;
        for(int i = primeNumberInput.getStart(); i <= primeNumberInput.getEnd(); i ++){
            boolean isPrime = true;

            for(int j = 2; j < i; j++){
                if(i % j == 0){
                    isPrime = false;
                    break;
                }
            }

            if(isPrime){
                count++;
                if(isFirst){
                    first = i;
                    isFirst = false;
                }
                last = i;
            }
        }
        
        repository.save(new PrimeNumber(primeNumberInput.getGuid(),first,last,count));


    }
}
