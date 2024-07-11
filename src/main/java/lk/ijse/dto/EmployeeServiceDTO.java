package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeServiceDTO implements Serializable {
    private String employeeID;
    private String serviceID;
    private String serviceDate;
    private double servicePrice;
    private double totalServicePrice;
    private int hoursAllocated;

}
