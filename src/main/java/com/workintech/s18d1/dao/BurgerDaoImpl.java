package com.workintech.s18d1.dao;

import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.exceptions.BurgerException;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BurgerDaoImpl implements BurgerDao {

    private EntityManager entityManager;

    @Autowired
    public BurgerDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public Burger save(Burger burger) {
        entityManager.persist(burger);
        return burger;
    }

    @Override
    public Burger findById(Long id) {
        Burger burger = entityManager.find(Burger.class, id);
        if (burger == null) {
            throw new BurgerException("Burger not found with id: " + id, HttpStatus.NOT_FOUND);
        }
        return burger;
    }

    @Override
    public List<Burger> findAll() {
        TypedQuery<Burger> query = entityManager.createQuery("select b from Burger b", Burger.class);
        return query.getResultList();
    }

    @Override
    public List<Burger> findByPrice(Integer price) {
        if (price < 0) {
            throw new BurgerException("Price cannot be negative", HttpStatus.BAD_REQUEST);
        }
        TypedQuery<Burger> query = entityManager.createQuery("select b from Burger b where b.price = :price", Burger.class);
        query.setParameter("price", price);
        return query.getResultList();
    }

    @Override
    public List<Burger> findByBreadType(BreadType breadType) {
        if (breadType == null) {
            throw new BurgerException("Bread type cannot be null", HttpStatus.BAD_REQUEST);
        }
        TypedQuery<Burger> query = entityManager.createQuery("select b from Burger b where b.breadType = :breadType", Burger.class);
        query.setParameter("breadType", breadType);
        return query.getResultList();
    }

    @Override
    public List<Burger> findByContent(String content) {
        if (content == null) {
            throw new BurgerException("Content cannot be null", HttpStatus.BAD_REQUEST);
        }
        TypedQuery<Burger> query = entityManager.createQuery("select b from Burger b where b.contents = :content", Burger.class);
        query.setParameter("content", content);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Burger update(Burger burger) {
        if (burger.getId() == 0) {
            throw new BurgerException("Burger id cannot be 0", HttpStatus.BAD_REQUEST);
        }
        if (burger.getId() < 0) {
            throw new BurgerException("Burger id cannot be negative", HttpStatus.BAD_REQUEST);
        }
        return entityManager.merge(burger);
    }

    @Transactional
    @Override
    public Burger remove(Long id) {
        if (id == 0) {
            throw new BurgerException("Burger id cannot be 0", HttpStatus.BAD_REQUEST);
        }
        if (id < 0) {
            throw new BurgerException("Burger id cannot be negative", HttpStatus.BAD_REQUEST);
        }
        Burger burger = findById(id);
        entityManager.remove(burger);
        return burger;
    }
}
