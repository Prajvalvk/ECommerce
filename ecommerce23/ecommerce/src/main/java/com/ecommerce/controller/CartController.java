package com.ecommerce.controller;

import com.ecommerce.dto.CartRequest;
import com.ecommerce.entity.Cart;
import com.ecommerce.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService service;

    @PostMapping("/add")
    public ResponseEntity<Cart> add(@Valid @RequestBody CartRequest r) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.add(r.userId(), r.productId(), r.quantity()));
    }
    @PutMapping("/update")
    public ResponseEntity<Cart> update(@Valid @RequestBody CartRequest r) {
        return ResponseEntity.ok(service.update(r.userId(), r.productId(), r.quantity()));
    }
    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<Cart> remove(@PathVariable Long productId, @RequestParam Long userId) {
        return ResponseEntity.ok(service.remove(userId, productId));
    }
    @GetMapping("/{userId}")
    public ResponseEntity<Cart> get(@PathVariable Long userId) { return ResponseEntity.ok(service.get(userId)); }
}
