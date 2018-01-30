package com.cryptomarket.cryptomarket.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ask {
    private double price;
    private double quantity;
}
