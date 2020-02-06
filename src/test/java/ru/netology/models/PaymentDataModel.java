package ru.netology.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDataModel {
    private String id;
    private int amount;
    private String created;
    private String status;
    private String transaction_id;
}

