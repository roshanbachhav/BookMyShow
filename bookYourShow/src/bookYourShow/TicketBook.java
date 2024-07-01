package bookYourShow;

import javax.swing.*;
import java.sql.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;

import javax.swing.border.LineBorder;

public class TicketBook extends JFrame {


	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TicketBook frame = new TicketBook();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection = null;
	private JLabel timendate;
	private JTextField txtpopcorn;
	private JTextField txtburger;
	private JTextField txtsandwich;
	private JTextField txtdrinkquantity;
	private JTextField combo3;
	private JTextField txtEnterCurrency;
	private JTextField txtadult;
	private JTextField txtchild;
	private JPanel contentPane;
	private JComboBox btnchoosemovie;
	
	double fastx = 350;
	double avatar2 = 300;
	double Johnwick4 = 250;
	double adultAge = 120;
	double childAge = 60;
	
	double LargePepsi = 150.00;
	double RedBull = 120.00;
	double HellEnergy = 150.00;
	double Monster = 110.00;
	
	double USD = 0.012;
	double KWD = 0.0037;
	double WON = 15.82;
	double YEN = 1.76;
	
	private JTextField txtmoviequantity;
	JLabel postershow;
	
	public void datetime()
	{
		JLabel timendate = new JLabel();
		
		Thread datetime = new Thread() {
			
	        public void run() {
	            try {
	                while(true) {
	                    Calendar cal = new GregorianCalendar();
	                     int day = cal.get(Calendar.DAY_OF_MONTH);
	                     int month = cal.get(Calendar.MONTH);
	                     int year = cal.get(Calendar.YEAR);

	                     int second = cal.get(Calendar.SECOND);
	                     int minute = cal.get(Calendar.MINUTE);
	                     int hour = cal.get(Calendar.HOUR_OF_DAY); // Use HOUR_OF_DAY instead of HOUR

	                    SwingUtilities.invokeLater(() -> {
	                        timendate.setText("Time: " + hour + ":" + minute + ":" +
	                                second + " " + year + "/" + month + "/" + day);
	                    });

	                    Thread.sleep(1000);
	                }
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    };

	    datetime.start();
}
	
	public TicketBook() {		
		connection = Database.dataConnector();
		setForeground(new Color(0, 0, 0));
		setFont(new Font("Dialog", Font.BOLD, 15));
		setTitle("Welcome - BookYourShow");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\javaProjects\\images\\ticket.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(-10, 0, 1500, 900);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(44, 44, 44));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1370, 30);
		menuBar.setBackground(Color.GRAY);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		mnNewMenu.setForeground(Color.BLACK);
		mnNewMenu.setFont(new Font("Rockwell", Font.PLAIN, 20));
		menuBar.add(mnNewMenu);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("RV CINEPLEX");
		lblNewLabel_1.setBounds(480, 41, 521, 68);
		lblNewLabel_1.setForeground(new Color(255, 255, 0));
		lblNewLabel_1.setFont(new Font("Stencil", Font.PLAIN, 80));
		contentPane.add(lblNewLabel_1);
		JPanel panel = new JPanel();
		panel.setBounds(267, 106, 1093, 406);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		contentPane.add(panel);
		panel.setLayout(null);		
		
		JComboBox btnchoosemovie = new JComboBox();
		btnchoosemovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnchoosemovie.getSelectedItem() == "Choose the show")
				{
					Image mainlogo = new ImageIcon(this.getClass().getResource("/ticketlogo.png")).getImage();
					postershow.setIcon(new ImageIcon(mainlogo));
				}
				if(btnchoosemovie.getSelectedItem() == "Deadpool and Wolvarine")
				{
					Image fastx = new ImageIcon(this.getClass().getResource("/deadpool.jpg")).getImage();
					postershow.setIcon(new ImageIcon(fastx));
				}
				if(btnchoosemovie.getSelectedItem() == "Kalki 2898 AD")
				{
					Image avatar = new ImageIcon(this.getClass().getResource("/kalki.jpg")).getImage();
					postershow.setIcon(new ImageIcon(avatar));
				}
				if(btnchoosemovie.getSelectedItem() == "Alyad Palyad")
				{
					Image jw4 = new ImageIcon(this.getClass().getResource("/ap.jpg")).getImage();		
					postershow.setIcon(new ImageIcon(jw4));
				}
			}
		});
		btnchoosemovie.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnchoosemovie.setModel(new DefaultComboBoxModel(new String[] {"Choose the show", "Deadpool and Wolvarine", "Kalki 2898 AD", "Alyad Palyad"}));
		btnchoosemovie.setBounds(190, 70, 214, 30);
		panel.add(btnchoosemovie);
	
		
		/*
		try {
		    String query = "SELECT moviename FROM movieshow";
		    PreparedStatement pstmt = connection.prepareStatement(query);
		    ResultSet result = pstmt.executeQuery();
		    
		    List<String> movieNames = new ArrayList<>();
		    
		    while (result.next()) {
		        String movieName = result.getString("moviename");
		        movieNames.add(movieName);
		    }
		    
		    DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>(movieNames.toArray(new String[0]));
		    
		    JComboBox<String> btnchoosemovie = new JComboBox<>(new DefaultComboBoxModel(new String[] {"Choose the show"}));
		    btnchoosemovie.setBounds(190, 70, 222, 31);
		    btnchoosemovie.setFont(new Font("Tahoma", Font.PLAIN, 20));
		    panel.add(btnchoosemovie);
		}
		catch (Exception error) {
		    JOptionPane.showMessageDialog(null, "Something went wrong in table..." + error);
		}
		 */
		
		JLabel lblNewLabel_2 = new JLabel("Select Movie : ");
		lblNewLabel_2.setBounds(30, 70, 131, 30);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblNewLabel_2);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(171, 141, 344, 2);
		separator_1_1.setForeground(new Color(128, 64, 64));
		panel.add(separator_1_1);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setForeground(new Color(128, 64, 64));
		separator_5.setBounds(550, 13, -3, 338);
		separator_5.setOrientation(SwingConstants.VERTICAL);
		panel.add(separator_5);
		
		JLabel lblNewLabel_3 = new JLabel("Date and ShowTime");
		lblNewLabel_3.setBounds(10, 129, 151, 19);
		lblNewLabel_3.setForeground(new Color(128, 64, 64));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Date of Show");
		lblNewLabel_4.setBounds(30, 156, 110, 30);
		lblNewLabel_4.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		panel.add(lblNewLabel_4);
		
		JComboBox btndatechoose = new JComboBox();
		btndatechoose.setModel(new DefaultComboBoxModel(new String[] {"Choose the date", "Sunday,July 02,2024", "Monday,July 03,2024", "Tuesday,July 04,2024"}));
		btndatechoose.setBounds(190, 154, 288, 31);
		btndatechoose.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(btndatechoose);
		
		JLabel lblNewLabel_4_1 = new JLabel("Time of Show");
		lblNewLabel_4_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		lblNewLabel_4_1.setBounds(30, 208, 110, 30);
		panel.add(lblNewLabel_4_1);
		
		JComboBox btnselecttime = new JComboBox();
		btnselecttime.setModel(new DefaultComboBoxModel(new String[] {"Select time", "07:00AM", "09:00AM", "04:00PM", "09:00PM"}));
		btnselecttime.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnselecttime.setBounds(200, 205, 169, 32);
		panel.add(btnselecttime);
		
		JLabel lblNewLabel_3_1 = new JLabel("Ticket Quantity");
		lblNewLabel_3_1.setForeground(new Color(128, 64, 64));
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3_1.setBounds(10, 269, 151, 19);
		panel.add(lblNewLabel_3_1);
		
		JSeparator separator_1_1_1 = new JSeparator();
		separator_1_1_1.setForeground(new Color(128, 64, 64));
		separator_1_1_1.setBounds(171, 281, 344, 2);
		panel.add(separator_1_1_1);
		
		JLabel lblNewLabel_4_1_1 = new JLabel("Child");
		lblNewLabel_4_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		lblNewLabel_4_1_1.setBounds(203, 308, 70, 30);
		panel.add(lblNewLabel_4_1_1);
		
		JLabel lblNewLabel_4_2 = new JLabel("Adult");
		lblNewLabel_4_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_2.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		lblNewLabel_4_2.setBounds(30, 308, 70, 30);
		panel.add(lblNewLabel_4_2);
		
		
		
		JLabel lblNewLabel_1_1 = new JLabel("Menu");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(580, 105, 65, 24);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Quantity");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1_1.setBounds(790, 105, 87, 24);
		panel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Price ₹");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_2.setBounds(961, 107, 76, 24);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblPopCorn = new JLabel("Pop Corn");
		lblPopCorn.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblPopCorn.setBounds(580, 155, 171, 24);
		panel.add(lblPopCorn);
		
		JLabel lblBurger = new JLabel("Burger");
		lblBurger.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblBurger.setBounds(580, 191, 171, 24);
		panel.add(lblBurger);
		
		JLabel lblSandwitch = new JLabel("Sandwich ");
		lblSandwitch.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblSandwitch.setBounds(580, 226, 171, 24);
		panel.add(lblSandwitch);
		
		txtpopcorn = new JTextField();
		txtpopcorn.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
		txtpopcorn.setColumns(10);
		txtpopcorn.setBounds(790, 155, 112, 24);
		panel.add(txtpopcorn);
		
		txtburger = new JTextField();
		txtburger.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
		txtburger.setColumns(10);
		txtburger.setBounds(790, 191, 112, 24);
		panel.add(txtburger);
		
		txtsandwich = new JTextField();
		txtsandwich.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
		txtsandwich.setColumns(10);
		txtsandwich.setBounds(790, 226, 112, 24);
		panel.add(txtsandwich);
		
		JLabel lblegg = new JLabel("₹ 239.40");
		lblegg.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblegg.setBounds(959, 228, 98, 24);
		panel.add(lblegg);
		
		JLabel lblchiken = new JLabel("₹ 219.45");
		lblchiken.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblchiken.setBounds(959, 193, 98, 24);
		panel.add(lblchiken);
		
		JLabel lbldalrice = new JLabel("₹ 289.00");
		lbldalrice.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lbldalrice.setBounds(959, 157, 98, 24);
		panel.add(lbldalrice);
		
		JComboBox btnchoosedrink = new JComboBox();
		btnchoosedrink.setModel(new DefaultComboBoxModel(new String[] {"Cold Drinks", "Large Pepsi", "RedBull", "Hell Energy", "Monster"}));
		btnchoosedrink.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnchoosedrink.setBounds(580, 307, 186, 31);
		panel.add(btnchoosedrink);
		
		txtdrinkquantity = new JTextField();
		txtdrinkquantity.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
		txtdrinkquantity.setColumns(10);
		txtdrinkquantity.setBounds(790, 307, 112, 30);
		panel.add(txtdrinkquantity);
		
		JSeparator separator_5_2 = new JSeparator();
		separator_5_2.setForeground(new Color(128, 64, 64));
		separator_5_2.setOrientation(SwingConstants.VERTICAL);
		separator_5_2.setBounds(554, 11, 2, 327);
		panel.add(separator_5_2);
		
		JLabel lblCombo = new JLabel("Combo3");
		lblCombo.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblCombo.setBounds(580, 264, 171, 24);
		panel.add(lblCombo);
		
		combo3 = new JTextField();
		combo3.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
		combo3.setColumns(10);
		combo3.setBounds(790, 264, 112, 24);
		panel.add(combo3);
		
		JLabel lblegg_1 = new JLabel("₹ 999.00");
		lblegg_1.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblegg_1.setBounds(959, 266, 87, 24);
		panel.add(lblegg_1);
		
		JLabel lblNewLabel_8 = new JLabel("Grab a ");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_8.setForeground(new Color(0, 0, 0));
		lblNewLabel_8.setBounds(764, 11, 65, 31);
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_8_1 = new JLabel("bite !");
		lblNewLabel_8_1.setForeground(new Color(128, 0, 0));
		lblNewLabel_8_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_8_1.setBounds(833, 11, 65, 31);
		panel.add(lblNewLabel_8_1);
		
		JLabel lblNewLabel_9 = new JLabel("Prebook Your Meal and Save More!");
		lblNewLabel_9.setForeground(new Color(128, 0, 128));
		lblNewLabel_9.setBounds(741, 43, 171, 14);
		panel.add(lblNewLabel_9);
		
		JLabel detail = new JLabel("?");
		detail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String details = "Caramel Popcorn 180g + 1 Pepsi 400ml + Burger \n (1136 Kcal | Allergens: Soybean, Caffeine)";
		        JOptionPane.showMessageDialog(null, details, "Combo 3", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		detail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		detail.setBounds(1057, 269, 14, 19);
		panel.add(detail);
		
		JSeparator separator_1_1_1_1 = new JSeparator();
		separator_1_1_1_1.setForeground(new Color(128, 64, 64));
		separator_1_1_1_1.setBounds(678, 70, 301, 2);
		panel.add(separator_1_1_1_1);
		
		JLabel Contactless = new JLabel("");
		Contactless.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		Image contactlessimg = new ImageIcon(this.getClass().getResource("/contactless.png")).getImage();
		Contactless.setIcon(new ImageIcon(contactlessimg));
		Contactless.setBounds(580, 11, 50, 50);
		panel.add(Contactless);
		
		JLabel Crowd = new JLabel("");
		Crowd.setBounds(1021, 7, 50, 50);
		Crowd.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		Image Crowdimg = new ImageIcon(this.getClass().getResource("/crowd.png")).getImage();
		Crowd.setIcon(new ImageIcon(Crowdimg));
		panel.add(Crowd);
		
		JLabel lblNewLabel_10 = new JLabel("ContactLess");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_10.setBounds(566, 64, 78, 31);
		panel.add(lblNewLabel_10);
		
		JLabel lblNewLabel_10_1 = new JLabel("Crow Free");
		lblNewLabel_10_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_10_1.setBounds(1013, 67, 70, 25);
		panel.add(lblNewLabel_10_1);
		
		txtadult = new JTextField();
		txtadult.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
		txtadult.setBounds(128, 307, 55, 30);
		panel.add(txtadult);
		txtadult.setColumns(10);
		
		txtchild = new JTextField();
		txtchild.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
		txtchild.setColumns(10);
		txtchild.setBounds(308, 307, 55, 30);
		panel.add(txtchild);
		
		JSeparator separator_1_1_2 = new JSeparator();
		separator_1_1_2.setForeground(new Color(128, 64, 64));
		separator_1_1_2.setBounds(203, 40, 306, 2);
		panel.add(separator_1_1_2);
		
		JLabel lblNewLabel_3_2 = new JLabel("Select Show and quntity");
		lblNewLabel_3_2.setForeground(new Color(128, 64, 64));
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3_2.setBounds(10, 27, 195, 19);
		panel.add(lblNewLabel_3_2);
		datetime();
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 515, 1350, 223);
		panel_1.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_11 = new JLabel("Movie Name : ");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_11.setBounds(50, 14, 141, 20);
		panel_1.add(lblNewLabel_11);
		
		JLabel lblNewLabel_11_1 = new JLabel("AdultTickets");
		lblNewLabel_11_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_11_1.setBounds(50, 158, 141, 20);
		panel_1.add(lblNewLabel_11_1);
		
		JLabel lblNewLabel_11_2 = new JLabel("Child Tickets");
		lblNewLabel_11_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_11_2.setBounds(285, 158, 86, 20);
		panel_1.add(lblNewLabel_11_2);
		
		JLabel lblNewLabel_11_3 = new JLabel("Show Time");
		lblNewLabel_11_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_11_3.setBounds(50, 109, 141, 20);
		panel_1.add(lblNewLabel_11_3);
		
		JSeparator separator_5_1_1 = new JSeparator();
		separator_5_1_1.setOrientation(SwingConstants.VERTICAL);
		separator_5_1_1.setForeground(new Color(128, 64, 64));
		separator_5_1_1.setBounds(534, 11, 2, 181);
		panel_1.add(separator_5_1_1);
		
		JLabel lblNewLabel_11_4 = new JLabel("Total cost of meal");
		lblNewLabel_11_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_11_4.setBounds(560, 14, 141, 20);
		panel_1.add(lblNewLabel_11_4);
		
		JLabel txtmealtotal = new JLabel("");
		txtmealtotal.setHorizontalAlignment(SwingConstants.CENTER);
		txtmealtotal.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
		txtmealtotal.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtmealtotal.setBounds(711, 14, 226, 34);
		panel_1.add(txtmealtotal);
		
		JLabel lblNewLabel_11_5 = new JLabel("Total cost of drinks");
		lblNewLabel_11_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_11_5.setBounds(560, 59, 141, 20);
		panel_1.add(lblNewLabel_11_5);
		
		JLabel txtdrinktotal = new JLabel("");
		txtdrinktotal.setHorizontalAlignment(SwingConstants.CENTER);
		txtdrinktotal.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
		txtdrinktotal.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtdrinktotal.setBounds(711, 59, 229, 35);
		panel_1.add(txtdrinktotal);
		
		JLabel lblNewLabel_11_6 = new JLabel("Sub total");
		lblNewLabel_11_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_11_6.setBounds(560, 109, 141, 20);
		panel_1.add(lblNewLabel_11_6);
		
		JLabel txtsubtotal = new JLabel("");
		txtsubtotal.setHorizontalAlignment(SwingConstants.CENTER);
		txtsubtotal.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
		txtsubtotal.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtsubtotal.setBounds(711, 109, 229, 33);
		panel_1.add(txtsubtotal);
		
		JLabel lblNewLabel_11_7 = new JLabel("Total");
		lblNewLabel_11_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_11_7.setBounds(560, 158, 141, 20);
		panel_1.add(lblNewLabel_11_7);
		
		JLabel txttotal = new JLabel("");
		txttotal.setHorizontalAlignment(SwingConstants.CENTER);
		txttotal.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
		txttotal.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txttotal.setBounds(711, 161, 229, 31);
		panel_1.add(txttotal);
		
		JLabel txtmoviename = new JLabel("");
		txtmoviename.setHorizontalAlignment(SwingConstants.CENTER);
		txtmoviename.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
		txtmoviename.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtmoviename.setBounds(211, 10, 313, 34);
		panel_1.add(txtmoviename);
		
		JLabel txtmovieprice = new JLabel("");
		txtmovieprice.setHorizontalAlignment(SwingConstants.CENTER);
		txtmovieprice.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
		txtmovieprice.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtmovieprice.setBounds(211, 56, 313, 34);
		panel_1.add(txtmovieprice);
		
		JLabel txtshowchild = new JLabel("");
		txtshowchild.setHorizontalAlignment(SwingConstants.CENTER);
		txtshowchild.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
		txtshowchild.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtshowchild.setBounds(392, 158, 65, 34);
		panel_1.add(txtshowchild);
		
		JLabel txtdateandtime = new JLabel("");
		txtdateandtime.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 18));
		txtdateandtime.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtdateandtime.setBounds(211, 102, 80, 34);
		panel_1.add(txtdateandtime);
		
		
		JLabel txtshowdate = new JLabel("");
		txtshowdate.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 18));
		txtshowdate.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtshowdate.setBounds(211, 102, 80, 34);
		panel_1.add(txtshowdate);
		
		JSeparator separator_5_1_1_1 = new JSeparator();
		separator_5_1_1_1.setOrientation(SwingConstants.VERTICAL);
		separator_5_1_1_1.setForeground(new Color(128, 64, 64));
		separator_5_1_1_1.setBounds(992, 11, 2, 181);
		panel_1.add(separator_5_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Currency Converter");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1_1_1.setBounds(1070, 0, 224, 24);
		panel_1.add(lblNewLabel_1_1_1_1);
		
		JComboBox txtSelectCountry = new JComboBox();
		txtSelectCountry.setModel(new DefaultComboBoxModel(new String[] {"Country", "U.S.Dollar", "Kuwati dinnar", "Korea", "Japan"}));
		txtSelectCountry.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSelectCountry.setBounds(1032, 35, 296, 31);
		panel_1.add(txtSelectCountry);
		
		txtEnterCurrency = new JTextField();
		txtEnterCurrency.setColumns(10);
		txtEnterCurrency.setBounds(1032, 77, 296, 31);
		panel_1.add(txtEnterCurrency);
		
		JLabel txtCurrencyOutput = new JLabel("");
		txtCurrencyOutput.setHorizontalAlignment(SwingConstants.CENTER);
		txtCurrencyOutput.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtCurrencyOutput.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		txtCurrencyOutput.setBounds(1032, 134, 296, 31);
		panel_1.add(txtCurrencyOutput);
		
		
		
		JLabel txtshowadult = new JLabel("");
		txtshowadult.setHorizontalAlignment(SwingConstants.CENTER);
		txtshowadult.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
		txtshowadult.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtshowadult.setBounds(210, 157, 65, 34);
		panel_1.add(txtshowadult);
		
		JLabel lblNewLabel_11_8 = new JLabel("Movie Price : ");
		lblNewLabel_11_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_11_8.setBounds(50, 63, 141, 20);
		panel_1.add(lblNewLabel_11_8);
		
		JLabel lblNewLabel_11_2_1 = new JLabel("Show Date");
		lblNewLabel_11_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_11_2_1.setBounds(299, 109, 72, 20);
		panel_1.add(lblNewLabel_11_2_1);
		
		JLabel showdate = new JLabel("");
		showdate.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 14));
		showdate.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		showdate.setBounds(375, 102, 149, 34);
		panel_1.add(showdate);
		
		JPanel pnrpanel = new JPanel();
		pnrpanel.setBounds(10, 106, 256, 406);
		contentPane.add(pnrpanel);
		pnrpanel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 236, 384);
		pnrpanel.add(tabbedPane);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Poster", null, panel_2, null);
		panel_2.setLayout(null);
		
		postershow = new JLabel("");
		postershow.setBackground(new Color(255, 255, 255));
		postershow.setBounds(10, 11, 211, 334);
		Image mainlogo = new ImageIcon(this.getClass().getResource("/ticketlogo.png")).getImage();
		postershow.setIcon(new ImageIcon(mainlogo));
		panel_2.add(postershow);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Recipt", null, panel_3, null);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 231, 356);
		panel_3.add(scrollPane);
		
		JTextArea ReciptArea = new JTextArea();
		ReciptArea.setEditable(false);
		scrollPane.setViewportView(ReciptArea);
		
		
		txtmoviequantity = new JTextField();
		txtmoviequantity.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
		txtmoviequantity.setColumns(10);
		txtmoviequantity.setBounds(439, 71, 55, 30);
		panel.add(txtmoviequantity);
		
		JMenu mnNewMenu_1 = new JMenu("New");
		mnNewMenu_1.setFont(new Font("Rockwell", Font.PLAIN, 15));
		mnNewMenu.add(mnNewMenu_1);
		
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Reset");
		mntmNewMenuItem_1.setFont(new Font("Rockwell", Font.PLAIN, 15));
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Total");
		mntmNewMenuItem_3.setFont(new Font("Rockwell", Font.PLAIN, 15));
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_2 = new JMenu("Profile");
		mnNewMenu_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		mnNewMenu_2.setForeground(new Color(0, 0, 0));
		mnNewMenu_2.setFont(new Font("Rockwell", Font.PLAIN, 20));
		menuBar.add(mnNewMenu_2);
		
		JMenu mnNewMenu_3 = new JMenu("Save");
		mnNewMenu_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		mnNewMenu_3.setFont(new Font("Rockwell", Font.PLAIN, 20));
		mnNewMenu_3.setForeground(new Color(0, 0, 0));
		menuBar.add(mnNewMenu_3);
		
		JMenu mnNewMenu_3_1 = new JMenu("Ticket");
		mnNewMenu_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		mnNewMenu_3_1.setForeground(Color.BLACK);
		mnNewMenu_3_1.setFont(new Font("Rockwell", Font.PLAIN, 20));
		menuBar.add(mnNewMenu_3_1);
		
		JLabel detail_1 = new JLabel("?");
		detail_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String details = "Enter quantity for show tickets";
		        JOptionPane.showMessageDialog(null, details, "Combo 3", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		detail_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		detail_1.setBounds(520, 76, 14, 19);
		panel.add(detail_1);
		
		//*********************Submit**************************		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					
				// Select Movie
				double Movie = Double.parseDouble(txtmoviequantity.getText());
				fastx = fastx*Movie;
				avatar2 = avatar2*Movie;
				Johnwick4 = Johnwick4*Movie;
				
				if(btnchoosemovie.getSelectedItem().equals("Deadpool and Wolvarine"));
				{
					String pfastx = String.format("%.2f" , fastx);
					txtmovieprice.setText(pfastx);
					txtmoviename.setText("Deadpool and Wolvarine");
				}
				
				if(btnchoosemovie.getSelectedItem().equals("Kalki 2898 AD"));
				{
					String pavatar = String.format("%.2f" , avatar2);
					txtmovieprice.setText(pavatar);
					txtmoviename.setText("Kalki 2898 AD");
					
				}
				
				if(btnchoosemovie.getSelectedItem().equals("Alyad Palyad"));
				{
					String pjohnwick4 = String.format("%.2f" , Johnwick4);
					txtmovieprice.setText(pjohnwick4);
					txtmoviename.setText("Alyad Palyad");
				}
				
				// Time
				
				if(btnselecttime.getSelectedItem().equals("07:00AM"))
				{
					txtdateandtime.setText("07:00AM");
				}
				if(btnselecttime.getSelectedItem().equals("09:00AM"))
				{
					txtdateandtime.setText("09:00AM");
				}
				if(btnselecttime.getSelectedItem().equals("04:00PM"))
				{
					txtdateandtime.setText("04:00PM");
				}
				if(btnselecttime.getSelectedItem().equals("09:00PM"))
				{
					txtdateandtime.setText("09:00PM");
				}		
				
				// Choose Date
				
				if(btndatechoose.getSelectedItem().equals("Sunday,July 02,2024"))
				{
					showdate.setText("Sunday,July 02,2024");
				}
				if(btndatechoose.getSelectedItem().equals("Monday,July 03,2024"))
				{
					showdate.setText("Monday,July 03,2024");
				}
				if(btndatechoose.getSelectedItem().equals("Tuesday,July 04,2024"))
				{
					showdate.setText("Tuesday,July 04,2024");
				}
				
				// Adult and child.
				
				String AdultAge = (txtadult.getText());
				txtshowadult.setText(AdultAge);
				
				String ChildAge = (txtchild.getText());
				txtshowchild.setText(ChildAge);
				
				//Meal
				
				double popcorns = Double.parseDouble(txtpopcorn.getText());
				double pricepopcorns = 289;
				double getPopcorns;
				
				getPopcorns = (popcorns * pricepopcorns);
				String totalpc = String.format("%.2f", getPopcorns);
				txtmealtotal.setText(totalpc);
				
				
				double Burgers = Double.parseDouble(txtburger.getText());
				double priceburger = 219.45;
				double getburger;
				
				getburger = (Burgers * priceburger);
				String totalb = String.format("%.2f", getburger + getPopcorns);
				txtmealtotal.setText(totalb);
				
				double sandwiches = Double.parseDouble(txtsandwich.getText());
				double pricesandwich = 239.14;
				double getsandwich;
				
				getsandwich = (sandwiches * pricesandwich);
				String totals = String.format("%.2f", getsandwich + getburger + getPopcorns);
				txtmealtotal.setText(totals);
				
				double combo3s = Double.parseDouble(combo3.getText());
				double combo3 = 999.00;
				double getcombo3;
				
				getcombo3 = (combo3s * combo3);
				String totalc = String.format("%.2f", getcombo3 + getsandwich + getburger + getPopcorns);
				txtmealtotal.setText(totalc);
				
				// Drinks
				
				double Drinks = Double.parseDouble(txtdrinkquantity.getText());
				LargePepsi = (LargePepsi * Drinks);
				RedBull = (RedBull * Drinks);
				HellEnergy = (HellEnergy * Drinks);
				Monster = (Monster * Drinks);
				
				
				if(btnchoosedrink.getSelectedItem().equals("Large Pepsi"))
				{
					String totallp = String.format("%.2f", LargePepsi);
					txtdrinktotal.setText(totallp);
				}
				if(btnchoosedrink.getSelectedItem().equals("RedBull"))
				{
					String totalrb = String.format("%.2f", RedBull);
					txtdrinktotal.setText(totalrb);
				}
				if(btnchoosedrink.getSelectedItem().equals("Hell Energy"))
				{
					String totalhe = String.format("%.2f", HellEnergy);
					txtdrinktotal.setText(totalhe);
				}
				if(btnchoosedrink.getSelectedItem().equals("Monster"))
				{
					String totalm = String.format("%.2f", Monster);
					txtdrinktotal.setText(totalm);
				}
				
				// Sub-Total
				
				double adults = Double.parseDouble(txtshowadult.getText());
				double pAdult = (adults * adultAge);
				
				double childs = Double.parseDouble(txtshowchild.getText());
				double pchild = (childs * childAge);
				
				double FirstTotal = Double.parseDouble(txtmovieprice.getText());
				double SecondTotal = Double.parseDouble(txtmealtotal.getText());
				double ThirdTotal = Double.parseDouble(txtdrinktotal.getText());
				double FourthTotal = pAdult;
				double FifthTotal = pchild;
				
				double Tax = 98.00;
				
				double subtotal = (FirstTotal + SecondTotal + ThirdTotal + FourthTotal + FifthTotal);
				String psubtotal = String.format("%.2f", subtotal);
				txtsubtotal.setText(psubtotal);
				
				// Total
				
				double total = (FirstTotal + SecondTotal + ThirdTotal + FourthTotal + FifthTotal + Tax);
				String ptotal = String.format("%.2f", total);
				txttotal.setText(ptotal);
				
				
			}
			catch(Exception error)
			{
				JOptionPane.showMessageDialog(null, "Please fill the all boxes. If You cannot buy in that things so enter amount 0");
			}
			}
		});
		btnSubmit.setForeground(new Color(0, 0, 0));
		btnSubmit.setFont(new Font("Arial", Font.PLAIN, 20));
		btnSubmit.setFocusable(false);
		btnSubmit.setBackground(new Color(0, 128, 0));
		btnSubmit.setBounds(190, 362, 110, 33);
		panel.add(btnSubmit);
		
		
		//*********************Refresh**************************
		JButton btnrefresh = new JButton("Refresh");
		btnrefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtmoviequantity.setText(null);
				txtmoviename.setText(null);
				txtmovieprice.setText(null);
				txtdateandtime.setText(null);
				txtdateandtime.setText(null);
				txtadult.setText(null);
				txtchild.setText(null);
				txtshowadult.setText(null);
				txtshowchild.setText(null);
				postershow.setIcon(null);
				txtmealtotal.setText(null);
				txtpopcorn.setText(null);
				txtburger.setText(null);
				txtsandwich.setText(null);
				combo3.setText(null);
				txtdrinktotal.setText(null);
				txtdrinkquantity.setText(null);
				showdate.setText(null);
				txtshowdate.setText(null);
				txttotal.setText(null);
				txtsubtotal.setText(null);
				btndatechoose.setSelectedItem("Choose the date");
				btnchoosemovie.setSelectedItem("Choose the show");
				btnselecttime.setSelectedItem("Select time");
				btnchoosedrink.setSelectedItem("Cold Drinks");
				
			}
		});
		btnrefresh.setForeground(new Color(0, 0, 0));
		btnrefresh.setFont(new Font("Arial", Font.PLAIN, 20));
		btnrefresh.setFocusable(false);
		btnrefresh.setBackground(new Color(255, 255, 0));
		btnrefresh.setBounds(439, 362, 110, 33);
		panel.add(btnrefresh);
		
		
		//*********************Clear**************************
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtmoviequantity.setText(null);
				txtmoviename.setText(null);
				txtmovieprice.setText(null);
				txtdateandtime.setText(null);
				btndatechoose.setSelectedItem("Choose the date");
				btnchoosemovie.setSelectedItem("Choose the show");
				btnselecttime.setSelectedItem("Select time");
				txtdateandtime.setText(null);
				txtadult.setText(null);
				txtchild.setText(null);
				txtshowadult.setText(null);
				txtshowchild.setText(null);
				Image mainlogo = new ImageIcon(this.getClass().getResource("/ticketlogo.png")).getImage();
				postershow.setIcon(new ImageIcon(mainlogo));
				txtmealtotal.setText(null);
				txtpopcorn.setText(null);
				txtburger.setText(null);
				txtsandwich.setText(null);
				combo3.setText(null);
				txtdrinktotal.setText(null);
				txtdrinkquantity.setText(null);
				btnchoosedrink.setSelectedItem("Cold Drinks");
				showdate.setText(null);
				txtshowdate.setText(null);
				txttotal.setText(null);
				txtsubtotal.setText(null);
				ReciptArea.setText(null);
				}
		});
		btnClear.setForeground(new Color(0, 0, 0));
		btnClear.setFont(new Font("Arial", Font.PLAIN, 20));
		btnClear.setFocusable(false);
		btnClear.setBackground(Color.RED);
		btnClear.setBounds(683, 362, 110, 33);
		panel.add(btnClear);
		
		
		JButton btnConvert = new JButton("Convert");
		btnConvert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Double INR_RS= Double.parseDouble(txtEnterCurrency.getText());
				
				if(txtSelectCountry.getSelectedItem().equals("U.S.Dollar"))
				{
					String Usd = String.format("$ %.2f", USD*INR_RS);
					txtCurrencyOutput.setText(Usd);
				}
				if(txtSelectCountry.getSelectedItem().equals("Kuwati Dinnar"))
				{
					String Kwd = String.format("KWD %.2f", KWD*INR_RS);
					txtCurrencyOutput.setText(Kwd);
				}
				if(txtSelectCountry.getSelectedItem().equals("Korea"))
				{
					String Won = String.format("₩ %.2f", WON*INR_RS);
					txtCurrencyOutput.setText(Won);
				}
				if(txtSelectCountry.getSelectedItem().equals("Korea"))
				{
					String Yen = String.format("¥ %.2f", YEN*INR_RS);
					txtCurrencyOutput.setText(Yen);
				}
				if(txtSelectCountry.getSelectedItem().equals("Country"))
				{
					txtCurrencyOutput.setText("0.00");
				}
			}
		});
		btnConvert.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnConvert.setFocusable(false);
		btnConvert.setBounds(1042, 176, 112, 41);
		panel_1.add(btnConvert);
		
		JButton btncurrencyclear = new JButton("Clear");
		btncurrencyclear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCurrencyOutput.setText(null);
				txtEnterCurrency.setText(null);
				txtSelectCountry.setSelectedItem("Country");
				}
		});
		btncurrencyclear.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btncurrencyclear.setFocusable(false);
		btncurrencyclear.setBounds(1204, 176, 112, 41);
		panel_1.add(btncurrencyclear);
		
		JMenu mnNewMenu_2_1 = new JMenu("Recipt");
		mnNewMenu_2_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String moviename  = (String)(txtmoviename.getText());
				double movieprice = Double.parseDouble(txtmovieprice.getText());
				String timing= (String)(txtdateandtime.getText());
				String date = (String)(showdate.getText());
				double child = Double.parseDouble(txtshowchild.getText());
				double adult = Double.parseDouble(txtshowadult.getText());
				double Invoice1 = Double.parseDouble(txtpopcorn.getText());
				double Invoice2 = Double.parseDouble(txtburger.getText());
				double Invoice3 = Double.parseDouble(txtsandwich.getText());
				double Invoice4= Double.parseDouble(combo3.getText());
				Double Invoice5 = Double.parseDouble(txtdrinkquantity.getText());
				Double TotalOfAll = Double.parseDouble(txttotal.getText());
				
				ReciptArea.append(""
						+ "       Welcome To RV Cineplex \n"
						+"\n------------------------------------------------------------\n"
						+ "  Movie Name : \t" + moviename
						+"\n"
						+ "  Movie Price : \t" + movieprice
						+"\n"
						+ "  Time : \t" + timing
						+"\n"
						+ "  Child`s : \t" + child
						+"\n"
						+ "  Adult`s : \t" + adult
						+"\n"
						+ "  PopCorn`s : \t" + Invoice1
						+"\n"
						+ "  Burger`s : \t" + Invoice2
						+"\n"
						+ "  Sandwiche`s \t" + Invoice3
						+"\n"
						+ "  Combo-3 \t" + Invoice4
						+"\n"
						+ "  Date : \t" + date
						+"\n"
						+ btnchoosedrink.getSelectedItem() + "\t" + Invoice5
						+ "\n\n------------------------------------------------------------\n"
						+ "  Total : \t" + TotalOfAll
						+"\n\n\n");
			}
		});
		
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Recipt");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String moviename  = (String)(txtmoviename.getText());
				double movieprice = Double.parseDouble(txtmovieprice.getText());
				String timing= (String)(txtdateandtime.getText());
				String date = (String)(showdate.getText());
				double child = Double.parseDouble(txtshowchild.getText());
				double adult = Double.parseDouble(txtshowadult.getText());
				double Invoice1 = Double.parseDouble(txtpopcorn.getText());
				double Invoice2 = Double.parseDouble(txtburger.getText());
				double Invoice3 = Double.parseDouble(txtsandwich.getText());
				double Invoice4= Double.parseDouble(combo3.getText());
				Double Invoice5 = Double.parseDouble(txtdrinkquantity.getText());
				Double TotalOfAll = Double.parseDouble(txttotal.getText());
				
				ReciptArea.append(""
						+ "Welcome To RV Cineplex \n"
						+"\n------------------------------------------------------------\n"
						+ "Movie Name : \t" + moviename
						+"\n"
						+ "Movie Price : \t" + movieprice
						+"\n"
						+ "Time : \t" + timing
						+"\n"
						+ "Date :    " + date
						+"\n"
						+ "Child`s : \t" + child
						+"\n"
						+ "Adult`s : \t" + adult
						+"\n"
						+ "PopCorn`s : \t" + Invoice1
						+"\n"
						+ "Burger`s : \t" + Invoice2
						+"\n"
						+ "Sandwiche`s \t" + Invoice3
						+"\n"
						+ "Combo-3 \t" + Invoice4
						+"\n"
						+ btnchoosedrink.getSelectedItem() + "\t" + Invoice5
						+ "\n\n------------------------------------------------------------\n"
						+ "Total : \t" + TotalOfAll);
				
			}
		});
		mntmNewMenuItem.setFont(new Font("Rockwell", Font.PLAIN, 15));
		mnNewMenu_1.add(mntmNewMenuItem);
		mnNewMenu_2_1.setForeground(Color.BLACK);
		mnNewMenu_2_1.setFont(new Font("Rockwell", Font.PLAIN, 20));
		menuBar.add(mnNewMenu_2_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Save Ticket");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//Sql
				
				try {
				    String query = "INSERT INTO tickets(MovieName, MoviePrice, MovieTime, MovieDate, Child, Adult, Invoices1, Invoices2, Invoices3, Invoices4, Invoices5, Totals) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				    PreparedStatement ps = connection.prepareStatement(query);

				    ps.setString(1, txtmoviename.getText());
				    ps.setBigDecimal(2, new BigDecimal(txtmovieprice.getText()));
				    ps.setString(3, txtdateandtime.getText());
				    ps.setString(4, showdate.getText());
				    ps.setInt(5, Integer.parseInt(txtshowchild.getText()));
				    ps.setInt(6, Integer.parseInt(txtshowadult.getText()));
				    ps.setBigDecimal(7, new BigDecimal(txtpopcorn.getText()));
				    ps.setBigDecimal(8, new BigDecimal(txtburger.getText()));
				    ps.setBigDecimal(9, new BigDecimal(txtsandwich.getText()));
				    ps.setBigDecimal(10, new BigDecimal(combo3.getText()));
				    ps.setBigDecimal(11, new BigDecimal(txtdrinkquantity.getText()));
				    ps.setBigDecimal(12, new BigDecimal(txttotal.getText()));

				    ps.executeUpdate();
				    ps.close();
				    JOptionPane.showMessageDialog(null, "Data inserted successfully!");
				} catch (Exception error1) {
				    error1.printStackTrace(); // Print full stack trace for debugging
				    JOptionPane.showMessageDialog(null, "Oops, something went wrong: " + error1.getMessage());
				}
			}
		});
		mntmNewMenuItem_2.setFont(new Font("Rockwell", Font.PLAIN, 15));
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Exit");
		mntmNewMenuItem_4.setForeground(new Color(255, 0, 0));
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mntmNewMenuItem_4.setFont(new Font("Rockwell", Font.PLAIN, 15));
		mnNewMenu.add(mntmNewMenuItem_4);
		
		JMenu mnNewMenu_3_2 = new JMenu("Help");
		mnNewMenu_3_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		mnNewMenu_3_2.setForeground(Color.BLACK);
		mnNewMenu_3_2.setFont(new Font("Rockwell", Font.PLAIN, 20));
		menuBar.add(mnNewMenu_3_2);
		
		JMenuItem mntmNewMenuItem_3_1 = new JMenuItem("Welcome");
		mntmNewMenuItem_3_1.setFont(new Font("Rockwell", Font.PLAIN, 15));
		mnNewMenu_3_2.add(mntmNewMenuItem_3_1);
		
		JMenuItem mntmNewMenuItem_3_2 = new JMenuItem("Help Content");
		mntmNewMenuItem_3_2.setFont(new Font("Rockwell", Font.PLAIN, 15));
		mnNewMenu_3_2.add(mntmNewMenuItem_3_2);
		
		JSeparator separator = new JSeparator();
		mnNewMenu_3_2.add(separator);
		
		JMenuItem mntmNewMenuItem_3_3 = new JMenuItem("About us");
		mntmNewMenuItem_3_3.setFont(new Font("Rockwell", Font.PLAIN, 15));
		mnNewMenu_3_2.add(mntmNewMenuItem_3_3);
	}
	
}
