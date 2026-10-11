package com.ecommerce.dto;

import jakarta.validation.constraints.*;

public record CheckoutRequest(
        @NotNull(message = "userId is required") Long userId,
        @NotNull(message = "addressId is required") Long addressId,
        @NotBlank(message = "paymentMethod is required") String paymentMethod) {}
