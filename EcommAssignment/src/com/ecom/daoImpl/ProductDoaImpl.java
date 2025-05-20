package com.ecom.daoImpl;

import com.ecom.dao.ProductDao;
import com.ecom.model.Product;
import com.ecom.utility.DbUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDoaImpl implements ProductDao {

    @Override
    public void insert(Product p) {
        Connection con = DbUtility.getInstance().connect();
        String sql = "insert into product(prod_name,price,prod_desc,category_id) values(?,?,?,?)";
        try{
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1,p.getTitle());
            pstmt.setDouble(2,p.getPrice());
            pstmt.setString(3,p.getDescription());
            pstmt.setInt(4,p.getCategory_id());
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

    @Override
    public List<Product> getAllProdByCategory(int category_id) {
        List<Product> products = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DbUtility.getInstance().connect();
            String sql = "SELECT * FROM product WHERE category_id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, category_id);
            rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setTitle(rs.getString("prod_name"));
                product.setPrice(rs.getDouble("price"));
                product.setDescription(rs.getString("prod_desc"));
                product.setCategory_id(rs.getInt("category_id"));

                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching products by category: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                DbUtility.getInstance().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return products;
    }
}
