package program.game;


public class GameState {
    enum State {
        start,
        hostTurn,
        clientTurn,
        end
    }
    public State gameStage;


    public GameState() {
        this.gameStage = State.start;
    }


    public void update(Game game){

    }

}
