package xieao.theora.common.item;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import xieao.theora.Theora;
import xieao.theora.common.block.IGenericBlock;
import xieao.theora.common.block.TheoraBlocks;

import java.util.HashSet;
import java.util.Set;

public class TheoraItems {

    public static final Set<Item> ITEMS = new HashSet<>();

    public static final ItemShroomBit SHROOM_BIT;
    public static final ItemGliophin GLIOPHIN;
    public static final ItemClearSlime CLEAR_SLIME;
    public static final ItemWand WAND;
    public static final ItemVial VIAL;
    public static final ItemPigCoin PIG_COIN;
    public static final ItemPigCoinBag PIG_COIN_BAG;
    public static final ItemWitherTear WITHER_TEAR;
    public static final ItemSlateSummoning SUMMONING_SLATE;
    public static final ItemSlateLooting LOOTING_SLATE;
    public static final ItemSlateEfficiency EFFICIENCY_SLATE;
    public static final ItemSlateXP XP_SLATE;

    static {
        SHROOM_BIT = register(new ItemShroomBit(), "shroombit");
        GLIOPHIN = register(new ItemGliophin(), "gliophin");
        CLEAR_SLIME = register(new ItemClearSlime(), "clearslime");
        WAND = register(new ItemWand(), "wand");
        VIAL = register(new ItemVial(), "vial");
        PIG_COIN = register(new ItemPigCoin(), "pigcoin");
        PIG_COIN_BAG = register(new ItemPigCoinBag(), "pigcoinbag");
        WITHER_TEAR = register(new ItemWitherTear(), "withertear");
        SUMMONING_SLATE = register(new ItemSlateSummoning(), "summoningslate");
        LOOTING_SLATE = register(new ItemSlateLooting(), "lootingslate");
        EFFICIENCY_SLATE = register(new ItemSlateEfficiency(), "efficiencyslate");
        XP_SLATE = register(new ItemSlateXP(), "xpslate");

        for (Block block : TheoraBlocks.BLOCKS) {
            if (block instanceof IGenericBlock) {
                IGenericBlock block1 = (IGenericBlock) block;
                ResourceLocation location = block.getRegistryName();
                register(block1.getItemBlock(), location.toString());
            }
        }
    }

    public static <T extends Item & IGenericItem> T register(T item, String name) {
        item.setRegistryName(name);
        item.setUnlocalizedName(name);
        item.setCreativeTab(Theora.TAB);
        ITEMS.add(item);
        return item;
    }
}
