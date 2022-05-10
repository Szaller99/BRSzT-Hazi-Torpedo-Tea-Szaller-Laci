package program.communication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Communication {
    protected DataInputStream dis;
    protected DataOutputStream dos;
    protected int port = 4999;
    protected InetAddress myIpAddress;
    protected InetAddress serverIpAddress;
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


    
}

