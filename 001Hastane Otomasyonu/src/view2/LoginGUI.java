package view2;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import helper.DBConnection;
import helper.Helper;
import model.Bashekim;
import model.Doktor;
import model.Hasta;


public class LoginGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_hastatc;
	private JPasswordField fld_hastasifre;
	private JTextField doctortc;
	private JPasswordField doktorpass;
	private DBConnection conn = new DBConnection();	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		setResizable(false);
		setTitle("HASTANE OTOMASYONU");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource("hastane.png")));
		lbl_logo.setBounds(218, 11, 55, 39);
		w_pane.add(lbl_logo);
		
		JLabel lblNewLabel = new JLabel("HASTANE ISLETIM SISTEMI");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(132, 72, 234, 23);
		w_pane.add(lblNewLabel);
		
		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(24, 130, 434, 220);
		w_pane.add(w_tabpane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		w_tabpane.addTab("Hasta Giris", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblTcNumarasi = new JLabel("TC Numarasi");
		lblTcNumarasi.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTcNumarasi.setBounds(25, 30, 117, 23);
		panel.add(lblTcNumarasi);
		
		JLabel lblSifre = new JLabel("Sifre");
		lblSifre.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSifre.setBounds(24, 80, 117, 23);
		panel.add(lblSifre);
		
		fld_hastatc = new JTextField();
		fld_hastatc.setBounds(151, 30, 198, 23);
		panel.add(fld_hastatc);
		fld_hastatc.setColumns(10);
		
		fld_hastasifre = new JPasswordField();
		fld_hastasifre.setBounds(151, 78, 198, 25);
		panel.add(fld_hastasifre);
		
		JButton btn_Kayitol = new JButton("KAYIT OL");
		btn_Kayitol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI rgui = new RegisterGUI();
				rgui.setVisible(true);
				dispose();
			}
		});
		btn_Kayitol.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_Kayitol.setBounds(53, 136, 129, 45);
		panel.add(btn_Kayitol);
		
		JButton btnGirisYap = new JButton("GIRIS YAP");
		btnGirisYap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_hastatc.getText().length() ==0 || fld_hastasifre.getText().length() ==0) {
					Helper.SHOWmSG("fill");
					
				}else {
					boolean key = true;
						try {
							Connection con = conn.connDb();
							Statement st = con.createStatement();
							ResultSet rs = st.executeQuery("SELECT * FROM hasta");
							while (rs.next()) {					
								if(fld_hastatc.getText().equals(rs.getString("tcno")) && fld_hastasifre.getText().equals(rs.getString("password"))) {
									if(rs.getString("type").equals("hasta")) {
										Hasta hasta = new Hasta();
										hasta.setId(rs.getInt("id"));
										hasta.setTcno(rs.getString("tcno"));
										hasta.setPassword("password");						
										hasta.setName(rs.getString("name"));
										hasta.setType(rs.getString("type"));
										//System.out.println(bhekim.getName());
										HastaGUI hGUI = new HastaGUI(hasta);
										hGUI.setVisible(true);
										dispose();
										key = false;
									}
									}
								}
						}catch (Exception e2) {	
							e2.printStackTrace();
						}
						
						if(key ) {
							Helper.SHOWmSG("Hasta bulunamadi Lutfen kayit olunuz..");
				}
			}
				}
		});
		btnGirisYap.setRolloverEnabled(false);
		btnGirisYap.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnGirisYap.setBounds(246, 136, 129, 45);
		panel.add(btnGirisYap);
		
		JPanel panel_1 = new JPanel();
		w_tabpane.addTab("Doktor Giris", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblTcNumarasi_1 = new JLabel("TC Numarasi");
		lblTcNumarasi_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTcNumarasi_1.setBounds(47, 30, 117, 23);
		panel_1.add(lblTcNumarasi_1);
		
		doctortc = new JTextField();
		doctortc.setFont(new Font("Tahoma", Font.BOLD, 17));
		doctortc.setColumns(10);
		doctortc.setBounds(171, 30, 198, 23);
		panel_1.add(doctortc);
		
		JLabel lblSifre_1 = new JLabel("Sifre");
		lblSifre_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSifre_1.setBounds(44, 80, 117, 23);
		panel_1.add(lblSifre_1);
		
		doktorpass = new JPasswordField();
		doktorpass.setFont(new Font("Tahoma", Font.BOLD, 17));
		doktorpass.setBounds(171, 78, 198, 25);
		panel_1.add(doktorpass);
		
		JButton btn_doktorg = new JButton("GIRIS");
		btn_doktorg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(doctortc.getText().length() == 0 || doktorpass.getText().length() == 0) {
					Helper.SHOWmSG("fill");
				}else {
					
					try {
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM public.\"user\"");
						while (rs.next()) {
							
								if(doctortc.getText().equals(rs.getString("tcno")) && doktorpass.getText().equals(rs.getString("password"))) {
									if(rs.getString("type").equals("Bashekim")) {
									Bashekim bhekim = new Bashekim();
									bhekim.setId(rs.getInt("id"));
									bhekim.setTcno(rs.getString("tcno"));
									bhekim.setPassword("password");						
									bhekim.setName(rs.getString("name"));
									bhekim.setType(rs.getString("type"));
									System.out.println(bhekim.getName());
									BashekimGUI bGUI = new BashekimGUI(bhekim);
									bGUI.setVisible(true);
									dispose();

								}
							if(rs.getString("type").equals("Doktor")){
								Doktor doktor = new Doktor();
								doktor.setId(rs.getInt("id"));
								doktor.setTcno(rs.getString("tcno"));
								doktor.setPassword("password");						
								doktor.setName(rs.getString("name"));
								doktor.setType(rs.getString("type"));
								DoktorGUI dgui = new DoktorGUI(doktor);
								dgui.setVisible(true);
								dispose();
								
							}
								}	
							}
						 
									
					} catch (SQLException e1) {
					
						e1.printStackTrace();
					}
				} 

			}
		});
		btn_doktorg.setActionCommand("");
		btn_doktorg.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_doktorg.setBounds(201, 125, 129, 45);
		panel_1.add(btn_doktorg);
	}
}
