package ru.ssau.practice.service.db.pagination;

import java.util.Collection;

public class PaginationResult<I>
{
    private final Collection<I> items;

    private final long pages;

    public PaginationResult(Collection<I> items, long pages)
    {
        this.items = items;
        this.pages = pages;
    }

    public Collection<I> getItems()
    {
        return items;
    }

    public long getPages()
    {
        return pages;
    }
}
