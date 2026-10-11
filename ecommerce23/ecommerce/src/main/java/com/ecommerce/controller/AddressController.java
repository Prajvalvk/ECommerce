package com.ecommerce.controller;

import com.ecommerce.entity.*;
import com.ecommerce.service.*;
import jakarta.validation.Valid;
import java.util.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService service;

    @PostMapping
    public ResponseEntity<Address> create(@Valid @RequestBody Address body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(body));
    }
    @GetMapping
    public ResponseEntity<List<Address>> getAll() {
        return ResponseEntity.ok(service.all());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getById(@PathVariable Long id) { return ResponseEntity.ok(service.get(id)); }

    @PutMapping("/{id}")
    public ResponseEntity<Address> update(@PathVariable Long id, @Valid @RequestBody Address body) {
        return ResponseEntity.ok(service.update(id, body));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(Map.of("message", "Address " + id + " deleted successfully"));
    }
}
