package io.bagelz.editor.model;


/**
 * Created by green3 on 23/3/17.
 */
public class Pong {

    private final String message;
    private final int timeStamp;

    public Pong(String message, int timeStamp) {
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public int getTimeStamp() {
        return timeStamp;
    }

}
