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
public class AddressService {
    private final AddressRepository repo;
    private final UserService users;

    @Transactional
    public Address create(Address a) {
        a.setAddressId(null);
        a.setUser(users.resolve(a.getUser()));
        return repo.save(a);
    }
    public List<Address> all() { return repo.findAll(); }
    public Address get(Long id) { return repo.findById(id).orElseThrow(() -> ApiException.notFound("Address not found: " + id)); }

    @Transactional
    public Address update(Long id, Address in) {
        Address a = get(id);
        a.setCity(in.getCity()); a.setState(in.getState()); a.setCountry(in.getCountry());
        if (in.getUser() != null) a.setUser(users.resolve(in.getUser()));
        return repo.save(a);
    }
    public void delete(Long id) { repo.delete(get(id)); }
}
