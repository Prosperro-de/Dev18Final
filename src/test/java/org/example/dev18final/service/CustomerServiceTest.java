package org.example.dev18final.service;

import org.example.dev18final.mapper.CustomerMapperImpl;
import org.example.dev18final.model.Customer;
import org.example.dev18final.model.dto.request.CustomerUpdateRequest;
import org.example.dev18final.model.dto.response.CustomerResponse;
import org.example.dev18final.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
   @Mock
   private CustomerRepository customerRepository;
   @Spy
   private CustomerMapperImpl customerMapper;

   @InjectMocks
   private CustomerService customerService;

   @Test
   void testCustomerUpdatedSuccessfully() {
      //given
      Long customerId = 1L;
      CustomerUpdateRequest customerUpdateRequest = CustomerUpdateRequest.builder()
              .firstName("Taras")
              .lastName("Shevchenko")
              .telNumber("555-555")
              .postCode("34567")
              .build();

      Customer customer = Customer.builder()
              .firstName("Mykola")
              .lastName("Shevchenko")
              .telNumber("555-555")
              .postCode("34567")
              .build();

      when(customerRepository.findForUpdateById(customerId)).thenReturn(Optional.of(customer));

      //when
      CustomerResponse customerResponse = customerService.updateCustomer(customerId, customerUpdateRequest);
      //then

      //jUnit5
//      assertNotNull(customerResponse);
//      Assertions.assertEquals(customerUpdateRequest, customerResponse);

//      assertEquals(customerUpdateRequest.firstName(), customerResponse.firstName());
//      assertEquals(customerUpdateRequest.lastName(), customerResponse.lastName());
//      assertEquals(customerUpdateRequest.telNumber(), customerResponse.telNumber());
//      assertEquals(customerUpdateRequest.postCode(), customerResponse.postCode());

      assertThat(customerResponse).isNotNull();
      assertThat(customerResponse)
              .usingRecursiveComparison()
              .ignoringFields("email")
              .isEqualTo(customerUpdateRequest);

      verify(customerRepository).findForUpdateById(customerId);
      verify(customerMapper).toCustomerResponse(customer);
   }
}