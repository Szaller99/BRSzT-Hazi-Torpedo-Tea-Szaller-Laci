package program.communication;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public Client() throws IOException{
        Socket clientSocket = new Socket("localhost", 4999);
    }
}
