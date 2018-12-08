package xieao.theora.api.player.ability;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xieao.theora.api.registry.RegistryEntry;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class Ability extends RegistryEntry<Ability> {

    public static final Map<ResourceLocation, Ability> REGISTRY;
    public static final Ability EMPTY;

    public float[] levelCosts = new float[0];

    static {
        REGISTRY = new HashMap<>();
        EMPTY = register(new Ability(), "theora:empty");
    }

    public void onUpdate(EntityPlayer player, World world, int level, NBTTagCompound data) {
    }

    public void onAcquired(EntityPlayer player, World world, int level, NBTTagCompound data) {
    }

    public void onRemoved(EntityPlayer player, World world, int level) {
    }

    public void onLevelUp(EntityPlayer player, World world, int level, NBTTagCompound data) {
    }

    public void onLevelDown(EntityPlayer player, World world, int level, NBTTagCompound data) {
    }

    @Nullable
    @SideOnly(Side.CLIENT)
    public GuiScreen getAbilityGui() {
        return new AbilityGui(this);
    }

    @SideOnly(Side.CLIENT)
    public ResourceLocation getIcon() {
        return new ResourceLocation(getResourceDomain(), "textures/abilities/" + getResourcePath() + ".png");
    }

    public static Ability register(Ability ability, String name) {
        ability.setRegistryName(name);
        REGISTRY.put(ability.getRegistryName(), ability);
        return ability;
    }

    public static Ability getAbility(String name) {
        Ability ability = REGISTRY.get(new ResourceLocation(name));
        return ability == null ? EMPTY : ability;
    }

    public static Ability readNBT(NBTTagCompound compound) {
        return getAbility(compound.getString("regName"));
    }

    public static void writeNBT(Ability ability, NBTTagCompound compound) {
        compound.setString("regName", ability.getRegistryString());
    }

    public float[] getLevelCosts() {
        return levelCosts;
    }

    public int getMaxLevel() {
        return this.levelCosts.length + 1;
    }

    public void setLevelCosts(float... levelCosts) {
        this.levelCosts = levelCosts;
    }

    public boolean isEmpty() {
        return this == EMPTY;
    }
}
