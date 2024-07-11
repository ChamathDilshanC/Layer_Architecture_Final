package lk.ijse.bo.custom.impl.custom.forms;

import lk.ijse.bo.SuperBO;

import java.sql.SQLException;

public interface ForgetPasswordBO extends SuperBO {
    boolean checkForgetPasswordUserIDName(String userID, String name) throws SQLException, ClassNotFoundException;
    boolean updateForgetPassword(String userID, String password) throws SQLException, ClassNotFoundException;
}
