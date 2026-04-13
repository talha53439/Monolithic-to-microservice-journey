package com.example.demo_producer.stream;



import java.io.IOException;

import com.example.demo_producer.domain.CashCard;
import com.example.demo_producer.domain.Transaction;
import com.example.demo_producer.service.DataSourceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.Message;
import org.springframework.test.context.bean.override.mockito.MockitoBean;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;


@SpringBootTest
@Import(TestChannelBinderConfiguration.class)
class CashCardApplicationTests {

  // Autowire a mock bean for the DataSourceService
  @MockitoBean
  private DataSourceService dataSourceService;

  @Test
  void basicCashCardSupplier1(@Autowired OutputDestination outputDestination) throws IOException {
    // Configure the mocked DataSourceService
    Transaction testTransaction = new Transaction(1L, new CashCard(123L, "sarah1", 1.00));
    given(dataSourceService.getData()).willReturn(testTransaction);
    // invoke the outputDestination and make sure it returned something
    Message<byte[]> result = outputDestination.receive(5000, "approvalRequest-out-0");
    assertThat(result).isNotNull();

    // Deserialize the transaction and inspect it
    ObjectMapper objectMapper = new ObjectMapper();
    Transaction transaction = objectMapper.readValue(result.getPayload(), Transaction.class);

    assertThat(transaction.id()).isEqualTo(1L);
    assertThat(transaction.cashCard()).isEqualTo(testTransaction.cashCard());
  }
}