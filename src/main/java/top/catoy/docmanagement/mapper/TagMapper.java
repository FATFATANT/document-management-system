package top.catoy.docmanagement.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.catoy.docmanagement.domain.Tag;

import java.util.List;


@Mapper
public interface TagMapper {

    public int insertTag(Tag tag);


    public int updateTag(Tag tag);


    public List<Tag> getAllTag();


    public int getTagIdByName(@Param("name") String name);


    public Tag getTagByName(@Param("name") String name);

    public Tag getTagById(@Param("id") int id);


}
