package com.kpfu.itis.gasstation.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Rustem.
 */
@Entity
@Table(name = "engineoils")
public class EngineOil {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", length = 60)
    private String name;

    @Column(name = "price")
    private int price;

    @Column(name = "manafacturer", length = 20)
    private String manafacturer;

    @Column(name = "photopath", nullable = false)
    private String photoPath;

    @OneToMany(mappedBy = "transactionlotEngineOil", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<TransactionLot> transactionLotsWithFuel;

    public String getManafacturer() {
        return manafacturer;
    }

    public void setManafacturer(String manafacturer) {
        this.manafacturer = manafacturer;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public List<TransactionLot> getTransactionLotsWithFuel() {
        return transactionLotsWithFuel;
    }

    public void setTransactionLotsWithFuel(List<TransactionLot> transactionLotsWithFuel) {
        this.transactionLotsWithFuel = transactionLotsWithFuel;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
