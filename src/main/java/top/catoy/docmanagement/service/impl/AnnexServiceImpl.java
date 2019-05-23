package top.catoy.docmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.catoy.docmanagement.domain.Annex;
import top.catoy.docmanagement.domain.ResponseBean;
import top.catoy.docmanagement.mapper.AnnexMapper;
import top.catoy.docmanagement.service.AnnexService;


@Service
public class AnnexServiceImpl implements AnnexService {

    @Autowired
    private AnnexMapper annexMapper;

    @Override
    public int insertAnnex(Annex annex) {
        return annexMapper.insertAnnex(annex);
    }

    @Override
    public ResponseBean delAnnexById(int annexId) {
//        try {
//            int sum  = annexMapper.delAnnexById(annexId);
//            if(sum>0){
//                return new ResponseBean(ResponseBean.SUCCESS,"查询成功",null);
//            }else{
//                return new ResponseBean(ResponseBean.FAILURE,"查询失败",null);
//            }
//        }catch (RuntimeException r){
//            r.printStackTrace();
//            return new ResponseBean(ResponseBean.ERROR,"错误",null);
//        }
        return null;
    }
}
