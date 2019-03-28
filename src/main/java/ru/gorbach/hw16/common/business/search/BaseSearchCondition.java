package ru.gorbach.hw16.common.business.search;

public class BaseSearchCondition {
    protected Long id;
    protected OrderDirection orderDirection;
    protected OrderType orderType = OrderType.SIMPLE;
    protected Paginator paginator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderDirection getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(OrderDirection orderDirection) {
        this.orderDirection = orderDirection;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public Paginator getPaginator() {
        return paginator;
    }

    public void setPaginator(Paginator paginator) {
        this.paginator = paginator;
    }

    public boolean needOrdering() {
        return orderDirection != null && orderType != null;
    }

    public boolean needPagination() {
        return paginator != null  && paginator.getLimit() > 0 && paginator.getOffset() >= 0;
    }
}
