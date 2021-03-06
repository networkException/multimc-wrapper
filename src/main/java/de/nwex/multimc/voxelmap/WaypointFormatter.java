package de.nwex.multimc.voxelmap;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WaypointFormatter {

    public static List<String> format(List<String> input) {
        List<String> out = new ArrayList<>();

        input.forEach(line -> {
            if(line.startsWith("name:[")) {
                Optional<WaypointType> optional = WaypointType.list()
                    .stream()
                    .filter(type -> type.getName().equals(line.substring(6, line.indexOf(']'))))
                    .findAny();

                if(optional.isPresent()) {
                    WaypointType waypoint = optional.get();

                    String prefix = line.substring(0, line.indexOf(",red:"));
                    String postfix = line.substring(line.indexOf("world:"));

                    out.add(String.format("%s, %s, suffix:%s, %s", prefix, waypoint.getColor(), waypoint.getIcon(), postfix));
                } else {
                    out.add(line);
                }
            } else {
                out.add(line);
            }
        });

        return out;
    }
}
