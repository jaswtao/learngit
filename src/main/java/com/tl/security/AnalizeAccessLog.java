package com.tl.security;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tl.common.CustomizeProperties;
import com.tl.common.StringUtils;
import com.tl.exceptions.BaseException;

public class AnalizeAccessLog {

	public static void main(String[] args) {
		/*
		 * AnalizeAccessLog aal = new AnalizeAccessLog(); aal.getRoot();
		 */
		String projectPath = System.getProperty("user.dir");
		System.out.println("project path: " + projectPath);
		Properties properties = null;
		String sourcePath = "";
		String outputPath = "";
		String iptablePath = "";
		try {
			String configPath = projectPath + File.separator + "config.properties";
			System.out.println("config path: " + configPath);
			properties = CustomizeProperties.initProperties(configPath);
			sourcePath = properties.getProperty("security.log.filename");
			outputPath = properties.getProperty("security.output.filename");
			iptablePath = properties.getProperty("security.iptable.path");
		} catch (Exception e) {
			sourcePath = projectPath + "/src/main/resources/blocklist.txt";
			outputPath = projectPath + "/src/main/resources/script.sh";
			iptablePath = "";
			System.out.println("error: " + e.getMessage());
		}
		System.out.println("source path: " + sourcePath);
		System.out.println("output path: " + outputPath);
		try {
			analizeFile(sourcePath, outputPath, iptablePath);
		} catch (Exception e) {
			System.out.println("error: " + e.getMessage());
		}
	}

	public static void analizeFile(String sourcePath, String outputPath, String iptablePath) throws Exception {
		if (StringUtils.isBlank(sourcePath) || StringUtils.isBlank(outputPath)) {
			throw new BaseException("empty path!");
		}
		List<String> container = new ArrayList<>();
		BufferedReader iptableReader = null;
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			File file = new File(sourcePath); // log file
			if (!StringUtils.isBlank(iptablePath)) {
				File iptableFile = new File(iptablePath);
				iptableReader = new BufferedReader(new FileReader(iptableFile));
				for (String line; (line = iptableReader.readLine()) != null;) {
					String ip = getIp(line);
					container.add(ip);
				}
			}
			br = new BufferedReader(new FileReader(file));
			File tofile = new File(outputPath); // script file
			if (tofile.exists()) {
				tofile.delete();
			}
			tofile.createNewFile();
			Set<String> ipSet = new HashSet<>();
			for (String line; (line = br.readLine()) != null;) {
				// System.out.println(line);
				String ip = getIp(line);
				if (!container.contains(ip)) {
					ipSet.add(ip);
				}
			}
			
			bw = new BufferedWriter(new FileWriter(tofile));
			StringBuilder result = new StringBuilder();
			bw.append("#!/bin/bash");
			bw.newLine();
			for (String ip : ipSet) {
				result.setLength(0);
				result.append("iptables -I INPUT -s ");
				result.append(ip);
				result.append(" -j DROP");
				// System.out.println(result.toString());
				bw.append(result.toString());
				bw.newLine();
			}
			if (ipSet.isEmpty()) {
				bw.append("echo \"nothing update!\"");
			} else {
				bw.append("service iptables save");	
			}
			bw.newLine();
			bw.append("files=`ls /usr/tomcat7/logs/localhost_access_log.*`");
			bw.newLine();
			bw.append("for file in $files");
			bw.newLine();
			bw.append("do");
			bw.newLine();
			bw.append("> $file");
			bw.newLine();
			bw.append("done");
			//bw.append(" > ").append("/usr/tomcat7/logs/localhost_access_log.*");
			System.out.println("mission complete!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (iptableReader != null) {
				iptableReader.close();
			}
			if (br != null) {
				br.close();
			}
			if (bw != null) {
				bw.close();
			}
		}
	}

	public void getRoot() throws IOException, URISyntaxException {
		URL u = getClass().getProtectionDomain().getCodeSource().getLocation();
		File f = new File(u.toURI());
		System.out.println(f.getParent());
	}

	public static String getIp(String target) {
		Matcher m = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}").matcher(target);
		try {
			m.find();
			return m.group(0);
		} catch (Exception e) {
			return "";
		}

	}

}
