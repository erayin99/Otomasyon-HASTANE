package view2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import helper.Helper;
import model.Hasta;
import model.User;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class RegisterGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_adsoyad;
	private JTextField fld_tcno;
	private JPasswordField fld_pass;
	private Hasta hasta = new Hasta();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setResizable(false);
		setTitle("HASTANE YONETIM SISTEMI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 330);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbl_adsoyad = new JLabel("Adi Soyadi");
		lbl_adsoyad.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_adsoyad.setBounds(10, 11, 88, 25);
		w_pane.add(lbl_adsoyad);
		
		fld_adsoyad = new JTextField();
		fld_adsoyad.setColumns(10);
		fld_adsoyad.setBounds(10, 37, 252, 25);
		w_pane.add(fld_adsoyad);
		
		JLabel lbl_tcno = new JLabel("TC Numarasi");
		lbl_tcno.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_tcno.setBounds(10, 73, 88, 25);
		w_pane.add(lbl_tcno);
		
		fld_tcno = new JTextField();
		fld_tcno.setColumns(10);
		fld_tcno.setBounds(10, 99, 252, 25);
		w_pane.add(fld_tcno);
		
		JLabel lbl_sifre = new JLabel("Sifre");
		lbl_sifre.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_sifre.setBounds(10, 135, 88, 25);
		w_pane.add(lbl_sifre);
		
		fld_pass = new JPasswordField();
		fld_pass.setBounds(10, 158, 252, 25);
		w_pane.add(fld_pass);
		
		JButton btn_addreister = new JButton("KAYDET");
		btn_addreister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_tcno.getText().length() ==0 || fld_pass.getText().length() ==0 
						|| fld_adsoyad.getText().length() ==0) {
					Helper.SHOWmSG("fill");
				}else {
					try {
						boolean control = hasta.addregister(
								fld_tcno.getText(), fld_pass.getText(), fld_adsoyad.getText());
						if(control) {
							Helper.SHOWmSG("success");
							LoginGUI login = new LoginGUI();
							login.setVisible(true);
							dispose();
							
						}else {
							Helper.SHOWmSG("error");
							
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_addreister.setFont(new Font("Tahoma", Font.ITALIC, 18));
		btn_addreister.setBounds(10, 195, 252, 31);
		w_pane.add(btn_addreister);
		
		JButton btn_backreg = new JButton("GERI DON");
		btn_backreg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btn_backreg.setFont(new Font("Tahoma", Font.ITALIC, 18));
		btn_backreg.setBounds(10, 238, 252, 31);
		w_pane.add(btn_backreg);
	}
}
