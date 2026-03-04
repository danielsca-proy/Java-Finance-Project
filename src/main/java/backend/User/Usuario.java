package backend.User;

import backend.Exceptions.AppExceptions;

import java.math.BigDecimal;

public class Usuario {
    private int id;
    private static int counterUser = 1;
    private String name;
    private String password;
    private String user;
    private BigDecimal balance;

    public Usuario(String name, String password, String user, BigDecimal balance) {
        this.id = counterUser++;
        this.name = name;
        this.password = password;
        this.user = user;
        this.balance = BigDecimal.ZERO;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void withdraw(BigDecimal money){
        if (getBalance().compareTo(money) < 0){
            throw new AppExceptions("No hay saldo suficiente para realizar esta operacion. ");
        }else{
            setBalance(getBalance().subtract(money));
        }
    }

    public void deposit(BigDecimal money){
        if (money == null || money.compareTo(BigDecimal.ZERO) <= 0){
            throw new AppExceptions("El monto es invalido. ");
        }
        setBalance(getBalance().add(money));
    }
}
