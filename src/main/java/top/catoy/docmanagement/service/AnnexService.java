package top.catoy.docmanagement.service;

import top.catoy.docmanagement.domain.Annex;
import top.catoy.docmanagement.domain.ResponseBean;

public interface AnnexService {

    int insertAnnex(Annex annex);

    ResponseBean delAnnexById(int annexId);
}
