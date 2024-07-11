package lk.ijse.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.bo.SuperBO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dto.ReservationroominfoDTO;
import lk.ijse.dto.RoomDTO;
import java.sql.SQLException;
import java.util.List;

public interface RoomBO extends SuperBO {
    String getRoomStatus(String roomId) throws SQLException, ClassNotFoundException;

    List<String> getRoomIds() throws SQLException, ClassNotFoundException;

    RoomDTO searchRoomById(String id) throws SQLException, ClassNotFoundException;

    boolean saveRoom(RoomDTO entity) throws SQLException, ClassNotFoundException;

    boolean updateRoom(RoomDTO entity) throws SQLException, ClassNotFoundException;

    String getNextRoomId() throws SQLException, ClassNotFoundException;

    RoomDTO searchRoomByPhone(String phone) throws SQLException, ClassNotFoundException;

    ObservableList<RoomDTO> getAllRoom() throws SQLException, ClassNotFoundException;

    boolean DeleteRoom(String id) throws SQLException, ClassNotFoundException;

    List<String> getDistinctDepartments() throws SQLException, ClassNotFoundException;

    boolean updateRoomAvailability(List<ReservationroominfoDTO> reservationroominfo) throws SQLException, ClassNotFoundException ;
}
