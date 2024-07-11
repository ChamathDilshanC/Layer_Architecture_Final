package lk.ijse.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
@AllArgsConstructor
@Data
@NoArgsConstructor
public class PaymentDTO implements Serializable {
    private String paymentId;
    private String reservationId;
    private double amount;
    private LocalDate date;
    private String method;
    private String status;
}
