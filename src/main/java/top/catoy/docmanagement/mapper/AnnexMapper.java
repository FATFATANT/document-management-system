package top.catoy.docmanagement.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.catoy.docmanagement.domain.Annex;

import java.util.List;

/**
 * @description:
 * @author: xjn
 * @create: 2019-05-15 20:02
 **/
@Mapper
public interface AnnexMapper {
    List<Annex> getAnnexsByDocId(@Param("docId") int docId);

//    int delAnnexById(@Param("annexId") int annexId);

    int insertAnnex(Annex annex);
}
