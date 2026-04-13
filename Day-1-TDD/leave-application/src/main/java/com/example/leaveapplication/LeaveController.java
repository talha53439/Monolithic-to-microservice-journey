package com.example.leaveapplication;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/leaves")
public class LeaveController {

    private final LeaveRepository leaveRepository;


    @GetMapping("/{requestedId}")
    private ResponseEntity<LeaveApplication> findById(@PathVariable Long requestedId) {
        Optional<LeaveApplication> leave = leaveRepository.findById(requestedId);
        if(leave.isPresent()) {
            return ResponseEntity.ok(leave.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    private ResponseEntity<Void> createCashCard(@RequestBody LeaveApplication newCashCardRequest, UriComponentsBuilder ucb) {
        LeaveApplication savedCashCard = leaveRepository.save(newCashCardRequest);
        URI locationOfNewCashCard = ucb
                .path("leaves/{id}")
                .buildAndExpand(savedCashCard.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewCashCard).build();
    }
    @GetMapping
    private ResponseEntity<Iterable<LeaveApplication>> findAll(Pageable pageable) {
        Page<LeaveApplication> page=leaveRepository.findAll(
                PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        pageable.getSortOr(Sort.by(Sort.Direction.ASC,"leaveType"))
                )
        );
        return ResponseEntity.ok(page.getContent());
    }

}
