package Lab2;

import java.util.*;

import Lab2.Interfaces.Attribute;
import Lab2.Interfaces.Classification;
import Lab2.Interfaces.SpaceComparable;
import Lab2.Interfaces.WithAttributes;
import Lab2.enums.*;


/**
 * The Mushroom class is used to contain the data for each mushroom found in the datafile.
 * More info on each attribute in agaricus-lepiotaexplanation.txt.
 *
 */
public class Mushroom implements WithAttributes<SpaceComparable>{


	/**
	 * The attribute to built a classifier for. 
	 * I.e. whether or not the mushroom is poisonous. 
	 */
	public Class_Label m_Class;
	
	public Cap_Shape m_cap_shape;
	
	public Cap_Surface m_cap_surface;
	
	public Cap_Color m_cap_color;
	
	public Bruises m_bruises;
	
	public Odor m_odor;
	
	public Gill_Attachment m_gill_attach;
	
	public Gill_Spacing m_gill_spacing;
	
	public Gill_Size m_gill_size;
	
	public Gill_Color m_gill_color;
	
	public Stalk_Shape m_stalk_shape;
	
	public Stalk_Root m_stalk_root;
	
	public Stalk_Surface_Above_Ring m_stalk_surface_above;
	
	public Stalk_Surface_Below_Ring m_stalk_surface_below;
	
	public Stalk_Color_Above_Ring m_stalk_color_above;
	
	public Stalk_Color_Below_Ring m_stalk_color_below;
	
	public Veil_Type m_veil_type;
	
	public Veil_Color m_veil_color;
	
	public Ring_Number m_ring_number;
	
	public Ring_Type m_ring_type;
	
	public Spore_Print_Color m_spore_color;
		
	public Population m_population;
	
	public Habitat m_habitat;

	private Map<Attribute, SpaceComparable> attributeValues;
	protected void buildMap()
    {
        attributeValues = new Hashtable<Attribute, SpaceComparable>(){{
            put(new Attribute(Cap_Shape.class), m_cap_shape);
            put(new Attribute(Cap_Color.class), m_cap_color);
            put(new Attribute(Cap_Surface.class), m_cap_surface);
            put(new Attribute(Cap_Color.class), m_cap_color);
            put(new Attribute(Bruises.class), m_bruises);
            put(new Attribute(Odor.class), m_odor);
            put(new Attribute(Gill_Attachment.class), m_gill_attach);
            put(new Attribute(Gill_Spacing.class), m_gill_spacing);
            put(new Attribute(Gill_Size.class), m_gill_size);
            put(new Attribute(Gill_Color.class), m_gill_color);
            put(new Attribute(Stalk_Shape.class), m_stalk_shape);
            put(new Attribute(Stalk_Root.class), m_stalk_root);
            put(new Attribute(Stalk_Surface_Above_Ring.class), m_stalk_surface_above);
            put(new Attribute(Stalk_Surface_Below_Ring.class),m_stalk_surface_below);
            put(new Attribute(Stalk_Color_Above_Ring.class), m_stalk_color_above);
            put(new Attribute(Stalk_Color_Below_Ring.class), m_stalk_color_below);
            put(new Attribute(Veil_Type.class), m_veil_type);
            put(new Attribute(Veil_Color.class), m_veil_color);
            put(new Attribute(Ring_Number.class), m_ring_number);
            put(new Attribute(Ring_Type.class), m_ring_type);
            put(new Attribute(Spore_Print_Color.class), m_spore_color);
            put(new Attribute(Population.class), m_population);
            put(new Attribute(Habitat.class), m_habitat);
        }};
    }

	@Override
	public Collection<Attribute> getAttributes() {
		return attributeValues.keySet();
	}

	@Override
	public SpaceComparable getValueOfAttribute(Attribute attribute) {
		return attributeValues.get(attribute);
	}

    @Override
    public Classification getClassification() {
        if(m_Class == Class_Label.edible)
            return Classification.negative;
        else
            return Classification.positive;
    }

    @Override
    public boolean checkClassification(Classification classification) {
        return getClassification() == classification;
    }
}
