package com.example.demo_producer.service;


import com.example.demo_producer.domain.CashCard;
import com.example.demo_producer.domain.Transaction;
import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class DataSourceService {

  public Transaction getData() {
    CashCard cashCard = new CashCard(
      new Random().nextLong(), // Random ID
      "sarah1",
      new Random().nextDouble(100.00) // Random Amount
    );
    return new Transaction(new Random().nextLong(), cashCard);
  }
}