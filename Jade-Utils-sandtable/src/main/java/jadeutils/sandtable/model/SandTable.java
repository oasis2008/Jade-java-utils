package jadeutils.sandtable.model;

import java.util.HashMap;
import java.util.Map;

public class SandTable {
	private int width = 65535;
	private int height = 65535;
	private Map<String, Area> areaMap = new HashMap<>();

	public SandTable(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void putArea(Area area) {
		StringBuffer sb = new StringBuffer();
		sb.append(area.getCordX()).append(",").append(area.getCordY());
		// System.out.print(sb.toString() + " | ");
		this.areaMap.put(sb.toString(), area);
	}

	public Area getArea(int x, int y) {
		StringBuffer sb = new StringBuffer();
		sb.append(x).append(",").append(y);
		// System.out.print(sb.toString()+" | ");
		return this.areaMap.get(sb.toString());
	}
}
