package lk.ijse.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {


    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        ResultSet rst  = SQLUtil.execute("SELECT CustomerID FROM customers");
        List<String> ids = new ArrayList<>();
        while (rst.next()){
            ids.add(rst.getString("CustomerID"));
        }
        return ids;
    }

    @Override
    public Customer searchById(String id) throws SQLException, ClassNotFoundException {
        System.out.println("id = " + id);
        ResultSet rst = SQLUtil.execute("SELECT * FROM customers WHERE CustomerID=?", id);
        if (rst.next()) {
            return new Customer(rst.getString("CustomerID"),
                    rst.getString("FirstName"),
                    rst.getString("LastName"),
                    rst.getString("Nationality"),
                    rst.getString("Email"),
                    rst.getString("Phone"),
                    rst.getString("Address"),
                    rst.getInt("TotalReservations"),
                    rst.getDate("RegistrationDate"));

        }
        System.out.println("rst = " + rst);
        return null;

    }

    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO customers (CustomerID, FirstName, LastName, Nationality, Email, Phone, Address, TotalReservations, RegistrationDate) VALUES (?,?,?,?,?,?,?,?,?)",
                entity.getCustomerID(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getNationality(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getAddress(),
                entity.getTotalReservations(),
                entity.getRegistrationDate());
        }


    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE customers SET FirstName = ?, LastName = ?, Nationality = ?, Email = ?, Phone = ?, Address = ?, TotalReservations = ?, RegistrationDate = ? WHERE CustomerID = ?",
                entity.getFirstName(),
                entity.getLastName(),
                entity.getNationality(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getAddress(),
                entity.getTotalReservations(),
                entity.getRegistrationDate(),
                entity.getCustomerID());
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT CustomerID FROM customers ORDER BY CustomerID DESC LIMIT 1");
        if (rst.next()) {
            String lastId = rst.getString("CustomerID");
            int num = Integer.parseInt(lastId.replace("CUST", "")) + 1;
            return String.format("CUST%03d", num);
        } else {
            return "CUST001";  // Return the first ID if no existing records
        }
    }


    @Override
    public Customer searchByPhone(String phone) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM customers WHERE Phone=?", phone);
        if (rst.next()){
            return new Customer(rst.getString("CustomerID"),
                    rst.getString("FirstName"),
                    rst.getString("LastName"),
                    rst.getString("Nationality"),
                    rst.getString("Email"),
                    rst.getString("Phone"),
                    rst.getString("Address"),
                    rst.getInt("TotalReservations"),
                    rst.getDate("RegistrationDate"));
        }
        return null;
    }

    @Override
    public ObservableList<Customer> getAll() {
        try {
            ResultSet rst = SQLUtil.execute("SELECT * FROM customers");
            ObservableList<Customer> customers = FXCollections.observableArrayList();
            while (rst.next()) {
                customers.add(new Customer(rst.getString("CustomerID"),
                        rst.getString("FirstName"),
                        rst.getString("LastName"),
                        rst.getString("Nationality"),
                        rst.getString("Email"),
                        rst.getString("Phone"),
                        rst.getString("Address"),
                        rst.getInt("TotalReservations"),
                        rst.getDate("RegistrationDate")));
            }
            return customers;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean Delete(String id) {
        try {
            return SQLUtil.execute("DELETE FROM customers WHERE CustomerID=?", id);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getDistinctDepartments() throws SQLException, ClassNotFoundException {
        return List.of();
    }
}
