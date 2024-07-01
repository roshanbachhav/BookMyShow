package bookYourShow;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	
	private JPasswordField password;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					Login frame = new Login();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection = null;
	
	
	public Login() {
		connection = Database.dataConnector();
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\lenovo\\Downloads\\register.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(-10, 0, 700, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login Page");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(243, 42, 198, 35);
		contentPane.add(lblNewLabel);
		
		username = new JTextField();
		username.setFont(new Font("Dialog", Font.BOLD, 20));
		username.setHorizontalAlignment(SwingConstants.CENTER);
		username.setBounds(244, 148, 243, 35);
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Monospaced", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(64, 149, 107, 27);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Monospaced", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(64, 231, 107, 27);
		contentPane.add(lblNewLabel_1_1);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String query = "select * from signup where username =? and password =?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, username.getText());
					pst.setString(2, password.getText());
					
					
					ResultSet result = pst.executeQuery();
					
					int count = 0;
					
					while(result.next())
					{
						count = count + 1;
					}
					if(count == 1)
					{
						JOptionPane.showMessageDialog(null,"Data send Successfully");
						TicketBook tb = new TicketBook();
						tb.setVisible(true);
						dispose();
					}
					else if(count > 1)
					{
						JOptionPane.showMessageDialog(null,"Username and Password are alredy exist");
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Invaid username and password");
					}
					
					result.close();
					pst.close();
				}
				catch (Exception e2){
					JOptionPane.showMessageDialog(null, "Something went wrong \n" + e2);
				}
			}
		});
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 20));
		btnNewButton.setBounds(242, 323, 107, 41);
		contentPane.add(btnNewButton);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username.setText("");
				password.setText("");
			}
		});
		btnClear.setFont(new Font("Calibri", Font.BOLD, 20));
		btnClear.setBounds(380, 323, 107, 41);
		contentPane.add(btnClear);
		
		password = new JPasswordField();
		password.setHorizontalAlignment(SwingConstants.CENTER);
		password.setFont(new Font("Dialog", Font.BOLD, 20));
		password.setBounds(243, 231, 243, 35);
		contentPane.add(password);
		
		final JToggleButton show = new JToggleButton("");
		show.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(show.isSelected())
				{
					password.setEchoChar((char)0);
					Image image1 = new ImageIcon(this.getClass().getResource("/passwords.png")).getImage();
					show.setIcon(new ImageIcon(image1));
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
		show.setBounds(517, 222, 66, 47);
		contentPane.add(show);
	}
}
