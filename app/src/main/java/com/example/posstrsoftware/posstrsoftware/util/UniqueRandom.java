package com.example.posstrsoftware.posstrsoftware.util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by Wasabi on 11/28/2016.
 */

public class UniqueRandom {
    private Random random = new Random();
    private Set<Integer> usedNumbers = new HashSet();

    public int nextInt() {
        int potentialRandom = this.random.nextInt();

        // try while we don't find unique random
        while (this.usedNumbers.contains(potentialRandom)) {
            potentialRandom = this.random.nextInt();
        }

        // reserve found unique random number
        this.usedNumbers.add(potentialRandom);

        return potentialRandom;
    }

}
