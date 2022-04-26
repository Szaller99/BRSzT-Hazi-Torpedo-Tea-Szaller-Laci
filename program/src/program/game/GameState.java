package program.game;

import org.json.simple.JSONObject;
import program.view.components.Tile;


public class GameState {
    enum State {
        start,
        hostTurn,
        clientTurn,
        end
    }
    public State gameStage;
    public Tile[] hostTiles = new Tile[11];
    public Tile[] clientTiles = new Tile[11];


    public GameState() {
        this.gameStage = State.start;
    }
    public void update(Game game){

    }
    public JSONObject getJSON() {
        JSONObject gameJSON = new JSONObject();
        gameJSON.put("state", this.gameStage);
        return gameJSON;
    }
}
