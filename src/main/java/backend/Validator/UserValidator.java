package backend.Validator;

public class UserValidator {

    //Verifica usuario
    public static boolean isValidUser(String user){
        return user.matches("^(?=.*[A-Z])(?=.*[a-z])(?=(?:.*\\d){3,})(?=.*[^A-Za-z0-9]).{6,20}$");
    }

}

