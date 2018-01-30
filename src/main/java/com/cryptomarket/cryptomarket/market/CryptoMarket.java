package com.cryptomarket.cryptomarket.market;

import com.cryptomarket.cryptomarket.model.Candlestick;
import com.cryptomarket.cryptomarket.model.Interval;
import com.cryptomarket.cryptomarket.model.OrderBook;
import com.webcerebrium.binance.api.BinanceApiException;

import java.util.List;

public interface CryptoMarket {
    OrderBook getOrderBook(String symbol) throws BinanceApiException;
    List<Candlestick> getKlines(String symbol, Interval interval, int limit) throws BinanceApiException;
}
