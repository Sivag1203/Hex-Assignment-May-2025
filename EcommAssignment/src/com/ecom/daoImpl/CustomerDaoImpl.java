package com.ecom.daoImpl;

import com.ecom.dao.CustomerDao;
import com.ecom.model.Customer;
import com.ecom.utility.DbUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public void insert(Customer c) {
        Connection con = DbUtility.getInstance().connect();

        String sql = "Insert into customer(customer_name,address) values(?,?)";
        try
        {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1,c.getName());
            pstmt.setString(2,c.getAddress());
            pstmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            DbUtility.getInstance().close();
        }
    }
}
