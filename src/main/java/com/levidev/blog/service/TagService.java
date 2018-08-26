package com.levidev.blog.service;

import com.levidev.blog.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface TagService {
    Tag saveTag(Tag tag);

    void deleteTag(Long id);

    Tag updateTag(Long id, Tag tag);

    Tag getTag(Long id);

    Page<Tag> listTag(Pageable pageable);

    List<Tag> listTag();

    List<Tag> listTop(Integer size);

    List<Tag> listTag(String ids);

    Tag getTagByName(String name);
}
