package hexlet.code.schemas;

import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema {

    public NumberSchema required() {

        this.required = true;
        this.predicateRequired = x -> x instanceof Integer;
        this.predicates.add(predicateRequired);

        return this;
    }
    public NumberSchema positive() {

        Predicate<Object> predicatePositive = x -> x instanceof Integer && (int) x > 0;
        this.predicates.add(predicatePositive);

        return this;
    }
    public NumberSchema range(int number1, int number2) {

        Predicate<Object> predicateRange = x -> (int) x >= number1 && (int) x <= number2;
        predicates.add(predicateRange);

        return this;
    }
}
