package fr.ensicaen.tp1;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.junit.Test;

import fr.ensicaen.tp1.model.CalculatorModel;
import static org.junit.Assert.*;

public class ModelTests {

    @Test
    public void should_return_the_sum_when_using_model_calculator() {
        CalculatorModel calculatorModel = new CalculatorModel();
        assertEquals(1050.0, calculatorModel.calculateResult("1025 + 25"), 0.001);
    }

    @Test
    public void should_return_the_sum_when_adding_two_numbers() {
        Expression expression = new ExpressionBuilder("53+64").build();
        assertEquals(117.0, expression.evaluate(), 0.001);
    }

    @Test
    public void should_return_the_subtraction_when_subtracting_two_numbers() {
        Expression expression = new ExpressionBuilder("53-21").build();
        assertEquals(32.0, expression.evaluate(), 0.001);
    }

    @Test
    public void should_return_the_multiplication_when_multiplying_two_numbers() {
        Expression expression = new ExpressionBuilder("53*21").build();
        assertEquals(1113.0, expression.evaluate(), 0.001);
    }

    @Test
    public void should_return_the_division_when_dividing_two_numbers() {
        Expression expression = new ExpressionBuilder("100/5").build();
        assertEquals(20.0, expression.evaluate(), 0.001);
    }

    @Test
    public void should_return_the_modulo_when_using_modulo_operator() {
        Expression expression = new ExpressionBuilder("143%5").build();
        assertEquals(3.0, expression.evaluate(), 0.001);
    }

}