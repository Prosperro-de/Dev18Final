package org.example.dev18final.util;

import lombok.experimental.UtilityClass;
import org.example.dev18final.model.dto.request.CustomerCreateRequest;
import org.testcontainers.shaded.org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;

@UtilityClass
public class TestUtil {
    public static CustomerCreateRequest createCustomerRequest(String login, String password) {
        return CustomerCreateRequest.builder()
                .firstName(RandomStringUtils.randomAlphabetic(10))
                .lastName(RandomStringUtils.randomAlphabetic(10))
                .email(login)
                .password(password)
                .dateOfBirth(LocalDate.of(1814, 3, 9))
                .telNumber(RandomStringUtils.randomAlphabetic(10))
                .build();
    }
}
