package implementation;

import entities.Purchase;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import service.BaseService;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseImplementation implements BaseService<Purchase> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Purchase> readAll() {
        return entityManager.createQuery("select p from Purchase p", Purchase.class).getResultList();
    }

    @Override
    @Transactional
    public void create(Purchase entity) {
        entityManager.persist(entity);
    }

    @Override
    @Transactional
    public void update(Purchase entity) {
        entityManager.merge(entity);
    }

    @Override
    public Optional<Purchase> readById(Long id) {
        return Optional.ofNullable(entityManager.find(Purchase.class, id));
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        Optional<Purchase> optionalPurchase = readById(id);
        if (optionalPurchase.isPresent()) {
            entityManager.remove(optionalPurchase.get());
            return true;
        }
        return false;
    }
}
