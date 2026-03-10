package backend.Session;

import backend.User.Usuario;

public class UsuarioLogueado {
    private static Usuario usuarioLogueado;

    public static void login(Usuario usuario){
        usuarioLogueado = usuario;
    }

    public static Usuario getUsuario(){
        return usuarioLogueado;
    }

    public static void logout(){
        usuarioLogueado = null;
    }

    public static boolean isLoggedIn(){
        return usuarioLogueado != null;
    }

}
