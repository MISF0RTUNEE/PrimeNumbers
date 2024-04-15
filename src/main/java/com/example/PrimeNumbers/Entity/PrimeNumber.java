package com.example.PrimeNumbers.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity(name = "PrimeNumber")
public class PrimeNumber {

    @Id
    private UUID guid;
    private int first;
    private int last;
    private int count;


    public PrimeNumber(UUID guid,  int first, int last, int count) {
        this.guid = guid;
        this.first = first;
        this.last = last;
        this.count = count;
    }

    public PrimeNumber() {
    }

    public int getFirst() {
        return first;
    }

    public int getLast() {
        return last;
    }

    public int getCount() {
        return count;
    }
}
