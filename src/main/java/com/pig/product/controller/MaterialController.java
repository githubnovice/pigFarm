package com.pig.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig.product.entity.Material;
import com.pig.product.entity.Reference;
import com.pig.product.entity.Usemateriallog;
import com.pig.product.service.IMaterialService;
import com.pig.product.service.IReferenceService;
import com.pig.product.service.IUsemateriallogService;
import com.pig.product.util.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 材料管理
 */
@RestController
@RequestMapping("/material/")
@CrossOrigin
public class MaterialController extends BaseController {

    @Autowired
    private IMaterialService iMaterialService;

    @Autowired
    private IReferenceService iReferenceService;

    @Autowired
    private IUsemateriallogService iUsemateriallogService;

    /***
     * 添加材料
     * @return
     */
    @PostMapping("addOrModifyMaterial")
    @Transactional
    public Object addOrModifyMaterial(Material material, Reference reference,Integer state){
        if(1 == state){
            int bool = iMaterialService.addOrModifyMaterial(material);
            if(0 == bool){
                 return renderError("操作失败");
            }
            int bo = iReferenceService.addOrModifyReference(reference);
            return renderSuccess(bo);
        }
        int bool = iMaterialService.addOrModifyMaterial(material);
        return renderSuccess(bool);
    }

    /**
     * 查询材料
     * @return
     */
    @GetMapping("getMaterialList")
    public Object getMaterialList(Integer page,Integer limit, Integer pid, String mname){
        Page pages = iMaterialService.getMaterialList(page,limit,pid,mname);
        return renderSuccess(pages.getTotal(),pages.getRecords());
    }

    /***
     * 查询本场未用完饲料
     * @param pid
     * @return
     */
    @PostMapping("getUnfinishedMaterialList")
    public Object getUnfinishedMaterialList(Integer pid){
        List list = iMaterialService.getUnfinishedMaterialList(pid);
        return renderSuccess(list);
    }

    /***
     * 添加修改使用材料记录
     * @param usemateriallog
     * @return
     */
    @PostMapping("addOrModifyUseMaterial")
    @Transactional
    public Object addOrModifyUseMaterial(Usemateriallog usemateriallog){
        Material material = iMaterialService.getMaterialBymNumber(usemateriallog.getMNumber());
        //材料使用量
        Integer musage;
        if(null == material.getMUsage()){
            musage = 0;
        }else{
            musage = material.getMUsage();
        }
        if(null == usemateriallog.getId()){
            if(material.getMCount() - musage < usemateriallog.getMUsage()){
                return renderError("材料剩余量不足本次消耗");
            }
            Material m = new Material();
            m.setMId(material.getMId());
            m.setMUsage(musage + usemateriallog.getMUsage());
            int bo = iMaterialService.addOrModifyMaterial(m);
            if(0 == bo){
                return renderError("操作失败");
            }
            int bool = iUsemateriallogService.addOrModifyUseMaterial(usemateriallog);
            return 1 == bool ? renderSuccess("添加成功") : renderError("添加失败");
        }else{
            Long id = usemateriallog.getId();
            Usemateriallog umObj = iUsemateriallogService.getUseMaterialById(id);
            //使用记录中的数量
            Integer musages = umObj.getMUsage();
            //本次要修改的使用数量
            Integer usage = usemateriallog.getMUsage();
            boolean bool = musages < usage;
            Integer result = bool ? usage - musages : musages - usage;
            Material m = new Material();
            m.setMId(material.getMId());
            if(bool){
                m.setMUsage(musage + result);
            }else{
                m.setMUsage(musage - result);
            }
            int bo = iMaterialService.addOrModifyMaterial(m);
            if(0 == bo){
                return renderError("修改失败");
            }
            int b = iUsemateriallogService.addOrModifyUseMaterial(usemateriallog);
            return b == 1 ? renderSuccess("修改成功") : renderError("修改失败");
        }
    }

    /***
     * 查询本场材料使用记录
     * @param page 当前页
     * @param limit   一页几条
     * @param pid    猪场ID
     * @param purpose  用途
     * @return
     */
    @GetMapping("getUseMaterialByList")
    public Object getUseMaterialByList(Integer page,Integer limit, Integer pid, String purpose){
        Page pages = iUsemateriallogService.getUseMaterialByList(page,limit,pid,purpose);
        return renderSuccess(pages.getTotal(),pages.getRecords());
    }
}
