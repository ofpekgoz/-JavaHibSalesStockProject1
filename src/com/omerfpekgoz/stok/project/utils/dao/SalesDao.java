package com.omerfpekgoz.stok.project.utils.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import com.omerfpekgoz.stok.project.utils.dao.DbServicessBase;
import com.omerfpekgoz.stok.project.models.Sales;
import com.omerfpekgoz.stok.project.utils.MyHBUtil;

public class SalesDao extends DbServicessBase<Sales>{

	Session ss = MyHBUtil.getSessionFactory().openSession();
	Transaction tt = ss.beginTransaction();
	
	
	public List<Sales> searchBetween(Sales temp,Date baslangicTarihi,Date bitisTarih ) {
		List<Sales> results ;
		try {
			Criteria cr = ss.createCriteria(temp.getClass());
			cr.add(Restrictions.between("salesDate", baslangicTarihi, bitisTarih));
			
		
			results =cr.list();
			tt.commit();
			return results;
		}
		catch (Exception e) {
			tt.rollback();
			e.printStackTrace();
			return null;
		}
		
		
	}
 
	
}
