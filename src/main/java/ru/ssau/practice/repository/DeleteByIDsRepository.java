package ru.ssau.practice.repository;

public interface DeleteByIDsRepository
{
    long[] selectIDs(long[] IDs);

    void deleteByIdIn(long[] IDs);
}
