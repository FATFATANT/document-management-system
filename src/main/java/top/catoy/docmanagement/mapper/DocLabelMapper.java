package top.catoy.docmanagement.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.catoy.docmanagement.domain.DocLabel;

import java.util.List;

/**
 * @description:
 * @author: xjn
 * @create: 2019-04-28 12:49
 **/
@Mapper
public interface DocLabelMapper {
    int insertLabel(DocLabel docLabel);

    int delDocLabel(DocLabel docLabel);

//    int delDocLabelById(@Param("docLabelId") String docLabelId);

    int editDocLabel(DocLabel docLabel);

    List<DocLabel> getAllDocLabels();

    List<DocLabel> getDocLabelBySuperId(@Param("superId") int superId);

    DocLabel getDocLabelById(@Param("id") int id);

    public List<DocLabel> getLabelsByName(@Param("labels") String[] labels);

    public DocLabel getLabelByName(@Param("label") String label);


}
