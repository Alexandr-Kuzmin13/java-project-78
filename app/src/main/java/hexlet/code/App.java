package hexlet.code;

import schemas.StringSchema;

public class App {
    public static void main(String[] args) {

        Validator v = new Validator();

        StringSchema schema = v.string();

        System.out.println(schema.isValid(""));
        System.out.println(schema.isValid(null));
    }
}
