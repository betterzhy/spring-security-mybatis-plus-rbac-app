package com.zhy.model.dto;

import java.util.List;

/**
 * @author zhy
 */
public class PagedResponse<T> {
    private List<T> content;
    private Long pageNum;
    private Long pageSize;
    private Long totalElements;
    private Long totalPages;
    private Boolean last;

    public PagedResponse() {

    }

    public PagedResponse(List<T> content, Long pageNum, Long pageSize, Long totalElements, Long totalPages, Boolean last) {
        this.content = content;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.last = last;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public Long getPageNum() {
        return pageNum;
    }

    public void setPageNum(Long pageNum) {
        this.pageNum = pageNum;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }

    public Boolean isLast() {
        return last;
    }

    public void setLast(Boolean last) {
        this.last = last;
    }
}
