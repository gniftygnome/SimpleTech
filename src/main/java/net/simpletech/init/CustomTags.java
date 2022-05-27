package net.simpletech.init;

import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.simpletech.SimpleTech;

public class CustomTags {
    public static final TagKey<Item> TAGS_BIOS = TagKey.of(Registry.ITEM_KEY, new Identifier(SimpleTech.MOD_ID, "bios"));
    public static final TagKey<Item> TAGS_COAL = TagKey.of(Registry.ITEM_KEY, new Identifier(SimpleTech.MOD_ID, "coal"));
    public static final TagKey<Item> TAGS_DIAMONDS = TagKey.of(Registry.ITEM_KEY, new Identifier(SimpleTech.MOD_ID, "diamonds"));
    public static final TagKey<Item> TAGS_DIRT_REMAINS = TagKey.of(Registry.ITEM_KEY, new Identifier(SimpleTech.MOD_ID, "dirt_remains"));
    public static final TagKey<Item> TAGS_GOLD_INGOTS = TagKey.of(Registry.ITEM_KEY, new Identifier(SimpleTech.MOD_ID, "gold_ingots"));
    public static final TagKey<Item> TAGS_LAPIS = TagKey.of(Registry.ITEM_KEY, new Identifier(SimpleTech.MOD_ID, "lapis_lazulis"));
    public static final TagKey<Item> TAGS_REDSTONE = TagKey.of(Registry.ITEM_KEY, new Identifier(SimpleTech.MOD_ID, "redstone_dusts"));

    public static void init() {

    }

}
