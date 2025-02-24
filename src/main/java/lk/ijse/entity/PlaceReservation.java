package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlaceReservation {
    private Reservation reservation;
    private List<Reservationpackageinfo> reservationpackageinfo;
    private List<Reservationroominfo> reservationroominfo;
    private List<Reservationserviceinfo> reservationserviceinfo;
    private Payment reservationpaymentinfo;

}
