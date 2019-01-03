package com.kkdz.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

public class CompareWar {
	public void compare(Config config) throws Exception {
		if (!checkPath(config)) {
			throw new Exception("path error");
		}
		Map<String, String> oldMap = extract(config.getOldWar());
		Map<String, String> newMap = extract(config.getNewWar());
		List<String> changedFileNames = new ArrayList<>();
		for (Map.Entry<String, String> entry : newMap.entrySet()) {
			String newValue = entry.getValue();
			String oldValue = oldMap.get(entry.getKey());
			if (!newValue.equals(oldValue)) {
				changedFileNames.add(entry.getKey());
			}
		}
		writeChangedFile(changedFileNames, config.getNewWar(), config.getOutDir());
	}

	private void writeChangedFile(List<String> changedFileNames, File newWar, File outDir) throws Exception {
		List<String> names = new ArrayList<>(changedFileNames);
		ZipInputStream zipInputStream = null;
		try {
			zipInputStream = new ZipInputStream(new FileInputStream(newWar));
			ZipEntry entry = null;
			while ((entry = zipInputStream.getNextEntry()) != null) {
				if (entry.isDirectory()) {
					continue;
				}
				String name = entry.getName();
				if (!names.contains(name)) {
					continue;
				}
				OutputStream output = null;
				try {
					File file = new File(outDir, name);
					if (!file.getParentFile().exists()) {
						file.getParentFile().mkdirs();
					}
					output = new FileOutputStream(file);
					IOUtils.copy(zipInputStream, output);
					names.remove(name);
				} finally {
					if (output != null) {
						output.close();
					}
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (zipInputStream != null) {
				zipInputStream.close();
			}
		}
	}

	private Map<String, String> extract(File war) throws Exception {
		Map<String, String> map = new HashMap<>();
		ZipInputStream zipInputStream = null;
		ZipEntry entry = null;
		try {
			zipInputStream = new ZipInputStream(new FileInputStream(war));
			while ((entry = zipInputStream.getNextEntry()) != null) {
				if (entry.isDirectory()) {
					continue;
				}
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				IOUtils.copy(zipInputStream, byteArrayOutputStream);
				byte[] bs = byteArrayOutputStream.toByteArray();
				map.put(entry.getName(), DigestUtils.md5Hex(bs));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (zipInputStream != null) {
				zipInputStream.close();
			}
		}
		return map;
	}

	private boolean checkPath(Config config) {
		if (!config.getNewWar().exists() || !config.getOldWar().exists()) {
			return false;
		}
		File outDir = config.getOutDir();
		if (!outDir.exists()) {
			outDir.mkdirs();
		}
		return true;
	}
}
