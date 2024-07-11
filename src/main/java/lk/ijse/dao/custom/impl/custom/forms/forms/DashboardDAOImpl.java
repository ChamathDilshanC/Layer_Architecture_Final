package lk.ijse.dao.custom.impl.custom.forms.forms;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.impl.custom.forms.DashboardDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DashboardDAOImpl implements DashboardDAO {
    public String getUsername(String userId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT Username FROM user WHERE UserId = ?", userId);
        if (rst.next()) {
            return rst.getString("Username");
        } else {
            return null;
        }
    }

    public String getBestPackage() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT PackageID, COUNT(PackageID) AS PackageCount FROM reservation_package_info GROUP BY PackageID ORDER BY PackageCount DESC LIMIT 1");
        if (rst.next()) {
            return rst.getString("PackageID");
        } else {
            return null;
        }
    }

    public int getAvailableRooms() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT COUNT(*) AS AvailableRooms FROM room WHERE Status = 'Available'");
        if (rst.next()) {
            return rst.getInt("AvailableRooms");
        } else {
            return 0;
        }
    }

    public int getCheckInCount() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT COUNT(*) AS CheckInCount FROM reservation WHERE CheckInDate BETWEEN '2024-03-01' AND '2024-07-31'");
        if (rst.next()) {
            return rst.getInt("CheckInCount");
        } else {
            return 0;
        }
    }

    public int getCheckOutCount() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT COUNT(*) AS CheckOutCount FROM reservation WHERE CheckOutDate BETWEEN '2024-03-01' AND '2024-07-31'");
        if (rst.next()) {
            return rst.getInt("CheckOutCount");
        } else {
            return 0;
        }
    }

    public int getTotalReservations() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT COUNT(*) AS TotalReservations FROM reservation");
        if (rst.next()) {
            return rst.getInt("TotalReservations");
        } else {
            return 0;
        }
    }

    public int getCustomerCount() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT COUNT(*) AS CustomerCount FROM customers WHERE RegistrationDate >= '2024-02-01'");
        if (rst.next()) {
            return rst.getInt("CustomerCount");
        } else {
            return 0;
        }
    }

    public double getTotalRevenue() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT SUM(Amount) AS TotalRevenue FROM payment");
        if (rst.next()) {
            return rst.getDouble("TotalRevenue");
        } else {
            return 0.0;
        }
    }

    public List<String> getRoomIdsByCheckoutDate(Date checkoutDate) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT rri.RoomID " +
                "FROM reservation r " +
                "JOIN reservation_room_info rri ON r.ReservationID = rri.ReservationID " +
                "WHERE r.CheckOutDate = ?", checkoutDate);
        List<String> roomIds = new ArrayList<>();
        while (rst.next()) {
            roomIds.add(rst.getString("RoomID"));
        }
        return roomIds;
    }

    public void updateRoomStatusToAvailable(String roomId) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("UPDATE room SET Status = 'Available' WHERE RoomID = ?", roomId);
    }
    public  ResultSet setEmployeeDetails(String UserId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT e.EmployeeID, e.Position, e.Phone FROM user u JOIN employee e ON u.EmployeeID = e.EmployeeID WHERE u.UserId = ?", UserId);
    }
}
