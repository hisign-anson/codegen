<#include "include/head.ftl">
package ${restDir};

import ${ModelDir}.${Po};
import ${serviceDir}.${Po}Service;
import ${VoDir}.${Po}Model;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import ${bfun}.bif.*;
import ${bfun}.butils.JsonResultUtil;
import ${bfun}.bexception.BusinessException;
import ${bfun}.bmodel.*;


 /**
 * 《${tableLabel}》 rest服务类
 * @author ${copyright.author}
 *
 */
@RestController
public class ${Po}Rest extends BaseRest<${Po}, ${Po}Model, ${pkcolumnSimpleClassName}, ${Po}Service> {

	@Override
	@Autowired
	@Resource(name = "${po}Service")
	public void setBaseService(${Po}Service baseService) {
		super.setBaseService(baseService);
	}
}