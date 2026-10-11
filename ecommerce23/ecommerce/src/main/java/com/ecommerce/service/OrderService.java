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
public class OrderService {
    private final OrderRepository repo;
    private final TrackingDetailRepository trackingRepo;
    private final UserService users;
    private final ProductService products;
    private final AddressService addresses;

    static double total(List<Product> list) { return list.stream().mapToDouble(Product::getPrice).sum(); }

    @Transactional
    public Order create(Order in) {
        Order o = new Order();
        o.setUser(users.resolve(in.getUser()));
        o.setProducts(products.resolveAll(in.getProducts()));
        if (in.getShippingAddress() != null) o.setShippingAddress(addresses.get(in.getShippingAddress().getAddressId()));
        o.setOrderAmount(total(o.getProducts()));
        o.setOrderDate(LocalDate.now());
        o = repo.save(o);
        trackingRepo.save(new TrackingDetail(null, "ORDER_PLACED", o));
        return o;
    }
    public List<Order> all() { return repo.findAll(); }
    public Order get(Long id) { return repo.findById(id).orElseThrow(() -> ApiException.notFound("Order not found: " + id)); }

    @Transactional
    public Order update(Long id, Order in) {
        Order o = get(id);
        if (in.getUser() != null) o.setUser(users.resolve(in.getUser()));
        if (in.getProducts() != null && !in.getProducts().isEmpty()) {
            o.setProducts(products.resolveAll(in.getProducts()));
            o.setOrderAmount(total(o.getProducts()));
        }
        if (in.getShippingAddress() != null) o.setShippingAddress(addresses.get(in.getShippingAddress().getAddressId()));
        return repo.save(o);
    }
    @Transactional
    public void delete(Long id) {
        Order o = get(id);
        trackingRepo.deleteByOrderOrderNo(id);
        repo.delete(o);
    }
}
