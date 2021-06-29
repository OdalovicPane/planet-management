package com.example.planetmanagement.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageResponse<TDto, TEntity> extends Response<List<TDto>> {

    private Integer currentPage;
    private Long totalItems;
    private Integer totalPages;

    public PageResponse(Page<TEntity> pageEntities, Function<TEntity, TDto> converterFunction) {
        super.setData(pageEntities.getContent().stream().map(pageEntity-> converterFunction.apply(pageEntity)).collect(Collectors.toList()));
        this.setCurrentPage(pageEntities.getNumber());
        this.setTotalItems(pageEntities.getTotalElements());
        this.setTotalPages(pageEntities.getTotalPages());
        this.setError(SUCCESS);
    }

}
