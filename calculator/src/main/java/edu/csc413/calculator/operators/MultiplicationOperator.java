package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

public class MultiplicationOperator extends Operator {
    @Override
    public int priority() {
        return 3;
    } // end priority

    @Override
    public Operand execute(Operand operandOne, Operand operandTwo) {
        return new Operand(operandOne.getValue() * operandTwo.getValue());
    }
}