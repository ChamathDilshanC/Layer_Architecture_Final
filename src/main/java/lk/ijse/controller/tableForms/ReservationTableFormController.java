package lk.ijse.controller.tableForms;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.ReservationBO;
import lk.ijse.dto.ReservationDTO;

import java.sql.Date;
import java.sql.SQLException;

public class ReservationTableFormController {

    @FXML
    private TableView<ReservationDTO> tablereservations;

    @FXML
    private TableColumn<ReservationDTO, String> colReservationID;

    @FXML
    private TableColumn<ReservationDTO, Date> colCheckInDate;

    @FXML
    private TableColumn<ReservationDTO, Date> colCheckOutDate;

    @FXML
    private TableColumn<ReservationDTO, Date> colReservationDate;

    @FXML
    private TableColumn<ReservationDTO, String> colDuration;

    @FXML
    private TableColumn<ReservationDTO, Integer> colNumberofGuests;

    @FXML
    private TableColumn<ReservationDTO, String> colStatus;

    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValueFactory();
        loadTableData();
    }

    ReservationBO reservationBO = (ReservationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVATION);

    private void setCellValueFactory() {
        colReservationID.setCellValueFactory(new PropertyValueFactory<>("ReservationID"));
        colCheckInDate.setCellValueFactory(new PropertyValueFactory<>("CheckInDate"));
        colCheckOutDate.setCellValueFactory(new PropertyValueFactory<>("CheckOutDate"));
        colReservationDate.setCellValueFactory(new PropertyValueFactory<>("ReservationDate"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("Duration"));
        colNumberofGuests.setCellValueFactory(new PropertyValueFactory<>("NumberofGuests"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        tablereservations.setItems(reservationBO.getAllReservation());
    }
}
