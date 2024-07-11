package lk.ijse.dao.custom.impl.custom.forms.forms;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.impl.custom.forms.ForgetPasswordDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ForgetPasswordDAOImpl implements ForgetPasswordDAO {

    public boolean checkUserIDName(String userID, String name) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM User WHERE UserID=? AND Username=?", userID, name);
        return rst.next();

    }
    public boolean updatePassword(String userID, String password) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE User SET Password=? WHERE UserID=?", password, userID);
    }
}
