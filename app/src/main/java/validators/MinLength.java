package validators;

import schemas.StringSchema;

public final class MinLength implements Validation {

    private final int number;

    private final StringSchema stringSchema;

    public MinLength(StringSchema schema, int otherNumber) {
        this.stringSchema = schema;
        this.number = otherNumber;

    }
    @Override
    public boolean isValid(Object value) {
        String string = (String) value;
        return string.length() >= this.number;
    }
}
