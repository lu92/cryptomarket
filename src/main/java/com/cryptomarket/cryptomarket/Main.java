package com.cryptomarket.cryptomarket;

import com.cryptomarket.cryptomarket.market.BinanceMarket;
import com.cryptomarket.cryptomarket.model.Interval;
import com.cryptomarket.cryptomarket.model.OrderBook;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.webcerebrium.binance.api.BinanceApi;
import com.webcerebrium.binance.api.BinanceApiException;
import com.webcerebrium.binance.datatype.*;

import java.math.BigDecimal;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world");

        try {
            BinanceApi api = new BinanceApi("yonho6quclJOAOgXRRm48IfR5658xzo0tD1fFKvr9dMHTQbhgyaCKYZsMUce2e0M", "RihxeBKuF0e8hPOQf2l2spkU4hmjbkhlmD7dKyVKyLbSpm7ShfhrxGHLXDiU3PHt");
//            BigDecimal ethbtc = api.pricesMap().get("ETHBTC");
//            System.out.println("ETH-BTC PRICE=" + ethbtc);
//
//            System.out.println(api.ping());
//            JsonArray balances = api.balances();
//            System.out.println(balances);
//
//
//            List<BinanceOrder> eth = api.openOrders(BinanceSymbol.BTC("ETH"));
//            eth.stream().forEach(System.out::println);
//            List<BinanceOrder> binanceOrders = api.allOrders(new BinanceSymbol("BQXBTC"));
//            binanceOrders.forEach(System.out::println);
//
//            BinanceSymbol symbol = BinanceSymbol.valueOf("ETHBTC");
//            JsonObject depth = (api).depth(symbol);
//            System.out.println("BIDS=" + depth.get("bids").getAsJsonArray());
//            System.out.println("ASKS=" + depth.get("asks").getAsJsonArray());
//
////            BinanceSymbol symbol = new BinanceSymbol("ETHBTC");
////            BinanceOrderPlacement placement = new BinanceOrderPlacement(symbol, BinanceOrderSide.BUY);
////            placement.setType(BinanceOrderType.MARKET);
////            placement.setPrice(BigDecimal.valueOf(0.00001));
////            placement.setQuantity(BigDecimal.valueOf(10000)); // buy 10000 of asset for 0.00001 BTC
//////            BinanceOrder order = api.getOrder(symbol, api.createOrder(placement).get("orderId").getAsLong());
////            System.out.println(order.toString());
//
            long beginTime = System.currentTimeMillis();
            BinanceMarket binanceMarket = new BinanceMarket("yonho6quclJOAOgXRRm48IfR5658xzo0tD1fFKvr9dMHTQbhgyaCKYZsMUce2e0M", "RihxeBKuF0e8hPOQf2l2spkU4hmjbkhlmD7dKyVKyLbSpm7ShfhrxGHLXDiU3PHt");
            OrderBook ethbtcOrderBook = binanceMarket.getOrderBook("ETHBTC");
            System.out.println("time = " + (System.currentTimeMillis() - beginTime));
//            System.out.println(ethbtcOrderBook);

            beginTime = System.currentTimeMillis();
            binanceMarket.getKlines("ETHBTC", Interval.ONE_MIN, 1000);
            System.out.println("time = " + (System.currentTimeMillis() - beginTime));
        } catch (BinanceApiException e) {
            e.printStackTrace();
        }
    }
}
