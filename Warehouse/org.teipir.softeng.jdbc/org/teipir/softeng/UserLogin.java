package org.teipir.softeng;

import java.awt.BorderLayout;
import java.sql.Statement;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7072032697389841939L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField username;
	private JTextField password;
	Connection conn = null;
	Statement stmt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserLogin frame = new UserLogin(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param sqlstatement 
	 */
	public UserLogin(java.sql.Statement sqlstatement) {
		setTitle("Account Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setVisible(false);
		tabbedPane.addTab("Sign In", null, panel, null);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
	
		try{Class.forName("com.mysql.jdbc.Driver");
        String connectionUrl="jdbc:mysql://localhost:3306/warehouse";
    	String username="root";
    	String password="12345";
    	Connection conn=DriverManager.getConnection(connectionUrl,username,password);
    	stmt = conn.createStatement();
		}
    	catch(Exception e){
			e.printStackTrace();
		}
		
		JLabel lblId = new JLabel("id");
		panel.add(lblId, "4, 2, 3, 1");
		
		JLabel lblUsername = new JLabel("username");
		panel.add(lblUsername, "8, 8");
		
		username = new JTextField();
		panel.add(username, "14, 8, fill, default");
		username.setColumns(10);
		
		JLabel lblPassword = new JLabel("password");
		panel.add(lblPassword, "8, 12");
		
		password = new JTextField();
		password.setFont(UIManager.getFont("PasswordField.font"));
		panel.add(password, "14, 12, fill, default");
		password.setColumns(10);
		
		JButton btnLgin = new JButton("Login");
		btnLgin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String user = username.getText();
				String strpass = password.getText();
	            try {
	                Class.forName("com.mysql.jdbc.Driver");
	             } catch (ClassNotFoundException ex) {
	                 Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, null, ex);
	             }
	             try {
	                 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse", "root", "12345");
	                 Statement st = con.createStatement();
	                 String query = "SELECT password FROM login where username='"+user+"'";
	                 System.out.println(query);
	                 ResultSet rs = st.executeQuery(query);
	  
	                 if(rs.next())
	                 {
	                     String dbpass = rs.getString(1);
	                     if(dbpass.equals(strpass)){
	                     JOptionPane.showMessageDialog(null,"Login Successful! ","Success",JOptionPane.PLAIN_MESSAGE);
	                 }
	                 else
	                 {
	                     JOptionPane.showMessageDialog(null,"Login Unsuccessful!","Error",1);
	                 }
	                }
	                 else
	                 {
	                     JOptionPane.showMessageDialog(null,"Username not found","Error",1);
	                 }
	             } catch (SQLException ex) {
	                 Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, null, ex);
	             }



			}
		});
		panel.add(btnLgin, "14, 18");
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Create account", null, panel_1, null);
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		
		
		
		JLabel lblName = new JLabel("name");
		panel_1.add(lblName, "4, 2");
		
		textField = new JTextField();
		panel_1.add(textField, "8, 2, fill, default");
		textField.setColumns(10);
		
		final JTextArea name = new JTextArea(); 
	
		
		JLabel lblSname = new JLabel("sName");
		panel_1.add(lblSname, "4, 4");
		
		textField_1 = new JTextField();
		panel_1.add(textField_1, "8, 4, fill, default");
		textField_1.setColumns(10);
		
		final JTextArea sName = new JTextArea(); 
	
		
		JLabel lblEmail = new JLabel("e-mail");
		panel_1.add(lblEmail, "4, 6");
		
		textField_2 = new JTextField();
		panel_1.add(textField_2, "8, 6, fill, default");
		textField_2.setColumns(10);
		
		final JTextArea email = new JTextArea(); 

		JLabel lblTelnumber = new JLabel("telnumber");
		panel_1.add(lblTelnumber, "4, 8");
		
		textField_3 = new JTextField();
		panel_1.add(textField_3, "8, 8, fill, default");
		textField_3.setColumns(10);
		
		final JTextArea telnumber = new JTextArea(); 

		
		JLabel lblCity = new JLabel("city");
		panel_1.add(lblCity, "4, 10");
		
		textField_4 = new JTextField();
		panel_1.add(textField_4, "8, 10, fill, default");
		textField_4.setColumns(10);
		
		final JTextArea city = new JTextArea(); 

		
		JLabel lblAdress = new JLabel("adress");
		panel_1.add(lblAdress, "4, 12");
		
		textField_5 = new JTextField();
		panel_1.add(textField_5, "8, 12, fill, default");
		textField_5.setColumns(10);
		
		final JTextArea adress = new JTextArea(); 

		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{ResultSet rs=stmt.executeQuery("insert into users values('"+name+"','"+sName+"','"+email+"','"+telnumber+"','"+city+"','"+adress+"')");
				}
				catch(Exception e){
					e.printStackTrace();
				}
				finally{
					
					
					try{
						if(stmt !=null)
							stmt.close();
					}
					catch (SQLException e){
						e.printStackTrace();
					}
					
				}
			}
		});
		panel_1.add(btnSubmit, "8, 16");
	}

}

