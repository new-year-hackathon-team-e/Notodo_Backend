package com.example.notodo_backend.global.paging;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {

    private List<T> data;
    private Long totalCount;
    private Long nextPage;

    public PageResponse(Page<T> pageResponse) {
        this.data = pageResponse.getContent();
        this.totalCount = pageResponse.getTotalElements();
        this.nextPage = pageResponse.hasNext() ? Long.valueOf(pageResponse.getNumber() + 1) : null;
    }
}
