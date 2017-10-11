package com.xhh.codegen.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.xhh.codegen.model.InOutType;
import com.xhh.codegen.model.OutputModel;
import com.xhh.codegen.service.BuildConfig;
import com.xhh.codegen.service.Builder;
import com.xhh.codegen.utils.StringTemplateLoader;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * ����������
 * @author ������  360138308@qq.com
 *
 */
public class CodeBuilder implements Builder {
	private static Logger log = Logger.getLogger(Builder.class);
	
	private String outputEncoding = "UTF-8";
	private Locale locale = Locale.CHINA;
	private Map<String,Object> dataModelMap = new LinkedHashMap<String,Object>();
	private Map<String,OutputModel> outputMap = new LinkedHashMap<String, OutputModel>();
	
	/**
	 * ����ָ��������ģ�ͼ��Ϻ����ģ�ͼ���ʵ����һ�������ļ�������
	 * @param dataModelMap ����ģ�ͼ���
	 * @param outputMap ���ģ�ͼ���
	 */
	public CodeBuilder(Map<String, Object> dataModelMap,
			Map<String, OutputModel> outputMap) {
		super();
		this.dataModelMap = dataModelMap;
		this.outputMap = outputMap;
	}
	
	/**
	 * ����һ����������ģ��ʵ����һ�������ļ�������
	 * @param buildConfig
	 */
	public CodeBuilder(BuildConfig buildConfig){
		this(buildConfig.getDataModel(),buildConfig.getOutputModel());
		if(buildConfig instanceof ProjectBuildConfig){
			ProjectBuildConfig pbConfig = (ProjectBuildConfig)buildConfig;
			if(pbConfig.isHasParseDataModel()==false){
				pbConfig.parseDataModel();
			}
			if(pbConfig.isHasParseOutputModel()==false){
				pbConfig.parseOutputModel();
			}
		}
		this.setOutputEncoding(buildConfig.getOutputEncoding());
	}

	/**
	 * @return ��ȡ��ǰ�ı��ػ���Ϣ
	 */
	public Locale getLocale() {
		return locale;
	}

	/**
	 * @param locale ���õ�ǰ�ı��ػ���Ϣ
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	/**
	 * @return ��ȡ�ļ�����ı�������
	 */
	public String getOutputEncoding() {
		return outputEncoding;
	}

	/**
	 * @param outputEncoding �����ļ�����ı�������
	 */
	public void setOutputEncoding(String outputEncoding) {
		this.outputEncoding = outputEncoding;
	}

	/**
	 * @return ��ȡ��ǰ������ģ�ͼ���
	 */
	public Map<String, Object> getDataModelMap() {
		return dataModelMap;
	}
	/**
	 * @param dataModelMap ���õ�ǰ������ģ�ͼ���
	 */
	public void setDataModelMap(Map<String, Object> dataModelMap) {
		this.dataModelMap = dataModelMap;
	}
	/**
	 * @return ��ȡ��ǰ�����ģ�ͼ���
	 */
	public Map<String, OutputModel> getOutputMap() {
		return outputMap;
	}
	/**
	 * @param outputMap ���õ�ǰ�����ģ�ͼ���
	 */
	public void setOutputMap(Map<String, OutputModel> outputMap) {
		this.outputMap = outputMap;
	}
	
	
	/***
	 * ���ɴ����ļ�������
	 */
	public Map<String, OutputModel> build(){
		Template tp = null;
		PrintWriter pw = null;
		Configuration cfg = new Configuration();
		File templateFile, outputFile;
		try {
			cfg.setEncoding(locale, outputEncoding);
			for (Entry<String, OutputModel>  entry : outputMap.entrySet()) {
				if(entry.getValue().isDisabled()){
					continue; //���õ����ģ�Ͳ�������
				}
				if(entry.getValue().getTemplateModel().getType()==InOutType.FILE){
					templateFile = new File(entry.getValue().getTemplateModel().getTemplate());
					if(templateFile.exists()==false){
						throw new IOException(String.format("ģ���ļ�%s�����ڣ�",templateFile));
					}
					cfg.setDirectoryForTemplateLoading(templateFile.getParentFile());
					tp = cfg.getTemplate(templateFile.getName(), locale);
				}else{
					cfg.setTemplateLoader(new StringTemplateLoader(entry.getValue().getTemplateModel().getTemplate())); 
					tp = cfg.getTemplate(""); 
				}
				tp.setEncoding(outputEncoding);
				if(entry.getValue().getType()==InOutType.FILE){
					outputFile = new File(entry.getValue().getOutput());
					if(outputFile.getParentFile().exists()==false){
						outputFile.getParentFile().mkdirs();
					}
					pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(outputFile), outputEncoding));
					tp.process(dataModelMap, pw);
					pw.close();
					log.debug("����������ļ�="+outputFile.getAbsolutePath());
				}else{
					StringWriter writer = new StringWriter(); 
					tp.process(dataModelMap, writer);
					entry.getValue().setOutput(writer.toString());
				}
			}
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(pw!=null){
				pw.close();
			}
		}
		
		return outputMap;
	}
}
