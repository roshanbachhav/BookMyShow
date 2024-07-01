package bookYourShow;

import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class profile extends JFrame {

		private JPanel contentPane;
		Connection connection = null;
		JLabel txtuser , txtpass;
		JLabel lblNewLabel , lblPassword;
		
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					profile frame = new profile();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private String loggedInUsername;
    private String loggedInPassword;
	
	public profile() {
		connection = Database.dataConnector();
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 724, 427);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel("UserName :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(161, 54, 117, 25);
		getContentPane().add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPassword.setBounds(168, 131, 110, 25);
		getContentPane().add(lblPassword);
		
		JLabel txtuser = new JLabel("");
		txtuser.setBorder(BorderFactory.createLineBorder(Color.black , 1));
		txtuser.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtuser.setBounds(365, 54, 158, 29);
		contentPane.add(txtuser);
		
		JLabel txtpass = new JLabel("");
		txtpass.setBorder(BorderFactory.createLineBorder(Color.black , 1));
		txtpass.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtpass.setBounds(365, 127, 158, 29);
		contentPane.add(txtpass);
		
		
		
		 loggedInUsername = "username";
	     retrieveUserCredentials(loggedInUsername);

	}


	private void retrieveUserCredentials(String username) {
		
		try {
			 String query = "SELECT username, password FROM signup WHERE username = ?";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setString(1, username);

	         ResultSet resultSet = statement.executeQuery();
	         if (resultSet.next()) {
	                // Retrieve the username and password values
	                loggedInUsername = resultSet.getString("username");
	                loggedInPassword = resultSet.getString("password");

	                // Update the JLabels with the retrieved values
	                txtuser.setText(loggedInUsername);
	                txtpass.setText(loggedInPassword);
	            }

	            // Close the resources
	            resultSet.close();
	            statement.close();
	            connection.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
