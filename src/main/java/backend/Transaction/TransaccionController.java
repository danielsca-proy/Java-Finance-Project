package backend.Transaction;

import backend.Category.Categoria;
import backend.Exceptions.AppExceptions;
import backend.User.Usuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.List;

public class TransaccionController {
    TransaccionService service;

    public TransaccionController(TransaccionService service) {
        this.service = service;
    }

    //Guardamos
    public void saveTransaction(Categoria category, Usuario user, String resume, String date){
        try {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date1 = LocalDate.parse(date, formato);

            service.save(category, user, resume, date1);

        }catch (DateTimeParseException e){
            throw new AppExceptions("Formato de fecha invalido, intente formato dd/MM/yyyy");
        }
    }

    //Eliminamos
    public void deleteTransaction(String id1){
        try {
            int id = Integer.parseInt(id1.trim());
            service.delete(id);
        } catch (NumberFormatException e){
            throw new AppExceptions("ID invalido, debe ser un numero entero.");
        }
    }

    //Devolvemos las transacciones en un rango de fecha
    public List<Transaccion> searchByRange(String first, String last, String id){
        try {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date1 = LocalDate.parse(first, formato);
            LocalDate date2 = LocalDate.parse(last, formato);

            int idUser = Integer.parseInt(id.trim());

            return service.findByRange(date1, date2, idUser);

        }catch (DateTimeParseException e){
            throw new AppExceptions("Error al pasar fechas");
        }catch (NumberFormatException e){
            throw new AppExceptions("ID invalido, debe ser un numero entero.");
        }
    }

    //Devolvemos todas las transacciones del usuario
    public List<Transaccion> findAll(String id){
        try {
            int idUser = Integer.parseInt(id.trim());
            return service.findAll(idUser);
        }catch (NumberFormatException e){
            throw new AppExceptions("ID invalido, debe ser un numero entero.");
        }
    }

}
