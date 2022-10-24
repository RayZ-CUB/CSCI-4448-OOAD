package com.rz.observer;

import java.io.IOException;

//use Observer patterns

public interface Observer {
     void update(String event) throws IOException;
}
