package validators;

import schemas.StringSchema;

public final class Required implements Validation {

    private final StringSchema stringSchema;

    public Required(StringSchema schema) {
        this.stringSchema = schema;
    }

    @Override
    public boolean isValid(Object value) {
        if (!(value instanceof String)) {
            return false;
        } else {
            return !value.equals("");
        }
    }

}
