package com.example.PrimeNumbers;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;



public class PrimeNumberInput {

    private UUID guid;

    private int start;

    private int end;

    public PrimeNumberInput(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public PrimeNumberInput() {
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public UUID getGuid() {
        return guid;
    }

    public void setGuid(UUID guid) {
        this.guid = guid;
    }
}
