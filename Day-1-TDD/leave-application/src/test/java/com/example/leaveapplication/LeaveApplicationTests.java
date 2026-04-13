package com.example.leaveapplication;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LeaveApplicationTests {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
    }
    @Test
    void shouldReturnALeaveWhenDataIsSaved() {
        ResponseEntity<String> response = restTemplate.getForEntity("/leaves/100", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        DocumentContext documentContext = JsonPath.parse(response.getBody());

        Number id = documentContext.read("$.id");
        assertThat(id).isEqualTo(100);

        String leaveType = documentContext.read("$.leaveType");
        assertThat(leaveType).isEqualTo("ML");
    }
    @Test
    void shouldNotReturnALeavedWithAnUnknownId() {
        ResponseEntity<String> response = restTemplate.getForEntity("/leaves/1000", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isBlank();
    }
  /*  @Test
    void shouldCreateANewCashCard() {
        CashCard newCashCard = new CashCard(null, 250.00);
        ResponseEntity<Void> createResponse = restTemplate.postForEntity("/cashcards", newCashCard, Void.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        URI locationOfNewCashCard = createResponse.getHeaders().getLocation();
        ResponseEntity<String> getResponse = restTemplate.getForEntity(locationOfNewCashCard, String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }*/


    @Test
    void shouldReturnAllCashCardsWhenListIsRequested() {
        ResponseEntity<String> response = restTemplate.getForEntity("/leaves", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int cashCardCount = documentContext.read("$.length()");
        assertThat(cashCardCount).isEqualTo(3);

        JSONArray ids = documentContext.read("$..id");
        assertThat(ids).containsExactlyInAnyOrder(99, 100, 101);

        JSONArray amounts = documentContext.read("$..leaveDays");
        assertThat(amounts).containsExactlyInAnyOrder(2,5,7);
    }
    @Test
    @DirtiesContext
    void shouldCreateANewLeave() {
        LeaveApplication leaveApplication = new LeaveApplication(null,"AL",2L,5);
        ResponseEntity<Void> response = restTemplate.postForEntity("/leaves", leaveApplication, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        URI locationOfNewLeave = response.getHeaders().getLocation();
        assertThat(locationOfNewLeave).isNotNull();
        ResponseEntity<String> getResponse = restTemplate.getForEntity(locationOfNewLeave, String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
        Number id = documentContext.read("$.id");
        Integer amount = documentContext.read("$.leaveDays");

        assertThat(id).isNotNull();
        assertThat(amount).isEqualTo(5);
    }
    @Test
    void shouldReturnAPageOfLeaves() {
        ResponseEntity<String> response = restTemplate.getForEntity("/leaves?page=0&size=1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        JSONArray page = documentContext.read("$[*]");
        assertThat(page.size()).isEqualTo(1);
    }
    @Test
    void shouldReturnASortedPageOfLeaves() {
        ResponseEntity<String> response = restTemplate.getForEntity("/leaves?page=0&size=1&sort=leaveDays,desc", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        JSONArray read = documentContext.read("$[*]");
        assertThat(read.size()).isEqualTo(1);

        int leaveDays = documentContext.read("$[0].leaveDays");
        assertThat(leaveDays).isEqualTo(7);
    }

    @Test
    void shouldReturnASortedPageOfLeavesWithNoParametersAndUseDefaultValues() {
        ResponseEntity<String> response = restTemplate.getForEntity("/leaves", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        JSONArray page = documentContext.read("$[*]");
        assertThat(page.size()).isEqualTo(3);

        JSONArray amounts = documentContext.read("$..leaveDays");
        assertThat(amounts).containsExactly(2,5,7);
    }
}
