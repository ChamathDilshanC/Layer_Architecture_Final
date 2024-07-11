package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlaceReservationDTO implements Serializable {
    private ReservationDTO reservation;
    private List<ReservationpackageinfoDTO> reservationpackageinfo;
    private List<ReservationroominfoDTO> reservationroominfo;
    private List<ReservationserviceinfoDTO> reservationserviceinfo;
    private PaymentDTO reservationpaymentinfo;

}
