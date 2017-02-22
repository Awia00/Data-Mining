package Lab2.enums;

import Lab2.Interfaces.SpaceComparable;

public enum Veil_Color implements SpaceComparable<Veil_Color> {
	brown,
	orange,
	white,
	yellow;

	public double distance(Veil_Color comparable)
	{
		return this==comparable ? 0 : 1;
	}
}
