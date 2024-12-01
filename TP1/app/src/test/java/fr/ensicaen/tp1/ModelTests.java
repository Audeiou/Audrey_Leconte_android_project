package fr.ensicaen.tp1;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.ValidationResult;
import net.objecthunter.exp4j.function.Function;

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

    @Test
    public void should_return_the_sum_when_adding_two_variables() {
        Expression expression = new ExpressionBuilder("3 * x + y").variables("x","y").build().setVariable("x", 2).setVariable("y",5);
        assertEquals(11.0, expression.evaluate(), 0.001);
    }

    @Test
    public void should_return_the_correct_result_when_using_mathematical_functions() {
        Expression expression = new ExpressionBuilder("cos(0) * 5 + log(1)").build();
        assertEquals(5.0, expression.evaluate(), 0.001);
    }

    @Test
    public void should_return_the_correct_result_when_using_implicit_multiplication() {
        Expression expression = new ExpressionBuilder("25cos(0)").build();
        assertEquals(25.0, expression.evaluate(), 0.001);
    }

    @Test
    public void should_return_the_correct_result_when_using_numerical_constants() {
        Expression expression = new ExpressionBuilder("pi+e").build();
        assertEquals(Math.PI + Math.E, expression.evaluate(), 0.001);
    }

    @Test
    public void should_return_the_correct_result_when_using_custom_functions() {

        Function multiply = new Function("multiply", 3) {
            @Override
            public double apply(double... args) {
                return args[0]*args[1]*args[2];
            }
        };
        Expression expression = new ExpressionBuilder("multiply(2,6,9)").function(multiply).build();
        assertEquals(108.0, expression.evaluate(), 0.001);
    }

    @Test
    public void should_throw_an_ArithmeticException_when_dividing_by_zero() {
        Expression expression = new ExpressionBuilder("5/0").build();
        assertThrows(ArithmeticException.class, expression::evaluate);
    }

    @Test
    public void should_invalidate_the_expression_when_using_the_function_validate() {
        Expression expression = new ExpressionBuilder("x+y").variables("x","y").build();
        ValidationResult result = expression.validate();
        assertFalse(result.isValid());
    }


}