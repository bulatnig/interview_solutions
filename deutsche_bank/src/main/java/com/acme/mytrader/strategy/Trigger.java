package com.acme.mytrader.strategy;

/**
 * Trigger is a condition of order execution.
 * Basic implementation below is bound to specific security.
 * We might want to build more complex conditions involving:
 *  - logical operations as AND, OR, NOR
 *  - more than one or two conditions as part of trigger
 *  - triggers based on time or other data
 * Potentially we might be looking into having rule engine.
 * We can take and adopt existing rule engine or build our own from scratch.
 * We should also consider building domain specific DSL
 * I have I pet project of Scala based DSL for Transaction Monitoring
 * Tests provide usage examples
 * https://github.com/bulatnig/rule-dsl/blob/master/v2/src/test/scala/RuleEvaluatorTest.scala
 */
public class Trigger {

    private final Condition condition;
    private final double price;

    public Trigger(Condition condition, double price) {
        this.condition = condition;
        this.price = price;
    }

    public boolean evaluate(double currentPrice) {
        switch (condition) {
            case LESS: return currentPrice < price;
            case LESS_OR_EQUAL: return currentPrice <= price;
            case EQUAL: return currentPrice == price;
            case MORE_OR_EQUAL: return currentPrice >= price;
            case MORE: return currentPrice > price;
            default: throw new IllegalArgumentException("Unsupported condition type " + condition);
        }
    }

    public Condition getCondition() {
        return condition;
    }

    public double getPrice() {
        return price;
    }

    enum Condition {
        LESS,
        LESS_OR_EQUAL,
        EQUAL,
        MORE_OR_EQUAL,
        MORE
    }
}
