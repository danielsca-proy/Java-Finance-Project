package backend.Validator;

public class PasswordValidator {

    //Verifica contraseña
    public static boolean isValidPassword(String user){
        return user.matches("^(?=.*[A-Z])(?=.*[a-z])(?=(?:.*\\d){3,}).{8,25}$");
    }

}
