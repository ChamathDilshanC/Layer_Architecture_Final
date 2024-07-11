package lk.ijse.bo.custom.impl.custom.forms;

import lk.ijse.bo.SuperBO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DashboardBO extends SuperBO {
    String getDashboardUsername(String userId) throws SQLException, ClassNotFoundException;
    String getDashboardBestPackage() throws SQLException, ClassNotFoundException;
    int getDashboardAvailableRooms() throws SQLException, ClassNotFoundException;
    int getDashboardCheckInCount() throws SQLException, ClassNotFoundException;
    int getDashboardCheckOutCount() throws SQLException, ClassNotFoundException;
    int getDashboardTotalReservations() throws SQLException, ClassNotFoundException;
    int getDashboardCustomerCount() throws SQLException, ClassNotFoundException;
    double getDashboardTotalRevenue() throws SQLException, ClassNotFoundException;
    List<String> getDashboardRoomIdsByCheckoutDate(Date checkoutDate) throws SQLException, ClassNotFoundException;
    void updateDashboardRoomStatusToAvailable(String roomId) throws SQLException, ClassNotFoundException;
    ResultSet setDashboardEmployeeDetails(String UserId) throws SQLException, ClassNotFoundException;
}
