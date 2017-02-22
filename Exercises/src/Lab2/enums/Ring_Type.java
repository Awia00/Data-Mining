package Lab2.enums;

import Lab2.Interfaces.SpaceComparable;

public enum Ring_Type implements SpaceComparable<Ring_Type> {
	cobwebby,
	evanescent,
	flaring,
	large,
	none,
	pendant,
	sheathing,
	zone;

	public double distance(Ring_Type comparable)
	{
		return this==comparable ? 0 : 1;
	}
}
