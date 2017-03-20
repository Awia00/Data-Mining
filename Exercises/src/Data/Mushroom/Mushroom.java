package Data.Mushroom;

import Common.DataTypes.BooleanNominal;
import Common.DataTypes.Nominal;
import Common.DataTypes.SpaceComparable;
import Common.Interfaces.ClassifiablePoint;
import Common.Interfaces.NDimensionalPoint;
import Data.Mushroom.Enums.*;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;


/**
 * The Mushroom class is used to contain the data for each mushroom found in the datafile.
 * More info on each attribute in agaricus-lepiotaexplanation.txt.
 */
public class Mushroom implements ClassifiablePoint<BooleanNominal> {

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

    private Map<Integer, SpaceComparable> attributeValues;

    protected void buildMap() {
        attributeValues = new Hashtable<Integer, SpaceComparable>() {{
            put(0, new Nominal(m_cap_shape));
            put(1, new Nominal(m_cap_color));
            put(2, new Nominal(m_cap_surface));
            put(3, new Nominal(m_cap_color));
            put(4, new Nominal(m_bruises));
            put(5, new Nominal(m_odor));
            put(6, new Nominal(m_gill_attach));
            put(7, new Nominal(m_gill_spacing));
            put(8, new Nominal(m_gill_size));
            put(9, new Nominal(m_gill_color));
            put(10, new Nominal(m_stalk_shape));
            put(11, new Nominal(m_stalk_root));
            put(12, new Nominal(m_stalk_surface_above));
            put(13, new Nominal(m_stalk_surface_below));
            put(14, new Nominal(m_stalk_color_above));
            put(15, new Nominal(m_stalk_color_below));
            put(16, new Nominal(m_veil_type));
            put(17, new Nominal(m_veil_color));
            put(18, new Nominal(m_ring_number));
            put(19, new Nominal(m_ring_type));
            put(20, new Nominal(m_spore_color));
            put(21, new Nominal(m_population));
            put(22, new Nominal(m_habitat));
        }};
    }

    @Override
    public SpaceComparable get(Integer key) {
        return attributeValues.get(key);
    }

    @Override
    public Set<Integer> keySet() {
        return attributeValues.keySet();
    }

    @Override
    public Collection<SpaceComparable> values() {
        return attributeValues.values();
    }

    @Override
    public Set<Map.Entry<Integer, SpaceComparable>> entrySet() {
        return attributeValues.entrySet();
    }

    @Override
    public NDimensionalPoint getDefaultPoint() {
        Mushroom mushroom = new Mushroom();
        mushroom.buildMap();
        return mushroom;
    }

    @Override
    public BooleanNominal getClassification() {
        return m_Class.equals(Class_Label.edible) ? BooleanNominal.negative() : BooleanNominal.positive();
    }

    @Override
    public boolean checkClassification(BooleanNominal other) {
        return getClassification().equals(other);
    }
}
