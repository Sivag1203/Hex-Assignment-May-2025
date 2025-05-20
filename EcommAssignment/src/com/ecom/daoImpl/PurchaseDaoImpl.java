package com.ecom.daoImpl;

import com.ecom.dao.PurchaseDao;
import com.ecom.model.Purchase;
import com.ecom.utility.DbUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class PurchaseDaoImpl implements PurchaseDao {

    @Override
    public void makePurchase(Purchase p) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DbUtility.getInstance().connect();
            String query = "INSERT INTO purchase (purchase_date, customer_id, product_id) VALUES (?, ?, ?)";
            ps = con.prepareStatement(query);

            // If the purchase date is not set, use today's date
            String purchaseDate = p.getDate_of_purchase();
            if (purchaseDate == null || purchaseDate.isEmpty()) {
                purchaseDate = LocalDate.now().toString();
            }

            ps.setString(1, purchaseDate);
            ps.setInt(2, p.getCustomer_id());
            ps.setInt(3, p.getProduct_id());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error making purchase: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                DbUtility.getInstance().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
