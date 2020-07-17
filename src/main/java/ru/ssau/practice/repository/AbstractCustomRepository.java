package ru.ssau.practice.repository;

import ru.ssau.practice.service.db.pagination.Paginator;

import javax.persistence.EntityManager;

public abstract class AbstractCustomRepository<I>
{
    protected final EntityManager em;

    protected final Paginator<I> paginator;

    public AbstractCustomRepository(EntityManager em, Paginator<I> paginator)
    {
        this.em = em;
        this.paginator = paginator;
    }
}
