package com.rz.observer;

import java.io.BufferedWriter;
import java.io.IOException;

// 55 & 96
public class Logger implements Observer {
    BufferedWriter writer;

//    public Logger(BufferedWriter writer) {
//        this.writer = writer;
//    }

    private static Logger logger = null;

    //lazy instantiation Singleton pattern for logger
    public static Logger getInstance(BufferedWriter writer) {
        if (logger == null)
            logger = new Logger();
        return logger;
    }

//    private static final Logger logger = new logger;
//    private logger(){};
//    public static Logger getInstance(){return logger};

    @Override
    public void update(String event) throws IOException {
        writer.write(event);
        writer.newLine();
    }
}
