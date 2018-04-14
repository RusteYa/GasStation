package com.kpfu.itis.gasstation.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Rustem.
 */
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "price")
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fuels_id", nullable = false)
    private Fuel productType;

    @OneToMany(mappedBy = "transactionProduct", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Transaction> transactionsWithProduct;

    public List<Transaction> getTransactionsWithProduct() {
        return transactionsWithProduct;
    }

    public void setTransactionsWithProduct(List<Transaction> transactionsWithProduct) {
        this.transactionsWithProduct = transactionsWithProduct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Fuel getProductType() {
        return productType;
    }

    public void setProductType(Fuel productType) {
        this.productType = productType;
    }
}
