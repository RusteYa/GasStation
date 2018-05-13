package com.kpfu.itis.gasstation.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Rustem.
 */
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "totalsum")
    private int totalSum;

    @Column(name = "date")
    private Timestamp date;

    @OneToMany(mappedBy = "transactionlotTransaction", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<TransactionLot> transactionLotsForTransaction;

    public int getId() {
        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public List<TransactionLot> getTransactionLotsForTransaction() {
        return transactionLotsForTransaction;
    }

    public void setTransactionLotsForTransaction(List<TransactionLot> transactionLotsForTransaction) {
        this.transactionLotsForTransaction = transactionLotsForTransaction;
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
}
