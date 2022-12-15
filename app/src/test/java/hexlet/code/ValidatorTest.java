package hexlet.code;

import org.junit.jupiter.api.Test;
import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorTest {

    private static final int CHECKS_NUMBER_0 = 0;
    private static final int CHECKS_NUMBER_4 = 4;
    private static final int CHECKS_NUMBER_5 = 5;
    private static final int CHECKS_NUMBER_MINUS_5 = -5;
    private static final int CHECKS_NUMBER_7 = 7;
    private static final int CHECKS_NUMBER_10 = 10;
    private static final int CHECKS_NUMBER_MINUS_10 = -10;
    private static final int CHECKS_NUMBER_11 = 11;
    private static final int CHECKS_NUMBER_100 = 100;

    @Test
    public void testStringSchema() {

        Validator v = new Validator();

        StringSchema schema = v.string();

        assertThat(schema.isValid("")).isTrue();
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid("what does the fox say")).isTrue();

        schema.required();

        assertThat(schema.isValid("what does the fox say")).isTrue();
        assertThat(schema.isValid("hexlet")).isTrue();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(CHECKS_NUMBER_5)).isFalse();
        assertThat(schema.isValid("")).isFalse();

        assertThat(schema.contains("wh").isValid("what does the fox say")).isTrue();
        assertThat(schema.contains("what").isValid("what does the fox say")).isTrue();
        assertThat(schema.contains("whatthe").isValid("what does the fox say")).isFalse();

        assertThat(schema.isValid("what does the fox say")).isFalse();

        schema.minLength(CHECKS_NUMBER_7);
        assertThat(schema.isValid("whatthe")).isTrue();
        assertThat(schema.isValid("hexlet")).isFalse();
    }
    @Test
    public void testNumberSchema() {

        Validator v = new Validator();

        NumberSchema schema = v.number();

        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(CHECKS_NUMBER_10)).isTrue();
        assertThat(schema.positive().isValid(null)).isTrue();
        assertThat(schema.isValid(CHECKS_NUMBER_10)).isTrue();
        assertThat(schema.isValid(CHECKS_NUMBER_MINUS_10)).isFalse();

        schema.required();

        assertThat(schema.isValid(CHECKS_NUMBER_10)).isTrue();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid("5")).isFalse();
        assertThat(schema.isValid(CHECKS_NUMBER_MINUS_10)).isFalse();
        assertThat(schema.isValid(CHECKS_NUMBER_0)).isFalse();

        schema.range(CHECKS_NUMBER_5, CHECKS_NUMBER_10);

        assertThat(schema.isValid(CHECKS_NUMBER_5)).isTrue();
        assertThat(schema.isValid(CHECKS_NUMBER_10)).isTrue();
        assertThat(schema.isValid(CHECKS_NUMBER_4)).isFalse();
        assertThat(schema.isValid(CHECKS_NUMBER_11)).isFalse();

        assertThat(schema.isValid(CHECKS_NUMBER_10)).isTrue();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(CHECKS_NUMBER_MINUS_10)).isFalse();
    }
    @Test
    public void testMapSchema() {

        Validator v = new Validator();

        MapSchema schema = v.map();

        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(new HashMap<>())).isTrue();

        schema.required();

        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(new HashMap<>())).isTrue();

        Map<String, Object> data = new HashMap<>();
        data.put("key1", "value1");

        assertThat(schema.isValid(data)).isTrue();

        schema.sizeof(2);

        assertThat(schema.isValid(data)).isFalse();
        data.put("key2", "value2");
        assertThat(schema.isValid(data)).isTrue();

    }
    @Test
    public void testMapShapeSchema() {

        Validator v = new Validator();

        MapSchema schema = v.map();

        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", CHECKS_NUMBER_100);
        assertThat(schema.isValid(human1)).isTrue();

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertThat(schema.isValid(human2)).isTrue();

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertThat(schema.isValid(human3)).isFalse();

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", CHECKS_NUMBER_MINUS_5);
        assertThat(schema.isValid(human4)).isFalse();

    }
}
