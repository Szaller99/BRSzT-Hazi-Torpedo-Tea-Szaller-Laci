package program.game;


public class GameState {
    
    public GameSM sm;


    public GameState() {
        this.sm = GameSM.Setup;
    }

    public void updateSM() {
        this.sm = this.sm.nextState();
    }


    public void updateSM(Game game){
        this.sm.nextState();
    }

    public void endGameSM() {
        this.sm.endGame();
    }

    public GameSM getState(){
        return this.sm;
    }

}
