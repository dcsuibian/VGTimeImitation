package com.dcsuibian.entity.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;

import static com.dcsuibian.util.Util.batchConvert;

@Getter
@Setter
public class PageWrapper<T> {
    private Long pageSize;
    private Long pageNo;
    private Long totalCount;
    private List<T> data;

    public Long getTotalPage() {
        return 0==totalCount%pageSize?(totalCount/pageSize):1+(totalCount/pageSize);
    }

    public static <T> PageWrapper<T> of(Page<T> page) {
        PageWrapper<T> result = new PageWrapper<>();
        result.setPageNo(page.getNumber() + 1L);
        result.setPageSize(page.getSize() + 0L);
        result.setTotalCount(page.getTotalElements());
        result.setData(page.getContent());
        return result;
    }

    public static <T, R> PageWrapper<R> of(Page<T> page, Function<T, R> converter) {
        PageWrapper<R> result = new PageWrapper<>();
        result.setPageNo(page.getNumber() + 1L);
        result.setPageSize(page.getSize() + 0L);
        result.setTotalCount(page.getTotalElements());
        result.setData(batchConvert(page.getContent(), converter));
        return result;
    }
}
