package hk.com.easyview.risk.controller;



import hk.com.easyview.risk.service.ITbExchangeRateService;
import hk.com.easyview.risk.pojo.TbExchangeRate;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hong.generate.common.PageResult;
import com.hong.generate.common.Result;
import com.hong.generate.common.StatusCode;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
    import org.springframework.web.bind.annotation.RestController;

/**
* <p>
    * 汇率数据录入表 前端控制器
    * </p>
*
* @author Hunter
* @since 2021-12-24
*/

@Slf4j
@Api(tags = "汇率数据录入表")
@RestController
@RequestMapping("//tb-exchange-rate")
public class TbExchangeRateController {

    @Autowired
    public ITbExchangeRateService tbExchangeRateService;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@RequestBody TbExchangeRate tbExchangeRate){
        tbExchangeRateService.save(tbExchangeRate);
        return new Result(StatusCode.SUCCESS,"保存成功");
    }

    @ApiOperation(value = "根据id删除")
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        tbExchangeRateService.removeById(id);
        return new Result(StatusCode.SUCCESS,"删除成功");
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/get")
    public Result list(@RequestBody TbExchangeRate tbExchangeRate){
        List<TbExchangeRate> tbExchangeRateList = tbExchangeRateService.list(new QueryWrapper<>(tbExchangeRate));
        return new Result(StatusCode.SUCCESS,"查询成功",tbExchangeRateList);
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Object list(@PathVariable("pageNum")Long pageNum, @PathVariable("pageSize")Long pageSize){
        IPage<TbExchangeRate> page = tbExchangeRateService.page(
        new Page<>(pageNum, pageSize), null);
        return new Result(StatusCode.SUCCESS,"查询成功",new PageResult<>(page.getTotal(),page.getRecords()));
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") String id){
        TbExchangeRate tbExchangeRate = tbExchangeRateService.getById(id);
        return new Result(StatusCode.SUCCESS,"查询成功",tbExchangeRate);
    }

    @ApiOperation(value = "根据id修改")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable("id") String id, @RequestBody TbExchangeRate tbExchangeRate){
        tbExchangeRate.setId(id);
        tbExchangeRateService.updateById(tbExchangeRate);
        return new Result(StatusCode.SUCCESS,"更新成功");
     }

}
