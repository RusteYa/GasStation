package com.kpfu.itis.gasstation.entities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Rustem.
 */
@Entity
@Table(name = "gasstations")
public class GasStation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", length = 20)
    private String name;

    @Column(name = "address", length = 50)
    private String address;

    @OneToMany(mappedBy = "transactionGasStation", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Collection<Transaction> transactionsForGasStation;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Collection<Transaction> getTransactionsForGasStation() {
        return transactionsForGasStation;
    }

    public void setTransactionsForGasStation(Collection<Transaction> transactionsForGasStation) {
        this.transactionsForGasStation = transactionsForGasStation;
    }
}
