package ru.ssau.practice.service;

import ru.ssau.practice.ex.NotFoundException;
import ru.ssau.practice.repository.DeleteByIDsRepository;
import ru.ssau.practice.service.util.SetUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractDeleteService
{
    private final DeleteByIDsRepository repository;

    public AbstractDeleteService(DeleteByIDsRepository repository)
    {
        this.repository = repository;
    }

    public void delete(long[] IDs) throws Exception
    {
        long[] fromDB = repository.selectIDs(IDs);

        Long[] wrappedIDs = new Long[IDs.length];
        for (int i = 0; i < IDs.length; i++) {
            wrappedIDs[i] = IDs[i];
        }

        Long[] wrappedFromDB = new Long[fromDB.length];
        for (int i = 0; i < fromDB.length; i++) {
            wrappedFromDB[i] = fromDB[i];
        }

        Set<Long> diff = SetUtil.difference(new HashSet<>(Arrays.asList(wrappedIDs)), new HashSet<>(Arrays.asList(wrappedFromDB)));
        if (diff.size() != 0) {
            throw new NotFoundException(diff.toArray(new Long[0]));
        }

        preDelete(IDs);
        repository.deleteByIdIn(IDs);
    }

    protected void preDelete(long[] IDs) throws Exception
    {
    }
}
