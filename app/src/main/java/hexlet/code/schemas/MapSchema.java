package hexlet.code.schemas;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema {

    private final List<Predicate<Map<String, Object>>> predicateMaps = new LinkedList<>();

    public MapSchema() {
        nonRequired = copyValue == null;
    }


    public void required() {

        this.required = true;
        Predicate<Object> predicateRequired = x -> !Objects.equals(x, null);
        predicates.add(predicateRequired);

    }
    public MapSchema sizeof(int number) {

        Predicate<Object> predicateSizeof = x -> ((Map) x).size() == number;
        predicates.add(predicateSizeof);

        return this;
    }
    public void shape(Map<String, BaseSchema> schemasField) {

        required();

        Predicate<Object> predicateShape = x -> ((Map) x).entrySet().stream()
                .allMatch(y -> schemasField.get(((Map.Entry) y).getKey())
                        .isValid(((Map.Entry) y).getValue()));

        predicates.add(predicateShape);
    }

}
