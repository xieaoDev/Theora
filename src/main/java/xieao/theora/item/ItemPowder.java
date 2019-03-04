package xieao.theora.item;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xieao.theora.client.gui.player.GuiPlayer;
import xieao.theora.entity.EntityWorker;
import xieao.theora.lib.util.PlayerUtil;

import java.util.List;

public class ItemPowder extends ItemBase {
    public ItemPowder(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if (world.isRemote) Minecraft.getInstance().displayGuiScreen(new GuiPlayer());
        return super.onItemRightClick(world, player, hand);
    }

    @Override
    public EnumActionResult onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        EntityPlayer player = context.getPlayer();
        if (player != null && !PlayerUtil.isFake(player)) {
            BlockPos pos = context.getPos();
            if (isObsidian(world, pos)) {
                BlockPos up = pos.up();
                BlockPos down = pos.down();
                if (isObsidian(world, up) && world.isAirBlock(up.up())) {
                    if (checkWorkers(world, pos)) {
                        if (!world.isRemote) {
                            EntityWorker worker = new EntityWorker(EntityWorker.Job.BUILD_GATE, player, world);
                            worker.setPosition(pos.getX(), pos.getY(), pos.getZ());
                            world.spawnEntity(worker);
                            if (!player.isCreative()) {
                                context.getItem().shrink(1);
                            }
                        }
                        return EnumActionResult.SUCCESS;
                    }
                } else if (isObsidian(world, down) && world.isAirBlock(up)) {
                    if (checkWorkers(world, down)) {
                        if (!world.isRemote) {
                            EntityWorker worker = new EntityWorker(EntityWorker.Job.BUILD_GATE, player, world);
                            worker.setPosition(down.getX(), down.getY(), down.getZ());
                            world.spawnEntity(worker);
                            if (!player.isCreative()) {
                                context.getItem().shrink(1);
                            }
                        }
                        return EnumActionResult.SUCCESS;
                    }
                }
            }
        }
        return super.onItemUse(context);
    }

    private boolean isObsidian(World world, BlockPos pos) {
        return world.getBlockState(pos).getBlock() == Blocks.OBSIDIAN;
    }

    private static boolean checkWorkers(World world, BlockPos pos) {
        AxisAlignedBB alignedBB = new AxisAlignedBB(0.0D, -1.0D, 0.0D, 0.2D, 2.0D, 0.2D).offset(pos);
        List<EntityWorker> entities = world.getEntitiesWithinAABB(EntityWorker.class, alignedBB);
        return entities.size() == 0;
    }
}