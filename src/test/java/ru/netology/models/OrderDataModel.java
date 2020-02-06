package ru.netology.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDataModel {
    private String id;
    private String created;
    private String credit_id;
    private String payment_id;
}
