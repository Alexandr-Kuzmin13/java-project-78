package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema {

    public NumberSchema() {
        nonRequired = copyValue == null;
    }


    public NumberSchema required() {

        this.required = true;
        this.predicateRequired = x -> x instanceof Integer;
        this.predicates.add(predicateRequired);

        return this;
    }
    public NumberSchema positive() {

        this.required = true;
        this.predicateRequired = x -> Objects.equals(x, null) || x instanceof Integer;
        this.predicates.add(predicateRequired);

        Predicate<Object> predicatePositive = x -> {
            if (Objects.equals(x, null)) {
                return true;
            } else {
                return  (int) x > 0;
            }
        };
        this.predicates.add(predicatePositive);

        return this;
    }
    public NumberSchema range(int number1, int number2) {

        Predicate<Object> predicateRange = x -> (int) x >= number1 && (int) x <= number2;
        predicates.add(predicateRange);

        return this;
    }
}
