package program.controller.game;

public class Player {
    public String name;
    public String ipAddress;
    private boolean isMe = false;
    private boolean isReady = false;

    public Player() {

    }

    public Player(boolean isMe) { this.isMe = isMe; }

    public void setReady(){
        this.isReady = true;
    }

    public void setMe(boolean isMe){
        this.isMe = isMe;
    }

    public boolean getReady(){
        return this.isReady;
    }

    public boolean isMe(){
        return this.isMe;
    }
}
