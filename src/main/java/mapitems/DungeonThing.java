package mapitems;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum DungeonThing {
    EMPTY(Character.MIN_VALUE),
    HERO('@');

    private static Map<Character, DungeonThing> converter = Collections.unmodifiableMap(initializeMapping());

    private final Character displayCharacter;

    DungeonThing(Character displayCharacter) {
        this.displayCharacter = displayCharacter;
    }

    private static Map<Character, DungeonThing> initializeMapping() {
        Map<Character, DungeonThing> map = new HashMap<Character, DungeonThing>();
        for (DungeonThing value : values()) {
            map.put(value.displayCharacter, value);
        }
        return map;
    }

    public static DungeonThing forCharacter(Character character) {
        DungeonThing dungeonThing = converter.get(character);
        if (dungeonThing == null) {
            throw new UnknownDungeonItemException("Unknown character: [" + character + "]");
        }
        return dungeonThing;
    }
}
