package com.omerfpekgoz.stok.project.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.omerfpekgoz.stok.project.models.Category;
import com.omerfpekgoz.stok.project.models.Customer;
import com.omerfpekgoz.stok.project.models.Products;
import com.omerfpekgoz.stok.project.models.Staff;
import com.omerfpekgoz.stok.project.models.UserRoleTypes;
import com.omerfpekgoz.stok.project.utils.CourseUtils;
import com.omerfpekgoz.stok.project.utils.dao.DbServicessBase;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddCustomerScreen extends JFrame {
	private JPanel panel;
	private JLabel lblMteriAdsoyad;
	private JTextField txtMusteriAdSoyad;
	private JLabel lblMteriTelNo;
	private JTextField txtMusteriTelno;
	private JLabel lblAdres;
	private JButton btnEkle;
	private JButton btnSil;
	private JButton btnGuncelle;
	private JButton btnTemizle;
	private JButton btnCikis;
	private JTextField txtAdres;
	private JLabel lblMesaj;
	private JScrollPane scrollPane;
	private JTable tblMusterilerTablosu;
	private Integer selectedCustomerId;
	public AddCustomerScreen() {
		initialize();
	}

	private void initialize() {
		setTitle("M\u00FC\u015Fteri \u0130\u015Flemleri");
		setBounds(-10, 20, 1370, 650);
		getContentPane().setLayout(null);
		getContentPane().add(getPanel());
		getContentPane().add(getBtnCikis());
		getContentPane().add(getLblMesaj());
		getContentPane().add(getScrollPane());
		musteriTablosuGoster();
		
		txtMusteriAdSoyad.setText("");
		txtMusteriTelno.setText("");
		txtAdres.setText("");
		lblMesaj.setText("");
	}
DefaultTableModel model=new DefaultTableModel();
	

	public void musteriTablosuGoster() {
	
	DbServicessBase<Customer> dao=new DbServicessBase<Customer>();
	Customer temp=new Customer();
	List<Customer> musteri_listesi=dao.getAllRows(temp);
	
	String [] columnNames= {"ID","MÜÞTERÝ AD-SOYAD","MÜÞTERÝ TEL. NO.","MÜÞTERÝ ADRESÝ"};
	Object [][] data=new Object [musteri_listesi.size()][columnNames.length];
	
	
	for (int i = 0; i < musteri_listesi.size(); i++) {
		
		data[i][0]=CourseUtils.getValue(musteri_listesi.get(i).getId());
		data[i][1]=CourseUtils.getValue(musteri_listesi.get(i).getNameSurname());
		data[i][2]=CourseUtils.getValue(musteri_listesi.get(i).getPhoneNumber());
		data[i][3]=CourseUtils.getValue(musteri_listesi.get(i).getAdress());
		
		
		
	}
	
	model=new DefaultTableModel(data,columnNames);
	tblMusterilerTablosu.setModel(model);
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "M\u00FC\u015Fteri Bigileri", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 11, 293, 534);
			panel.setLayout(null);
			panel.add(getLblMteriAdsoyad());
			panel.add(getTxtMusteriAdSoyad());
			panel.add(getLblMteriTelNo());
			panel.add(getTxtMusteriTelno());
			panel.add(getLblAdres());
			panel.add(getBtnEkle());
			panel.add(getBtnSil());
			panel.add(getBtnGuncelle());
			panel.add(getBtnTemizle());
			panel.add(getTxtAdres());
		}
		return panel;
	}
	private JLabel getLblMteriAdsoyad() {
		if (lblMteriAdsoyad == null) {
			lblMteriAdsoyad = new JLabel(" Ad-Soyad:");
			lblMteriAdsoyad.setBounds(23, 54, 64, 14);
		}
		return lblMteriAdsoyad;
	}
	private JTextField getTxtMusteriAdSoyad() {
		if (txtMusteriAdSoyad == null) {
			txtMusteriAdSoyad = new JTextField();
			txtMusteriAdSoyad.setBounds(97, 51, 158, 20);
			txtMusteriAdSoyad.setColumns(10);
		}
		return txtMusteriAdSoyad;
	}
	private JLabel getLblMteriTelNo() {
		if (lblMteriTelNo == null) {
			lblMteriTelNo = new JLabel("Tel. No.:");
			lblMteriTelNo.setBounds(35, 116, 52, 14);
		}
		return lblMteriTelNo;
	}
	private JTextField getTxtMusteriTelno() {
		if (txtMusteriTelno == null) {
			txtMusteriTelno = new JTextField();
			txtMusteriTelno.setColumns(10);
			txtMusteriTelno.setBounds(97, 113, 158, 20);
		}
		return txtMusteriTelno;
	}
	private JLabel getLblAdres() {
		if (lblAdres == null) {
			lblAdres = new JLabel("Adres:");
			lblAdres.setBounds(35, 175, 46, 14);
		}
		return lblAdres;
	}
	private JButton getBtnEkle() {
		if (btnEkle == null) {
			btnEkle = new JButton("Ekle");
			btnEkle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					DbServicessBase<Customer> dao=new DbServicessBase<Customer>();
					Customer newCustomer =new Customer();
					
					newCustomer.setNameSurname(txtMusteriAdSoyad.getText());
					newCustomer.setPhoneNumber(txtMusteriTelno.getText());
					newCustomer.setAdress(txtAdres.getText());
					if (dao.save(newCustomer)) {
						lblMesaj.setText("Müþteri Baþarý Ýle Kaydedildi");
						musteriTablosuGoster();
					}
					else {
						lblMesaj.setText("Müþteri Kaydedilemedi");
					}
				}
			});
			btnEkle.setIcon(new ImageIcon("images\\ekle4.png"));
			btnEkle.setBounds(10, 344, 125, 47);
		}
		return btnEkle;
	}
	private JButton getBtnSil() {
		if (btnSil == null) {
			btnSil = new JButton("Sil");
			btnSil.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DbServicessBase<Customer> dao=new DbServicessBase<Customer>();
					Customer deletedCustomer=new Customer();
					int row=tblMusterilerTablosu.getSelectedRow();
					deletedCustomer.setId(selectedCustomerId);
					
					
					if (dao.delete(deletedCustomer)) {
						lblMesaj.setText("Müþteri Baþarý ile Silindi");
						musteriTablosuGoster();
					}
					else {
						lblMesaj.setText("MüþteriSilinemedi");
					}
					
				}
				
			});
			btnSil.setIcon(new ImageIcon("images\\delete.png"));
			btnSil.setBounds(158, 344, 125, 47);
		}
		return btnSil;
	}
	private JButton getBtnGuncelle() {
		if (btnGuncelle == null) {
			btnGuncelle = new JButton("G\u00FCncelle");
			btnGuncelle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					DbServicessBase<Customer> dao=new DbServicessBase<Customer>();
					Customer updatedCustomer=new Customer();
					updatedCustomer.setId(selectedCustomerId);
					updatedCustomer.setNameSurname(txtMusteriAdSoyad.getText().toString());
					updatedCustomer.setPhoneNumber(txtMusteriTelno.getText().toString());
					updatedCustomer.setAdress(txtAdres.getText().toString());
					
					if (dao.update(updatedCustomer)) {
						lblMesaj.setText("Müþteri Baþarý Ýle Güncellendi");
						musteriTablosuGoster();
						
					}
					else {
						lblMesaj.setText("Müþteri Güncellenemedi");
					}
				}
				
			});
			btnGuncelle.setIcon(new ImageIcon("images\\guncelle2.png"));
			btnGuncelle.setBounds(10, 425, 125, 47);
		}
		return btnGuncelle;
	}
	private JButton getBtnTemizle() {
		if (btnTemizle == null) {
			btnTemizle = new JButton("Temizle");
			btnTemizle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					txtMusteriAdSoyad.setText("");
					txtMusteriTelno.setText("");
					txtAdres.setText("");
					lblMesaj.setText("");
				}
			});
			btnTemizle.setIcon(new ImageIcon("images\\temizle.png"));
			btnTemizle.setBounds(158, 425, 125, 47);
		}
		return btnTemizle;
	}
	private JButton getBtnCikis() {
		if (btnCikis == null) {
			btnCikis = new JButton("\u00C7\u0131k\u0131\u015F");
			btnCikis.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
			btnCikis.setIcon(new ImageIcon("images\\cikis.png"));
			btnCikis.setBounds(91, 548, 131, 44);
		}
		return btnCikis;
	}
	private JTextField getTxtAdres() {
		if (txtAdres == null) {
			txtAdres = new JTextField();
			txtAdres.setColumns(10);
			txtAdres.setBounds(97, 172, 186, 107);
		}
		return txtAdres;
	}
	private JLabel getLblMesaj() {
		if (lblMesaj == null) {
			lblMesaj = new JLabel("");
			lblMesaj.setForeground(Color.RED);
			lblMesaj.setBackground(Color.RED);
			lblMesaj.setBounds(338, 567, 692, 23);
		}
		return lblMesaj;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(338, 28, 994, 517);
			scrollPane.setViewportView(getTblMusterilerTablosu());
		}
		return scrollPane;
	}
	private JTable getTblMusterilerTablosu() {
		if (tblMusterilerTablosu == null) {
			tblMusterilerTablosu = new JTable();
			tblMusterilerTablosu.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					int row=tblMusterilerTablosu.getSelectedRow();
					selectedCustomerId=Integer.valueOf(tblMusterilerTablosu.getValueAt(row, 0).toString());
					txtMusteriAdSoyad.setText(tblMusterilerTablosu.getValueAt(row,1).toString());
					txtMusteriTelno.setText(tblMusterilerTablosu.getValueAt(row, 2).toString());
					txtAdres.setText(tblMusterilerTablosu.getValueAt(row, 3).toString());
				}
			});
		}
		return tblMusterilerTablosu;
	}
}
