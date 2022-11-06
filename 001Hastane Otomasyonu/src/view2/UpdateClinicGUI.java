package view2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import helper.Helper;
import model.Clinic;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UpdateClinicGUI extends JFrame {

	private JPanel contentPane;
	private JTextField fld_update;
	private static Clinic clinic;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateClinicGUI frame = new UpdateClinicGUI(clinic);
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
	public UpdateClinicGUI(Clinic clinic) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 225, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		fld_update = new JTextField();
		fld_update.setColumns(10);
		fld_update.setBounds(13, 33, 186, 25);
		fld_update.setText(clinic.getCname());
		contentPane.add(fld_update);
		
		JButton btn_updateclinic = new JButton("Duzenle");
		btn_updateclinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Helper.confirm("sure")) { 
					
				try {
					clinic.updateClinic(clinic.getCid(), fld_update.getText());
					Helper.SHOWmSG("success");
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				}
			}
		});
		btn_updateclinic.setFont(new Font("Tahoma", Font.ITALIC, 18));
		btn_updateclinic.setBounds(10, 69, 189, 31);
		contentPane.add(btn_updateclinic);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Poliklinik Adi");
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_1_2.setBounds(65, 11, 114, 25);
		contentPane.add(lblNewLabel_1_1_1_2);
	}

}
