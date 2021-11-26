package hk.com.easyview.risk.controller;



import hk.com.easyview.risk.service.ITsAccountService;
import hk.com.easyview.risk.pojo.TsAccount;
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
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.web.bind.annotation.*;
import java.util.List;
    import org.springframework.web.bind.annotation.RestController;

/**
* <p>
    * 账户信息表 前端控制器
    * </p>
*
* @author Hunter
* @since 2021-11-22
*/

@Slf4j
@Api(tags = "账户信息表")
@RestController
@RequestMapping("//ts-account")
public class TsAccountController {


    @Autowired
    public ITsAccountService tsAccountService;

    @ReadOperation
    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@RequestBody TsAccount tsAccount){
        tsAccountService.save(tsAccount);
        return new Result(StatusCode.SUCCESS,"保存成功");
    }

    @ApiOperation(value = "根据id删除")
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        tsAccountService.removeById(id);
        return new Result(StatusCode.SUCCESS,"删除成功");
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/get")
    public Result list(@RequestBody TsAccount tsAccount){
        List<TsAccount> tsAccountList = tsAccountService.list(new QueryWrapper<>(tsAccount));
        return new Result(StatusCode.SUCCESS,"查询成功",tsAccountList);
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Object list(@PathVariable("pageNum")Long pageNum, @PathVariable("pageSize")Long pageSize){
        IPage<TsAccount> page = tsAccountService.page(
        new Page<>(pageNum, pageSize), null);
        return new Result(StatusCode.SUCCESS,"查询成功",new PageResult<>(page.getTotal(),page.getRecords()));
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") String id){
        TsAccount tsAccount = tsAccountService.getById(id);
        return new Result(StatusCode.SUCCESS,"查询成功",tsAccount);
    }

    @ApiOperation(value = "根据id修改")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable("id") String id, @RequestBody TsAccount tsAccount){
        tsAccount.setId(id);
        tsAccountService.updateById(tsAccount);
        return new Result(StatusCode.SUCCESS,"更新成功");
     }

}
