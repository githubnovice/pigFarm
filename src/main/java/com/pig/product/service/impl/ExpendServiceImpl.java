package com.pig.product.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig.product.entity.Expend;
import com.pig.product.mapper.ExpendMapper;
import com.pig.product.service.IExpendService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 * 日常支出 服务实现类
 * </p>
 *
 * @author ST
 * @since 2020-05-23
 */
@Service
@SuppressWarnings("unchecked")
public class ExpendServiceImpl extends ServiceImpl<ExpendMapper, Expend> implements IExpendService {

    @Resource
    private ExpendMapper expendMapper;

    /***
     * 添加修改日常支出
     * @param expend
     * @return
     */
    @Override
    public int addOrModifyExpend(Expend expend) {
        if(null == expend.getEId()){
            expend.setETime(new Date());
            return expendMapper.insert(expend);
        }
        return expendMapper.updateById(expend);
    }

    /***
     * 查询本场日常支出
     * @param curren
     * @param size
     * @param pid
     * @param ePurpose
     * @return
     */
    @Override
    public Page getExpendByList(Integer curren, Integer size, Integer pid, String ePurpose) {
        if(null == curren){
            curren = 1;
        }
        if(null == size){
            size = 10;
        }
        Page page = new Page(curren,size);
        if(null != ePurpose){
            ePurpose = "%"+ePurpose+"%";
        }
        page.setRecords(expendMapper.getExpendByList(page,pid,ePurpose));
        return page;
    }

    @Override
    public String getExpendPayCount(Integer pid) {
        return expendMapper.getExpendPayCount(pid,getTimesWeekmorning().toLocaleString(),getTimesWeeknight().toLocaleString());
    }
    // 获得本周一0点时间
    public Date getTimesWeekmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    // 获得本周日24点时间
    public Date getTimesWeeknight() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getTimesWeekmorning());
        cal.add(Calendar.DAY_OF_WEEK, 7);
        return cal.getTime();
    }
}
