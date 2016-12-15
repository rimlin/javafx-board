package client.model;

import java.io.Serializable;

/**
 * Created by ilmir on 2016-11-21.
 */
public class Options implements Serializable {
    private final Integer maxAmount;

    public Options(Integer maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Integer getMaxAmount() {
        return maxAmount;
    }
}
