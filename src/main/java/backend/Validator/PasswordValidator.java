package backend.Validator;

public class PasswordValidator {

    //Verifica contraseña
    public boolean isValidPassword(String user){
        return user.matches("^(?=.*[A-Z])(?=.*[a-z])(?=(?:.*\\d){3,}).{8,25}$");
    }

}
