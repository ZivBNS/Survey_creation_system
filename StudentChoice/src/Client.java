import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class Client {

 // IO streams
 private static ObjectOutputStream toServer;
 private static ObjectInputStream fromServer;
	
 private Socket socket;

 public Client(){
		
    try {
     // Create a socket to connect to the server
     socket = new Socket("localhost", 8000);
   
   // Create an output stream to send data 
  // to the server
   toServer = new ObjectOutputStream(socket.getOutputStream());
   
   // Create an input stream to receive data
   // from the server
    fromServer = new ObjectInputStream(socket.getInputStream());
	 }
 catch (IOException ex) {  }
}
	
 public static void writeToServer(Info info1){
   try {
	toServer.writeObject(info1);
	toServer.flush();
	toServer.reset();
  } catch (IOException e) {e.printStackTrace(); }
 }

 public Object readFromServer(){		
   try {
 return fromServer.readObject();
  } catch (IOException e) {e.printStackTrace(); } catch (ClassNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
 return 0;
}
			
}

