package xyz.stackoverflow.blog.util;

import javax.validation.ConstraintViolation;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 校验工具类
 *
 * @author 凉衫薄
 */
public class ValidationUtil {

    /**
     * 包装验证结果
     *
     * @param violations
     * @return
     */
    public static <T> Map<String, String> errorMap(Set<ConstraintViolation<T>> violations) {
        Map<String, String> map = new HashMap<>();
        Iterator<ConstraintViolation<T>> iter = violations.iterator();
        while (iter.hasNext()) {
            ConstraintViolation<T> violation = iter.next();
            map.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        return map;
    }
}
