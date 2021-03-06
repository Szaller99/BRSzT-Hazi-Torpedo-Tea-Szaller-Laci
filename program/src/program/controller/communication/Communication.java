package program.controller.communication;

import program.controller.Controller;
import program.controller.game.Battleship;
import program.controller.game.Orient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Communication extends Thread {
    // private Controller app;
    protected Controller app;
    protected static final String okMessage = "OK";
    protected static final String readyMessage = "READY";
    protected DataInputStream dis;
    protected DataOutputStream dos;
    protected int port = 4999;
    protected InetAddress myIpAddress;
    protected InetAddress serverIpAddress;
    protected boolean waitForShot = false;
    public String serverIpAddressString;
    public boolean clientAccepted = false;

    public Communication(Controller app, boolean isHost){
        this.app = app;
    }

    public Communication(Controller app, InetAddress serverIp){
        this.app = app;
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

    public String prepareBattleshipToSend(Battleship ship) {
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
            data += this.prepareBattleshipToSend(ships[i]);
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
            battleships[i] = new Battleship(len, posX, posY, orient);
            i++;
        }

        return battleships;
    }

    public String prepareShotToSend(int x, int y){
        String data = "";
        data += String.valueOf(x);
        data += ",";
        data += String.valueOf(y);
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

    public void sendOkMessage() {
		// System.out.println("[client] trying to send ready message..");
		try {
			this.dos.writeUTF(Communication.okMessage);  
			this.dos.flush();
		} catch (Exception e) {
			//TODO: handle exception
		}
	}

    public void sendReadyMessage() {
		// System.out.println("[client] trying to send ready message..");
		try {
			this.dos.writeUTF(Communication.readyMessage);  
			this.dos.flush();
		} catch (Exception e) {
			//TODO: handle exception
		}
	}
    
    public void sendShips(Battleship[] ships) {
		// System.out.println("[client] trying to send ready message..");
		String dataToSend = this.prepareAllBattleshipToSend(ships);
		try {
			this.dos.writeUTF(dataToSend);  
			this.dos.flush();
		} catch (Exception e) {
			//TODO: handle exception
		}
	}

    public void sendShot(int x, int y) {
		System.out.println("trying to send shot message..");
		String dataToSend = this.prepareShotToSend(x, y);
		try {
			this.dos.writeUTF(dataToSend);  
			this.dos.flush();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
 
    public String receiveEnemyShips() throws IOException {
        String str = (String)dis.readUTF(); 
        System.out.println("got ships: " + str);
        return str;
    }

    public String receiveEnemyShot() throws IOException {
        String str = (String)dis.readUTF(); 
        System.out.println("got shot: " + str);
        return str;
    }

    public void setwaitForShot(boolean value) {
        this.waitForShot = value;
    }

    public void handleEnemyShot() throws IOException {
        String shot = this.receiveEnemyShot();
		this.app.game.receiveEnemysShoot(this.parseShotX(shot), this.parseShotY(shot));
    }
}

