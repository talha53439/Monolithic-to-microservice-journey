package com.example.leaveapplication;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LeaveRepository extends CrudRepository<LeaveApplication,Long>, PagingAndSortingRepository<LeaveApplication,Long> {
}
