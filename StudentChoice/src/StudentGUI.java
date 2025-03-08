
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StudentGUI extends JFrame implements ActionListener {

	private Client client = new Client();
	JLabel lblQus = new JLabel("the qustion");
	JButton btnRefresh = new JButton("Refresh");
	JButton btn1 = new JButton("btn1");
	JButton btn2 = new JButton("btn2");
	JButton btn3 = new JButton("btn3");

	private Info info = new Info(null, "", "", "", 0, 0, 0, 0,"");

	public static void main(String[] args) {
		new StudentGUI();
	}

	public StudentGUI() {
		JPanel main = new JPanel();
		main.setLayout(new GridLayout(0, 3, 20, 20));
		main.add(new JLabel(""));
		main.add(lblQus, BorderLayout.PAGE_START); // add text for the question
		lblQus.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		main.add(btnRefresh, BorderLayout.EAST); // add refresh button
		main.add(btn1, BorderLayout.WEST); // add button1
		main.add(btn2, BorderLayout.CENTER);// add button2
		main.add(btn3, BorderLayout.EAST);// add button3
		btnRefresh.setFont(new Font("TimesRoman", Font.PLAIN, 50));
		btn1.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		btn2.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		btn3.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		btnRefresh.addActionListener(this);

		main.add(new JLabel(""));

		add(main);
		setTitle("Client");
		setSize(1920, 1080);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRefresh) // Refresh the page to see the question and the answers
		{
			info.setStatus(0);
			client.writeToServer(info);
			lblQus.setText("");
			info = (Info) client.readFromServer();
			lblQus.setText(info.getQueString());
			btn1.setText(info.getOpt1());
			btn2.setText(info.getOpt2());
			btn3.setText(info.getOpt3());
			
			
			
		}
		else {
			info.setStatus(1);
			if (e.getSource() == btn1) //if option 1 chosen

			{
				info.setOpt1(btn1.getText());
				info.setOpt2(btn2.getText());
				info.setOpt3(btn3.getText());
				info.setCount1(1);
				info.setCount2(0);
				info.setCount3(0);
				btn1.setVisible(false);
				btn2.setVisible(false);
				btn3.setVisible(false);
				client.writeToServer(info);
			}
			
			else if (e.getSource() == btn2) //if option 2 chosen
				{
				info.setOpt1(btn1.getText());
				info.setOpt2(btn2.getText());
				info.setOpt3(btn3.getText());
				info.setCount1(0);
				info.setCount2(1);
				info.setCount3(0);
				btn1.setVisible(false);
				btn2.setVisible(false);
				btn3.setVisible(false);
				client.writeToServer(info);

			}
			else if (e.getSource() == btn3) //if option 3 chosen
				{
				info.setOpt1(btn1.getText());
				info.setOpt2(btn2.getText());
				info.setOpt3(btn3.getText());
				info.setCount1(0);
				info.setCount2(0);
				info.setCount3(1);
				btn1.setVisible(false);
				btn2.setVisible(false);
				btn3.setVisible(false);
				client.writeToServer(info);

			}
			
		}

	}
}
