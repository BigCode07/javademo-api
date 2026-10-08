package com.tuapp.authapi.presentation.dto;

import java.util.List;
import java.util.function.Function;

import com.tuapp.authapi.application.common.PageResult;

public record PageResponse<T>(
    List<T> content,
    int page,
    int size,
    long totalElement,
    int totalPage)
 {
    public static <D,T> PageResponse<T> from (PageResult<D> result, Function <D,T> mapper){
        return new PageResponse<>(
                result.content().stream().map(mapper).toList(),
                result.page(),
                result.size(),
                result.totalElements(),
                result.totalPages());
    }

}
