package com.kpfu.itis.gasstation.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Rustem.
 */
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "productamount")
    private int productAmount;

    @Column(name = "productprice")
    private int productPrice;

    @Column(name = "totalsum")
    private int totalSum;

    @Column(name = "date")
    private Timestamp date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gasstation_id", nullable = false)
    private GasStation transactionGasStation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product transactionProduct;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(int totalSum) {
        this.totalSum = totalSum;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public GasStation getTransactionGasStation() {
        return transactionGasStation;
    }

    public void setTransactionGasStation(GasStation transactionGasStation) {
        this.transactionGasStation = transactionGasStation;
    }

    public Product getTransactionProduct() {
        return transactionProduct;
    }

    public void setTransactionProduct(Product transactionProduct) {
        this.transactionProduct = transactionProduct;
    }
}
