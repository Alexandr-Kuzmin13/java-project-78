package hexlet.code;

import org.junit.jupiter.api.Test;
import schemas.StringSchema;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorTest {

    private static final int CHECKS_NUMBER_1 = 5;
    private static final int CHECKS_NUMBER_2 = 6;
    private static final int CHECKS_NUMBER_3 = 10;
    private static final int CHECKS_NUMBER_4 = 7;

    @Test
    public void testStringSchema() {

        Validator v = new Validator();

        StringSchema schema = v.string();

        assertThat(schema.isValid("")).isTrue();
        assertThat(schema.isValid(null)).isTrue();

        schema.required();

        assertThat(schema.isValid("what does the fox say")).isTrue();
        assertThat(schema.isValid("hexlet")).isTrue();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(CHECKS_NUMBER_1)).isFalse();
        assertThat(schema.isValid("")).isFalse();

        assertThat(schema.contains("wh").isValid("what does the fox say")).isTrue();
        assertThat(schema.contains("what").isValid("what does the fox say")).isTrue();
        assertThat(schema.contains("whatthe").isValid("what does the fox say")).isFalse();

        assertThat(schema.isValid("what does the fox say")).isFalse();

        assertThat(schema.minLength(CHECKS_NUMBER_2).isValid("hexlet")).isTrue();
        assertThat(schema.minLength(CHECKS_NUMBER_3).isValid("what does the fox say")).isTrue();
        assertThat(schema.minLength(CHECKS_NUMBER_4).isValid("hexlet")).isFalse();

        assertThat(schema.isValid("hexlet")).isFalse();
    }
}
