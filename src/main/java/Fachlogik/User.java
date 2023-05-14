package Fachlogik;

import java.io.Serializable;

public abstract class User implements Serializable {
    static int x;

    int id;
    String name;
    String email;
    String password;

    public User(String name, String email, String password) {
        this.id=++x;
        this.name=name;
        this.email=email;
        this.password=password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public static void setNextId(int nextId) {
        User.x = nextId;
    }
}
