package com.omerfpekgoz.stok.project.ui;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.util.Date;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;

import com.omerfpekgoz.stok.project.models.Customer;
import com.omerfpekgoz.stok.project.models.Sales;
import com.omerfpekgoz.stok.project.models.Stock;
import com.omerfpekgoz.stok.project.utils.CourseUtils;
import com.omerfpekgoz.stok.project.utils.dao.DbServicessBase;
import com.omerfpekgoz.stok.project.utils.dao.SalesDao;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ReportScreen extends JFrame {
	private JPanel panel;
	private JLabel lblMteri;
	private JComboBox cmbMusteri;
	private JButton btnMusteriRapor;
	private JScrollPane scrollPaneRapor;
	private JPanel panel_1;
	private JLabel lblBalangTarihi;
	private JLabel lblBititarihi;
	private JDateChooser dateBaslangicTarihi;
	private JDateChooser dateBitisTarihi;
	private JButton btnÝkiTarihRapor;
	private JPanel panel_2;
	private JLabel lblTarih;
	private JDateChooser dateTekTarih;
	private JButton btnTekTarihRapor;
	private JTable tblSatisListele;
	private JTable tblMusteriListele;
	private JLabel lblMesaj;
	private String sec = "bekle";

	public ReportScreen() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("images\\rapor2.png"));
		initialize();
	}

	private void initialize() {
		setTitle("Raporlama");
		setBounds(-10, 20, 1370, 650);
		getContentPane().setLayout(null);
		getContentPane().add(getPanel());
		getContentPane().add(getScrollPaneRapor());
		getContentPane().add(getPanel_1());
		getContentPane().add(getPanel_2());

		DbServicessBase<Customer> dao = new DbServicessBase<Customer>();
		List<Customer> customers = dao.getAllRows(new Customer());
		DefaultComboBoxModel model = new DefaultComboBoxModel(customers.toArray());
		cmbMusteri.setModel(model);
		getContentPane().add(getLblMesaj());
	}

	private void tableSec() {
		switch (sec) {
		case "musteri":
			scrollPaneRapor.setViewportView(getTblMusteriListele());
			musteriSatisTablosuGoster();
			tblMusteriListele.setVisible(true);
			break;
		case "tekTarih":
			scrollPaneRapor.setViewportView(getTblSatisListele());
			TekTarihSatisTablosuGoster();
			tblSatisListele.setVisible(true);
			break;
		case "ikiTarih":
			scrollPaneRapor.setViewportView(getTblIkiTarihArasiSatislar());
			IkiTarihSatisTablosuGoster();
			tblIkiTarihArasiSatislar.setVisible(true);
			break;

		default:
			break;
		}
	}

	DefaultTableModel model = new DefaultTableModel();
	private JTable tblIkiTarihArasiSatislar;

	public void musteriSatisTablosuGoster() {

		Customer salesCustomer = new Customer();
		salesCustomer = (Customer) cmbMusteri.getModel().getSelectedItem();

		DbServicessBase<Sales> dao = new DbServicessBase<Sales>();
		Sales temp = new Sales();
		temp.setCustomer(salesCustomer);
		List<Sales> satis_listesi = dao.search(temp);
		if (satis_listesi.size() == 0) {
			lblMesaj.setText("Bu Müþteriye Ürün Satýlmamýþtýr");
		}

		String[] columnNames = { "ID", "MÜÞTERÝ AD-SOYAD", "ÜRÜN ADI", "KATEGORÝ", "ÜRÜN SATIÞ FÝYATI",
				"ÜRÜN SATIÞ ADEDÝ", "SATIÞ TARÝHÝ", "SATIÞ YAPAN PERSONEL" };
		Object[][] data = new Object[satis_listesi.size()][columnNames.length];

		for (int i = 0; i < satis_listesi.size(); i++) {

			data[i][0] = CourseUtils.getValue(satis_listesi.get(i).getId());
			data[i][1] = CourseUtils.getValue(satis_listesi.get(i).getCustomer().getNameSurname());
			data[i][2] = CourseUtils.getValue(satis_listesi.get(i).getProducts().getName());
			data[i][3] = CourseUtils.getValue(satis_listesi.get(i).getProducts().getCategory());
			data[i][4] = CourseUtils.getValue(satis_listesi.get(i).getProducts().getPrice());
			data[i][5] = CourseUtils.getValue(satis_listesi.get(i).getSalesPiece());
			data[i][6] = CourseUtils.getValue(satis_listesi.get(i).getSalesDate());
			data[i][7] = CourseUtils.getValue(satis_listesi.get(i).getStaff());

		}

		model = new DefaultTableModel(data, columnNames);
		tblMusteriListele.setModel(model);
	}

	
	public void IkiTarihSatisTablosuGoster() {

		Date IkiTarihBaslangic = null;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String satisBaslangic = df.format(dateBaslangicTarihi.getDate());
		System.out.println(satisBaslangic);

		try {
			IkiTarihBaslangic = new SimpleDateFormat("yyyy-MM-dd").parse(satisBaslangic);
			System.out.println(IkiTarihBaslangic);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Date IkiTarihBitis = null;
		DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		String satisBitis = df1.format(dateBitisTarihi.getDate());
		System.out.println(satisBitis);

		try {
			IkiTarihBitis = new SimpleDateFormat("yyyy-MM-dd").parse(satisBitis);
			System.out.println(IkiTarihBitis);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SalesDao salesdao=new SalesDao();
		Sales temp = new Sales();
		List<Sales> tumSatislar = salesdao.searchBetween(temp, IkiTarihBaslangic, IkiTarihBitis);

		/*
		 * for (int i = 0; i < tumSatislar.size(); i++) { if
		 * (IkiTarihBaslangic.before(tumSatislar.get(i).getSalesDate()) &&
		 * IkiTarihBitis.after(tumSatislar.get(i).getSalesDate())) { Sales temp1=new
		 * Sales(); temp1.setSalesDate(tumSatislar.get(i).getSalesDate());
		 * ikiTarihSatislar=dao.search(temp1); } }
		 */
		if (tumSatislar.size()==0) {
			lblMesaj.setText("Bu Tarih Aralýklarýnda Satýþ Yapýlmamýþtýr");
		}
		String[] columnNames = { "ID", "MÜÞTERÝ AD-SOYAD", "ÜRÜN ADI", "KATEGORÝ", "ÜRÜN SATIÞ FÝYATI",
				"ÜRÜN SATIÞ ADEDÝ", "SATIÞ TARÝHÝ", "SATIÞ YAPAN PERSONEL" };
		Object[][] data = new Object[tumSatislar.size()][columnNames.length];

		for (int j = 0; j < tumSatislar.size(); j++) {
			data[j][0] = CourseUtils.getValue(tumSatislar.get(j).getId());
			data[j][1] = CourseUtils.getValue(tumSatislar.get(j).getCustomer().getNameSurname());
			data[j][2] = CourseUtils.getValue(tumSatislar.get(j).getProducts().getName());
			data[j][3] = CourseUtils.getValue(tumSatislar.get(j).getProducts().getCategory());
			data[j][4] = CourseUtils.getValue(tumSatislar.get(j).getProducts().getPrice());
			data[j][5] = CourseUtils.getValue(tumSatislar.get(j).getSalesPiece());
			data[j][6] = CourseUtils.getValue(tumSatislar.get(j).getSalesDate());
			data[j][7] = CourseUtils.getValue(tumSatislar.get(j).getStaff());

		}

		model = new DefaultTableModel(data, columnNames);
		tblIkiTarihArasiSatislar.setModel(model);

	}

	public void TekTarihSatisTablosuGoster() {

		Date dateTekTarihSatis = null;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String tekTarihSatis = df.format(dateTekTarih.getDate());
		System.out.println(tekTarihSatis);

		try {
			dateTekTarihSatis = new SimpleDateFormat("yyyy-MM-dd").parse(tekTarihSatis);
			System.out.println(dateTekTarihSatis);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DbServicessBase<Sales> dao = new DbServicessBase<Sales>();
		Sales temp = new Sales();
		temp.setSalesDate(dateTekTarihSatis);

		List<Sales> satis_listesi = dao.search(temp);
		if (satis_listesi.size() == 0) {
			lblMesaj.setText("Bu Tarihte Satýþ Yapýlmamýþtýr");
		}

		String[] columnNames = { "ID", "MÜÞTERÝ AD-SOYAD", "ÜRÜN ADI", "KATEGORÝ", "ÜRÜN SATIÞ FÝYATI",
				"ÜRÜN SATIÞ ADEDÝ", "SATIÞ TARÝHÝ", "SATIÞ YAPAN PERSONEL" };
		Object[][] data = new Object[satis_listesi.size()][columnNames.length];

		for (int i = 0; i < satis_listesi.size(); i++) {

			data[i][0] = CourseUtils.getValue(satis_listesi.get(i).getId());
			data[i][1] = CourseUtils.getValue(satis_listesi.get(i).getCustomer().getNameSurname());
			data[i][2] = CourseUtils.getValue(satis_listesi.get(i).getProducts().getName());
			data[i][3] = CourseUtils.getValue(satis_listesi.get(i).getProducts().getCategory());
			data[i][4] = CourseUtils.getValue(satis_listesi.get(i).getProducts().getPrice());
			data[i][5] = CourseUtils.getValue(satis_listesi.get(i).getSalesPiece());
			data[i][6] = CourseUtils.getValue(satis_listesi.get(i).getSalesDate());
			data[i][7] = CourseUtils.getValue(satis_listesi.get(i).getStaff());

		}

		model = new DefaultTableModel(data, columnNames);
		tblSatisListele.setModel(model);

	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "M\u00FC\u015Fteriye G\u00F6re", TitledBorder.LEADING,
					TitledBorder.TOP, null, null));
			panel.setBounds(24, 21, 235, 166);
			panel.setLayout(null);
			panel.add(getLblMteri());
			panel.add(getCmbMusteri());
			panel.add(getBtnMusteriRapor());
		}
		return panel;
	}

	private JLabel getLblMteri() {
		if (lblMteri == null) {
			lblMteri = new JLabel("M\u00FC\u015Fteri:");
			lblMteri.setBounds(10, 34, 74, 14);
		}
		return lblMteri;
	}

	private JComboBox getCmbMusteri() {
		if (cmbMusteri == null) {
			cmbMusteri = new JComboBox();
			cmbMusteri.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					lblMesaj.setText("");
				}
			});
			cmbMusteri.setBounds(73, 31, 152, 20);

		}
		return cmbMusteri;
	}

	private JButton getBtnMusteriRapor() {
		if (btnMusteriRapor == null) {
			btnMusteriRapor = new JButton("Rapor Al");
			btnMusteriRapor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					sec = "musteri";
					tableSec();
				}
			});
			btnMusteriRapor.setIcon(new ImageIcon("images\\giris.png"));
			btnMusteriRapor.setBounds(94, 108, 131, 49);
		}
		return btnMusteriRapor;
	}

	private JScrollPane getScrollPaneRapor() {
		if (scrollPaneRapor == null) {
			scrollPaneRapor = new JScrollPane();
			scrollPaneRapor.setBounds(24, 215, 1320, 385);
			// scrollPaneRapor.setViewportView(getTblIkiTarihArasiSatislar());
			// scrollPaneRapor.setViewportView(getTblSatisListele());
			// scrollPaneRapor.setRowHeaderView(getTblMusteriListele());
			tableSec();
		}
		return scrollPaneRapor;
	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(null, "\u0130ki Tarih Aral\u0131\u011F\u0131na G\u00F6re",
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setBounds(295, 21, 299, 166);
			panel_1.setLayout(null);
			panel_1.add(getLblBalangTarihi());
			panel_1.add(getLblBititarihi());
			panel_1.add(getDateBaslangicTarihi());
			panel_1.add(getDateBitisTarihi());
			panel_1.add(getBtnÝkiTarihRapor());
		}
		return panel_1;
	}

	private JLabel getLblBalangTarihi() {
		if (lblBalangTarihi == null) {
			lblBalangTarihi = new JLabel("Ba\u015Flang\u0131\u00E7 Tarihi:");
			lblBalangTarihi.setBounds(10, 35, 123, 14);
		}
		return lblBalangTarihi;
	}

	private JLabel getLblBititarihi() {
		if (lblBititarihi == null) {
			lblBititarihi = new JLabel("Biti\u015FTarihi:");
			lblBititarihi.setBounds(10, 77, 94, 14);
		}
		return lblBititarihi;
	}

	private JDateChooser getDateBaslangicTarihi() {
		if (dateBaslangicTarihi == null) {
			dateBaslangicTarihi = new JDateChooser();
			dateBaslangicTarihi.getCalendarButton().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					lblMesaj.setText("");
				}
			});
			dateBaslangicTarihi.setBounds(130, 35, 128, 20);
			dateBaslangicTarihi.setDateFormatString("yyyy-MM-dd");
		}
		return dateBaslangicTarihi;
	}

	private JDateChooser getDateBitisTarihi() {
		if (dateBitisTarihi == null) {
			dateBitisTarihi = new JDateChooser();
			dateBaslangicTarihi.getCalendarButton().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					lblMesaj.setText("");
				}
			});
			dateBitisTarihi.setBounds(130, 77, 128, 20);
			dateBitisTarihi.setDateFormatString("yyyy-MM-dd");
		}
		return dateBitisTarihi;
	}

	private JButton getBtnÝkiTarihRapor() {
		if (btnÝkiTarihRapor == null) {
			btnÝkiTarihRapor = new JButton("Rapor Al");
			btnÝkiTarihRapor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					sec = "ikiTarih";
					tableSec();
				}
			});
			btnÝkiTarihRapor.setIcon(new ImageIcon("images\\giris.png"));
			btnÝkiTarihRapor.setBounds(140, 108, 131, 49);
		}
		return btnÝkiTarihRapor;
	}

	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setBorder(
					new TitledBorder(null, "Tek Tarihe G\u00F6re", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_2.setBounds(645, 21, 282, 166);
			panel_2.setLayout(null);
			panel_2.add(getLblTarih());
			panel_2.add(getDateTekTarih());
			panel_2.add(getBtnTekTarihRapor());
		}
		return panel_2;
	}

	private JLabel getLblTarih() {
		if (lblTarih == null) {
			lblTarih = new JLabel("Tarih:");
			lblTarih.setBounds(10, 48, 46, 14);
		}
		return lblTarih;
	}

	private JDateChooser getDateTekTarih() {
		if (dateTekTarih == null) {
			dateTekTarih = new JDateChooser();
			dateTekTarih.getCalendarButton().addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					lblMesaj.setText("");
				}
			});
			dateTekTarih.setBounds(117, 48, 128, 20);
			dateTekTarih.setDateFormatString("yyyy-MM-dd");
		}
		return dateTekTarih;
	}

	private JButton getBtnTekTarihRapor() {
		if (btnTekTarihRapor == null) {
			btnTekTarihRapor = new JButton("Rapor Al");
			btnTekTarihRapor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					sec = "tekTarih";
					tableSec();
				}
			});
			btnTekTarihRapor.setIcon(new ImageIcon("images\\giris.png"));
			btnTekTarihRapor.setBounds(117, 104, 131, 49);
		}
		return btnTekTarihRapor;
	}

	private JTable getTblSatisListele() {
		if (tblSatisListele == null) {
			tblSatisListele = new JTable();
		}
		return tblSatisListele;
	}

	private JTable getTblMusteriListele() {
		if (tblMusteriListele == null) {
			tblMusteriListele = new JTable();
		}
		return tblMusteriListele;
	}

	private JLabel getLblMesaj() {
		if (lblMesaj == null) {
			lblMesaj = new JLabel("");
			lblMesaj.setForeground(Color.RED);
			lblMesaj.setBounds(34, 190, 523, 25);
		}
		return lblMesaj;
	}

	private JTable getTblIkiTarihArasiSatislar() {
		if (tblIkiTarihArasiSatislar == null) {
			tblIkiTarihArasiSatislar = new JTable();
		}
		return tblIkiTarihArasiSatislar;
	}
}
