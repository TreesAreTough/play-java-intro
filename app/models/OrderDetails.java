package models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class OrderDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "ProductID")
    public Long productId;

    @Column(name = "UnitPrice")
    public BigDecimal unitPrice;

    @Column(name = "Quantity")
    public Integer quantity;
}
