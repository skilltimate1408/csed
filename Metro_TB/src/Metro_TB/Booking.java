package Metro_TB;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Booking {

	private JFrame frame;
	private JTextField t1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Booking window = new Booking();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Booking() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 668, 475);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(57, 43, 162, 55);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblFromStation = new JLabel("From Station");
		lblFromStation.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblFromStation.setBounds(57, 108, 162, 55);
		frame.getContentPane().add(lblFromStation);
		
		JLabel lblToStation = new JLabel("To Station");
		lblToStation.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblToStation.setBounds(57, 173, 162, 55);
		frame.getContentPane().add(lblToStation);
		
		JLabel lblTickets = new JLabel("Tickets");
		lblTickets.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTickets.setBounds(57, 238, 162, 55);
		frame.getContentPane().add(lblTickets);
		
		t1 = new JTextField();
		t1.setBounds(288, 53, 171, 43);
		frame.getContentPane().add(t1);
		t1.setColumns(10);
		
		JComboBox c1 = new JComboBox();
		c1.setModel(new DefaultComboBoxModel(new String[] {"Select", "Nagool", "AMR", "SRNagar", "JNTUH", "MYP"}));
		c1.setBounds(301, 129, 162, 34);
		frame.getContentPane().add(c1);
		
		JComboBox c2 = new JComboBox();
		c2.setModel(new DefaultComboBoxModel(new String[] {"Select", "Nagool", "AMR", "SRNagar", "JNTUH", "MYP"}));
		c2.setBounds(301, 194, 176, 34);
		frame.getContentPane().add(c2);
		
		JComboBox c3 = new JComboBox();
		c3.setModel(new DefaultComboBoxModel(new String[] {"Select", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		c3.setBounds(301, 259, 188, 34);
		frame.getContentPane().add(c3);
		
		JButton btnNewButton = new JButton("Book");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=t1.getText();
				String from=(String) c1.getSelectedItem();
				String to=(String) c2.getSelectedItem();
				String t=(String) c3.getSelectedItem();
				int tickets=Integer.parseInt(t);
				int bill=0;
				if(from.equals("MYP") && to.equals("JNTUH"))
				{
					bill=tickets*40;
				}
				else if(from.equals("MYP") && to.equals("SRNagar"))
				{
					bill=tickets*60;
				}
				else
				{
					JOptionPane.showMessageDialog(btnNewButton,
							"Please select");
				}
				try {
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/csed_db","root","Welcome@123");
					String q="insert into mbill values"
						+ "('"+name+"','"+from+"','"+to+"','"+tickets+"','"+bill+"')";
					Statement sta=con.createStatement();
					sta.execute(q);
					con.close();
					JOptionPane.showMessageDialog(btnNewButton, "Hello "+name+" \n From:"+from+" \nTo:"+to+" \n Tickets:"+tickets+"\n Total Bill:"+bill);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(237, 351, 104, 34);
		frame.getContentPane().add(btnNewButton);
	}
}
