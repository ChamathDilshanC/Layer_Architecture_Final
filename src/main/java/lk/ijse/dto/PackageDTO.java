package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PackageDTO implements Serializable {
    private String PackageID;
    private String PackageName;
    private double PackagePrice;
    private String ServicesIncluded;

}
