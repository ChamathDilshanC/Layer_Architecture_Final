package lk.ijse.dao.custom.impl.custom.forms.forms;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.impl.custom.forms.LoginFormDAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class LoginFormDAOImpl implements LoginFormDAO {

    public ResultSet checkLogin(String UserID) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT * FROM user WHERE UserID = ?", UserID);
    }
    public  boolean updateLoginTimestamp (String UserID, Date date, Time time) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE user SET LastLoginDate = ?, LastLoginTime = ?, IsActive = 1 WHERE UserID = ?",date,time, UserID);
    }

}
