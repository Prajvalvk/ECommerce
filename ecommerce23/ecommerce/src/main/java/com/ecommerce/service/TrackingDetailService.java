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
public class TrackingDetailService {
    private final TrackingDetailRepository repo;
    private final OrderService orders;

    @Transactional
    public TrackingDetail create(TrackingDetail t) {
        if (t.getOrder() == null || t.getOrder().getOrderNo() == null) throw ApiException.badRequest("order.orderNo is required");
        t.setTrackingId(null);
        t.setOrder(orders.get(t.getOrder().getOrderNo()));
        return repo.save(t);
    }
    public List<TrackingDetail> all() { return repo.findAll(); }
    public List<TrackingDetail> byOrder(Long orderNo) { orders.get(orderNo); return repo.findByOrderOrderNo(orderNo); }
    public TrackingDetail get(Long id) { return repo.findById(id).orElseThrow(() -> ApiException.notFound("Tracking detail not found: " + id)); }

    @Transactional
    public TrackingDetail update(Long id, TrackingDetail in) {
        TrackingDetail t = get(id);
        t.setStatus(in.getStatus());
        if (in.getOrder() != null && in.getOrder().getOrderNo() != null) t.setOrder(orders.get(in.getOrder().getOrderNo()));
        return repo.save(t);
    }
    public void delete(Long id) { repo.delete(get(id)); }
}
