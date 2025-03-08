import java.awt.print.Printable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

class HandleAClient implements Runnable {
	private Socket socket; // A connected socket

	private ObjectInputStream inputFromClient;
	private ObjectOutputStream outputToClient;

	private static Info localInfo;
	private static Info ServerInfo;
	public static int count1 = 0;
	public static int count2 = 0;
	public static int count3 = 0;
	public static int win = 0;
	public static String winnerString = "";

	public HandleAClient(Socket socket, Info info, String winnerString1) {
		this.socket = socket;
		this.ServerInfo = info;
		winnerString = winnerString1;

	}

	public Info getInfo() {
		return localInfo;
	}

	public void run() {
		try {
			// Create data input and output streams
			inputFromClient = new ObjectInputStream(socket.getInputStream());
			outputToClient = new ObjectOutputStream(socket.getOutputStream());

// Continuously serve the client
			while (true) {
				// Receive info from the client
				localInfo = (Info) inputFromClient.readObject();

				// load teachers text - send it to client (if status = 0)
				if (localInfo.getStatus() == 0)
					outputToClient.writeObject(ServerInfo);

				// get result from server(if status = 1)
				else if (localInfo.getStatus() == 1) {

					win = AddPoint(); // function that add the choose option to the summary and put the highest vote
										// count into win

					ServerInfo.setWin(WhoIsTheWinner(win));// check if it is a TIE
					localInfo.setWin(ServerInfo.getWin()); // set the winning option
					
					System.out.println("count1: " + count1);
					System.out.println("count2: " + count2);
					System.out.println("count3: " + count3);
					System.out.println("win: " + ServerInfo.getWin());

				}
			}
		}
		catch (
		IOException e) {
			System.err.println(e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static synchronized int AddPoint() { // adding the option chosen to the summery
		if (localInfo.getCount1() == 1) {
			count1++;
			ServerInfo.setCount1(count1);
		}
		if (localInfo.getCount2() == 1) {
			count2++;
			ServerInfo.setCount1(count2);

		}
		if (localInfo.getCount3() == 1) {
			count3++;
			ServerInfo.setCount1(count3);
		}
		return Math.max(count1, Math.max(count2, count3)); //return who has the most points

	}

	public static String WhoIsTheWinner(int win) { //testing if there is a tie

		if (count1 == win && count2 == win && count3 == win) {
			return "a tie between All The Options ";
		} else if (count1 == win && count2 == win) {
			return "a tie between " + localInfo.getOpt1() + " and " + localInfo.getOpt2();
		} else if (count1 == win && count3 == win) {
			return "a tie between " + localInfo.getOpt1() + " and " + localInfo.getOpt3();
		} else if (count3 == win && count2 == win) {
			return "a tie between " + localInfo.getOpt2() + " and " + localInfo.getOpt3();
		}
		else if (count1 == win) {
			return localInfo.getOpt1();

		} else if (count2 == win) {
			return localInfo.getOpt2();

		} else if (count3 == win) {
			return localInfo.getOpt3();
			
		}
		return "Error";

	}

	public static String getWinnerString() {
		return winnerString;
	}

	public static void setWinnerString(String winnerString) {
		HandleAClient.winnerString = winnerString;
	}

}
