package com.rz.project3.observer;

import java.io.BufferedWriter;
import java.io.IOException;

public class Logger implements Observer{
    BufferedWriter writer;

    public Logger(BufferedWriter writer) {
        this.writer = writer;
    }

    @Override
    public void update(String event) throws IOException {
        writer.write(event);
        writer.newLine();
    }
}
