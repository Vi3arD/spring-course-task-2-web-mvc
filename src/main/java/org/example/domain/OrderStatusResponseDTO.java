package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusResponseDTO {
    private String orderNumber;
    private int amount;
    private int currency;
    private String status;
}
