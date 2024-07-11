package lk.ijse.dao.custom.impl;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.EventDAO;
import lk.ijse.entity.Event;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EventDAOImpl  implements EventDAO {
    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public Event searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Event entity) throws SQLException, ClassNotFoundException {
        boolean save = SQLUtil.execute("INSERT INTO event VALUES(?,?,?,?,?,?,?)",
                entity.getEventID(),
                entity.getReservationID(),
                entity.getEventName(),
                entity.getEventDate(),
                entity.getNumberOfAttendees(),
                entity.getCustomerPhone(),
                entity.getHallNumber());
        if (!save) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to save the Event");
            alert.show();
        }
        return save;
    }

    @Override
    public boolean update(Event entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT EventID FROM event ORDER BY EventID DESC LIMIT 1");
        if (resultSet.next()) {
            String lastId = resultSet.getString("EventID");
            int num = Integer.parseInt(lastId.replace("EV", "")) + 1;
            return String.format("EV%03d", num);
        }
        return "EV001";
    }

    @Override
    public Event searchByPhone(String phone) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ObservableList<Event> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean Delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<String> getDistinctDepartments() throws SQLException, ClassNotFoundException {
        return List.of();
    }
}
