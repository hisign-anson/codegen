<#include "include/head.ftl">
package ${serviceImplDir};

import ${MapperDir}.${Po}Mapper;
import ${ModelDir}.${Po};
import ${VoDir}.${Po}Model;
import ${serviceDir}.${Po}Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ${bfun}.benum.BaseEnum;
import ${bfun}.bif.*;
import ${bfun}.butils.JsonResultUtil;
import ${bfun}.bexception.BusinessException;
import ${bfun}.bmodel.*;


 /**
 * 《${tableLabel}》 业务逻辑服务类
 * @author ${copyright.author}
 *
 */
@Service("${po}Service")
public class ${Po}ServiceImpl extends BaseServiceImpl<${Po},${Po}Model, ${pkcolumnSimpleClassName}> implements ${Po}Service{

	@Autowired
	protected ${Po}Mapper ${po}Mapper;
	
	@Override
	protected BaseMapper<${Po}, ${Po}Model, ${pkcolumnSimpleClassName}> initMapper() {
		return ${po}Mapper;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public JsonResult add(List<${Po}> list) throws BusinessException {
		try {
			${po}Mapper.batchInsert(list);
		} catch (Exception e) {
			throw new BusinessException(BaseEnum.BusinessExceptionEnum.INSERT,e);
		}
		return JsonResultUtil.success();
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public JsonResult update(UpdateParams params) throws BusinessException {
		try {
			${po}Mapper.updateCustom(params);
		} catch (Exception e) {
			throw new BusinessException(BaseEnum.BusinessExceptionEnum.UPDATE,e);
		}
		return JsonResultUtil.success();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JsonResult delByIds(List<${pkcolumnSimpleClassName}> ids) throws BusinessException {
		try {
			${po}Mapper.deleteByIds(ids);
		} catch (Exception e) {
			throw new BusinessException(BaseEnum.BusinessExceptionEnum.DELETE,e);
		}
		return JsonResultUtil.success();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JsonResult delBy(Conditions conditions) throws BusinessException {
		try {
			${po}Mapper.deleteCustom(conditions);
		} catch (Exception e) {
			throw new BusinessException(BaseEnum.BusinessExceptionEnum.DELETE,e);
		}
		return JsonResultUtil.success();
	}

}