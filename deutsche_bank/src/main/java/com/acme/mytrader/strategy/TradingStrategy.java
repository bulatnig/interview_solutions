package com.acme.mytrader.strategy;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.price.PriceListener;

import java.util.*;

/**
 * <pre>
 * User Story: As a trader I want to be able to monitor stock prices such
 * that when they breach a trigger level orders can be executed automatically
 * </pre>
 */
public class TradingStrategy implements PriceListener {

    private final ExecutionService executionService;
    /**
     * I imagine that number of different securities is very big.
     * And we want very quickly locate orders related to securities changed.
     */
    private final Map<String, Set<Order>> securityToOrders = new HashMap<>();

    public TradingStrategy(ExecutionService executionService, Set<Order> orders) {
        this.executionService = executionService;
        for (Order order : orders) {
            Set<Order> orderSet = securityToOrders.computeIfAbsent(order.getSecurity(), k -> new HashSet<>());
            orderSet.add(order);
        }
    }

    @Override
    public synchronized void priceUpdate(String security, double price) {
        Set<Order> orders = securityToOrders.get(security);
        if (orders == null || orders.isEmpty()) {
            return;
        }
        for (Iterator<Order> it = orders.iterator(); it.hasNext(); ) {
            Order order = it.next();
            if (order.getTrigger().evaluate(price)) {
                order.execute(executionService, price);
                it.remove();
            }
        }
    }
}
