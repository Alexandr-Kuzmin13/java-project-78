package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema {

    public MapSchema required() {

        this.required = true;
        Predicate<Object> predicateRequired = x -> x instanceof Map;
        predicates.add(predicateRequired);

        return this;
    }
    public MapSchema sizeof(int number) {

        Predicate<Object> predicateSizeof = x -> ((Map) x).size() == number;
        predicates.add(predicateSizeof);

        return this;
    }
    public MapSchema shape(Map<String, BaseSchema> schemasField) {

        required();

        Predicate<Object> predicateShape = x -> ((Map) x).entrySet().stream()
                .allMatch(y -> schemasField.get(((Map.Entry) y).getKey())
                        .isValid(((Map.Entry) y).getValue()));

        predicates.add(predicateShape);

        return this;
    }

    @Override
    public boolean isInvalidData(Object value) {

        return !(value instanceof Map) || ((Map) value).isEmpty();
    }
}
