package implementation;

import entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import service.BaseService;

import java.util.List;
import java.util.Optional;

@Service
public class UserImplementation implements BaseService<User> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> readAll() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    @Transactional
    public void create(User entity) {
        entityManager.persist(entity);
    }

    @Override
    @Transactional
    public void update(User entity) {
        entityManager.merge(entity);
    }

    @Override
    public Optional<User> readById(Long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        Optional<User> optionalUser = readById(id);
        if(optionalUser.isPresent()){
            entityManager.remove(optionalUser.get());
            return true;
        }
        return false;
    }
}