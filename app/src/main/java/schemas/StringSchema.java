package schemas;

import validators.Content;
import validators.MinLength;
import validators.Required;
import validators.Validation;

public final class StringSchema {

    private Validation validation;

    public Validation getValidation() {
        return validation;
    }

    public boolean isValid(Object value) {
        if (getValidation() == null) {
            return true;
        } else {
            return getValidation().isValid(value);
        }
    }

    public void required() {
        this.validation = new Required(this);
    }

    public Validation minLength(int number) {
        this.validation = new MinLength(this, number);
        return new MinLength(this, number);
    }

    public Validation contains(String subString) {
        this.validation = new Content(this, subString);
        return new Content(this, subString);

    }
}
