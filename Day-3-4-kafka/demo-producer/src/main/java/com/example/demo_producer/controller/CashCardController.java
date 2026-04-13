package com.example.demo_producer.controller;


import com.example.demo_producer.domain.Transaction;
import com.example.demo_producer.ondemand.CashCardTransactionOnDemand;
import com.example.demo_producer.stream.CashCardTransactionStream;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CashCardController {
  private CashCardTransactionOnDemand onDemand;

  public CashCardController(CashCardTransactionOnDemand cashCardTransactionStream) {
    this.onDemand = cashCardTransactionStream;
  }
  @PostMapping(path = "/publish/txn")
  public void publishTxn(@RequestBody Transaction transaction) {
    System.out.println("POST for Transaction: " + transaction);
    this.onDemand.publishOnDemand(transaction);
  }
}