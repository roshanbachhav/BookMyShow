package bookYourShow;
import java.sql.*;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import java.awt.Toolkit;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JPasswordField;
import javax.swing.JToggleButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class signin {

	private JFrame frame;
	private JTextField firstname;
	private JTextField lastname;
	private JTextField username;
	private JTextField age;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					signin window = new signin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection = null;	
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();
	private JTextField email;
	private JPasswordField password;
	
	public signin() {
		initialize();
		connection = Database.dataConnector();
		
	}
	
	private void initialize() {
		frame = new JFrame("Welcome to BookYourShow - Registration form");
		frame.setBackground(new Color(255, 255, 255));
		frame.setForeground(new Color(0, 0, 0));
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\lenovo\\Downloads\\register.png"));
		frame.getContentPane().setBackground(new Color(0, 64, 64));
		frame.getContentPane().setForeground(new Color(255, 255, 255));
		frame.setBounds(-10, 0, 1400, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registration Form");
		lblNewLabel.setForeground(new Color(255, 255, 0));
		lblNewLabel.setFont(new Font("Franklin Gothic Book", Font.BOLD, 25));
		lblNewLabel.setBounds(664, 44, 297, 36);
		frame.getContentPane().add(lblNewLabel);
		
		firstname = new JTextField();
		firstname.setFont(new Font("Tahoma", Font.BOLD, 15));
		firstname.setColumns(10);
		firstname.setBounds(664, 128, 254, 36);
		frame.getContentPane().add(firstname);
		
		lastname = new JTextField();
		lastname.setFont(new Font("Tahoma", Font.BOLD, 15));
		lastname.setColumns(10);
		lastname.setBounds(667, 194, 254, 36);
		frame.getContentPane().add(lastname);
		
		username = new JTextField();
		username.setFont(new Font("Tahoma", Font.BOLD, 15));
		username.setColumns(10);
		username.setBounds(664, 322, 254, 36);
		frame.getContentPane().add(username);
		
		age = new JTextField();
		age.setFont(new Font("Tahoma", Font.BOLD, 15));
		age.setColumns(10);
		age.setBounds(664, 450, 254, 36);
		frame.getContentPane().add(age);
		
		JLabel lblNewLabel_1_1 = new JLabel("First Name");
		lblNewLabel_1_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 20));		
		lblNewLabel_1_1.setBounds(528, 130, 110, 36);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Last Name");
		lblNewLabel_1_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1_2.setBounds(528, 196, 110, 36);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Username");
		lblNewLabel_1_3.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_3.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1_3.setBounds(528, 320, 110, 36);
		frame.getContentPane().add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Password");
		lblNewLabel_1_4.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_4.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1_4.setBounds(528, 387, 110, 36);
		frame.getContentPane().add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("Age");
		lblNewLabel_1_5.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_5.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1_5.setBounds(528, 448, 110, 36);
		frame.getContentPane().add(lblNewLabel_1_5);		
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.setBackground(new Color(0, 255, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				try {
					String query = "insert into signup(firstname,lastname,email,username,password,age) values(?,?,?,?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					
					if(firstname.getText().length()<=0 || lastname.getText().length()<=0 || email.getText().length()<=0 || username.getText().length()<=0 || password.getText().length()<=0 || age.getText().length()<=0)
					{
						JOptionPane.showMessageDialog(null, "Submit all boxes");
					}
					else
					{
					
						
						pst.setString(1, firstname.getText());
						pst.setString(2, lastname.getText());
						pst.setString(3, email.getText());
						pst.setString(4, username.getText());
						pst.setString(5, password.getText());
						pst.setString(6, age.getText());
					
					
						frame.dispose();
						Login l = new Login();
						l.setVisible(true);
					
						pst.executeUpdate();
						pst.close();			
						
					
						JOptionPane.showMessageDialog(null,"Registration was successful...");
					}
					
				}catch (Exception e1)
				{
					JOptionPane.showMessageDialog(null, "Username and password alredy exist...");
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(667, 577, 118, 36);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Gender");
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_2.setBounds(528, 510, 89, 36);
		frame.getContentPane().add(lblNewLabel_2);
		
		JRadioButton male = new JRadioButton("Male");
		buttonGroup.add(male);
		male.setBounds(667, 520, 71, 23);
		frame.getContentPane().add(male);
		
		JRadioButton female = new JRadioButton("Female");
		buttonGroup.add(female);
		female.setBounds(757, 520, 71, 23);
		frame.getContentPane().add(female);
		
		JRadioButton other = new JRadioButton("Other");
		buttonGroup.add(other);
		other.setBounds(847, 520, 71, 23);
		frame.getContentPane().add(other);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstname.setText("");
				lastname.setText("");
				email.setText("");
				username.setText("");
				password.setText("");
				age.setText("");
				buttonGroup.clearSelection();
			}
		});
		btnClear.setBackground(new Color(255, 0, 0));
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnClear.setBounds(826, 577, 110, 36);
		frame.getContentPane().add(btnClear);
		
		email = new JTextField();
		email.setFont(new Font("Tahoma", Font.BOLD, 15));
		email.setColumns(10);
		email.setBounds(667, 254, 254, 36);
		frame.getContentPane().add(email);
		
		JLabel lblNewLabel_1_6 = new JLabel("Email");
		lblNewLabel_1_6.setForeground(Color.BLACK);
		lblNewLabel_1_6.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1_6.setBounds(528, 254, 110, 36);
		frame.getContentPane().add(lblNewLabel_1_6);
		
		password = new JPasswordField();
		password.setBounds(664, 390, 254, 36);
		frame.getContentPane().add(password);
		
		final JToggleButton show = new JToggleButton();
		
		show.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(show.isSelected())
				{
					Image image1 = new ImageIcon(this.getClass().getResource("/passwords.png")).getImage();
					show.setIcon(new ImageIcon(image1));
					password.setEchoChar((char)0);
					
				}
				else
				{
					Image image1 = new ImageIcon(this.getClass().getResource("/shows.png")).getImage();
					show.setIcon(new ImageIcon(image1));
					password.setEchoChar('\u25cf');
				}
			}
		});
		Image image1 = new ImageIcon(this.getClass().getResource("/shows.png")).getImage();
		show.setIcon(new ImageIcon(image1));
		show.setBounds(954, 390, 71, 39);
		frame.getContentPane().add(show);
	}
}
