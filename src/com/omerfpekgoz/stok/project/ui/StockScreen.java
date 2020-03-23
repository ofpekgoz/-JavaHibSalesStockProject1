package com.omerfpekgoz.stok.project.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import com.omerfpekgoz.stok.project.models.Category;
import com.omerfpekgoz.stok.project.models.Products;
import com.omerfpekgoz.stok.project.models.Staff;
import com.omerfpekgoz.stok.project.models.Stock;
import com.omerfpekgoz.stok.project.models.UserRoleTypes;
import com.omerfpekgoz.stok.project.utils.CourseUtils;
import com.omerfpekgoz.stok.project.utils.dao.DbServicessBase;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Color;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class StockScreen extends JFrame {
	private JPanel panel;
	private JLabel lblStokYapanPersonel;
	private JLabel lblStokYaplanrn;
	private JComboBox cmbStokUrun;
	private JLabel lblStokYapldTarih;
	private JDateChooser dateStokTarihi;
	private JLabel lblrnKategori;
	private JComboBox cmbKategori;
	private JComboBox cmbStokPersonel;
	private JLabel lblStokAdedi;
	private JTextField txtStokAdet;
	private JButton btnStokEkle;
	private JButton btnStokSil;
	private JButton btnStokGuncelle;
	private JButton btnTemizle;
	private JTable tblStokTablosu;
	private JLabel lblMesaj;
	private JScrollPane scrollPane;
	private Integer selectedStockId;
	public StockScreen() {
		initialize();
	}

	private void initialize() {
		setBounds(-10, 20, 1370, 650);
		getContentPane().setLayout(null);
		getContentPane().add(getPanel());
		getContentPane().add(getLblMesaj());
		getContentPane().add(getScrollPane());
		getContentPane().setName("Stok Ýþlemleri");
		
		
		stokTablosuGoster();
		
		DbServicessBase<Staff> dao=new DbServicessBase<Staff>();
		List<Staff> staffs=dao.getAllRows(new Staff());
		DefaultComboBoxModel model=new DefaultComboBoxModel(staffs.toArray());
		cmbStokPersonel.setModel(model);
		DbServicessBase<Products> dao1=new DbServicessBase<Products>();
		List<Products> products=dao1.getAllRows(new Products());
		 model=new DefaultComboBoxModel(products.toArray());
		cmbStokUrun.setModel(model);
		/*DbServicessBase<Category> dao2=new DbServicessBase<Category>();
		List<Category> categories=dao2.getAllRows(new Category());
		 model=new DefaultComboBoxModel(categories.toArray());
		cmbKategori.setModel(model);*/
		
		cmbKategori.setSelectedItem(null);
		cmbStokPersonel.setSelectedItem(null);
		cmbStokUrun.setSelectedItem(null);
		txtStokAdet.setText("");
		dateStokTarihi.setDate(null);
		lblMesaj.setText("");
	}

DefaultTableModel model=new DefaultTableModel();
private JLabel lblrnAdedi;
private JTextField txtUrunAdet;

	

	public void stokTablosuGoster() {
	
	DbServicessBase<Stock> dao=new DbServicessBase<Stock>();
	Stock temp=new Stock();
	List<Stock> stok_listesi=dao.getAllRows(temp);
	
	String [] columnNames= {"ID","STOK YAPAN PERSONEL","ÜRÜN ADI","ÜRÜN ADEDÝ","ÜRÜN KATEGORÝSÝ","ÜRÜN STOK ADEDÝ","STOK TARÝHÝ"};
	Object [][] data=new Object [stok_listesi.size()][columnNames.length];
	
	
	for (int i = 0; i < stok_listesi.size(); i++) {
		
		data[i][0]=CourseUtils.getValue(stok_listesi.get(i).getId());
		data[i][1]=CourseUtils.getValue(stok_listesi.get(i).getStaff().toString());
		data[i][2]=CourseUtils.getValue(stok_listesi.get(i).getProducts().toString());
		data[i][3]=CourseUtils.getValue(stok_listesi.get(i).getProducts().getPiece()).toString();
		data[i][4]=CourseUtils.getValue(stok_listesi.get(i).getProducts().getCategory().toString());
		data[i][5]=CourseUtils.getValue(String.valueOf(stok_listesi.get(i).getStockPiece()).toString());
		data[i][6]=CourseUtils.getValue(stok_listesi.get(i).getStockdate());
		
		
		
		
	}
	
	model=new DefaultTableModel(data,columnNames);
	tblStokTablosu.setModel(model);
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Stok Bilgileri", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(20, 11, 355, 562);
			panel.setLayout(null);
			panel.add(getLblStokYapanPersonel());
			panel.add(getLblStokYaplanrn());
			panel.add(getCmbStokUrun());
			panel.add(getLblStokYapldTarih());
			panel.add(getDateStokTarihi());
			panel.add(getLblrnKategori());
			panel.add(getCmbKategori());
			panel.add(getCmbStokPersonel());
			panel.add(getLblStokAdedi());
			panel.add(getTxtStokAdet());
			panel.add(getBtnStokEkle());
			panel.add(getBtnStokSil());
			panel.add(getBtnStokGuncelle());
			panel.add(getBtnTemizle());
			panel.add(getLblrnAdedi());
			panel.add(getTxtUrunAdet());
		}
		return panel;
	}
	private JLabel getLblStokYapanPersonel() {
		if (lblStokYapanPersonel == null) {
			lblStokYapanPersonel = new JLabel("Stok Yapan Personel:");
			lblStokYapanPersonel.setBounds(41, 50, 132, 14);
		}
		return lblStokYapanPersonel;
	}
	private JLabel getLblStokYaplanrn() {
		if (lblStokYaplanrn == null) {
			lblStokYaplanrn = new JLabel("\u00DCr\u00FCn Ad\u0131:");
			lblStokYaplanrn.setBounds(83, 104, 107, 14);
		}
		return lblStokYaplanrn;
	}
	private JComboBox getCmbStokUrun() {
		if (cmbStokUrun == null) {
			cmbStokUrun = new JComboBox();
			cmbStokUrun.setBounds(173, 101, 150, 20);
		}
		return cmbStokUrun;
	}
	private JLabel getLblStokYapldTarih() {
		if (lblStokYapldTarih == null) {
			lblStokYapldTarih = new JLabel("Stok Yap\u0131ld\u0131\u011F\u0131 Tarih:");
			lblStokYapldTarih.setBounds(41, 313, 117, 14);
		}
		return lblStokYapldTarih;
	}
	private JDateChooser getDateStokTarihi() {
		if (dateStokTarihi == null) {
			dateStokTarihi = new JDateChooser();
			dateStokTarihi.setBounds(173, 307, 150, 20);
			dateStokTarihi.setDateFormatString("yyyy-MM-dd");
		}
		return dateStokTarihi;
	}
	private JLabel getLblrnKategori() {
		if (lblrnKategori == null) {
			lblrnKategori = new JLabel("\u00DCr\u00FCn Kategori:");
			lblrnKategori.setBounds(67, 162, 106, 14);
		}
		return lblrnKategori;
	}
	private JComboBox getCmbKategori() {
		if (cmbKategori == null) {
			cmbKategori = new JComboBox();
			cmbKategori.setEnabled(false);
			cmbKategori.setBounds(173, 159, 150, 20);
		}
		return cmbKategori;
	}
	private JComboBox getCmbStokPersonel() {
		if (cmbStokPersonel == null) {
			cmbStokPersonel = new JComboBox();
			cmbStokPersonel.setBounds(173, 47, 150, 20);
		}
		return cmbStokPersonel;
	}
	private JLabel getLblStokAdedi() {
		if (lblStokAdedi == null) {
			lblStokAdedi = new JLabel("Stok Adedi:");
			lblStokAdedi.setBounds(72, 258, 86, 14);
		}
		return lblStokAdedi;
	}
	private JTextField getTxtStokAdet() {
		if (txtStokAdet == null) {
			txtStokAdet = new JTextField();
			txtStokAdet.setBounds(173, 255, 150, 20);
			txtStokAdet.setColumns(10);
		}
		return txtStokAdet;
	}
	private JButton getBtnStokEkle() {
		if (btnStokEkle == null) {
			btnStokEkle = new JButton("Stok Ekle");
			btnStokEkle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DbServicessBase<Stock> dao=new DbServicessBase<Stock>();
					Products product=new Products();
					Stock addedStock=new Stock();
					
					addedStock.setStaff((Staff) cmbStokPersonel.getModel().getSelectedItem());
					addedStock.setProducts((Products) cmbStokUrun.getModel().getSelectedItem());
					addedStock.getProducts().setCategory((Category) cmbKategori.getModel().getSelectedItem());
					addedStock.setStockPiece(Integer.valueOf(txtStokAdet.getText().toString()));
					
					addedStock.setStockdate(dateStokTarihi.getDate());
					if (addedStock.getProducts().getPiece()<addedStock.getStockPiece()) {
						
						lblMesaj.setText("Bu ürün stok yapýlamaz(Ürün Adedi Stok Yapýlacak Adetten Azdýr)");
					}
					else {
						
						if (dao.save(addedStock)) {
							lblMesaj.setText("Stok Baþarý ile Eklendi");
							stokTablosuGoster();
						}
						else {
							lblMesaj.setText("Stok Eklenemedi");
						}
						
					}
				
				}
			});
			btnStokEkle.setIcon(new ImageIcon("images\\ekle4.png"));
			btnStokEkle.setBounds(10, 361, 141, 45);
		}
		return btnStokEkle;
	}
	private JButton getBtnStokSil() {
		if (btnStokSil == null) {
			btnStokSil = new JButton("Stok Sil");
			btnStokSil.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DbServicessBase<Stock> dao=new DbServicessBase<Stock>();
					Stock deletedStock=new Stock();
					int row=tblStokTablosu.getSelectedRow();
					deletedStock.setId(selectedStockId);
					
					
					if (dao.delete(deletedStock)) {
						lblMesaj.setText("Stok Baþarý ile Silindi");
						stokTablosuGoster();
					}
					else {
						lblMesaj.setText("Stok Silinemedi");
					}
					

				}
			});
			btnStokSil.setIcon(new ImageIcon("images\\delete.png"));
			btnStokSil.setBounds(191, 361, 132, 45);
		}
		return btnStokSil;
	}
	private JButton getBtnStokGuncelle() {
		if (btnStokGuncelle == null) {
			btnStokGuncelle = new JButton("Stok G\u00FCncelle");
			btnStokGuncelle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					DbServicessBase<Stock> dao=new DbServicessBase<Stock>();
					Stock updatedStock=new Stock();
					Products products=new Products();
					products=(Products) cmbStokUrun.getModel().getSelectedItem();
					updatedStock.setId(selectedStockId);
					updatedStock.setStaff( (Staff) cmbStokPersonel.getModel().getSelectedItem());
					updatedStock.setProducts((Products) cmbStokUrun.getModel().getSelectedItem());
					updatedStock.getProducts().setCategory((Category) cmbKategori.getModel().getSelectedItem());
					updatedStock.setStockPiece(Integer.valueOf(txtStokAdet.getText()));
					updatedStock.getProducts().setPiece(Integer.valueOf(txtUrunAdet.getText()));
					updatedStock.setStockdate(dateStokTarihi.getDate());
					
					Integer adet=Integer.valueOf(txtStokAdet.getText());
					if (updatedStock.getProducts().getPiece()<adet) {
						
						lblMesaj.setText("Bu Ürün Stoðunda Güncelleme Yapýlamaz(Ürün Adedi Stok Yapýlacak Adetten Azdýr)");
					}
					else {
						if (dao.update(updatedStock)) {
							lblMesaj.setText("Stok Baþarý ile Güncellendi");
							stokTablosuGoster();
						}
						else {
							lblMesaj.setText("Stok Güncellenemedi");

						}
					}

				}
			});
			btnStokGuncelle.setIcon(new ImageIcon("images\\guncelle2.png"));
			btnStokGuncelle.setBounds(10, 443, 148, 45);
		}
		return btnStokGuncelle;
	}
	private JButton getBtnTemizle() {
		if (btnTemizle == null) {
			btnTemizle = new JButton("Temizle");
			btnTemizle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cmbKategori.setSelectedItem(null);
					cmbStokPersonel.setSelectedItem(null);
					cmbStokUrun.setSelectedItem(null);
					txtStokAdet.setText("");
					dateStokTarihi.setDate(null);
					lblMesaj.setText("");
				}
			});
			btnTemizle.setIcon(new ImageIcon("images\\temizle.png"));
			btnTemizle.setBounds(191, 443, 132, 45);
		}
		return btnTemizle;
	}
	private JTable getTblStokTablosu() {
		if (tblStokTablosu == null) {
			tblStokTablosu = new JTable();
			tblStokTablosu.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					int row=tblStokTablosu.getSelectedRow();
					selectedStockId=Integer.valueOf(tblStokTablosu.getValueAt(row, 0).toString());
					String  s = (String)tblStokTablosu.getValueAt(row, 1);
					String[] s1 = s.split("-");
					Staff c = new Staff();
					c.setId(Integer.parseInt(s1[0]));
					c.setNameSurname(s1[1]);
					c.setRole(UserRoleTypes.valueOf(s1[2]));
					cmbStokPersonel.getModel().setSelectedItem(c);
					
					String  n = (String)tblStokTablosu.getValueAt(row, 2);
					String[] n1 = n.split("-");
					Products p = new Products();
					p.setId(Integer.parseInt(n1[0]));
					p.setName(n1[1]);
					cmbStokUrun.getModel().setSelectedItem(p);
					
					String  a = (String)tblStokTablosu.getValueAt(row, 4);
					String[] a1 = a.split("-");
					Category k = new Category();
					k.setId(Integer.parseInt(a1[0]));
					k.setName(a1[1]);
					cmbKategori.getModel().setSelectedItem(k);
					txtUrunAdet.setText((String) tblStokTablosu.getValueAt(row, 3));
					txtStokAdet.setText((String.valueOf(tblStokTablosu.getValueAt(row, 5))));
				
					try {
						Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)  tblStokTablosu.getValueAt(row, 6));
						dateStokTarihi.setDate(date);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			});
		}
		return tblStokTablosu;
	}
	private JLabel getLblMesaj() {
		if (lblMesaj == null) {
			lblMesaj = new JLabel("");
			lblMesaj.setForeground(Color.RED);
			lblMesaj.setBackground(Color.RED);
			lblMesaj.setBounds(408, 561, 692, 23);
		}
		return lblMesaj;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(408, 44, 917, 489);
			scrollPane.setViewportView(getTblStokTablosu());
		}
		return scrollPane;
	}
	private JLabel getLblrnAdedi() {
		if (lblrnAdedi == null) {
			lblrnAdedi = new JLabel("\u00DCr\u00FCn Adedi:");
			lblrnAdedi.setBounds(83, 215, 88, 14);
		}
		return lblrnAdedi;
	}
	private JTextField getTxtUrunAdet() {
		if (txtUrunAdet == null) {
			txtUrunAdet = new JTextField();
			txtUrunAdet.setEnabled(false);
			txtUrunAdet.setText("");
			txtUrunAdet.setColumns(10);
			txtUrunAdet.setBounds(173, 212, 150, 20);
		}
		return txtUrunAdet;
	}
}
