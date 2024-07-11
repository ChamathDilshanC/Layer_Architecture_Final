package lk.ijse.dao.custom.impl.custom.forms;
import lk.ijse.dao.SuperDAO;
import java.sql.*;
import java.util.List;


 public  interface DashboardDAO extends SuperDAO {

     String getUsername(String userId) throws SQLException, ClassNotFoundException;
     String getBestPackage() throws SQLException, ClassNotFoundException;
     int getAvailableRooms() throws SQLException, ClassNotFoundException;
     int getCheckInCount() throws SQLException, ClassNotFoundException;
     int getCheckOutCount() throws SQLException, ClassNotFoundException;
     int getTotalReservations() throws SQLException, ClassNotFoundException;
     int getCustomerCount() throws SQLException, ClassNotFoundException;
     double getTotalRevenue() throws SQLException, ClassNotFoundException;
     List<String> getRoomIdsByCheckoutDate(Date checkoutDate) throws SQLException, ClassNotFoundException;
     void updateRoomStatusToAvailable(String roomId) throws SQLException, ClassNotFoundException;
     ResultSet setEmployeeDetails(String UserId) throws SQLException, ClassNotFoundException;
}
