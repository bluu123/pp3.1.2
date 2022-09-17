package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RoleService {
    @PersistenceContext
    private EntityManager entityManager;
    public List getAllRoles() {
        return entityManager.createQuery("select r from Role r").getResultList();
    }
    public Set<Role> findByIdRoles(List<Long> roles) {
        TypedQuery<Role> q = entityManager.createQuery("select r from Role r where r.id in :role", Role.class);
        q.setParameter("role",roles);
        return new HashSet<>(q.getResultList());
    }
}
