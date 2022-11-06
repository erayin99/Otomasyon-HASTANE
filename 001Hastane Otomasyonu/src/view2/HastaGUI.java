package view2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import helper.Helper;
import helper.Item;
import model.Appointment;
import model.Clinic;
import model.Hasta;
import model.Whour;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class HastaGUI extends JFrame {

	private JPanel w_pane;
	private static Hasta hasta = new Hasta();
	private Clinic clinic = new Clinic();
	private JTable table_doktor;
	private DefaultTableModel doktorModel;
	private Object[] doktordata = null;
	private JTable table_whour;
	private Whour whour = new Whour();
	private DefaultTableModel whourModel;
	private Object[] whourdata = null;
	private int selectdoktorID = 0;
	private String selectdoktorname = null;
	private JTable table_appoint;
	private DefaultTableModel appointrModel;
	private Object[] appointdata = null;
	private Appointment appoint = new Appointment();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HastaGUI frame = new HastaGUI(hasta);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public HastaGUI(Hasta hasta) throws SQLException {
		
		//doktor model
				doktorModel = new DefaultTableModel();
				Object[] colDoctor = new Object[2];
				colDoctor[0] = "ID";
				colDoctor[1] = "TC NO";;
				doktorModel.setColumnIdentifiers(colDoctor);
				doktordata = new Object[2];
		// whour data
				whourModel = new DefaultTableModel();
				Object[] colwhour = new Object[2];
				colwhour[0] = "ID";
				colwhour[1] = "TARIH";;
				whourModel.setColumnIdentifiers(colwhour);
				whourdata = new Object[2];
				
				
		// Appointment model
				
				appointrModel = new DefaultTableModel();
				Object[] colappoint = new Object[3];
				colappoint[0] = "ID";
				colappoint[1] = "DOKTOR";;
				colappoint[2] = "TARIH";
				appointrModel.setColumnIdentifiers(colappoint);
				appointdata = new Object[3];
				for(int i =0; i < appoint.getHastaList(hasta.getId()).size(); i++) {
					appointdata[0] = appoint.getHastaList(hasta.getId()).get(i).getId();
					appointdata[1] = appoint.getHastaList(hasta.getId()).get(i).getDoktorname();
					appointdata[2] = appoint.getHastaList(hasta.getId()).get(i).getAppdate();
					appointrModel.addRow(appointdata);
				}
				
				
		setResizable(false);
		setTitle("HASTANE YONETIM SISTEMI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hosgeldiniz Sayin : " + hasta.getName() );
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 34, 227, 29);
		w_pane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("CIKIS");
		btnNewButton.setFont(new Font("Tahoma", Font.ITALIC, 18));
		btnNewButton.setBounds(582, 23, 114, 40);
		w_pane.add(btnNewButton);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(10, 88, 714, 362);
		w_pane.add(w_tab);
		
		JPanel w_scrolldoktor = new JPanel();
		w_scrolldoktor.setBackground(Color.WHITE);
		w_scrolldoktor.setToolTipText("");
		w_tab.addTab("Rnadevu Sistemi", null, w_scrolldoktor, null);
		w_scrolldoktor.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 26, 240, 297);
		w_scrolldoktor.add(scrollPane);
		
		table_doktor = new JTable(doktorModel);
		scrollPane.setViewportView(table_doktor);
		
		JLabel lbl_liste = new JLabel("Doktor Listesi");
		lbl_liste.setBounds(10, 11, 75, 14);
		w_scrolldoktor.add(lbl_liste);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Poliklinik Ismi");
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_2.setBounds(282, 26, 114, 25);
		w_scrolldoktor.add(lblNewLabel_1_1_1_2);
		
		JComboBox select_clinic = new JComboBox();
		select_clinic.setBounds(270, 51, 123, 31);
		select_clinic.addItem("--Policlinic Sec");
		for(int i =0; i < clinic.getList().size();i++) {
			select_clinic.addItem(new Item(clinic.getList().get(i).getCid(), clinic.getList().get(i).getCname()));
		}
		select_clinic.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			
				if(select_clinic.getSelectedIndex() !=0) {
					JComboBox c = (JComboBox) e.getSource();
					Item item = (Item) c.getSelectedItem();
					//System.out.println(item.getKey() + " - " + item.getValue());
					DefaultTableModel clearModel= (DefaultTableModel) table_doktor.getModel();
					clearModel.setRowCount(0);
					try {
						for(int i =0; i < clinic.getClinicDoktorList(item.getKey()).size();i++) {
							doktordata[0] = clinic.getClinicDoktorList(item.getKey()).get(i).getId();
							doktordata[1] = clinic.getClinicDoktorList(item.getKey()).get(i).getName();
							doktorModel.addRow(doktordata);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}else {
					DefaultTableModel clearModel= (DefaultTableModel) table_doktor.getModel();
					clearModel.setRowCount(0);
				}
			
				
			}
		
		});
		w_scrolldoktor.add(select_clinic);
		
		JLabel lbl_doktorsec = new JLabel("Doktor Sec");
		lbl_doktorsec.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_doktorsec.setBounds(282, 93, 114, 25);
		w_scrolldoktor.add(lbl_doktorsec);
		
		JButton btn_seldoktor = new JButton("Sec");
		btn_seldoktor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_doktor.getSelectedRow();
				if(row >= 0) {
					String value = table_doktor.getModel().getValueAt(row, 0).toString();
					int id = Integer.parseInt(value);
					DefaultTableModel clearModel= (DefaultTableModel) table_whour.getModel();
					clearModel.setRowCount(0);
					
					try {
						for(int i =0; i < whour.getWhourList(id).size(); i++) {
							whourdata[0] = whour.getWhourList(id).get(i).getWid();
							whourdata[1] = whour.getWhourList(id).get(i).getWdate();
							whourModel.addRow(whourdata);
							System.out.println(selectdoktorID + " - " + selectdoktorname);
							
							
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					table_whour.setModel(whourModel);
					selectdoktorID = id;
					selectdoktorname = table_doktor.getModel().getValueAt(row, 1).toString();
					System.out.println(selectdoktorID + " - " + selectdoktorname);
					
					
				}else {
					Helper.SHOWmSG("Lutfen bir Doktor Seciniz..");
				}
			}
		});
		btn_seldoktor.setFont(new Font("Tahoma", Font.ITALIC, 18));
		btn_seldoktor.setBounds(260, 114, 131, 31);
		w_scrolldoktor.add(btn_seldoktor);
		
		JScrollPane scrollwhour = new JScrollPane();
		scrollwhour.setBounds(459, 26, 240, 297);
		w_scrolldoktor.add(scrollwhour);
		
		table_whour = new JTable(whourModel);
		scrollwhour.setViewportView(table_whour);
		table_whour.getColumnModel().getColumn(0).setPreferredWidth(5);
		
		JLabel lbl_whour = new JLabel("Doktor Listesi");
		lbl_whour.setBounds(459, 11, 75, 14);
		w_scrolldoktor.add(lbl_whour);
		
		JLabel lbl_randevu = new JLabel("Randevu Girisi");
		lbl_randevu.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_randevu.setBounds(282, 156, 114, 25);
		w_scrolldoktor.add(lbl_randevu);
		
		JButton btn_addappointment = new JButton("Randevu Al");
		btn_addappointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_whour.getSelectedRow();
				if(selRow >=0) {
					String date = table_whour.getModel().getValueAt(selRow, 1).toString();
					try {
						boolean control = hasta.addappointment(selectdoktorID, hasta.getId(),selectdoktorname, hasta.getName(), date);
						if(control) {
							Helper.SHOWmSG("success");
							hasta.updateWhourStatus(selectdoktorID, date);
							updateWhourModel(selectdoktorID);
							updateAppointrModel(hasta.getId());
							
						}else {
							Helper.SHOWmSG("error");
							
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}else {
					Helper.SHOWmSG("Lutfen gecerli bir tarih seciniz");
					
				}
			}
		});
		btn_addappointment.setFont(new Font("Tahoma", Font.ITALIC, 18));
		btn_addappointment.setBounds(260, 181, 131, 31);
		w_scrolldoktor.add(btn_addappointment);
		
		JPanel w_appoint = new JPanel();
		w_tab.addTab("Randevularim", null, w_appoint, null);
		w_appoint.setLayout(null);
		
		JScrollPane w_scrollappoint = new JScrollPane();
		w_scrollappoint.setBounds(10, 0, 709, 323);
		w_appoint.add(w_scrollappoint);
		
		table_appoint = new JTable(appointrModel);
		w_scrollappoint.setViewportView(table_appoint);
	}
	public void updateWhourModel(int doktor_id) throws SQLException {
		DefaultTableModel clEArModel = (DefaultTableModel) table_whour.getModel();
		clEArModel.setRowCount(0);
		
		for(int i =0; i < whour.getWhourList(doktor_id).size(); i++) {
			whourdata[0] = whour.getWhourList(doktor_id).get(i).getWid();
			whourdata[1] = whour.getWhourList(doktor_id).get(i).getWdate();
			whourModel.addRow(whourdata);
	}
		
}
	public void updateAppointrModel(int hasta_id) throws SQLException {
		DefaultTableModel clEArModel = (DefaultTableModel) table_appoint.getModel();
		clEArModel.setRowCount(0);
		for(int i =0; i < appoint.getHastaList(hasta_id).size(); i++) {
			appointdata[0] = appoint.getHastaList(hasta_id).get(i).getId();
			appointdata[1] = appoint.getHastaList(hasta_id).get(i).getDoktorname();
			appointdata[2] = appoint.getHastaList(hasta_id).get(i).getAppdate();
			appointrModel.addRow(appointdata);
		}	
}
	
}

