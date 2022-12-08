package schemas;

import java.util.Objects;
import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema {

    private Predicate<Object> predicatePositive;
    private Predicate<Object> predicateRange;


    public NumberSchema required() {

        this.required = true;
        this.predicateRequired = x -> x instanceof Integer;

        return this;
    }
    public NumberSchema positive() {

        this.required = true;
        this.predicateRequired = x -> x instanceof Integer || Objects.equals(x, null);
        this.predicatePositive = x -> (int) x > 0;
        predicate.add(predicatePositive);

        return this;
    }
    public NumberSchema range(int number1, int number2) {

        this.predicateRange = x -> (int) x >= number1 && (int) x <= number2;
        predicate.add(predicateRange);

        return this;
    }
}
