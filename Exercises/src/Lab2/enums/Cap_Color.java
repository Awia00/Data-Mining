package Lab2.enums;

import Lab2.Interfaces.SpaceComparable;

public enum Cap_Color implements SpaceComparable<Cap_Color> {
	brown,
	buff,
	cinnamon,
	gray,
	green,
	pink,
	purple,
	red,
	white,
	yellow;

	public double distance(Cap_Color comparable)
	{
		return this==comparable ? 0 : 1;
	}
}
