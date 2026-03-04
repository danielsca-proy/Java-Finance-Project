package backend.User;

import backend.Transaction.Transaccion;

import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {
    List<Usuario> list = new ArrayList<>();

    //Agrega a la lista
    public void save(Usuario usuario){
        list.add(usuario);
    }

    //Elimina de la lista
    public void delete(Usuario usuario){
        list.remove(usuario);
    }

    //Buscar un usuario
    public Usuario searchUser(String user){
        for(Usuario usuario:list){
            if (usuario.getUser().equals(user)){
                return usuario;
            }
        }
        return null;
    }

    //Modifica un usuario
    public void modify(Usuario newUser){
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == newUser.getId()) {
                list.set(i, newUser);
                return;
            }
        }
    }

    //Devuelve la lista
    public List<Usuario> getList(){
        return list;
    }

}
