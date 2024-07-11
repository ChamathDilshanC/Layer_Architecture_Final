package lk.ijse.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.bo.custom.RoomBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.RoomDAO;
import lk.ijse.dto.ReservationroominfoDTO;
import lk.ijse.dto.RoomDTO;

import java.sql.SQLException;
import java.util.List;

public class RoomBOImpl implements RoomBO {

    RoomDAO roomDAO =(RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);

    @Override
    public String getRoomStatus(String roomId) throws SQLException, ClassNotFoundException {
        return roomDAO.getRoomStatus(roomId);
    }

    @Override
    public List<String> getRoomIds() throws SQLException, ClassNotFoundException {
        return roomDAO.getIds();
    }

    @Override
    public RoomDTO searchRoomById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveRoom(RoomDTO entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateRoom(RoomDTO entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getNextRoomId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public RoomDTO searchRoomByPhone(String phone) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ObservableList<RoomDTO> getAllRoom() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean DeleteRoom(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<String> getDistinctDepartments() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public boolean updateRoomAvailability(List<ReservationroominfoDTO> reservationroominfo) throws SQLException, ClassNotFoundException {
        return roomDAO.updateRoom(reservationroominfo);
    }
}
