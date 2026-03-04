package backend.Transaction;

import backend.Category.Categoria;
import backend.User.Usuario;

import java.time.LocalDate;

public class Transaccion {
    private int id;
    private static int counterTransaction = 1;
    private Categoria category;
    private Usuario user;
    private LocalDate date;
    private String resume;

    public Transaccion(Categoria category, Usuario user, String resume, LocalDate date) {
        this.id = counterTransaction++;
        this.category = category;
        this.user = user;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public Categoria getCategory() {
        return category;
    }

    public void setCategory(Categoria category) {
        this.category = category;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }
}
