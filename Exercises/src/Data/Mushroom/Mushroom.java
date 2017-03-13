package Data.Mushroom;

import Common.AttributeKey;
import Common.Classification;
import Common.Interfaces.Classifiable;
import Common.Interfaces.SpaceComparable;
import Common.NominalSpaceComparable;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;
import Data.Mushroom.Enums.*;


/**
 * The Mushroom class is used to contain the data for each mushroom found in the datafile.
 * More info on each attribute in agaricus-lepiotaexplanation.txt.
 */
public class Mushroom implements Classifiable<SpaceComparable> {


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

    private Map<AttributeKey, SpaceComparable> attributeValues;

    protected void buildMap() {
        attributeValues = new Hashtable<AttributeKey, SpaceComparable>() {{
            put(new AttributeKey<>(Cap_Shape.class), new NominalSpaceComparable(m_cap_shape));
            put(new AttributeKey<>(Cap_Color.class), new NominalSpaceComparable(m_cap_color));
            put(new AttributeKey<>(Cap_Surface.class), new NominalSpaceComparable(m_cap_surface));
            put(new AttributeKey<>(Cap_Color.class), new NominalSpaceComparable(m_cap_color));
            put(new AttributeKey<>(Bruises.class), new NominalSpaceComparable(m_bruises));
            put(new AttributeKey<>(Odor.class), new NominalSpaceComparable(m_odor));
            put(new AttributeKey<>(Gill_Attachment.class), new NominalSpaceComparable(m_gill_attach));
            put(new AttributeKey<>(Gill_Spacing.class), new NominalSpaceComparable(m_gill_spacing));
            put(new AttributeKey<>(Gill_Size.class), new NominalSpaceComparable(m_gill_size));
            put(new AttributeKey<>(Gill_Color.class), new NominalSpaceComparable(m_gill_color));
            put(new AttributeKey<>(Stalk_Shape.class), new NominalSpaceComparable(m_stalk_shape));
            put(new AttributeKey<>(Stalk_Root.class), new NominalSpaceComparable(m_stalk_root));
            put(new AttributeKey<>(Stalk_Surface_Above_Ring.class), new NominalSpaceComparable(m_stalk_surface_above));
            put(new AttributeKey<>(Stalk_Surface_Below_Ring.class), new NominalSpaceComparable(m_stalk_surface_below));
            put(new AttributeKey<>(Stalk_Color_Above_Ring.class), new NominalSpaceComparable(m_stalk_color_above));
            put(new AttributeKey<>(Stalk_Color_Below_Ring.class), new NominalSpaceComparable(m_stalk_color_below));
            put(new AttributeKey<>(Veil_Type.class), new NominalSpaceComparable(m_veil_type));
            put(new AttributeKey<>(Veil_Color.class), new NominalSpaceComparable(m_veil_color));
            put(new AttributeKey<>(Ring_Number.class), new NominalSpaceComparable(m_ring_number));
            put(new AttributeKey<>(Ring_Type.class), new NominalSpaceComparable(m_ring_type));
            put(new AttributeKey<>(Spore_Print_Color.class), new NominalSpaceComparable(m_spore_color));
            put(new AttributeKey<>(Population.class), new NominalSpaceComparable(m_population));
            put(new AttributeKey<>(Habitat.class), new NominalSpaceComparable(m_habitat));
        }};
    }

    @Override
    public Collection<AttributeKey> getAttributes() {
        return attributeValues.keySet();
    }

    @Override
    public SpaceComparable getValueOfAttribute(AttributeKey attributeKey) {
        return attributeValues.get(attributeKey);
    }

    @Override
    public Classification getClassification() {
        if (m_Class == Class_Label.edible)
            return Classification.negative;
        else
            return Classification.positive;
    }

    @Override
    public boolean checkClassification(Classification classification) {
        return getClassification() == classification;
    }
}
