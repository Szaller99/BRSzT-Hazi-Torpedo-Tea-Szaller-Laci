package program.game;

import org.json.simple.JSONObject;


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
        this.getJSON();
    }


    public void update(Game game){

    }

    public JSONObject getJSON() {
        JSONObject gameJSON = new JSONObject();
        gameJSON.put("state", this.gameStage);
        return gameJSON;
    }
}
