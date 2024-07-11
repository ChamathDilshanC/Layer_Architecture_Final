package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Package {
    private String PackageID;
    private String PackageName;
    private double PackagePrice;
    private String ServicesIncluded;

}
