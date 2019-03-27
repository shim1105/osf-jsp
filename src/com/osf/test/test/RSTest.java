package com.osf.test.test;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.osf.test.db.DBCon;
import com.osf.test.vo.Food;

public class RSTest {
	public static void main(String[] args) {
		String sql= "select * from food";
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(sql);
			ResultSet  rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			List<Map<String,String>> colList = new ArrayList<>();
			for(int i=1;i<=rsmd.getColumnCount();i++) {
				String colName=rsmd.getColumnLabel(i);
				colName= colName.toLowerCase();
				int idx= colName.indexOf("_");
				String fName= colName.substring(0,idx);
				String lName= colName.substring(idx+1); 
				lName = lName.substring(0,1).toUpperCase()+lName.substring(1);
				
				Map<String,String> col =new HashMap<>();
				col.put(colName, fName+lName);
				colList.add(col);
			}
			System.out.println(colList);
			while(rs.next()) {
				Map<String,String> row = new HashMap<>();
				for(Map<String,String> col:colList) {
					Iterator<String> it= col.keySet().iterator();
					while(it.hasNext()) {  // 한번만 돌릴때 필요없다.
						String key= it.next();
						String value= rs.getString(key);
						row.put(col.get(key), value);
					}
				}
				Food f = new Food();
				try {
					BeanUtils.populate(f, row);
					System.out.println(f);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
