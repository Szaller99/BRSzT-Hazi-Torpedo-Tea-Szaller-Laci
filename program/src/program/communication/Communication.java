package program.communication;

import java.net.DatagramSocket;
import java.net.InetAddress;

public class Communication {
    Client c;
    Server s;
    public Communication(boolean isHost){
        try {
            if(isHost) {
                s = new Server();
            }
            else {
                c = new Client();
            }
        } 
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static String getMyIpAddress() {
        try(final DatagramSocket socket = new DatagramSocket()){
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            return socket.getLocalAddress().getHostAddress();
        }
        catch (Exception e) {
            //TODO: handle exception
            return "";
        }
    }
    
}
