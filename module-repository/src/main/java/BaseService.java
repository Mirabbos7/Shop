import java.util.List;
import java.util.Optional;

public interface BaseService<T> {

    List<T> readAll();
    void create(T entity);
    void update(T entity);
    Optional<T> readById(Long id);
    boolean deleteById(Long id);

}
