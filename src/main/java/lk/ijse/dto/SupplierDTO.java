package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SupplierDTO implements Serializable {
    private String supplierId;
    private String name;
    private String postalCode;
    private String emailAddress;
    private String contactNumber;

}
