package Lab2.enums;

import Lab2.Interfaces.SpaceComparable;

public enum Stalk_Root implements SpaceComparable<Stalk_Root> {
	bulbous,
	club,
	cup,
	equal,
	rhizomorphs,
	rooted,
	missing;

	public double distance(Stalk_Root comparable)
	{
		return this==comparable ? 0 : 1;
	}
}
