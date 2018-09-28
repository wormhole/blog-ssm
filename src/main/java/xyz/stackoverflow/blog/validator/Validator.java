package xyz.stackoverflow.blog.validator;

import java.util.Map;

public interface Validator<T> {

    Map validate(T t);
}
