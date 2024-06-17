package src.backend;

public class Options {


    public boolean soundOn;
    public int port;
    public String username;


    public Options(boolean soundOn, int port, String username) {
        this.soundOn = soundOn;
        this.port = port;
        this.username = username;
    }
}
