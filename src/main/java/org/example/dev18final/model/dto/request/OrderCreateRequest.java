package org.example.dev18final.model.dto.request;

public record OrderCreateRequest(
        String status,
        String totalPrice,
        Long customerId
) {
}
