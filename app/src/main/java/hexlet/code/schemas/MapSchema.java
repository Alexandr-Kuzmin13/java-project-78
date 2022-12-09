package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema {

    private boolean shape;
    private Predicate<Object> predicateSizeof;
    private Map<String, BaseSchema> schemas;



    public boolean isValid(Map<String, Object> humans) {

        if (shape) {
            for (var human: humans.keySet()) {

                if (!schemas.get(human).isValid(humans.get(human))) {
                    return false;
                }
            }
        }
        return super.isValid(humans);
    }
    public void required() {

        this.required = true;
        this.predicateRequired = x -> !Objects.equals(x, null) && x instanceof Map;

    }
    public MapSchema sizeof(int number) {

        this.predicateSizeof = x -> ((Map) x).size() == number;
        predicate.add(predicateSizeof);

        return this;
    }
    public void shape(Map<String, BaseSchema> schemasField) {

        this.shape = true;
        this.schemas = schemasField;
    }

}
