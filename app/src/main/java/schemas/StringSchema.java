package schemas;

import java.util.Objects;
import java.util.function.Predicate;

public final class StringSchema extends BaseSchema {

    private Predicate<Object> predicateMinLength;
    private Predicate<Object> predicateContain;


    public StringSchema required() {

        this.required = true;
        this.predicateRequired = x -> !Objects.equals(x, null) && x instanceof String && !x.equals("");

        return this;
    }
    public StringSchema minLength(int number) {

        this.predicateMinLength = x -> ((String) x).length() >= number;
        predicate.add(predicateMinLength);

        return this;
    }
    public StringSchema contains(String subString) {

        this.predicateContain = x -> ((String) x).contains(subString);
        predicate.add(predicateContain);

        return this;
    }

}
