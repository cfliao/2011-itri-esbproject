package jpetstore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import jpetstore.dao.hibernate.LineItemKey;

@Entity
@IdClass(LineItemKey.class)
@Table(name = "lineitem")
public class LineItem
{

    @Id
    @Column(name = "orderid")
    private int orderId;

    @Id
    @Column(name = "linenum")
    private int lineNumber;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "itemid")
    private String itemId;

    @Column(name = "unitprice")
    private double unitPrice;

    @ManyToOne(targetEntity = Item.class)
    @JoinColumn(name = "itemid", insertable = false, updatable = false)
    private Item item;

    /* Constructors */

    public LineItem()
    {
    }

    public LineItem(int lineNumber, CartItem cartItem)
    {
        this.lineNumber = lineNumber;
        this.quantity = cartItem.getQuantity();
        this.itemId = cartItem.getItem().getItemId();
        this.unitPrice = cartItem.getItem().getListPrice();
        this.item = cartItem.getItem();
    }

    /* JavaBeans Properties */

    public int getOrderId()
    {
        return orderId;
    }

    public void setOrderId(int orderId)
    {
        this.orderId = orderId;
    }

    public int getLineNumber()
    {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber)
    {
        this.lineNumber = lineNumber;
    }

    public String getItemId()
    {
        return itemId;
    }

    public void setItemId(String itemId)
    {
        this.itemId = itemId;
    }

    public double getUnitPrice()
    {
        return unitPrice;
    }

    public void setUnitPrice(double unitprice)
    {
        this.unitPrice = unitprice;
    }

    public Item getItem()
    {
        return item;
    }

    public void setItem(Item item)
    {
        this.item = item;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public double getTotalPrice()
    {
        return this.unitPrice * this.quantity;
    }

}
