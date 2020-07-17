package ru.ssau.practice.service.db.pagination;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

public class PaginationRequest
{
    @NotNull
    @Range(min = 1)
    private Integer page;

    @NotNull
    @Range(min = 1)
    private Integer perPage;

    @NotNull
    private String sortBy;

    private Boolean sortDesc = false;

    private String filter = "";

    public PaginationRequest(Integer page, Integer perPage, String sortBy)
    {
        this.page = page;
        this.perPage = perPage;
        this.sortBy = sortBy;
    }

    public Integer getPage()
    {
        return page;
    }

    public Integer getPerPage()
    {
        return perPage;
    }

    public String getSortBy()
    {
        return sortBy;
    }

    public Boolean isSortDesc()
    {
        return sortDesc;
    }

    public void setSortDesc(Boolean sortDesc)
    {
        this.sortDesc = sortDesc;
    }

    public String getFilter()
    {
        return filter;
    }

    public void setFilter(String filter)
    {
        this.filter = filter;
    }
}
