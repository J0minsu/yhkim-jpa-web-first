package jpa.web.part.first.domain.res;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Result<T> {

    private List<T> list;
    private int count;

    public static Result of(List input) {
        return new Result(input, input.size());
    }

}
