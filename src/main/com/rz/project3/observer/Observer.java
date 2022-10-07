package com.rz.project3.observer;

import java.io.IOException;

public interface Observer {
    void update(String event) throws IOException;

}
