package hexlet.code.schemas;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema {

    protected boolean required;
    protected Predicate<Object> predicateRequired;
    protected List<Predicate<Object>> predicates = new LinkedList<>();


    public final boolean isValid(Object value) {

        if (!required && value == null) {
            return true;
        }

        for (var predicate : predicates) {

            if (!predicate.test(value)) {
                return false;
            }
        }
        return true;
    }

}
