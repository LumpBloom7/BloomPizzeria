package Database;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Database.Entities.Consumable;
import Database.Entities.User;

public class Datamapper {
    public static class DataCreationToken {
        private DataCreationToken() {

        }
    }

    private static final DataCreationToken CREATION_TOKEN = new DataCreationToken();

    public static <T> ArrayList<T> mapData(Class<T> structType, String sourceTable, String predicate) {
        ArrayList<T> results = new ArrayList<>();

        var fields = structType.getFields();

        try {
            var ctor = structType.getConstructor();

            ResultSet resultSet = Database.performQuery(String.format("SELECT * FROM %s %s", sourceTable, predicate));

            while (resultSet.next()) {
                T record = ctor.newInstance();
                for (var field : fields) {
                    var fieldType = field.getType();
                    var fieldName = field.getName();

                    switch (fieldType.getName()) {
                        case "int":
                            field.setInt(record, resultSet.getInt(fieldName));
                            break;
                        case "long":
                            field.setLong(record, resultSet.getLong(fieldName));
                            break;
                        case "java.lang.String":
                            field.set(record, resultSet.getString(fieldName));
                            break;
                        case "boolean":
                            field.setBoolean(record, resultSet.getBoolean(fieldName));
                            break;
                        case "float":
                            field.setFloat(record, resultSet.getFloat(fieldName));
                            break;
                        case "double":
                            field.setDouble(record, resultSet.getDouble(fieldName));
                            break;
                    }
                }
                results.add(record);
            }
            // TODO: Flesh out exception handling
        } catch (IllegalAccessException e) {
            System.out.println(e);
        } catch (InstantiationException e) {
            System.out.println(e);
        } catch (InvocationTargetException e) {
            System.out.println(e);
        } catch (NoSuchMethodException e) {
            System.out.println(e);
        } catch (SQLException e) {
            System.out.println(e);
        }

        return results;

    }

    public static void main(String[] args) {
        var consumables = Datamapper.mapData(Consumable.class, "Consumable", "");
        for (var m : consumables) {
            System.out.println(m);
        }

        System.out.println(User.getUser("Bloom"));
    }

    private static List<Field> getAllFields(List<Field> fields, Class<?> type) {
        fields.addAll(Arrays.asList(type.getFields()));

        if (type.getSuperclass() != null) {
            getAllFields(fields, type.getSuperclass());
        }

        return fields;
    }

}
