package tech.williamist.shadowartifacts.aspects;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import org.jetbrains.annotations.NotNull;
import tech.williamist.shadowartifacts.artifacts.urkolowsjournal.JournalEntityUtils;
import tech.williamist.shadowartifacts.aspects.emblems.EmblemType;

import java.util.ArrayList;
import java.util.List;

public class AspectsScreen extends Screen {

    private final ResourceLocation BG_LOCATION = new ResourceLocation("shadowartifacts", "textures/gui/aspects_ui.png");
    private final ResourceLocation EMBLEM_ATLAS = new ResourceLocation("shadowartifacts", "textures/gui/emblems.png");
    private final int IMAGE_WIDTH = 195;
    private final int IMAGE_HEIGHT = 142;

    private int leftPos;
    private int topPos;

    private List<EmblemSlot> emblemSlots = new ArrayList<>();
    private List<AspectsTab> tabList = new ArrayList<>();
    private int selectedTab = 0;

    protected AspectsScreen(Component screenTitle) {
        super(screenTitle);
    }

    @Override
    protected void init() {
        super.init();
        this.leftPos = (this.width - IMAGE_WIDTH) / 2;
        this.topPos = (this.height - IMAGE_HEIGHT) / 2;
        emblemSlots.clear();
        for (int i = 0; i < 10 * 7; i++) {
            emblemSlots.add(new EmblemSlot(EmblemType.ABSORPTION_EMBLEM, 7, this.leftPos + 8 + ((i % 10) * 18), this.topPos + 9 + ((Math.floorDiv(i, 10)) * 18)));
        }
        tabList.clear();
        tabList.add(new AspectsTab(new TranslatableComponent("ui.shadowartifacts.emblemsTab"), EMBLEM_ATLAS, this.leftPos + 3, this.topPos - 30, 16, 0, 16, 0));
        tabList.add(new AspectsTab(new TranslatableComponent("ui.shadowartifacts.testTab"), EMBLEM_ATLAS, this.leftPos + 3 + 28, this.topPos - 30, 32, 0, 16, 1));
        for (AspectsTab tab : tabList) {
            super.addWidget(tab);
        }
    }

    @Override
    public void render(@NotNull PoseStack poseStack, int mouseX, int mouseY, float idk) {
        this.renderBackground(poseStack);
        this.renderBg(poseStack);

        for (int i = 0; i < 10 * 7; i++) {
            emblemSlots.get(i).renderSlot(poseStack, mouseX, mouseY);
        }
        for (AspectsTab tab : tabList) {
            tab.render(poseStack, mouseX, mouseY);
        }

        for (int i = 0; i < 10 * 7; i++) {
            emblemSlots.get(i).renderHoverEffects(poseStack, mouseX, mouseY);
        }
        for (AspectsTab tab : tabList) {
            tab.renderHover(poseStack, mouseX, mouseY);
        }

        //super.render(poseStack, mouseX, mouseY, idk);
    }

    protected void renderBg(PoseStack poseStack) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, BG_LOCATION);
        this.blit(poseStack, this.leftPos, this.topPos, 0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
    }

    private class AspectsTab extends AbstractWidget {
        private boolean isSelected = false;
        private final Component hoverComponent;
        private final ResourceLocation iconLocation;
        private final int iconOffsetX;
        private final int iconOffsetY;
        private final int iconSize;
        private final int renderX;
        private final int renderY;
        private final short tabId;

        public AspectsTab(Component hoverTooltipComponent, ResourceLocation iconLocation, int renderX, int renderY, int iconOffsetX, int iconOffsetY, int iconSize, int tabId) {
            super(renderX, renderY, 28, 31, hoverTooltipComponent);
            this.hoverComponent = hoverTooltipComponent;
            this.iconLocation = iconLocation;
            this.renderX = renderX;
            this.renderY = renderY;
            this.iconOffsetX = iconOffsetX;
            this.iconOffsetY = iconOffsetY;
            this.iconSize = iconSize;
            this.tabId = (short)tabId;
        }

        public void render(PoseStack poseStack, int mouseX, int mouseY) {
            if (this.visible) {
                this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
                this.isSelected = selectedTab == this.tabId;
            }

            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
            RenderSystem.setShaderTexture(0, BG_LOCATION);
            this.blit(poseStack, renderX, renderY + (this.isSelected ? 2 : 0), 195, this.isSelected ? 30 : 0, 28, 31 + (this.isSelected ? 1 : 0));
            RenderSystem.setShaderTexture(0, this.iconLocation);
            this.blit(poseStack, renderX + (28 / 2 - this.iconSize / 2), renderY + (31 / 2 - this.iconSize / 2), this.iconOffsetX, this.iconOffsetY, this.iconSize, this.iconSize);
        }

        public void renderHover(PoseStack poseStack, int mouseX, int mouseY) {
            if (this.isHovered) {
                renderTooltip(poseStack, this.hoverComponent, mouseX, mouseY);
            }
        }

        @Override
        public void updateNarration(NarrationElementOutput narrationElementOutput) {
            narrationElementOutput.add(NarratedElementType.TITLE, this.hoverComponent);
        }

        @Override
        public void onClick(double p_93634_, double p_93635_) {
            selectedTab = this.tabId;
        }
    }

    private class EmblemSlot extends AbstractWidget {

        private EmblemType emblemType;
        private int emblemAmount;
        private Component emblemName;
        private TranslatableComponent emblemDescription;
        private List<FormattedCharSequence> emblemTooltipText = new ArrayList<>();

        public EmblemSlot(EmblemType emblemType, int emblemAmount, int x, int y) {
            super(x, y, 18, 18, new TranslatableComponent("shadowartifacts.emblems." + emblemType.toString().toLowerCase()));
            this.emblemName = new TranslatableComponent("shadowartifacts.emblems." + emblemType.toString().toLowerCase());
            this.emblemDescription = JournalEntityUtils.getEntityDescriptionFromEmblemType(emblemType);
            this.emblemType = emblemType;
            this.emblemAmount = emblemAmount;
            this.emblemTooltipText.add(emblemName.getVisualOrderText());
            Style darkGrayTextColor = Style.EMPTY;
            darkGrayTextColor.applyFormat(ChatFormatting.DARK_GRAY);
            this.emblemTooltipText.add(emblemDescription.setStyle(darkGrayTextColor).getVisualOrderText());
            this.emblemTooltipText.add(new TranslatableComponent("shaodwartifacts.emblems." + emblemType.toString().toLowerCase() + ".description2").setStyle(darkGrayTextColor).getVisualOrderText());
        }

        public void setEmblemTypeAndAmount(EmblemType emblemType, int emblemAmount) {
            this.emblemType = emblemType;
            this.emblemAmount = emblemAmount;
        }

        public void renderSlot(PoseStack poseStack, int mouseX, int mouseY) {
            if (this.visible) {
                this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            }

            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
            RenderSystem.setShaderTexture(0, EMBLEM_ATLAS);
            this.blit(poseStack, x + 1, y + 1, emblemType.getTexOffsetX(), emblemType.getTexOffsetY(), 16, 16);
            drawString(poseStack, Minecraft.getInstance().font, String.valueOf(emblemAmount), x + 2, y + 2, 0xFFFFFF);
        }

        public void renderHoverEffects(PoseStack poseStack, int mouseX, int mouseY) {
            if (this.isHovered) {
                fill(poseStack, x + 1, y + 1, x + 17, y + 17, 0xaaffffff);
                renderTooltip(poseStack, emblemTooltipText, mouseX, mouseY);
            }
        }

        @Override
        public void updateNarration(NarrationElementOutput narrationElementOutput) {
            narrationElementOutput.add(NarratedElementType.TITLE, this.emblemName);
        }
    }

}
