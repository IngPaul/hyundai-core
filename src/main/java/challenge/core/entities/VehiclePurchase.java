package challenge.core.entities;

import challenge.core.entities.base.GenericEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "purchase")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class VehiclePurchase extends GenericEntity {


    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "version", nullable = false)
    private String version;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "cryptocurrency", nullable = false)
    private String cryptocurrency;

    @Column(name = "price_usd", nullable = false)
    private BigDecimal priceUsd;

    @Column(name = "price_cryptocurrency", nullable = false)
    private BigDecimal priceCryptocurrency;

    @Column(name = "date", nullable = false)
    private LocalDate date;


}
