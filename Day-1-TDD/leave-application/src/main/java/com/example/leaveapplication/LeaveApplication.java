package com.example.leaveapplication;

import lombok.*;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class LeaveApplication {
    @Id
    private Long id;
    private String leaveType;
    private Long empId;
    private Integer leaveDays;
}
