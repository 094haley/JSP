package dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import db.DBHelper;
import vo.CustomerVO;

public class CustomerDAO extends DBHelper {

	private static CustomerDAO instance = new CustomerDAO();

	public static CustomerDAO getInstance() {
		return instance;
	}
	
	private CustomerDAO() {
	
		try {
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insertCustomer(CustomerVO customer){
		
		try {
			
			conn = getConnection();
			psmt = conn.prepareStatement("insert into `customer` (`name`, `address`, `phone`) values(?,?,?)");
			psmt.setString(1, customer.getName());
			psmt.setString(2, customer.getAddress());
			psmt.setString(3, customer.getPhone());
			
			psmt.executeUpdate();
			
			close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public CustomerVO selectCustomer(String custId){
		CustomerVO customer = null;
		try {
			
			conn = getConnection();
			psmt = conn.prepareStatement("select * from `customer` where `custId`=?");
			psmt.setString(1, custId);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				customer = new CustomerVO();
				customer.setCustId(rs.getInt(1));
				customer.setName(rs.getString(2));
				customer.setAddress(rs.getString(3));
				customer.setPhone(rs.getString(4));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return customer;
	}
	
	public List<CustomerVO> selectCustomers(){
		
		List<CustomerVO> customers = new ArrayList<>();
		try {
			
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from `customer`");
			
			while(rs.next()) {
				CustomerVO customer = new CustomerVO();
				customer.setCustId(rs.getInt(1));
				customer.setName(rs.getString(2));
				customer.setAddress(rs.getString(3));
				customer.setPhone(rs.getString(4));
				customers.add(customer);
			}
			
			close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return customers;
	}
	
	public void updateCustomer(CustomerVO customer){
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("update `customer` set `name`=?, `address`=?, `phone`=? where `custId`=?");
			psmt.setString(1, customer.getName());
			psmt.setString(2, customer.getAddress());
			psmt.setString(3, customer.getPhone());
			psmt.setInt(4, customer.getCustId());
			psmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteCustomer(String custId) {
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("delete from `customer` where `custId`=?");
			psmt.setString(1, custId);
			psmt.executeUpdate();
			
			close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
