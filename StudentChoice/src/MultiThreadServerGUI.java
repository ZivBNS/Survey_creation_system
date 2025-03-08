import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MultiThreadServerGUI extends JFrame implements ActionListener {

	private JTextArea jta = new JTextArea();
	private Server server = new Server();
	JLabel lblQus = new JLabel("Enter The Qustion-->");
	JLabel lbl1 = new JLabel("First Option:");
	JLabel lbl2 = new JLabel("Second Option:");
	JLabel lbl3 = new JLabel("Third Option:");

	JTextField TFQues = new JTextField();
	JTextField TF1 = new JTextField();
	JTextField TF2 = new JTextField();
	JTextField TF3 = new JTextField();

	JButton btnAccept = new JButton("Accept");
	JButton btnSum = new JButton("Summary");
	
	public static String winnerString="NOTHING";

	
	public Info info1 = new Info(TFQues.getText(), TF1.getText(), TF2.getText(), TF3.getText(), 0, 0, 0, 0,"default");//reset info

	public static void main(String[] args) {
		new MultiThreadServerGUI();
	}

	public MultiThreadServerGUI() //teacher app and server
	{
		// Place text area on the frame
		JPanel main = new JPanel();
		main.setLayout(new GridLayout(0, 3, 20, 100));
		main.add(lblQus, BorderLayout.PAGE_START);
		lblQus.setFont(new Font("TimesRoman", Font.PLAIN, 25));

		main.add(TFQues, BorderLayout.CENTER);

		main.add(new JLabel(""));
		main.add(lbl1);
		main.add(lbl2);
		main.add(lbl3);
		lbl1.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		lbl2.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		lbl3.setFont(new Font("TimesRoman", Font.PLAIN, 25));

		main.add(TF1, BorderLayout.WEST);
		main.add(TF2, BorderLayout.CENTER);
		main.add(TF3, BorderLayout.EAST);

		main.add(btnSum);
		main.add(btnAccept, BorderLayout.EAST);
		main.add(new JScrollPane(jta), BorderLayout.CENTER);
		btnSum.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		btnSum.addActionListener(this);
		btnAccept.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		btnAccept.addActionListener(this);
		add(main);
		setTitle("Host");
		setSize(1920, 1080);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		
		jta.append("MultiThreadServer started at " + new Date() + '\n');
		int clientNo = 1;
		while (true) { //server starting
			// Listen for a new connection request
			Socket socket = server.Accept();

			// Display the client number
			jta.append("Starting thread for client " + clientNo + " at " + new Date() + '\n');

			// Find the client's host name, and IP address
			InetAddress inetAddress = socket.getInetAddress();
			jta.append("Client " + clientNo + "'s host name is " + inetAddress.getHostName() + "\n");
			jta.append("Client " + clientNo + "'s IP Address is " + inetAddress.getHostAddress() + "\n");

			// Create a new task for the connection
			Thread task = new Thread(new HandleAClient(socket,info1,winnerString));
			task.start();
			clientNo++;
		}
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAccept) { // enter the info in to the object and sent it to the server

			info1.setQueString(TFQues.getText());
			info1.setOpt1(TF1.getText());
			info1.setOpt2(TF2.getText());
			info1.setOpt3(TF3.getText());
		}
		if (e.getSource() == btnSum) 
		{		
			JOptionPane.showMessageDialog(null, "The Winning option is " + info1.getWin(), "Final Result ", JOptionPane.INFORMATION_MESSAGE);

		}
	}
}
