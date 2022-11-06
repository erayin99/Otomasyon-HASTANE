package view2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import helper.Helper;
import helper.Item;
import model.Bashekim;
import model.Clinic;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class BashekimGUI extends JFrame {
	private static final ActionListener ActionListener = null;
	static Bashekim bashekim = new Bashekim();
	Clinic clinic = new Clinic();
	private JPanel w_pane;
	private JTextField fld_adsouyad;
	private JTextField fld_tcno;
	private JPasswordField fld_pass;
	private JTextField fld_doctorID;
	private DefaultTableModel doctorModel = null;
	private Object[] doctordata = null;
	private JTable doctor_table;
	private JTable table_clinic;
	private JTextField fld_clinic;
	private DefaultTableModel clinicModel = null;
	private Object[] clinicdata =null;
	private JPopupMenu  clinicMenu;
	private JTable table_worker;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BashekimGUI frame = new BashekimGUI(bashekim);
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
	public BashekimGUI(Bashekim bashekim) throws SQLException {
		//doktor model
		doctorModel = new DefaultTableModel();
		Object[] colDoctorName = new Object[4];
		colDoctorName[0] = "ID";
		colDoctorName[1] = "TC NO";
		colDoctorName[2] = "SIFRE";
		colDoctorName[3] = "AD SOYAD";
		doctorModel.setColumnIdentifiers(colDoctorName);
		doctordata = new Object[4];
		try {
			for(int i = 0; i < bashekim.getDoktorList().size();i++) {
				doctordata [0] = bashekim.getDoktorList().get(i).getId();
				doctordata [1] = bashekim.getDoktorList().get(i).getTcno();
				doctordata [2] = bashekim.getDoktorList().get(i).getPassword();
				doctordata [3] = bashekim.getDoktorList().get(i).getName();
				doctorModel.addRow(doctordata);
				}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		//clinic model
		clinicModel = new DefaultTableModel();
		Object[] colClinicName = new Object[2];
		colClinicName[0] = "ID";
		colClinicName[1] = "Poliklinik Adi";
		clinicModel.setColumnIdentifiers(colClinicName);
		clinicdata = new Object[2];
		for(int i = 0 ; i < clinic.getList().size(); i++) {
		clinicdata[0] = clinic.getList().get(i).getCid();
		clinicdata[1] = clinic.getList().get(i).getCname();
		clinicModel.addRow(clinicdata);
		}
		//worker Model
		DefaultTableModel workermodel = new DefaultTableModel();
		Object[] colworker = new Object[2];
		colworker[0] = "ID";
		colworker[1] = "AD SOYAD";
		workermodel.setColumnIdentifiers(colworker);
		Object[] workerdata = new Object[2];
		
				
		setTitle("HASTANE ISLETIM SISTEMI");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setForeground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hosgeldiniz Sayin : " + bashekim.getName());
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(46, 35, 227, 29);
		w_pane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("CIKIS");
		btnNewButton.setFont(new Font("Tahoma", Font.ITALIC, 18));
		btnNewButton.setBounds(587, 24, 114, 40);
		w_pane.add(btnNewButton);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(26, 86, 678, 344);
		w_pane.add(w_tab);
		
		JPanel panel = new JPanel();
		w_tab.addTab("Doktor Yonetimi", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Adi Soyadi");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(542, 103, 88, 25);
		panel.add(lblNewLabel_1);
		
		fld_adsouyad = new JTextField();
		fld_adsouyad.setBounds(510, 129, 153, 25);
		panel.add(fld_adsouyad);
		fld_adsouyad.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("TC No");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(542, 52, 88, 25);
		panel.add(lblNewLabel_1_1);
		
		fld_tcno = new JTextField();
		fld_tcno.setColumns(10);
		fld_tcno.setBounds(510, 77, 153, 25);
		panel.add(fld_tcno);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Sifre");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(556, 0, 88, 25);
		panel.add(lblNewLabel_1_1_1);
		
		fld_pass = new JPasswordField();
		fld_pass.setBounds(510, 30, 153, 25);
		panel.add(fld_pass);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Kullanici ID");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_1.setBounds(542, 196, 88, 25);
		panel.add(lblNewLabel_1_1_1_1);
		
		fld_doctorID = new JTextField();
		fld_doctorID.setColumns(10);
		fld_doctorID.setBounds(510, 226, 153, 25);
		panel.add(fld_doctorID);
		
		JButton btn_doktorsil = new JButton("Sil");
		btn_doktorsil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_doctorID.getText().length() == 0) {
					Helper.SHOWmSG("Lutfen gecerli bir doktor seciniz..");
					
				}else {
					if(Helper.confirm("sure")) {
						int selectID = Integer.parseInt(fld_doctorID.getText());
						try {
							
							boolean control = bashekim.deleteDoctor(selectID);
							if(control) {
								Helper.SHOWmSG("success");
								fld_doctorID.setText(null);
								updateDoktorModel();
							}
						} catch (SQLException e1) {
							
							e1.printStackTrace();			
					}
					}
				}
			}
		});
		btn_doktorsil.setFont(new Font("Tahoma", Font.ITALIC, 18));
		btn_doktorsil.setBounds(520, 262, 114, 31);
		panel.add(btn_doktorsil);
		
		JScrollPane w_scrollPane = new JScrollPane();
		w_scrollPane.setBounds(21, 21, 470, 272);
 		panel.add(w_scrollPane);
		
		doctor_table = new JTable(doctorModel);
		w_scrollPane.setViewportView(doctor_table);
		doctor_table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
			try {
				fld_doctorID.setText(doctor_table.getValueAt(doctor_table.getSelectedRow(), 0).toString());
			} catch (Exception ex) {
				
			}
				
			}
		});
			doctor_table.getModel().addTableModelListener(new TableModelListener() {
				
				@Override
				public void tableChanged(TableModelEvent e) {
				if(e.getType() == TableModelEvent.UPDATE) {
				int swlwxtID = Integer.parseInt(doctor_table.getValueAt(doctor_table.getSelectedRow(), 0).toString());
				String selecttcno = doctor_table.getValueAt(doctor_table.getSelectedRow(),1).toString();
				String selectpassword = doctor_table.getValueAt(doctor_table.getSelectedRow(),2).toString();
				String selectname = doctor_table.getValueAt(doctor_table.getSelectedRow(),3).toString();
				try {
					boolean control = bashekim.updateDoctor(swlwxtID, selecttcno, selectpassword, selectname);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				}
					
				}
			});
	
		JButton btn_doktorekle = new JButton("Ekle");
		btn_doktorekle.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				if(fld_adsouyad.getText().length() == 0 || fld_pass.getText().length() == 0 || fld_tcno.getText().length() == 0) {
					Helper.SHOWmSG("fill");
					
			}else {
				try {
					
					boolean control = bashekim.addDoctor(
							fld_tcno.getText(),fld_pass.getText(),fld_adsouyad.getText());
					if(control) {
						Helper.SHOWmSG("suuces");
						fld_doctorID.setText(null);
						fld_tcno.setText(null);
						fld_adsouyad.setText(null);
						fld_pass.setText(null);
				updateDoktorModel();
				
						
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
					}
					
				
			}		
			
		}
		});
		
		btn_doktorekle.setFont(new Font("Tahoma", Font.ITALIC, 18));
		btn_doktorekle.setBounds(531, 165, 114, 31);
		panel.add(btn_doktorekle);
		
		JPanel w_clinic = new JPanel();
		w_clinic.setBackground(Color.WHITE);
		w_tab.addTab("Poliklinikler", null, w_clinic, null);
		w_clinic.setLayout(null);
		
		JScrollPane scroll_clinic = new JScrollPane();
		scroll_clinic.setBounds(0, 11, 270, 294);
		w_clinic.add(scroll_clinic);
		
		clinicMenu = new JPopupMenu();
		JMenuItem  updateMenu = new JMenuItem("Guncelle");
		JMenuItem  deleteMenu = new JMenuItem("Sil");
		clinicMenu.add(updateMenu);
		clinicMenu.add(deleteMenu);
		
		updateMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			int selID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(),0).toString()); 
			Clinic selectclinic = clinic.getFetch(selID) ;
			UpdateClinicGUI clinicGUI = new UpdateClinicGUI(selectclinic);
			clinicGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			clinicGUI.setVisible(true);
			clinicGUI.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					try {
						updateClinicModel();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			}
		});
		
		deleteMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			if(Helper.confirm("sure")) {
				int selID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
				try {
					if(clinic.deleteClinic(selID)) {
						Helper.SHOWmSG("success");
						updateClinicModel();
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
		
		table_clinic = new JTable(clinicModel);
		table_clinic.setComponentPopupMenu(clinicMenu);;
		table_clinic.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {  
			Point point = e.getPoint();
			int selectedRow = table_clinic.rowAtPoint(point);
			table_clinic.setRowSelectionInterval(selectedRow, selectedRow);
			
			}
		});
		scroll_clinic.setViewportView(table_clinic);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Poliklinik Ismi");
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_2.setBounds(302, 6, 114, 25);
		w_clinic.add(lblNewLabel_1_1_1_2);
		
		JButton btn_clinicekle_1 = new JButton("Ekle");
		btn_clinicekle_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(fld_clinic.getText().length()  ==0) {
				Helper.SHOWmSG("fill");
				
			}else {
				try {
					if(clinic.addClinic(fld_clinic.getText())) {
						Helper.SHOWmSG("success");
						fld_clinic.setText(null);
						updateClinicModel();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			}
		});
		btn_clinicekle_1.setFont(new Font("Tahoma", Font.ITALIC, 18));
		btn_clinicekle_1.setBounds(285, 67, 131, 31);
		w_clinic.add(btn_clinicekle_1);
		
		fld_clinic = new JTextField();
		fld_clinic.setColumns(10);
		fld_clinic.setBounds(280, 31, 136, 25);
		w_clinic.add(fld_clinic);
		
		JScrollPane scroll_worker = new JScrollPane();
		scroll_worker.setBounds(434, 11, 239, 294);
		w_clinic.add(scroll_worker);
		
		table_worker = new JTable();
		scroll_worker.setViewportView(table_worker);
		
		JComboBox select_doktor = new JComboBox();
		select_doktor.setBounds(285, 232, 131, 31);
		for(int i = 0; i < bashekim.getDoktorList().size(); i++) {
			select_doktor.addItem(new Item(bashekim.getDoktorList().get(i).getId(), bashekim.getDoktorList().get(i).getName()));
			
		}
		select_doktor.addActionListener(e ->{
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			System.out.println(item.getKey() + "  " + item.getValue());
			
		});
		w_clinic.add(select_doktor);
		
		JButton btn_addworker = new JButton("Ekle");
		btn_addworker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_clinic.getSelectedRow();
				if(selRow >=0) {
					String selClinic = table_clinic.getModel().getValueAt(selRow, 0).toString();
					int selClinicID = Integer.parseInt(selClinic);
					Item doktoritem = (Item) select_doktor.getSelectedItem();
					try {
						boolean control = bashekim.addworker(doktoritem.getKey(), selClinicID);
						if(control) {
							Helper.SHOWmSG("success");
							DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
							clearModel.setRowCount(0);
							for(int i =0; i < bashekim.getClinicDoktorList(selClinicID).size();i++) {
								workerdata[0] = bashekim.getClinicDoktorList(selClinicID).get(i).getId();
								workerdata[1] = bashekim.getClinicDoktorList(selClinicID).get(i).getName();
								workermodel.addRow(workerdata);
								
							}
							table_worker.setModel(workermodel); 		
						}else { 
							Helper.SHOWmSG("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}else {
					Helper.SHOWmSG("Lutfen bir Policlinic Seciniz..");
					
				}
			}
		});
		btn_addworker.setFont(new Font("Tahoma", Font.ITALIC, 18));
		btn_addworker.setBounds(280, 274, 136, 31);
		w_clinic.add(btn_addworker);
		
		JLabel lblNewLabel_1_1_1_2_1 = new JLabel("Poliklinik Ismi");
		lblNewLabel_1_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_2_1.setBounds(302, 108, 114, 25);
		w_clinic.add(lblNewLabel_1_1_1_2_1);
		
		JButton btn_clinicselect = new JButton("Sec");
		btn_clinicselect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_clinic.getSelectedRow();
				if(selRow >=0) {
					String selClinic = table_clinic.getModel().getValueAt(selRow, 0).toString();
					int selClinicID = Integer.parseInt(selClinic);
					DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
					clearModel.setRowCount(0);
					try {
						for(int i =0; i < bashekim.getClinicDoktorList(selClinicID).size();i++) {
							workerdata[0] = bashekim.getClinicDoktorList(selClinicID).get(i).getId();
							workerdata[1] = bashekim.getClinicDoktorList(selClinicID).get(i).getName();
							workermodel.addRow(workerdata);
							
						}
						
					} catch (Exception e2) {
					
					}
				table_worker.setModel(workermodel);
				}else {
					Helper.SHOWmSG("Lutfen bir poliklinik secin");
				}
			}
		});
		btn_clinicselect.setFont(new Font("Tahoma", Font.ITALIC, 18));
		btn_clinicselect.setBounds(285, 134, 131, 31);
		w_clinic.add(btn_clinicselect);
		
	}
	public void updateClinicModel() throws SQLException{
		DefaultTableModel clearModel = (DefaultTableModel) table_clinic.getModel();
		clearModel.setRowCount(0);
		for(int i = 0; i < clinic.getList().size();i++) {
			clinicdata [0] = clinic.getList().get(i).getCid();
			clinicdata [1] = clinic.getList().get(i).getCname();
			clinicModel.addRow(clinicdata);
			}
		}		
public void updateDoktorModel() throws SQLException {
			
			DefaultTableModel clearModel = (DefaultTableModel) doctor_table.getModel();
			clearModel.setRowCount(0);
			for(int i = 0; i < bashekim.getDoktorList().size();i++) {
				doctordata [0] = bashekim.getDoktorList().get(i).getId();
				doctordata [1] = bashekim.getDoktorList().get(i).getTcno();
				doctordata [2] = bashekim.getDoktorList().get(i).getPassword();
				doctordata [3] = bashekim.getDoktorList().get(i).getName();
				doctorModel.addRow(doctordata);
				}
}
}


	