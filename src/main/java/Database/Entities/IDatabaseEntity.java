package Database.Entities;

import java.lang.reflect.Field;

public abstract class IDatabaseEntity {
    @Override
    public String toString() {
        var classInfo = this.getClass();
        var fields = classInfo.getDeclaredFields();

        String result = "{\n";

        for (int i = 0; i < fields.length; ++i) {
            try {

                result = String.format("%s    %s: %s\n", result, fields[i].getName(), fields[i].get(this));
            } catch (IllegalAccessException e) {
            }
        }

        return result + "}";
    }

    public String toStringMinimal() {
        var classInfo = this.getClass();
        var fields = classInfo.getDeclaredFields();

        String result = "(";

        for (int i = 0; i < fields.length; ++i) {
            try {

                result = String.format("%s %s: %s%s", result, fields[i].getName(), fields[i].get(this),
                        i == fields.length - 1 ? "" : ",");
            } catch (IllegalAccessException e) {
            }
        }

        return result + ")";
    }
}
