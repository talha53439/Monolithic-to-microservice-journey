package com.example.demo_producer.stream;


import java.util.function.Supplier;

import com.example.demo_producer.domain.Transaction;
import com.example.demo_producer.service.DataSourceService;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CashCardTransactionStream {

  @Bean
  public Supplier<Transaction> approvalRequest(DataSourceService dataSource) {

    // add this function call
    return dataSource::getData;
  }

}