package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema {

    public StringSchema() {
        nonRequired = copyValue == null || copyValue.equals("");
    }


    public StringSchema required() {

        this.required = true;
        this.predicateRequired = x -> x instanceof String && !x.equals("");
        this.predicates.add(predicateRequired);

        return this;
    }
    public StringSchema minLength(int number) {

        Predicate<Object> predicateMinLength = x -> ((String) x).length() >= number;
        this.predicates.add(predicateMinLength);

        return this;
    }
    public StringSchema contains(String subString) {

        Predicate<Object> predicateContain = x -> ((String) x).contains(subString);
        this.predicates.add(predicateContain);

        return this;
    }

}
