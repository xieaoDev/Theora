package xieao.theora;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xieao.lib.proxy.IProxy;
import xieao.lib.render.item.TEItemRenderer;
import xieao.lib.util.Registry;
import xieao.theora.block.IBlocks;
import xieao.theora.block.ITiles;
import xieao.theora.item.IItems;

public class Proxy implements IProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        IBlocks.BLOCKS.forEach(Registry::register);
        IItems.ITEMS.forEach(Registry::register);
        ITiles.TYPES.forEach(Registry::register);
    }

    @Override
    public void init(FMLInitializationEvent event) {

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

    }

    @OnlyIn(Dist.CLIENT)
    public static class Client extends Proxy {

        @Override
        public void preInit(FMLPreInitializationEvent event) {
            super.preInit(event);
            IItems.ITEMS.forEach(TEItemRenderer::register);
        }

        @Override
        public void init(FMLInitializationEvent event) {
            super.init(event);
        }
    }
}
