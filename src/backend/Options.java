package src.backend;

public class Options {


    boolean soundOn;
    public int port;
    String username;


    public Options(boolean soundOn, int port, String username) {
        this.soundOn = soundOn;
        this.port = port;
        this.username = username;
    }
}
