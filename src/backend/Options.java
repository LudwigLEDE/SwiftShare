package src.backend;

public class Options {


    public boolean soundOn;
    public int port;
    public String username;


    public Options( int port, String username ,boolean soundOn) {
        this.port = port;
        this.username = username;
        this.soundOn = soundOn;
    }
}
