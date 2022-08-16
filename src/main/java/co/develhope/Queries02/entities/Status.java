package co.develhope.Queries02.entities;

import java.util.Random;

public enum Status {
    ONTIME,
    DELAYED,
    CANCELLED;

    private static final Random STATUS = new Random();

    public static Status randomStatus()  {
        Status[] status = values();
        return status[STATUS.nextInt(status.length)];
    }

}
