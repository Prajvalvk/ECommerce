package com.ecommerce.service;

import com.ecommerce.entity.*;
import com.ecommerce.exception.ApiException;
import com.ecommerce.repository.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor
public class CheckoutService {
    private final CartService carts;
    private final CartRepository cartRepo;
    private final AddressService addresses;
    private final OrderRepository orderRepo;
    private final PaymentRepository paymentRepo;
    private final TrackingDetailRepository trackingRepo;

    @Transactional
    public com.ecommerce.dto.CheckoutResponse checkout(com.ecommerce.dto.CheckoutRequest r) {
        Cart cart = carts.get(r.userId());
        if (cart.getItems().isEmpty()) throw ApiException.badRequest("Cart is empty");
        Address addr = addresses.get(r.addressId());
        if (!addr.getUser().getUserId().equals(r.userId()))
            throw ApiException.badRequest("Address " + r.addressId() + " does not belong to user " + r.userId());

        double total = cart.getItems().stream().mapToDouble(i -> i.getProduct().getPrice() * i.getQuantity()).sum();

        Order o = new Order();
        o.setUser(cart.getUser());
        o.setShippingAddress(addr);
        o.setProducts(cart.getItems().stream().map(CartItem::getProduct).collect(Collectors.toList()));
        o.setOrderAmount(total);
        o.setOrderDate(LocalDate.now());
        o = orderRepo.save(o);

        Payment pay = paymentRepo.save(new Payment(null, r.paymentMethod(), total, cart.getUser()));
        TrackingDetail t = trackingRepo.save(new TrackingDetail(null, "ORDER_PLACED", o));

        cart.getItems().clear();
        cartRepo.save(cart);
        return new com.ecommerce.dto.CheckoutResponse(o, pay, t);
    }
}
