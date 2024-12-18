package org.example.dev18final.repository.projection;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerProjection {
    private String firstName;
    private String lastName;
}
