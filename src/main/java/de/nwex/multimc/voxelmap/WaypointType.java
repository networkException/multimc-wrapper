package de.nwex.multimc.voxelmap;

import java.util.Arrays;
import java.util.List;

public enum WaypointType {
    BASE("Base", "red:0.35, green:0.52, blue:0.97", IconType.HOUSE),
    STRUCTURE("Structure", "ed:0.44, green:1.0, blue:0.46", IconType.CAMERA),
    MAP("Map", "red:0.98, green:0.29, blue:0.23", IconType.STAR),
    MEMORIAL("Memorial", "red:0.98, green:0.98, blue:0.34", IconType.WORLD),
    RESOURCES("Resources", "red:0.22, green:0.89, blue:0.92", IconType.PICKAXE),
    PLOT("Plot", "red:0.34, green:1.0, blue:0.32", IconType.PICKAXE),
    EVENT("Event", "red:0.99, green:0.59, blue:0.31", IconType.STAR);

    private final String name;
    private final String color;
    private final IconType icon;

    WaypointType(String name, String color, IconType icon) {
        this.name = name;
        this.color = color;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getIcon() {
        return icon.name().toLowerCase();
    }

    public static List<WaypointType> list() {
        return Arrays.asList(values().clone());
    }
}
