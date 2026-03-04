package backend.Category;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CategoriaRepository {
    List<Categoria> list = new ArrayList<>();

    //Agrega a la lista
    public void save(Categoria categoria){
        list.add(categoria);
    }

    //Borra de la lista
    public void delete(Categoria categoria){
        list.remove(categoria);
    }

    //Devuelve la lista
    public List<Categoria> getList(){
        return list;
    }

    //Modifica la lista
    public void modify(Categoria newCategory){
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == newCategory.getId()) {
                list.set(i, newCategory);
                return;
            }
        }
    }

}
