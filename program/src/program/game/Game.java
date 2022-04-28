package program.game;
import program.game.BattleShip;

public class Game {

    private BattleShip[] hostShips;
    private BattleShip[] enemyShips;

    public Game() {
        int len;
        for(int i = 0; i < 7; i++)
        {
            switch (i) {
                case 0:
                case 1:
                    len = 1;
                    break;
                case 2:
                case 3:
                    len = 2;
                    break;
                case 4:
                    len = 3;
                    break;
                case 5:
                    len = 4;
                    break;
                case 6:
                    len = 5;
                    break;
                default:
                    len = 0;
                    break;
            }
            this.hostShips[i] = new BattleShip(len);
            this.enemyShips[i] = new BattleShip(len);
        }
        
    }

    public boolean placeShip(int len, int xPos, int yPos, Orient or) {
        int i = 0;
        while (!(this.hostShips[i].getlength() == len && this.hostShips[i].isPlaced() == false)) {
            i++;
        }
        if(i>7) {
            return false;
        } else {
            this.hostShips[i].placeShip(len, xPos, yPos, or);
            return true;
        }
    }
}
