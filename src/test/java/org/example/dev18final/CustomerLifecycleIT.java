package org.example.dev18final;

import org.assertj.core.api.AssertionsForClassTypes;
import org.example.dev18final.model.dto.request.CustomerCreateRequest;
import org.example.dev18final.model.dto.request.UserLoginRequest;
import org.example.dev18final.model.dto.response.CustomerResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.dev18final.util.TestUtil.createCustomerRequest;

class CustomerLifecycleIT extends BaseIT {

    @Test
    void registerCustomerSuccessfullyTest() {
        String login = "mykola@example.com";
        String password = "password";
        CustomerCreateRequest customerCreateRequest = createCustomerRequest(login, password);
        ResponseEntity<String> signupResponse =
                restTemplate.postForEntity(SERVER_BASE_URL + port + API_BASE_URL + "/signup",
                        customerCreateRequest, String.class);

        assertThat(signupResponse.getStatusCode().value()).isEqualTo(HttpStatus.CREATED.value());

        UserLoginRequest userLoginRequest = new UserLoginRequest(login, password);
        ResponseEntity<String> loginResponse =
                restTemplate.postForEntity(SERVER_BASE_URL + port + API_BASE_URL + "/login",
                        userLoginRequest, String.class);

        assertThat(loginResponse.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
        assertThat(loginResponse.getBody()).isNotNull();

        String jwt = loginResponse.getBody();

        HttpHeaders authHeaders = new HttpHeaders();
        authHeaders.setBearerAuth(jwt);

        HttpEntity<Void> objectHttpEntity = new HttpEntity<>(authHeaders);
        String customerId = "1";
        CustomerResponse customerResponse = restTemplate.exchange(SERVER_BASE_URL + port + API_BASE_URL + "/customers/" + customerId,
                HttpMethod.GET, objectHttpEntity, CustomerResponse.class)
                .getBody();

        assertThat(customerResponse).isNotNull();
        AssertionsForClassTypes.assertThat(customerResponse)
                .usingRecursiveComparison()
                .ignoringFields("email", "dateOfBirth", "loyaltyPoints", "password")
                .isEqualTo(customerCreateRequest);
    }
}
