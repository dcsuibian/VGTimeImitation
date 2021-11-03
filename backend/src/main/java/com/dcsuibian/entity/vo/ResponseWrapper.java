package com.dcsuibian.entity.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.time.Instant;
import java.util.function.Function;

import static com.dcsuibian.util.Util.batchConvert;

@Getter
@Setter
public class ResponseWrapper<T> {
    private T data;
    private String message;
    private int code;
    private Long timestamp = Instant.now().toEpochMilli();

    public ResponseWrapper(T data, String message, int code) {
        this.data = data;
        this.message = message;
        this.code = code;
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
