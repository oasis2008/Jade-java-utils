package jadeutils.sandtable.tools;

import jadeutils.sandtable.model.SandTable;
import jadeutils.sandtable.status.Direction;

public class MoveUtils {

	public static int[] move(SandTable sandTable, int x, int y,
			Direction direction, int speed, int time) {
		int[] result = { x, y };
		if (direction == Direction.d4) {
			return result;
		} else {
			/* caculate the distance */
			int distance = speed * time;
			switch (direction) {
			case d1:
			case d3:
			case d7:
			case d9:
				double tmp = distance * Math.sin(Math.PI / 4);
				distance = (int) Math.ceil(tmp);
				break;
			default:
				// nothing
				break;
			}
			// 7.8.9
			// 5.4.6
			// 1.2.3
			/* caculate the direction */
			int xd = 1, yd = 1;
			switch (direction) {
			case d1:
				yd = -1;
				xd = -1;
				break;
			case d2:
				yd = -1;
				break;
			case d3:
				yd = -1;
				break;
			case d5:
				xd = -1;
				break;
			case d7:
				xd = -1;
				break;
			default:
				break;
			}
			/* wrap if out of map */
			int ns = x * xd;
			int ny = y * yd;
		}
		return result;
	}
}
