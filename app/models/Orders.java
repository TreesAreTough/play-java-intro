package models;

import javax.persistence.*;

@Entity
public class Orders
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "orderId")
    public Long orderId;

    @OneToOne(optional=false)
    @JoinColumn(name = "employeeId")
    public Employees employee;
}