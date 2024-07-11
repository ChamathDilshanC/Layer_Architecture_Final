package lk.ijse.entity;

import lk.ijse.dto.PaymentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Payment {
    private String paymentId;
    private String reservationId;
    private double amount;
    private LocalDate date;
    private String method;
    private String status;

    public Payment(PaymentDTO reservationpaymentinfo) {
        this.paymentId = reservationpaymentinfo.getPaymentId();
        this.reservationId = reservationpaymentinfo.getReservationId();
        this.amount = reservationpaymentinfo.getAmount();
        this.date = reservationpaymentinfo.getDate();
        this.method = reservationpaymentinfo.getMethod();
        this.status = reservationpaymentinfo.getStatus();
    }
}
