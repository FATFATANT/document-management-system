package top.catoy.docmanagement.service;

import top.catoy.docmanagement.domain.Tag;

import java.util.List;

public interface TagService {

    public int insertTags(Tag tag);


    public int updateTags(Tag tag);


    public List<Tag> getAllTags();


    public int getIdByTagName(String name);

    public Tag getTagByName(String name);


    public Tag getTagById(int id);
}
