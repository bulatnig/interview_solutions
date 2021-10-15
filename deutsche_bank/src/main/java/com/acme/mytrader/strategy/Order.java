package com.acme.mytrader.strategy;

import com.acme.mytrader.execution.ExecutionService;

/**
 * In basic implementation of order we do only one operation.
 * We might want to have orders consisting of many operations
 * or even creating new orders.
 * Or instead make triggers reusable for multiple orders.
 */
public class Order {

    private final String security;
    private final Trigger trigger;
    private final Instruction instruction;
    private final int volume;

    public Order(String security, Trigger trigger, Instruction instruction, int volume) {
        this.security = security;
        this.trigger = trigger;
        this.instruction = instruction;
        this.volume = volume;
    }

    public void execute(ExecutionService executionService, double price) {
        switch (instruction) {
            case BUY:
                executionService.buy(security, price, volume);
                return;
            case SELL:
                executionService.sell(security, price, volume);
                return;
            default:
                throw new IllegalArgumentException("Unsupported instruction type " + instruction);
        }
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public Instruction getType() {
        return instruction;
    }

    public String getSecurity() {
        return security;
    }

    public int getVolume() {
        return volume;
    }

    enum Instruction {
        BUY,
        SELL
    }
}
