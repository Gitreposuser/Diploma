package com.example.deutschebank.service.interfaces.additionaltools;

public interface RandomDataGenerator {
    /**
     * Generate random transactions with defined quantity
     *
     * @param quantity of transactions
     */
    void generateTransactions(int quantity);
}