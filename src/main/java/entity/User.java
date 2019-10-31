package entity;

// 封装 User 类 用于对数据进行单独操作;
public class User {
    private int id;
    private String name;
    private Integer Age;

    public User() {
    }

    public User(String name, int Age) {
        this.name = name;
        this.Age = Age;
    }

    public User(int id, String name, int Age) {
        this.id = id;
        this.name = name;
        this.Age = Age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }
}
