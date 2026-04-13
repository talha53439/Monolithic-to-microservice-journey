package com.example.demo_producer.domain;

public record CashCard(
  Long id,
  String owner,
  Double amountRequestedForAuth
) {}