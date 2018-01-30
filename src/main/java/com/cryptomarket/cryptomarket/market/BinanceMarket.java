package com.cryptomarket.cryptomarket.market;

import com.cryptomarket.cryptomarket.model.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.webcerebrium.binance.api.BinanceApi;
import com.webcerebrium.binance.api.BinanceApiException;
import com.webcerebrium.binance.datatype.BinanceCandlestick;
import com.webcerebrium.binance.datatype.BinanceInterval;
import com.webcerebrium.binance.datatype.BinanceSymbol;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BinanceMarket implements CryptoMarket {
    private BinanceApi binanceApi;

    public BinanceMarket(String apiKey, String secretKey) throws BinanceApiException {
        binanceApi = new BinanceApi(apiKey, secretKey);
    }

    @Override
    public OrderBook getOrderBook(String symbol) throws BinanceApiException {
        BinanceSymbol binanceSymbol = BinanceSymbol.valueOf(symbol);
        JsonObject depth = binanceApi.depth(binanceSymbol);

        JsonArray bids = depth.get("bids").getAsJsonArray();
        ArrayList<Bid> bidList = new ArrayList<>(bids.size());

        JsonArray asks = depth.get("asks").getAsJsonArray();
        ArrayList<Ask> askList = new ArrayList<>(asks.size());

        long lastUpdateId = depth.get("lastUpdateId").getAsLong();

        for (int i=0; i<bids.size(); i++) {
            JsonArray singleBid = bids.get(i).getAsJsonArray();
            double price = singleBid.get(0).getAsDouble();
            double quantity = singleBid.get(1).getAsDouble();
            bidList.add(new Bid(price, quantity));
        }

        for (int i=0; i<asks.size(); i++) {
            JsonArray singleAsk = asks.get(i).getAsJsonArray();
            double price = singleAsk.get(0).getAsDouble();
            double quantity = singleAsk.get(1).getAsDouble();
            askList.add(new Ask(price, quantity));
        }

        OrderBook orderBook = new OrderBook(lastUpdateId, bidList, askList);
        return orderBook;
    }

    @Override
    public List<Candlestick> getKlines(String symbol, Interval interval, int limit) throws BinanceApiException {
        BinanceSymbol binanceSymbol = BinanceSymbol.valueOf(symbol);
        List<BinanceCandlestick> klines = (new BinanceApi()).klines(binanceSymbol, BinanceInterval.valueOf(interval.name()), limit, null);
        return klines.stream().map(kline -> new Candlestick(kline.getOpenTime(), kline.getOpen(), kline.getHigh(),
                kline.getLow(), kline.close, kline.getVolume(), kline.getCloseTime(), kline.getQuoteAssetVolume(),
                kline.getNumberOfTrades(), kline.getTakerBuyBaseAssetVolume(), kline.getTakerBuyQuoteAssetVolume()))
                .collect(Collectors.toList());
    }
}
