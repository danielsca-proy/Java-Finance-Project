package backend.Transaction;

import backend.Category.Categoria;
import backend.User.Usuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TransaccionController {
    TransaccionService service;

    public TransaccionController(TransaccionService service) {
        this.service = service;
    }
}
