package Data.Mushroom;

import Data.CSVFileReader;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The MushroomDataLoader class is used to convert the string data loaded in from the text file
 * to Mushroom objects.
 * The MushroomDataLoader class makes use of the CSVFileReader class to accomplish this.
 */
public class MushroomDataLoader {

    public static ArrayList<Mushroom> LoadData() {
        ArrayList<Mushroom> datalist = new ArrayList<>();

        //First step load in data from the text file.
        try {
            String[][] data = CSVFileReader.readDataFile("Resources/agaricus-lepiotadata.txt", ",", "", false);

            for (int i = 0; i < data.length; i++) {
                Mushroom mushroomToAdd = new Mushroom();

                mushroomToAdd.m_Class = loadMushroomClass(data[i][0]);
                mushroomToAdd.m_cap_shape = loadCapShape(data[i][1]);
                mushroomToAdd.m_cap_surface = loadCapSurface(data[i][2]);
                mushroomToAdd.m_cap_color = loadCapColor(data[i][3]);
                mushroomToAdd.m_bruises = loadBruises(data[i][4]);
                mushroomToAdd.m_odor = loadOdor(data[i][5]);
                mushroomToAdd.m_gill_attach = loadGillAttachment(data[i][6]);
                mushroomToAdd.m_gill_spacing = loadGillSpacing(data[i][7]);
                mushroomToAdd.m_gill_size = loadGillSize(data[i][8]);
                mushroomToAdd.m_gill_color = loadGillColor(data[i][9]);
                mushroomToAdd.m_stalk_shape = loadStalkShape(data[i][10]);
                mushroomToAdd.m_stalk_root = loadStalkRoot(data[i][11]);
                mushroomToAdd.m_stalk_surface_above = loadStalkSurfaceAbove(data[i][12]);
                mushroomToAdd.m_stalk_surface_below = loadStalkSurfaceBelow(data[i][13]);
                mushroomToAdd.m_stalk_color_above = loadStalkColorAbove(data[i][14]);
                mushroomToAdd.m_stalk_color_below = loadStalkColorBelow(data[i][15]);
                mushroomToAdd.m_veil_type = loadVeilType(data[i][16]);
                mushroomToAdd.m_veil_color = loadVeilColor(data[i][17]);
                mushroomToAdd.m_ring_number = loadRingNumber(data[i][18]);
                mushroomToAdd.m_ring_type = loadRingType(data[i][19]);
                mushroomToAdd.m_spore_color = loadSporePrintColor(data[i][20]);
                mushroomToAdd.m_population = loadPopulation(data[i][21]);
                mushroomToAdd.m_habitat = loadHabitat(data[i][22]);

                mushroomToAdd.buildMap();

                datalist.add(mushroomToAdd);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return datalist;

    }

    private static Lab2.enums.Class_Label loadMushroomClass(String m_class) {
        if (m_class.equals("e")) {
            return Lab2.enums.Class_Label.edible;
        } else if (m_class.equals("p")) {
            return Lab2.enums.Class_Label.poisonous;
        }

        return null;
    }

    private static Lab2.enums.Cap_Shape loadCapShape(String cap_shape) {
        if (cap_shape.equals("b")) {
            return Lab2.enums.Cap_Shape.bell;
        } else if (cap_shape.equals("c")) {
            return Lab2.enums.Cap_Shape.conical;
        } else if (cap_shape.equals("x")) {
            return Lab2.enums.Cap_Shape.convex;
        } else if (cap_shape.equals("f")) {
            return Lab2.enums.Cap_Shape.flat;
        } else if (cap_shape.equals("k")) {
            return Lab2.enums.Cap_Shape.knobbed;
        } else if (cap_shape.equals("s")) {
            return Lab2.enums.Cap_Shape.sunken;
        }

        return null; //INtentional, if we get down here something went wrong.
    }

    private static Lab2.enums.Cap_Surface loadCapSurface(String cap_surface) {
        if (cap_surface.equals("f")) {
            return Lab2.enums.Cap_Surface.fibrous;
        } else if (cap_surface.equals("g")) {
            return Lab2.enums.Cap_Surface.grooves;
        } else if (cap_surface.equals("y")) {
            return Lab2.enums.Cap_Surface.scaly;
        } else if (cap_surface.equals("s")) {
            return Lab2.enums.Cap_Surface.smooth;
        }
        return null;
    }

    private static Lab2.enums.Cap_Color loadCapColor(String cap_color) {
        if (cap_color.equals("n")) {
            return Lab2.enums.Cap_Color.brown;
        } else if (cap_color.equals("b")) {
            return Lab2.enums.Cap_Color.buff;
        } else if (cap_color.equals("c")) {
            return Lab2.enums.Cap_Color.cinnamon;
        } else if (cap_color.equals("g")) {
            return Lab2.enums.Cap_Color.gray;
        } else if (cap_color.equals("r")) {
            return Lab2.enums.Cap_Color.green;
        } else if (cap_color.equals("p")) {
            return Lab2.enums.Cap_Color.pink;
        } else if (cap_color.equals("u")) {
            return Lab2.enums.Cap_Color.purple;
        } else if (cap_color.equals("e")) {
            return Lab2.enums.Cap_Color.red;
        } else if (cap_color.equals("w")) {
            return Lab2.enums.Cap_Color.white;
        } else if (cap_color.equals("y")) {
            return Lab2.enums.Cap_Color.yellow;
        }
        return null;
    }

    private static Lab2.enums.Bruises loadBruises(String bruises) {
        if (bruises.equals("t")) {
            return Lab2.enums.Bruises.bruises;
        } else if (bruises.equals("f")) {
            return Lab2.enums.Bruises.no_bruises;
        }
        return null;

    }

    private static Lab2.enums.Odor loadOdor(String odor) {
        if (odor.equals("a")) {
            return Lab2.enums.Odor.almond;
        } else if (odor.equals("l")) {
            return Lab2.enums.Odor.anise;
        } else if (odor.equals("c")) {
            return Lab2.enums.Odor.creosote;
        } else if (odor.equals("y")) {
            return Lab2.enums.Odor.fishy;
        } else if (odor.equals("f")) {
            return Lab2.enums.Odor.foul;
        } else if (odor.equals("m")) {
            return Lab2.enums.Odor.musty;
        } else if (odor.equals("n")) {
            return Lab2.enums.Odor.none;
        } else if (odor.equals("p")) {
            return Lab2.enums.Odor.pungent;
        } else if (odor.equals("s")) {
            return Lab2.enums.Odor.spicy;
        }

        return null;
    }

    private static Lab2.enums.Gill_Attachment loadGillAttachment(String gill_att) {
        if (gill_att.equals("a")) {
            return Lab2.enums.Gill_Attachment.attached;
        } else if (gill_att.equals("d")) {
            return Lab2.enums.Gill_Attachment.descending;
        } else if (gill_att.equals("f")) {
            return Lab2.enums.Gill_Attachment.free;
        } else if (gill_att.equals("n")) {
            return Lab2.enums.Gill_Attachment.notched;
        }

        return null;
    }

    private static Lab2.enums.Gill_Spacing loadGillSpacing(String gill_space) {
        if (gill_space.equals("c")) {
            return Lab2.enums.Gill_Spacing.close;
        } else if (gill_space.equals("w")) {
            return Lab2.enums.Gill_Spacing.crowded;
        } else if (gill_space.equals("d")) {
            return Lab2.enums.Gill_Spacing.distant;
        }
        return null;
    }

    private static Lab2.enums.Gill_Size loadGillSize(String gill_size) {
        if (gill_size.equals("b")) {
            return Lab2.enums.Gill_Size.broad;
        } else if (gill_size.equals("n")) {
            return Lab2.enums.Gill_Size.narrow;
        }

        return null;
    }

    private static Lab2.enums.Gill_Color loadGillColor(String gill_color) {
        if (gill_color.equals("k")) {
            return Lab2.enums.Gill_Color.black;
        } else if (gill_color.equals("n")) {
            return Lab2.enums.Gill_Color.brown;
        } else if (gill_color.equals("b")) {
            return Lab2.enums.Gill_Color.buff;
        } else if (gill_color.equals("h")) {
            return Lab2.enums.Gill_Color.chocolate;
        } else if (gill_color.equals("g")) {
            return Lab2.enums.Gill_Color.gray;
        } else if (gill_color.equals("r")) {
            return Lab2.enums.Gill_Color.green;
        } else if (gill_color.equals("o")) {
            return Lab2.enums.Gill_Color.orange;
        } else if (gill_color.equals("p")) {
            return Lab2.enums.Gill_Color.pink;
        } else if (gill_color.equals("u")) {
            return Lab2.enums.Gill_Color.purple;
        } else if (gill_color.equals("e")) {
            return Lab2.enums.Gill_Color.red;
        } else if (gill_color.equals("w")) {
            return Lab2.enums.Gill_Color.white;
        } else if (gill_color.equals("y")) {
            return Lab2.enums.Gill_Color.yellow;
        }

        return null;
    }

    private static Lab2.enums.Stalk_Shape loadStalkShape(String stalk_shape) {
        if (stalk_shape.equals("e")) {
            return Lab2.enums.Stalk_Shape.enlarging;
        } else if (stalk_shape.equals("t")) {
            return Lab2.enums.Stalk_Shape.tapering;
        }

        return null;
    }

    private static Lab2.enums.Stalk_Root loadStalkRoot(String stalk_root) {
        if (stalk_root.equals("b")) {
            return Lab2.enums.Stalk_Root.bulbous;
        } else if (stalk_root.equals("c")) {
            return Lab2.enums.Stalk_Root.club;
        } else if (stalk_root.equals("u")) {
            return Lab2.enums.Stalk_Root.cup;
        } else if (stalk_root.equals("e")) {
            return Lab2.enums.Stalk_Root.equal;
        } else if (stalk_root.equals("z")) {
            return Lab2.enums.Stalk_Root.rhizomorphs;
        } else if (stalk_root.equals("r")) {
            return Lab2.enums.Stalk_Root.rooted;
        } else if (stalk_root.equals("?")) {
            return Lab2.enums.Stalk_Root.missing;
        }

        return null;
    }

    private static Lab2.enums.Stalk_Surface_Above_Ring loadStalkSurfaceAbove(String stalk_above) {
        if (stalk_above.equals("f")) {
            return Lab2.enums.Stalk_Surface_Above_Ring.ibrous;
        } else if (stalk_above.equals("y")) {
            return Lab2.enums.Stalk_Surface_Above_Ring.scaly;
        } else if (stalk_above.equals("k")) {
            return Lab2.enums.Stalk_Surface_Above_Ring.silky;
        } else if (stalk_above.equals("s")) {
            return Lab2.enums.Stalk_Surface_Above_Ring.smooth;
        }

        return null;
    }

    private static Lab2.enums.Stalk_Surface_Below_Ring loadStalkSurfaceBelow(String stalk_below) {
        if (stalk_below.equals("f")) {
            return Lab2.enums.Stalk_Surface_Below_Ring.ibrous;
        } else if (stalk_below.equals("y")) {
            return Lab2.enums.Stalk_Surface_Below_Ring.scaly;
        } else if (stalk_below.equals("k")) {
            return Lab2.enums.Stalk_Surface_Below_Ring.silky;
        } else if (stalk_below.equals("s")) {
            return Lab2.enums.Stalk_Surface_Below_Ring.smooth;
        }

        return null;
    }

    private static Lab2.enums.Stalk_Color_Above_Ring loadStalkColorAbove(String stalk_color_above) {
        if (stalk_color_above.equals("n")) {
            return Lab2.enums.Stalk_Color_Above_Ring.brown;
        } else if (stalk_color_above.equals("b")) {
            return Lab2.enums.Stalk_Color_Above_Ring.buff;
        } else if (stalk_color_above.equals("c")) {
            return Lab2.enums.Stalk_Color_Above_Ring.cinnamon;
        } else if (stalk_color_above.equals("g")) {
            return Lab2.enums.Stalk_Color_Above_Ring.gray;
        } else if (stalk_color_above.equals("o")) {
            return Lab2.enums.Stalk_Color_Above_Ring.orange;
        } else if (stalk_color_above.equals("p")) {
            return Lab2.enums.Stalk_Color_Above_Ring.pink;
        } else if (stalk_color_above.equals("e")) {
            return Lab2.enums.Stalk_Color_Above_Ring.red;
        } else if (stalk_color_above.equals("w")) {
            return Lab2.enums.Stalk_Color_Above_Ring.white;
        } else if (stalk_color_above.equals("y")) {
            return Lab2.enums.Stalk_Color_Above_Ring.yellow;
        }

        return null;
    }

    private static Lab2.enums.Stalk_Color_Below_Ring loadStalkColorBelow(String stalk_color_below) {
        if (stalk_color_below.equals("n")) {
            return Lab2.enums.Stalk_Color_Below_Ring.brown;
        } else if (stalk_color_below.equals("b")) {
            return Lab2.enums.Stalk_Color_Below_Ring.buff;
        } else if (stalk_color_below.equals("c")) {
            return Lab2.enums.Stalk_Color_Below_Ring.cinnamon;
        } else if (stalk_color_below.equals("g")) {
            return Lab2.enums.Stalk_Color_Below_Ring.gray;
        } else if (stalk_color_below.equals("o")) {
            return Lab2.enums.Stalk_Color_Below_Ring.orange;
        } else if (stalk_color_below.equals("p")) {
            return Lab2.enums.Stalk_Color_Below_Ring.pink;
        } else if (stalk_color_below.equals("e")) {
            return Lab2.enums.Stalk_Color_Below_Ring.red;
        } else if (stalk_color_below.equals("w")) {
            return Lab2.enums.Stalk_Color_Below_Ring.white;
        } else if (stalk_color_below.equals("y")) {
            return Lab2.enums.Stalk_Color_Below_Ring.yellow;
        }

        return null;
    }

    private static Lab2.enums.Veil_Type loadVeilType(String veil_type) {
        if (veil_type.equals("p")) {
            return Lab2.enums.Veil_Type.partial;
        } else if (veil_type.equals("u")) {
            return Lab2.enums.Veil_Type.universal;
        }

        return null;
    }

    private static Lab2.enums.Veil_Color loadVeilColor(String veil_color) {
        if (veil_color.equals("n")) {
            return Lab2.enums.Veil_Color.brown;
        } else if (veil_color.equals("o")) {
            return Lab2.enums.Veil_Color.orange;
        } else if (veil_color.equals("w")) {
            return Lab2.enums.Veil_Color.white;
        } else if (veil_color.equals("y")) {
            return Lab2.enums.Veil_Color.yellow;
        }

        return null;
    }

    private static Lab2.enums.Ring_Number loadRingNumber(String ring_number) {
        if (ring_number.equals("n")) {
            return Lab2.enums.Ring_Number.none;
        } else if (ring_number.equals("o")) {
            return Lab2.enums.Ring_Number.one;
        } else if (ring_number.equals("t")) {
            return Lab2.enums.Ring_Number.two;
        }

        return null;
    }

    private static Lab2.enums.Ring_Type loadRingType(String ring_type) {
        if (ring_type.equals("c")) {
            return Lab2.enums.Ring_Type.cobwebby;
        } else if (ring_type.equals("e")) {
            return Lab2.enums.Ring_Type.evanescent;
        } else if (ring_type.equals("f")) {
            return Lab2.enums.Ring_Type.flaring;
        } else if (ring_type.equals("l")) {
            return Lab2.enums.Ring_Type.large;
        } else if (ring_type.equals("n")) {
            return Lab2.enums.Ring_Type.none;
        } else if (ring_type.equals("s")) {
            return Lab2.enums.Ring_Type.sheathing;
        } else if (ring_type.equals("z")) {
            return Lab2.enums.Ring_Type.zone;
        } else if (ring_type.equals("p")) {
            return Lab2.enums.Ring_Type.pendant;
        }

        return null;
    }

    private static Lab2.enums.Spore_Print_Color loadSporePrintColor(String spore_color) {
        if (spore_color.equals("k")) {
            return Lab2.enums.Spore_Print_Color.black;
        } else if (spore_color.equals("n")) {
            return Lab2.enums.Spore_Print_Color.brown;
        } else if (spore_color.equals("b")) {
            return Lab2.enums.Spore_Print_Color.buff;
        } else if (spore_color.equals("h")) {
            return Lab2.enums.Spore_Print_Color.chocolate;
        } else if (spore_color.equals("r")) {
            return Lab2.enums.Spore_Print_Color.green;
        } else if (spore_color.equals("o")) {
            return Lab2.enums.Spore_Print_Color.orange;
        } else if (spore_color.equals("u")) {
            return Lab2.enums.Spore_Print_Color.purple;
        } else if (spore_color.equals("w")) {
            return Lab2.enums.Spore_Print_Color.white;
        } else if (spore_color.equals("y")) {
            return Lab2.enums.Spore_Print_Color.yellow;
        }

        return null;
    }

    private static Lab2.enums.Population loadPopulation(String population) {
        if (population.equals("a")) {
            return Lab2.enums.Population.abundant;
        } else if (population.equals("c")) {
            return Lab2.enums.Population.clustered;
        } else if (population.equals("n")) {
            return Lab2.enums.Population.numerous;
        } else if (population.equals("s")) {
            return Lab2.enums.Population.scattered;
        } else if (population.equals("v")) {
            return Lab2.enums.Population.several;
        } else if (population.equals("y")) {
            return Lab2.enums.Population.solitary;
        }

        return null;
    }

    private static Lab2.enums.Habitat loadHabitat(String habitat) {
        if (habitat.equals("g")) {
            return Lab2.enums.Habitat.grasses;
        } else if (habitat.equals("l")) {
            return Lab2.enums.Habitat.leaves;
        } else if (habitat.equals("m")) {
            return Lab2.enums.Habitat.meadows;
        } else if (habitat.equals("p")) {
            return Lab2.enums.Habitat.paths;
        } else if (habitat.equals("u")) {
            return Lab2.enums.Habitat.urban;
        } else if (habitat.equals("w")) {
            return Lab2.enums.Habitat.waste;
        } else if (habitat.equals("d")) {
            return Lab2.enums.Habitat.woods;
        }

        return null;
    }

}
