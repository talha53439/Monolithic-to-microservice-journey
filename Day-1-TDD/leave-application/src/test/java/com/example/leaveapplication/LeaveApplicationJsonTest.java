package com.example.leaveapplication;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
@JsonTest
public class LeaveApplicationJsonTest {
    @Autowired
    private JacksonTester<LeaveApplication> json;
    @Autowired
    private JacksonTester<LeaveApplication[]> jsonList;

    private LeaveApplication[] leaveApplications;

    @BeforeEach
    void setUp() {
        leaveApplications = Arrays.array(
                new LeaveApplication(1L,"AL",1L,2),
                new LeaveApplication(2L,"CL",1L,5),
                new LeaveApplication(3L,"ML",1L,7)
        );
    }




    @Test
    void myFirstTest(){
        assertThat(1).isEqualTo(1);
    }
    @Test
    void leaveApplicationSerializationTest() throws IOException {
        LeaveApplication leaveApplication = new LeaveApplication(1L,"AL",1L,2);
        assertThat(json.write(leaveApplication)).isStrictlyEqualToJson("single.json");

        assertThat(json.write(leaveApplication)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(leaveApplication)).extractingJsonPathNumberValue("@.id").isEqualTo(1);

        assertThat(json.write(leaveApplication)).hasJsonPathStringValue("@.leaveType");
        assertThat(json.write(leaveApplication)).extractingJsonPathStringValue("@.leaveType").isEqualTo("AL");
    }
    @Test
    void loanApplicationDeserializationTest() throws IOException {
        String expected = """
                {
                  "id": 1,
                  "leaveType": "AL",
                  "empId": 1,
                  "leaveDays": 2
                }
           """;
        assertThat(json.parse(expected))
                .isEqualTo(new LeaveApplication(1L,"AL",1L,2));
        assertThat(json.parseObject(expected).getId()).isEqualTo(1);
        assertThat(json.parseObject(expected).getLeaveType()).isEqualTo("AL");
    }
    @Test
    void leaveListSerializationTest() throws IOException {
        assertThat(jsonList.write(leaveApplications)).isStrictlyEqualToJson("list.json");
    }
    @Test
    void cashCardListDeserializationTest() throws IOException {
        String expected="""
                [
                    {"id": 1,"leaveType": "AL","empId": 1,"leaveDays": 2},
                  {"id": 2,"leaveType": "CL","empId": 1,"leaveDays": 5},
                   {"id": 3,"leaveType": "ML","empId": 1,"leaveDays": 7}
                  ]
         """;
        assertThat(jsonList.parse(expected)).isEqualTo(leaveApplications);
    }
}
