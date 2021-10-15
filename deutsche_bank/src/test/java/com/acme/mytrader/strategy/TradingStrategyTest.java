package com.acme.mytrader.strategy;

import com.acme.mytrader.execution.ExecutionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.collections.Sets;

import static org.mockito.Mockito.*;

public class TradingStrategyTest {

    ExecutionService executionService = mock(ExecutionService.class);
    String security = "IBM";
    int volume = 13;
    int volume2 = 600;
    TradingStrategy tradingStrategy;

    @Before
    public void setUp() {
        Trigger trigger1 = new Trigger(Trigger.Condition.LESS, 50);
        Order order1 = new Order(security, trigger1, Order.Instruction.BUY, volume);
        Order order2 = new Order(security, new Trigger(Trigger.Condition.LESS, 40), Order.Instruction.SELL, volume2);
        Order order3 = new Order("Xerox", trigger1, Order.Instruction.SELL, 5);
        tradingStrategy = new TradingStrategy(executionService, Sets.newSet(order1, order2, order3));
    }

    @Test
    public void testDoesntExecuteOrderOnMismatch() {
        tradingStrategy.priceUpdate(security, 60);
        verify(executionService, never()).buy(anyString(), anyDouble(), anyInt());
        verify(executionService, never()).sell(anyString(), anyDouble(), anyInt());
    }

    @Test
    public void testExecutesOrderOnMatch() {
        int price = 47;
        tradingStrategy.priceUpdate(security, price);
        verify(executionService).buy(security, price, volume);
        verify(executionService, never()).sell(anyString(), anyDouble(), anyInt());
    }

    @Test
    public void testExecutesMultipleOrdersInSingleUpdate() {
        int price = 34;
        tradingStrategy.priceUpdate(security, price);
        verify(executionService).buy(security, price, volume);
        verify(executionService).sell(security, price, volume2);
    }

    @Test
    public void testExecutesOrderOnlyOnce() {
        int price = 47;
        tradingStrategy.priceUpdate(security, price);
        verify(executionService).buy(security, price, volume);
        verify(executionService, never()).sell(anyString(), anyDouble(), anyInt());
        reset(executionService);

        tradingStrategy.priceUpdate(security, 43);

        verify(executionService, never()).buy(anyString(), anyDouble(), anyInt());
        verify(executionService, never()).sell(anyString(), anyDouble(), anyInt());
    }
}
