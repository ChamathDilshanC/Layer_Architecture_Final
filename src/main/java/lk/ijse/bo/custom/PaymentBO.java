package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;

import java.sql.SQLException;

public interface PaymentBO extends SuperBO {
    String getCurrentId() throws SQLException, ClassNotFoundException;
}
