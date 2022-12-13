package rentCars.dao;

import java.util.List;
import java.util.Optional;

public interface DaoRentCar<K, E> {
    boolean delete(K id);

    E add(E entity);

    void update(E entity);

    Optional<E> findById(K id);

    List<E> findAll();
}
