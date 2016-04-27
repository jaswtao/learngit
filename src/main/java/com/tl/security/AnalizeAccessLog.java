package com.tl.security;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tl.common.StringUtils;

public class AnalizeAccessLog {

	public static void main(String[] args) throws IOException, URISyntaxException {
		/*AnalizeAccessLog aal = new AnalizeAccessLog();
		aal.getRoot();*/
		try {
			analizeFile("E:\\project\\learngit\\src\\main\\resources\\blocklist.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void analizeFile(String path) throws Exception {
		if (StringUtils.isBlank(path)) {
			System.out.println("empty path!");
		}
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			File file = new File(path);
			br = new BufferedReader(new FileReader(file));
			File tofile = new File("E:\\project\\learngit\\src\\main\\resources\\script.sh");
			if (tofile.exists()) {
				tofile.delete();
			}
			tofile.createNewFile();
			Set<String> ipSet = new HashSet<>();
			for (String line; (line = br.readLine()) != null;) {
				//System.out.println(line);
				String ip = getIp(line);
				ipSet.add(ip);
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
				//System.out.println(result.toString());
				bw.append(result.toString());
				bw.newLine();
			}
			bw.append("service iptables save");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
		m.find();
		return m.group(0);
	}

}
