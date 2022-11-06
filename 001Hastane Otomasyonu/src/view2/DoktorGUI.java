package view2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import com.toedter.calendar.JDateChooser;

import helper.Helper;
import model.Doktor;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class DoktorGUI extends JFrame {

	private JPanel w_doktorpane;
	private static Doktor doktor = new Doktor();
	private JTable table_whour;
	private DefaultTableModel whourModel;
	private Object[] whourData = null;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoktorGUI frame = new DoktorGUI(doktor);
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
	public DoktorGUI(Doktor doktor) throws SQLException {
		whourModel = new DefaultTableModel();
		Object[] colwhour = new Object[2];
		colwhour[0] = "ID";
		colwhour[1] = "TARIH";
		whourModel.setColumnIdentifiers(colwhour);
		whourData = new Object[2];
		for(int i = 0; i < doktor.getWhourList(doktor.getId()).size(); i++) {
			whourData[0] = doktor.getWhourList(doktor.getId()).get(i).getWid();
			whourData[1] = doktor.getWhourList(doktor.getId()).get(i).getWdate();
			whourModel.addRow(whourData);
		}
		setTitle("HASTANE OTOMASYON SISTEMI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_doktorpane = new JPanel();
		w_doktorpane.setBackground(Color.WHITE);
		w_doktorpane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_doktorpane);
		w_doktorpane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hosgeldiniz Sayin : <dynamic>" + doktor.getName());
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(28, 22, 227, 29);
		w_doktorpane.add(lblNewLabel);
		
		JButton btn_diktorcikis = new JButton("CIKIS");
		btn_diktorcikis.setFont(new Font("Tahoma", Font.ITALIC, 18));
		btn_diktorcikis.setBounds(569, 11, 114, 40);
		w_doktorpane.add(btn_diktorcikis);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(20, 85, 693, 365);
		w_doktorpane.add(w_tab);
		
		JPanel whout = new JPanel();
		w_tab.addTab("Doktor Islemleri", null, whout, null);
		whout.setLayout(null);
		
		JDateChooser select_date = new JDateChooser();
		select_date.setBounds(32, 11, 112, 25);
		whout.add(select_date);
		
		JComboBox select_time = new JComboBox();
		select_time.setModel(new DefaultComboBoxModel(new String[] {"10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00"}));
		select_time.setBounds(176, 11, 45, 25);
		whout.add(select_time);
		
		JButton btn_addwhour = new JButton("EKLE");
		btn_addwhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = "";
				try {
					 date = sdf.format(select_date.getDate());
				} catch (Exception e2) {
				
				}
				if(date.length() == 0) {
					Helper.SHOWmSG("Lutfen bilgileri giriniz");
					
					}else{
						String time = " " + select_time.getSelectedItem().toString() + ":00";
						String selectdate = date + time;
						try {
							boolean control = doktor.addwhour(doktor.getId(), doktor.getName(), selectdate);
							if(control) {
								Helper.SHOWmSG("success");
								updatewhourrModel(doktor);
							}else {
								Helper.SHOWmSG("error");	
							}		
							
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
						}	
			}
			
		});
		btn_addwhour.setFont(new Font("Tahoma", Font.ITALIC, 18));
		btn_addwhour.setBounds(246, 11, 114, 25);
		whout.add(btn_addwhour);
		
		JScrollPane w_scrollwhur = new JScrollPane();
		w_scrollwhur.setBounds(10, 47, 668, 279);
		whout.add(w_scrollwhur);
		
		table_whour = new JTable(whourModel);
		w_scrollwhur.setViewportView(table_whour);
		
		JButton btn_deletewhour = new JButton("SIL");
		btn_deletewhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_whour.getSelectedRow();
				if(selRow >=0) {
					String selectRow = table_whour.getModel().getValueAt(selRow, 0).toString();
					int selID = Integer.parseInt(selectRow);
					try {
						boolean control = doktor.deleteWhour(selID);
						if(control) {
						Helper.SHOWmSG("success");
						updatewhourrModel(doktor);
						}else {
							Helper.SHOWmSG("error");
							
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}else {
					Helper.SHOWmSG("Lutfen Tarih seciniz");
				}
			}
		});
		btn_deletewhour.setFont(new Font("Tahoma", Font.ITALIC, 18));
		btn_deletewhour.setBounds(550, 11, 114, 25);
		whout.add(btn_deletewhour);
	}
	public void updatewhourrModel(Doktor doktor) throws SQLException {
		
		DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
		clearModel.setRowCount(0);
		for(int i = 0; i < doktor.getWhourList(doktor.getId()).size(); i++) {
			whourData[0] = doktor.getWhourList(doktor.getId()).get(i).getWid();
			whourData[1] = doktor.getWhourList(doktor.getId()).get(i).getWdate();
			whourModel.addRow(whourData);
		}
}
}