package jadeutils.sandtable.model;

import java.util.HashMap;
import java.util.Map;

public class SandTable {
	private int maxWidth = 65535;
	private int maxLength = 65535;
	private Map<String, Area> areaMap = new HashMap<>();

	public SandTable() {
	}

	public SandTable(int maxWidth, int maxLength) {
		super();
		this.maxWidth = maxWidth;
		this.maxLength = maxLength;
	}

	public int getMaxWidth() {
		return maxWidth;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void putArea(Area area) {
	}
}
