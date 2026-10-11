package com.ecommerce.dto;

import com.ecommerce.entity.*;

public record CheckoutResponse(Order order, Payment payment, TrackingDetail tracking) {}
