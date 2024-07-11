package lk.ijse.dao.custom.impl.custom.forms;

import lk.ijse.dao.SuperDAO;

import java.sql.SQLException;

public interface ForgetPasswordDAO extends SuperDAO {
    boolean checkUserIDName(String userID, String name) throws SQLException, ClassNotFoundException;
    boolean updatePassword(String userID, String password) throws SQLException, ClassNotFoundException;
}
