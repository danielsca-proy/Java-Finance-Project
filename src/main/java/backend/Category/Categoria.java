package backend.Category;

public class Categoria {
    private final int id;
    private static int counterCategory = 1;
    private String name;
    private Type type;

    public enum Type{
        INCOME,
        EXPENSE
    }

    public Categoria(String name, Type type){
        this.id = counterCategory++;
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
