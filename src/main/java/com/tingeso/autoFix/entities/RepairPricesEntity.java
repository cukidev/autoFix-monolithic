package com.tingeso.autoFix.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "repair_prices")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepairPricesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_repair_prices;

    @Column(name = "type", nullable = false, unique = true)
    private String type;

    @Column(name = "gasoline_price", nullable = false)
    private Integer gasoline_price;

    @Column(name = "diesel_price", nullable = false)
    private Integer diesel_price;

    @Column(name = "hybrid_price", nullable = false)
    private Integer hybrid_price;

    @Column(name = "electric_price", nullable = false)
    private Integer electric_price;


    public Integer getPriceByEngineType(String engineType) {
        switch (engineType) {
            case "Gasolina": return gasoline_price;
            case "Diésel": return diesel_price;
            case "Híbrido": return hybrid_price;
            case "Eléctrico": return electric_price;
            default: return 0;
        }
    }

}
