package Lab2.enums;

import Lab2.Interfaces.SpaceComparable;

public enum Cap_Surface implements SpaceComparable<Cap_Surface> {
	fibrous,
	grooves,
	scaly,
	smooth;

	public double distance(Cap_Surface comparable)
	{
		return this==comparable ? 0 : 1;
	}
}
