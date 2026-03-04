package backend.Transaction;

import backend.Exceptions.AppExceptions;

import java.time.LocalDate;
import java.util.List;

public class TransaccionService{
    TransaccionRepository repository;

    public TransaccionService(TransaccionRepository repository) {
        this.repository = repository;
    }
}
