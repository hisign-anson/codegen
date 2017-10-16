<#include "include/head.ftl">
<#assign isCbd = false>
<#assign idJavaType="long">
<#list table.columnList as column>
    <#if column.columnName?lower_case=='creator'>
        <#assign isCbd = true>
    </#if>
    <#if column.columnName?lower_case=='id'>
        <#assign idJavaType = column.columnSimpleClassName>
    </#if>
</#list>

package ${VoDir};

import java.io.Serializable;
import com.alibaba.fastjson.JSON;
import java.util.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 《${tableLabel}》 实体
 * @author ${copyright.author}
 *
 */
@ApiModel(value = "${tableLabel}")
public class ${Po}Model implements Serializable {

	private static final long serialVersionUID = 1L;

	<#list table.columnList as column>
    @ApiModelProperty(value = "${column.columnLabel}")
	private ${column.columnSimpleClassName} ${column.columnName?uncap_first}; //${column.columnLabel}
	
	</#list>
    
	/**
	 *默认空构造函数
	 */
	public ${Po}Model() {
		super();
	}
	 
	<#list table.columnList as column>
	/**
	 * @return ${column.fieldName} ${column.columnLabel}
	 */
	public ${column.columnSimpleClassName} get${column.columnName?cap_first}(){
		return this.${column.columnName?uncap_first};
	}
	/**
	 * @param ${column.fieldName} ${column.columnLabel}
	 */
	public void set${column.columnName?cap_first}(${column.columnSimpleClassName} ${column.columnName?uncap_first}){
		this.${column.columnName?uncap_first} = ${column.columnName?uncap_first};
	}
	</#list>
	
	@Override
	public String toString(){
		return JSON.toJSONString(this);
	}
}
