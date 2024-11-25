package fr.ensicaen.tp1.model;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class CalculatorModel {

    public double calculateResult(String formula) {
        Expression expression = new ExpressionBuilder(formula).build();
        return expression.evaluate();
    }
}
