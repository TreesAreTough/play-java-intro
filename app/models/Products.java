package models;

import javax.persistence.*;

@Entity
public class Products
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "productId")
    public Long productId;

    @Column(name = "productName")
    public String productName;

    @OneToOne(optional=true)
    @JoinColumn(name = "categoryId")
    public Categories category;
}