package com.levidev.blog.service;

import com.levidev.blog.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface TypeService {
    Type saveType(Type type);

    void deleteType(Long id);

    Type updateType(Long id, Type type);

    Type getType(Long id);

    Page<Type> listType(Pageable pageable);

    List<Type> listType();

    List<Type> listTop(Integer size);

    Type getTypeByName(String name);
}
