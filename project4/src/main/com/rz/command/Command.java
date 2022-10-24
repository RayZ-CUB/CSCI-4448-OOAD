package com.rz.command;

import java.io.IOException;

public interface Command {
    void execute(String command) throws IOException;
}
