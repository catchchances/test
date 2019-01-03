package com.kkdz.test;

import java.io.File;

public class Config {
	private File oldWar;
	private File newWar;
	private File outDir;

	public Config(String oldWarPath, String newWarPath, String outDir) {
		super();
		this.oldWar = new File(oldWarPath);
		this.newWar = new File(newWarPath);
		this.outDir = new File(outDir);
	}

	public File getOldWar() {
		return oldWar;
	}

	public void setOldWar(File oldWar) {
		this.oldWar = oldWar;
	}

	public File getNewWar() {
		return newWar;
	}

	public void setNewWar(File newWar) {
		this.newWar = newWar;
	}

	public File getOutDir() {
		return outDir;
	}

	public void setOutDir(File outDir) {
		this.outDir = outDir;
	}

}
