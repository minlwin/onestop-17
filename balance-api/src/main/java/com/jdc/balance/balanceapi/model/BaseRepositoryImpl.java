package com.jdc.balance.balanceapi.model;

import com.jdc.balance.balanceapi.model.dto.PageResult;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.List;
import java.util.function.Function;

public class BaseRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID>
    implements BaseRepository<T, ID>{

    private final EntityManager entityManager;

    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public <R> List<R> search(Function<CriteriaBuilder, CriteriaQuery<R>> queryFunc) {
        var criteriaQuery = queryFunc.apply(entityManager.getCriteriaBuilder());
        var query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public <R> PageResult<R> search(Function<CriteriaBuilder, CriteriaQuery<R>> queryFunc, Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc, int page, int size) {

        var criteriaQuery = queryFunc.apply(entityManager.getCriteriaBuilder());
        var query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult(page * size);
        query.setMaxResults(size);

        var contents = query.getResultList();
        var count = entityManager.createQuery(countFunc.apply(entityManager.getCriteriaBuilder())).getSingleResult();

        return new PageResult<>(contents, count, page, size);
    }
}
