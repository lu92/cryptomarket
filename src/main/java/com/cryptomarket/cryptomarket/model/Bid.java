package com.cryptomarket.cryptomarket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Bid {
    private double price;
    private double quantity;
}
