package xyz.stackoverflow.blog.validator;

import java.util.Map;

/**
 * @Author: 凉衫薄
 * @Date: 2018-10-21
 * @Description: 字段校验器接口
 */
public interface Validator<T> {

    Map validate(T t);
}
