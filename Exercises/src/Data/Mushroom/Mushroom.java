package Data.Mushroom;

import Common.AttributeKey;
import Common.Classification;
import Common.Interfaces.Classifiable;
import Common.Interfaces.SpaceComparable;
import Common.NominalSpaceComparable;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;


/**
 * The Mushroom class is used to contain the data for each mushroom found in the datafile.
 * More info on each attribute in agaricus-lepiotaexplanation.txt.
 */
public class Mushroom implements Classifiable<SpaceComparable> {


    /**
     * The attribute to built a classifier for.
     * I.e. whether or not the mushroom is poisonous.
     */
    public Lab2.enums.Class_Label m_Class;

    public Lab2.enums.Cap_Shape m_cap_shape;

    public Lab2.enums.Cap_Surface m_cap_surface;

    public Lab2.enums.Cap_Color m_cap_color;

    public Lab2.enums.Bruises m_bruises;

    public Lab2.enums.Odor m_odor;

    public Lab2.enums.Gill_Attachment m_gill_attach;

    public Lab2.enums.Gill_Spacing m_gill_spacing;

    public Lab2.enums.Gill_Size m_gill_size;

    public Lab2.enums.Gill_Color m_gill_color;

    public Lab2.enums.Stalk_Shape m_stalk_shape;

    public Lab2.enums.Stalk_Root m_stalk_root;

    public Lab2.enums.Stalk_Surface_Above_Ring m_stalk_surface_above;

    public Lab2.enums.Stalk_Surface_Below_Ring m_stalk_surface_below;

    public Lab2.enums.Stalk_Color_Above_Ring m_stalk_color_above;

    public Lab2.enums.Stalk_Color_Below_Ring m_stalk_color_below;

    public Lab2.enums.Veil_Type m_veil_type;

    public Lab2.enums.Veil_Color m_veil_color;

    public Lab2.enums.Ring_Number m_ring_number;

    public Lab2.enums.Ring_Type m_ring_type;

    public Lab2.enums.Spore_Print_Color m_spore_color;

    public Lab2.enums.Population m_population;

    public Lab2.enums.Habitat m_habitat;

    private Map<AttributeKey, SpaceComparable> attributeValues;

    protected void buildMap() {
        attributeValues = new Hashtable<AttributeKey, SpaceComparable>() {{
            put(new AttributeKey<>(Lab2.enums.Cap_Shape.class), new NominalSpaceComparable(m_cap_shape));
            put(new AttributeKey<>(Lab2.enums.Cap_Color.class), new NominalSpaceComparable(m_cap_color));
            put(new AttributeKey<>(Lab2.enums.Cap_Surface.class), new NominalSpaceComparable(m_cap_surface));
            put(new AttributeKey<>(Lab2.enums.Cap_Color.class), new NominalSpaceComparable(m_cap_color));
            put(new AttributeKey<>(Lab2.enums.Bruises.class), new NominalSpaceComparable(m_bruises));
            put(new AttributeKey<>(Lab2.enums.Odor.class), new NominalSpaceComparable(m_odor));
            put(new AttributeKey<>(Lab2.enums.Gill_Attachment.class), new NominalSpaceComparable(m_gill_attach));
            put(new AttributeKey<>(Lab2.enums.Gill_Spacing.class), new NominalSpaceComparable(m_gill_spacing));
            put(new AttributeKey<>(Lab2.enums.Gill_Size.class), new NominalSpaceComparable(m_gill_size));
            put(new AttributeKey<>(Lab2.enums.Gill_Color.class), new NominalSpaceComparable(m_gill_color));
            put(new AttributeKey<>(Lab2.enums.Stalk_Shape.class), new NominalSpaceComparable(m_stalk_shape));
            put(new AttributeKey<>(Lab2.enums.Stalk_Root.class), new NominalSpaceComparable(m_stalk_root));
            put(new AttributeKey<>(Lab2.enums.Stalk_Surface_Above_Ring.class), new NominalSpaceComparable(m_stalk_surface_above));
            put(new AttributeKey<>(Lab2.enums.Stalk_Surface_Below_Ring.class), new NominalSpaceComparable(m_stalk_surface_below));
            put(new AttributeKey<>(Lab2.enums.Stalk_Color_Above_Ring.class), new NominalSpaceComparable(m_stalk_color_above));
            put(new AttributeKey<>(Lab2.enums.Stalk_Color_Below_Ring.class), new NominalSpaceComparable(m_stalk_color_below));
            put(new AttributeKey<>(Lab2.enums.Veil_Type.class), new NominalSpaceComparable(m_veil_type));
            put(new AttributeKey<>(Lab2.enums.Veil_Color.class), new NominalSpaceComparable(m_veil_color));
            put(new AttributeKey<>(Lab2.enums.Ring_Number.class), new NominalSpaceComparable(m_ring_number));
            put(new AttributeKey<>(Lab2.enums.Ring_Type.class), new NominalSpaceComparable(m_ring_type));
            put(new AttributeKey<>(Lab2.enums.Spore_Print_Color.class), new NominalSpaceComparable(m_spore_color));
            put(new AttributeKey<>(Lab2.enums.Population.class), new NominalSpaceComparable(m_population));
            put(new AttributeKey<>(Lab2.enums.Habitat.class), new NominalSpaceComparable(m_habitat));
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
        if (m_Class == Lab2.enums.Class_Label.edible)
            return Classification.negative;
        else
            return Classification.positive;
    }

    @Override
    public boolean checkClassification(Classification classification) {
        return getClassification() == classification;
    }
}
