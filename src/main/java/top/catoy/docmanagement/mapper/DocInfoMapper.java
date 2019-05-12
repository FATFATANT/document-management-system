package top.catoy.docmanagement.mapper;


import org.apache.ibatis.annotations.Mapper;
import top.catoy.docmanagement.domain.DocInfo;

@Mapper
public interface DocInfoMapper {

    public int insertDocInfo(DocInfo docInfo);



}
