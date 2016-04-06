package br.com.emmanuelneri.app.service;

import br.com.emmanuelneri.app.utils.Model;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractService<T extends Model<ID>, ID extends Serializable> implements Serializable {

    private Class<T> type;

    @PersistenceContext
    private EntityManager entityManager;

    public AbstractService() {
        Class<?> clazz = getClass();
        do {
            if (clazz.getSuperclass().equals(AbstractService.class)) {
                break;
            }
        } while ((clazz = clazz.getSuperclass()) != null);

        this.type = (Class<T>) ((ParameterizedType) clazz
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Transactional(readOnly = true)
    public T findById(Long id) {
        return this.entityManager.find(type, id);
    }

    @Transactional(readOnly = true)
    public List<T> findAll() {
        return createSession().createCriteria(type).list();
    }

    @Transactional
    public void remove(T object) {
        this.entityManager.remove(this.entityManager.getReference(this.type, object.getId()));
    }

    @Transactional
    public void create(T object) {
        this.entityManager.persist(object);
    }

    @Transactional
    public void update(T object) {
        this.entityManager.merge(object);
    }

    @Transactional
    public void save(T object) {
        if (object.getId() == null) {
            this.entityManager.persist(object);
        } else {
            this.entityManager.merge(object);
        }
    }

    protected Session createSession() {
        return (Session) this.entityManager.getDelegate();
    }

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    protected Criteria getCriteria() {
        return createSession().createCriteria(type);
    }


}