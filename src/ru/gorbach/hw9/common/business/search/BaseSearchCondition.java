package ru.gorbach.hw9.common.business.search;

public class BaseSearchCondition {
    protected Long id;
    protected SortType sortType = SortType.ASC;

    protected boolean isSortById = true;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SortType getSortType() {
        return sortType;
    }

    public void setSortType(SortType sortType) {
        this.sortType = sortType;
    }

    public boolean isSortById() {
        return isSortById;
    }


}
