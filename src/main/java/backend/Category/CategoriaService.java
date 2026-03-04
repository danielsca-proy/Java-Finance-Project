package backend.Category;

import backend.Exceptions.AppExceptions;
import backend.Transaction.Transaccion;
import backend.Transaction.TransaccionService;

import java.util.List;

public class CategoriaService {

    CategoriaRepository repository = new CategoriaRepository();
    TransaccionService transaccion = new TransaccionService();

    public void add(Categoria categoria){
        //Verificar si no es nulo
        if(categoria == null) {
            throw new AppExceptions("Nueva categoria Invalida. ");
        }

        //Agregar
        repository.save(categoria);
    }

    public void eliminate(Categoria categoria){
        //Verificar si no es nulo
        if(categoria == null) throw new AppExceptions("La categoria a eliminar es nula. ");

        //Verificar si existe
        for(Categoria categoria1 : repository.getList()){
            if(!categoria1.equals(categoria)){
                throw new AppExceptions("La categoria no existe");
            }
        }

        //Verificar si existe transaccion asociada
        for (Transaccion transaccion1 : transaccion.viewList()){
            if(transaccion1.getCategory() != null && transaccion1.getCategory().equals(categoria)){
                throw new AppExceptions("Primero debe eliminar las transacciones asociadas. ");
            }
        }

        //Eliminar
        repository.delete(categoria);
    }

    public List<Categoria> viewList(){
        //Verificar si no es nulo
        if(repository.getList() == null){
            throw new AppExceptions("No permitido, lista vacia.");
        }

        //Devolver lista
        return repository.getList();
    }

    public void modifyCategory(Categoria newCategory){
        //Verificar si no es nulo
        if (newCategory == null) throw new AppExceptions("La categoria nueva no puede ser nula");

        //Verificar si existe
        for(Categoria categoria1 : repository.getList()){
            if(categoria1.equals(newCategory)){
                throw new AppExceptions("La categoria ya existe");
            }
        }

        //Modificar categoria
        repository.modify(newCategory);
    }
}
