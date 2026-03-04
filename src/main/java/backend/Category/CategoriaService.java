package backend.Category;

import backend.Exceptions.AppExceptions;
import java.util.List;

public class CategoriaService {
    CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    //Normalizador
    private String normalizeName(String name){
        if(name == null) throw new AppExceptions("Nombre invalido");
        name = name.trim();
        if(name.isBlank()) throw new AppExceptions("Nombre invalido");
        name = name.toLowerCase();
        return name.substring(0,1).toUpperCase() + name.substring(1);
    }

    //Guardar en la lista
    public void save(String name, Categoria.Type type){
        if(name == null || name.isBlank()) throw new AppExceptions("Creacion invalida");

        //Normalizamos
        name = normalizeName(name);

        //Verificar si ya existe
        for(Categoria categoria : repository.getList()){
            if(categoria.getName().equalsIgnoreCase(name)){
                throw new AppExceptions("Categoria ya existente");
            }
        }

        //Si no eiste, Agregamos
        repository.save(new Categoria(name, type));
    }

    //Eliminar de la lista
    public void delete(String name){
        if(name == null || name.isBlank()) throw new AppExceptions("Eliminacion invalida");
        //Verificar si existe la categoria
        for(Categoria categoria : repository.getList()){
            if(categoria.getName().equalsIgnoreCase(name)){
                //Existe, la elimina
                repository.delete(categoria);
                return;
            }
        }
        throw new AppExceptions("Categoria no existente");
    }



    //Modificar - en lista
    public void update(String currentName, String newName, Categoria.Type newType){
        String current = normalizeName(currentName);
        String next = normalizeName(newName);

        Categoria actual = repository.findByName(current);
        if(actual == null) throw new AppExceptions("Categoria no existente");

        if(!current.equalsIgnoreCase(next) && repository.existsByName(next)){
            throw new AppExceptions("Categoria ya existente");
        }

        repository.update(actual.getId(), next, newType);
    }

    //Buscar por nombre
    public Categoria findByName(String name){
        if(name == null || name.isBlank()) throw new AppExceptions("Busqueda invalida");
        //Normalizamos
        name = normalizeName(name);
        //Buscamos la categoria
        for(Categoria categoria : repository.getList()){
            if(categoria.getName().equalsIgnoreCase(name)){
                return categoria;
            }
        }
        throw new AppExceptions("Categoria no existente");
    }

    //Buscar todos
    public List<Categoria> findAll(){
        return repository.getList();
    }
}
