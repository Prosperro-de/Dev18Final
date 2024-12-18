package org.example.dev18final.model.dto;

public record ErrorResponse(String status, Integer errorCode, String message) {
}
