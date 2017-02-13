package models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MyOrderId implements Serializable
{
    @Column(name = "orderId")
    public Long orderId;

    @Column(name = "productId")
    public Long productId;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof MyOrderId)) return false;
        MyOrderId that = (MyOrderId) o;
        return Objects.equals(orderId, that.orderId) &&
                Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(orderId, productId);
    }
}