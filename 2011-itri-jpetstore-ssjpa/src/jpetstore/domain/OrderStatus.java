package jpetstore.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "orderstatus")
public class OrderStatus
{
    @Id
    @Column(name = "orderid")
    private int orderId;

    @Column(name = "linenum")
    private int lineLumber;

    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @Column(name = "status")
    private String status;

    public Date getTimestamp()
    {
        return timestamp;
    }

    public String getStatus()
    {
        return status;
    }

    public int getOrderId()
    {
        return orderId;
    }

    public void setLineLumber(int lineLumber)
    {
        this.lineLumber = lineLumber;
    }

    public void setTimestamp(Date timestamp)
    {
        this.timestamp = timestamp;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public void setOrderId(int orderId)
    {
        this.orderId = orderId;
    }

    public int getLineLumber()
    {
        return lineLumber;
    }

    public OrderStatus()
    {
    }

}
