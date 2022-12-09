package schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public abstract class BaseSchema {

    protected boolean required;
    protected Predicate<Object> predicateRequired;
    protected List<Predicate<Object>> predicate = new ArrayList<>();



    public final boolean isValid(Object value) {

        if (required) {

            if (!predicateRequired.test(value)) {
                return false;
            }
            if (Objects.equals(value, null)) {
                return true;
            }

            for (var l : predicate) {
                if (!l.test(value)) {
                    return false;
                }
            }
        }
        return true;
    }
}
