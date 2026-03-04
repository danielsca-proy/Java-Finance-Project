package backend.Category;

import backend.Exceptions.AppExceptions;
import java.util.List;

public class CategoriaController {
    CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    //Lo guarda
    public void save(String name, String type){
        Categoria.Type type1 = null;

        //Parseamos el string a enum
        try{
            type1 = Categoria.Type.valueOf(type.trim().toUpperCase());
        }catch(IllegalArgumentException e){
            throw new AppExceptions("Tipo invalido - " + type);
        }
        //Lo manda a la logica
        service.save(name.trim(), type1);
    }

    //Lo elimina
    public void delete(String name){
        //Lo manda a la logica
        service.delete(name.trim());
    }

    //Lo modifica
    public void update(String name, String newName, String type){
        Categoria.Type type1 = null;

        //Parseamos el string a enum
        try{
            type1 = Categoria.Type.valueOf(type.trim().toUpperCase());
        }catch(IllegalArgumentException e){
            throw new AppExceptions("Tipo invalido - " + type);
        }

        //Lo manda a la logica
        service.update(name, newName, type1);
    }

    //Busca por nombre
    public Categoria findByName(String name){
        return service.findByName(name.trim());
    }

    //Busca todos
    public List<Categoria> findAll(){
        return service.findAll();
    }
}
