package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema {

    protected boolean required;
    protected Predicate<Object> predicateRequired;
    protected List<Predicate<Object>> predicates = new ArrayList<>();


    public final boolean isValid(Object value) {

        if (!required && isInvalidData(value)) {
            return true;
        }

        for (var predicate : predicates) {

            if (!predicate.test(value)) {
                return false;
            }
        }
        return true;
    }
    abstract boolean isInvalidData(Object value);

}
