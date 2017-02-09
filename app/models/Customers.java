package models;


import javax.persistence.*;

@Entity
public class Customers
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerId")
    public String customerId;

    @Column (name = "contactName")
    public String contactName;

    @Column (name = "companyName")
    public String companyName;

}
