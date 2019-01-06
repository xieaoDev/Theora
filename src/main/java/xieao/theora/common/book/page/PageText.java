package xieao.theora.common.book.page;

import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xieao.theora.client.gui.book.GuiBook;
import xieao.theora.client.helper.I18nHelper;

public class PageText extends Page {

    public String text;

    public PageText(String text) {
        this.text = I18nHelper.formatBookText(text);
    }

    public PageText setText(String text) {
        this.text = text;
        return this;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void draw(GuiBook gui, int mouseX, int mouseY, float partialTicks) {
        int yOffset = 0;
        if (!this.title.isEmpty()) {
            drawTitle(gui, gui.mc.fontRenderer, this.title, 17);
            yOffset = 20;
        }
        drawText(gui, gui.mc.fontRenderer, this.text, yOffset + 14);
    }

    protected void drawText(GuiBook gui, FontRenderer fr, String text, int y) {
        fr.FONT_HEIGHT = 11;
        fr.drawSplitString(text, 12, y, gui.w - 24, 0x2C553C);
        fr.FONT_HEIGHT = 9;
    }
}