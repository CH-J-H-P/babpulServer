package com.example.babpulServer.DTO;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AllReceiptDTO {
    private String orderNumber;
    private LocalDate orderDate;
    private int totalAmount;
}
