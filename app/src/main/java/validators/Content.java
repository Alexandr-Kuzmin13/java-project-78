package validators;

import schemas.StringSchema;

public final class Content implements Validation {

    private final String subString;

    private final StringSchema stringSchema;

    public Content(StringSchema schema, String otherString) {
        this.stringSchema = schema;
        this.subString = otherString;
    }

    @Override
    public boolean isValid(Object value) {
        String string = (String) value;
        return string.contains(this.subString);
    }
}
