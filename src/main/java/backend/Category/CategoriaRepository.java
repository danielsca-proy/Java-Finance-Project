package backend.Category;

import backend.Exceptions.AppExceptions;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRepository {
    private List<Categoria> listInMemory = new ArrayList<>();

    //Guardar en la lista
    public void save(Categoria categoria){
        listInMemory.add(categoria);
    }

    //Eliminar de la lista
    public void delete(Categoria categoria){
        listInMemory.remove(categoria);
    }

    //Modificar categoria en lista
    public void update(int id, String newName, Categoria.Type newType){
        for(Categoria categoria : listInMemory){
            if(categoria.getId() == id){
                categoria.setName(newName);
                categoria.setType(newType);
                return;
            }
        }

        throw new AppExceptions("Categoria no encontrada.");
    }

    //Buscar por Name
    public Categoria findByName(String name){
        for (Categoria categoria : listInMemory){
            if (categoria.getName().equalsIgnoreCase(name)){
                return categoria;
            }
        }
        return  null;
    }

    //Existencia
    public boolean existsByName(String name){
        return findByName(name) != null;
    }

    //Buscar todos
    public List<Categoria> getList(){
        return List.copyOf(listInMemory);
    }
}
