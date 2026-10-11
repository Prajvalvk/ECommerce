package com.ecommerce.controller;

import com.ecommerce.entity.TrackingDetail;
import com.ecommerce.service.TrackingDetailService;
import jakarta.validation.Valid;
import java.util.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tracking")
@RequiredArgsConstructor
public class TrackingDetailController {
    private final TrackingDetailService service;

    @PostMapping
    public ResponseEntity<TrackingDetail> create(@Valid @RequestBody TrackingDetail body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(body));
    }
    @GetMapping
    public ResponseEntity<List<TrackingDetail>> getAll() { return ResponseEntity.ok(service.all()); }

    /** Tracking history of one order. */
    @GetMapping("/{orderId}")
    public ResponseEntity<List<TrackingDetail>> byOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(service.byOrder(orderId));
    }
    @PutMapping("/{id}")
    public ResponseEntity<TrackingDetail> update(@PathVariable Long id, @Valid @RequestBody TrackingDetail body) {
        return ResponseEntity.ok(service.update(id, body));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(Map.of("message", "Tracking detail " + id + " deleted successfully"));
    }
}
