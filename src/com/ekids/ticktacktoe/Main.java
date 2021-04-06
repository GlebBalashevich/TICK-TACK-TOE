package com.ekids.ticktacktoe;

import com.ekids.ticktacktoe.processor.GameProcessor;

public class Main {

    public static void main(String[] args) {
        GameProcessor gameProcessor = new GameProcessor();
        gameProcessor.run();
    }
}
