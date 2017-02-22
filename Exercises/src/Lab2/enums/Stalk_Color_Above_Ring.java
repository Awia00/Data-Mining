package Lab2.enums;

import Lab2.Interfaces.SpaceComparable;

public enum Stalk_Color_Above_Ring implements SpaceComparable<Stalk_Color_Above_Ring> {
	brown,
	buff,
	cinnamon,
	gray,
	orange,
	pink,
	red,
	white,
	yellow;

	public double distance(Stalk_Color_Above_Ring comparable)
	{
		return this==comparable ? 0 : 1;
	}
}
