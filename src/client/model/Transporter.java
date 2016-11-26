package client.model;

import java.io.Serializable;

/**
 * Created by ilmir on 2016-11-21.
 */
public class Transporter implements Serializable {
    private final String operation;
    private final String module;

    public Transporter(String Operation, String Module) {
        this.operation = Operation;
        this.module = Module;
    }

    public String getOperation() {
        return this.operation;
    }

    public String getModule() {
        return this.module;
    }
}
