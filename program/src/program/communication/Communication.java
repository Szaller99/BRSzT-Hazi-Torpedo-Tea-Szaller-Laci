package program.communication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.DatagramSocket;
import java.net.InetAddress;

import program.game.Battleship;
import program.game.Orient;

public class Communication extends Thread {
    protected DataInputStream dis;
    protected DataOutputStream dos;
    protected int port = 4999;
    protected InetAddress myIpAddress;
    protected InetAddress serverIpAddress;
    public String serverIpAddressString;
    public boolean clientAccepted = false;

    public Communication(boolean isHost){
        
    }

    public Communication(InetAddress serverIp){
        this.serverIpAddress = serverIp;
    }

    public static String getMyIpAddressString() {
        try(final DatagramSocket socket = new DatagramSocket()){
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            return socket.getLocalAddress().getHostAddress();
        }
        catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    public void getMyIpAddress() {
        try(final DatagramSocket socket = new DatagramSocket()){
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            this.myIpAddress = socket.getLocalAddress();
        }
        catch (Exception e) {
            e.printStackTrace();
            
        }
    }

    public String prepareBattleshiToSend(Battleship ship) {
        String data = "";
        data += ship.getX();
        data += ",";
        data += ship.getY();
        data += ",";
        data += ship.getIntOrient();
        data += ",";
        data += ship.getLength();
        data += ";";
    
        return data;
    }

    public String prepareAllBattleshipToSend(Battleship[] ships) {
        String data = "";
        int k = ships.length;
        for (int i = 0; i < k; i++) {
            data += this.prepareBattleshiToSend(ships[i]);
        }
        return data;
    }

    public Battleship[] parseShips(String dataFromCommunication) {
        Battleship[] battleships = new Battleship[7];
        int posX, posY, len;
        Orient orient;
        String[] stringShips = dataFromCommunication.split(";");
        int i = 0;
        for (String ship : stringShips) {
            String[] stringData = ship.split(",");
            posX = Integer.parseInt(stringData[0]);
            posY = Integer.parseInt(stringData[1]);
            orient =(Integer.parseInt(stringData[2]) == 0) ? Orient.VERTICAL : Orient.HORIZONTAL;
            len = Integer.parseInt(stringData[3]);
            battleships[i] = new Battleship(posX, posY, len, orient);
        }

        return battleships;
    }

    public String prepareShotToSend(int x, int y){
        String data = "";
        data += String.valueOf(x);
        data += ",";
        data += String.valueOf(x);
        return data;
    }

    public int parseShotX(String shot){
        String[] coordinates = shot.split(",");
        return Integer.parseInt(coordinates[0]);
    }

    public int parseShotY(String shot){
        String[] coordinates = shot.split(",");
        return Integer.parseInt(coordinates[1]);
    }


    
}

