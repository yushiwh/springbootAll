package com.jzt.cms.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ehaoyao.filecenter.service.AliyunUploadService;
import com.jzt.cms.util.ApplicationContextUtils;

import freemarker.template.Template;

@Named
@Service
public class FreeMarkService {

	@Reference
	private AliyunUploadService aliyunUploadService;

	@Value("${aliyun.baseurl}")
	private String aliyunurl;

	@Transactional
	public void buildHtml(String templatePath, String htmlPath, Map<String, Object> data) {
		try {
			FreeMarkerConfigurer config = ApplicationContextUtils.getService(FreeMarkerConfigurer.class);
			Template template = config.getConfiguration().getTemplate(templatePath);
			String path = "";
			if (htmlPath != null && htmlPath != "") {
				path = htmlPath.substring(0, htmlPath.indexOf('.'));
				String houzui = htmlPath.substring(htmlPath.indexOf('.'), htmlPath.length());
				path = path + houzui;
			}
			htmlPath = path;
			File htmlFile = new File(htmlPath);
			File htmlDirectory = htmlFile.getParentFile();
			if (!htmlDirectory.exists()) {
				htmlDirectory.mkdirs();
			}
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), "UTF-8"));
			template.process(data, out);

			out.flush();
			out.close();

			// 创建文件输入流对象
			FileInputStream is = new FileInputStream(htmlPath);
			// 设定读取的字节数
			int n = 512;
			byte buffer[] = new byte[n];
			// 读取输入流
			while ((is.read(buffer, 0, n) != -1) && (n > 0)) {
			}
			// 关闭输入流
			is.close();
			String str = aliyunUploadService.upload(buffer, "html", null);
			System.out.println("str==" + aliyunurl + str);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
