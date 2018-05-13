package com.kpfu.itis.gasstation.entities;

import javax.persistence.*;

/**
 * Created by Rustem.
 */
@Entity
@Table(name = "transactionlots")
public class TransactionLot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "amount", nullable = false)
    private int amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "engineoil_id", nullable = false)
    private EngineOil transactionlotEngineOil;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "transaction_id", nullable = false)
    private EngineOil transactionlotTransaction;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EngineOil getTransactionlotEngineOil() {
        return transactionlotEngineOil;
    }

    public void setTransactionlotEngineOil(EngineOil transactionlotEngineOil) {
        this.transactionlotEngineOil = transactionlotEngineOil;
    }

    public EngineOil getTransactionlotTransaction() {
        return transactionlotTransaction;
    }

    public void setTransactionlotTransaction(EngineOil transactionlotTransaction) {
        this.transactionlotTransaction = transactionlotTransaction;
    }
}
