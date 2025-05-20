package com.ecom.daoImpl;

import com.ecom.dao.CategoryDao;
import com.ecom.model.Category;
import com.ecom.utility.DbUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public void insert(Category c) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DbUtility.getInstance().connect();
            String query = "INSERT INTO category (category_name) VALUES (?)";
            ps = con.prepareStatement(query);
            ps.setString(1, c.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting category: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                DbUtility.getInstance().close(); // close connection
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}