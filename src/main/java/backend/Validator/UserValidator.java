package backend.Validator;

public class UserValidator {

    //Verifica usuario
    public boolean isValidUser(String user){
        return user.matches("^(?=.*[A-Z])(?=.*[a-z])(?=(?:.*\\d){3,})(?=.*[^A-Za-z0-9]).{4,20}$");
    }

}

