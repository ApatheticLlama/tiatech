package tiatech.common.metatileentities.multi;

import org.jetbrains.annotations.NotNull;

import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.LabelWidget;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.gui.widgets.RecipeProgressWidget;
import gregtech.api.gui.widgets.SlotWidget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapPrimitiveMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;
import gregtech.client.particle.VanillaParticleEffects;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockMachineCasing.MachineCasingType;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.multi.MetaTileEntityPrimitiveBlastFurnace;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

    public class MetaTileEntityMegaPrimitiveBlastFurnace extends RecipeMapPrimitiveMultiblockController {

        public MetaTileEntityMegaPrimitiveBlastFurnace(ResourceLocation metaTileEntityId) {
            super(metaTileEntityId, RecipeMaps.PRIMITIVE_BLAST_FURNACE_RECIPES);
        }


        @Override
        protected ModularUI.Builder createUITemplate(EntityPlayer entityPlayer) {
            return ModularUI.builder(GuiTextures.PRIMITIVE_BACKGROUND, 176, 166)
                    .shouldColor(false)
                    .widget(new LabelWidget(5, 5, getMetaFullName()))
                    .widget(new SlotWidget(importItems, 0, 52, 20, true, true)
                            .setBackgroundTexture(GuiTextures.PRIMITIVE_SLOT, GuiTextures.PRIMITIVE_INGOT_OVERLAY))
                    .widget(new SlotWidget(importItems, 1, 52, 38, true, true)
                            .setBackgroundTexture(GuiTextures.PRIMITIVE_SLOT, GuiTextures.PRIMITIVE_DUST_OVERLAY))
                    .widget(new SlotWidget(importItems, 2, 52, 56, true, true)
                            .setBackgroundTexture(GuiTextures.PRIMITIVE_SLOT, GuiTextures.PRIMITIVE_FURNACE_OVERLAY))
                    .widget(new RecipeProgressWidget(recipeMapWorkable::getProgressPercent, 77, 39, 20, 15,
                            GuiTextures.PRIMITIVE_BLAST_FURNACE_PROGRESS_BAR, ProgressWidget.MoveType.HORIZONTAL,
                            RecipeMaps.PRIMITIVE_BLAST_FURNACE_RECIPES))
                    .widget(new SlotWidget(exportItems, 0, 104, 38, true, false)
                            .setBackgroundTexture(GuiTextures.PRIMITIVE_SLOT, GuiTextures.PRIMITIVE_INGOT_OVERLAY))
                    .widget(new SlotWidget(exportItems, 1, 122, 38, true, false)
                            .setBackgroundTexture(GuiTextures.PRIMITIVE_SLOT, GuiTextures.PRIMITIVE_DUST_OVERLAY))
                    .widget(new SlotWidget(exportItems, 2, 140, 38, true, false)
                            .setBackgroundTexture(GuiTextures.PRIMITIVE_SLOT, GuiTextures.PRIMITIVE_DUST_OVERLAY))
                    .bindPlayerInventory(entityPlayer.inventory, GuiTextures.PRIMITIVE_SLOT, 0);
        }

        @SideOnly(Side.CLIENT)
        @NotNull
        @Override
        public ICubeRenderer getFrontOverlay() {
            return Textures.PRIMITIVE_BLAST_FURNACE_OVERLAY;
        }


        @Override
        public boolean hasMaintenanceMechanics() {
            return false;
        }

        @Override
        public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
            return new MetaTileEntityMegaPrimitiveBlastFurnace(metaTileEntityId);
        }


        //Fix this structure later, it's upside down and half-finished right now
        @Override
        @NotNull
        protected BlockPattern createStructurePattern() {
            return FactoryBlockPattern.start()
//                    .aisle( "                                 ",
//                            "         N   N     N   N         ", "         N   N     N   N         ", "         N   N     N   N         ",
//                            "                                 ", "                                 ", "                                 ",
//                            "         N   N     N   N         ", "         N   N     N   N         ", " NNN   NNN   N     N   NNN   NNN ",
//                            "                                 ", "                                 ", "                                 ",
//                            " NNN   NNN             NNN   NNN ", "                                 ", "                                 ",
//                            "                                 ", "                                 ", "                                 ",
//                            " NNN   NNN             NNN   NNN ", "                                 ", "                                 ",
//                            "                                 ", " NNN   NNN             NNN   NNN ")
//                    .aisle( "         N   N     N   N         ", "         bCCCb     bCCCb         ", "         bCCCb     bCCCb         ",
//                            "         bCCCb     bCCCb         ", "         N   N     N   N         ",
//                            "                                 ", "         N   N     N   N         ",
//                            "         bCCCb     bCCCb         ", "         bCCCb     bCCCb         ",
//                            "NbbbN NbbNCCCb     bCCCNbbN NbbbN", " CCC   CCC   N     N   CCC   CCC ",
//                            " CCC   CCC             CCC   CCC ", " CCC   CCC             CCC   CCC ",
//                            "NbbbN NbbbN           NbbbN NbbbN", "  N     N               N     N  ",
//                            "  N     N               N     N  ", "                                 ",
//                            "  N     N               N     N  ", "  N     N               N     N  ",
//                            "NbbbN NbbbN           NbbbN NbbbN", " CCC   CCC             CCC   CCC ",
//                            " CCC   CCC             CCC   CCC ", " CCC   CCC             CCC   CCC ",
//                            "NbbbN NbbbN    N N    NbbbN NbbbN")
//                    .aisle( "         N   N     N   N         ", "         bCCCb     bCCCb         ", "      NNNbbbbbNNsNNbbbbbNNN      ",
//                            "    ss   bCCCb     bCCCb   ss    ", "   s     N   N     N   N     s   ",
//                            "   s                         s   ", "  N      N   N     N   N      N  ",
//                            "  N      bCCCb     bCCCb      N  ", "  N     sbbbbbNNsNNbbbbbs     N  ",
//                            "NbbbN NbbNCCCb     bCCCNbbN NbbbN", " CbC   CbC   N     N   CbC   CbC ",
//                            " CbC   CbC             CbC   CbC ", " CbC   CbC             CbC   CbC ",
//                            "NbbbN NbbbN           NbbbN NbbbN", " NNN   NNN             NNN   NNN ",
//                            " NNN   NNN             NNN   NNN ", "  s     s               s     s  ",
//                            " NNN   NNN             NNN   NNN ", " NNN   NNN             NNN   NNN ",
//                            "NbbbN NbbbN           NbbbN NbbbN", " CbC   CbC             CbC   CbC ",
//                            " CbC   CbC             CbC   CbC ", " CbC   CbC             CbC   CbC ",
//                            "NbbbN NbbbNNNNNsNsNNNNNbbbN NbbbN")
//                    .aisle( "         N   N     N   N         ", "         bCCCb     bCCCb         ", "    ss   bCCCb     bCCCb   ss    ",
//                            "         bCCCb     bCCCb         ", "  s      NCCCN     NCCCN      s  ",
//                            "  s      NCCCN     NCCCN      s  ", "         NCCCN     NCCCN         ",
//                            "         bCCCb     bCCCb         ", "         bCCCb     bCCCb         ",
//                            "NbbbNNNbbNCCCb     bCCCNbbNNNbbbN", " CCCCCCCCC   N     N   CCCCCCCCC ",
//                            " CCCCCCCCC             CCCCCCCCC ", " CCCCCCCCC             CCCCCCCCC ",
//                            "NbbbNNNbbbN           NbbbNNNbbbN", "  N     N               N     N  ",
//                            "  N     N               N     N  ", "                                 ",
//                            "  N     N               N     N  ", "  N     N               N     N  ",
//                            "NbbbNNNbbbN           NbbbNNNbbbN", " CCCCCCCCC             CCCCCCCCC ",
//                            " CCCCCCCCC             CCCCCCCCC ", " CCCCCCCCC             CCCCCCCCC ",
//                            "NbbbNNNbbbN    NbN    NbbbNNNbbbN")
//                    .aisle( "                                 ", "         N   N     N   N         ", "   s     N   N     N   N     s   ",
//                            "  s      NCCCN     NCCCN      s  ", "                                 ",
//                            "                                 ", "                                 ",
//                            "         NCCCN     NCCCN         ", "         N   N     N   N         ",
//                            " NNN   NN    N     N    NN   NNN ", "   C   C                 C   C   ",
//                            "   C   C                 C   C   ", "   C   C                 C   C   ",
//                            " NNN   NNN             NNN   NNN ", "                                 ",
//                            "                                 ", "                                 ",
//                            "                                 ", "                                 ",
//                            " NNN   NNN             NNN   NNN ", "   C   C                 C   C   ",
//                            "   C   C                 C   C   ", "   C   C                 C   C   ",
//                            " NNN   NNN     NbN     NNN   NNN ")
//                    .aisle("                                 ", "                                 ", "   s                         s   ",
//                            "  s      NCCCN     NCCCN      s  ", "                                 ",
//                            "                                 ", "                                 ",
//                            "         NCCCN     NCCCN         ", "                                 ",
//                            "   N   N                 N   N   ", "   C   C                 C   C   ",
//                            "   C   C                 C   C   ", "   C   C                 C   C   ",
//                            "   N   N                 N   N   ", "                                 ",
//                            "                                 ", "                                 ",
//                            "                                 ", "                                 ",
//                            "   N   N                 N   N   ", "   C   C                 C   C   ",
//                            "   C   C                 C   C   ", "   C   C                 C   C   ",
//                            "   N   N       NbN       N   N   ")
//                    .aisle( "                                 ", "         N   N     N   N         ", "  N      N   N     N   N      N  ",
//                            "         NCCCN     NCCCN         ", "                                 ",
//                            "                                 ", "                                 ",
//                            "         NCCCN     NCCCN         ", "         N   N     N   N         ",
//                            " NNN   NN    N     N    NN   NNN ", "   C   C                 C   C   ",
//                            "   C   C                 C   C   ", "   C   C                 C   C   ",
//                            " NNN   NNN             NNN   NNN ", "                                 ",
//                            "                                 ", "                                 ",
//                            "                                 ", "                                 ",
//                            " NNN   NNN             NNN   NNN ", "   C   C                 C   C   ",
//                            "   C   C                 C   C   ", "   C   C                 C   C   ",
//                            " NNN   NNN     NbN     NNN   NNN ")
//                    .aisle( "         N   N     N   N         ", "         bCCCb     bCCCb         ", "  N      bCCCb     bCCCb      N  ",
//                            "         bCCCb     bCCCb         ", "         NCCCN     NCCCN         ",
//                            "         NCCCN     NCCCN         ", "         NCCCN     NCCCN         ",
//                            "         bCCCb     bCCCb         ", "         bCCCb     bCCCb         ",
//                            "NbbbNNNbbNCCCb     bCCCNbbNNNbbbN", " CCCCCCCCC   N     N   CCCCCCCCC ",
//                            " CCCCCCCCC             CCCCCCCCC ", " CCCCCCCCC             CCCCCCCCC ",
//                            "NbbbNNNbbbN           NbbbNNNbbbN", "  N     N               N     N  ",
//                            "  N     N               N     N  ", "                                 ",
//                            "  N     N               N     N  ", "  N     N               N     N  ",
//                            "NbbbNNNbbbN           NbbbNNNbbbN", " CCCCCCCCC             CCCCCCCCC ",
//                            " CCCCCCCCC             CCCCCCCCC ", " CCCCCCCCC             CCCCCCCCC ",
//                            "NbbbNNNbbbN    NbN    NbbbNNNbbbN")
//                    .aisle( "         N   N     N   N         ", "         bCCCb     bCCCb         ", "  N     sbbbbbNNsNNbbbbbs     N  ",
//                            "         bCCCb     bCCCb         ", "         N   N     N   N         ",
//                            "                                 ", "         N   N     N   N         ",
//                            "         bCCCb     bCCCb         ", "  s     sbbbbbNNsNNbbbbbs     s  ",
//                            "NbbbN NbbNCCCb     bCCCNbbN NbbbN", " CbC   CbC   N     N   CbC   CbC ",
//                            " CbC   CbC             CbC   CbC ", " CbC   CbC             CbC   CbC ",
//                            "NbbbN NbbbN           NbbbN NbbbN", " NNN   NNN             NNN   NNN ",
//                            " NNN   NNN             NNN   NNN ", "  s     s               s     s  ",
//                            " NNN   NNN             NNN   NNN ", " NNN   NNN             NNN   NNN ",
//                            "NbbbN NbbbN           NbbbN NbbbN", " CbC   CbC             CbC   CbC ",
//                            " CbC   CbC             CbC   CbC ", " CbC   CbC             CbC   CbC ",
//                            "NbbbN NbbbNNNNNsNsNNNNNbbbN NbbbN")
//                    .aisle( " NNN   NNN   N     N   NNN   NNN ", "NbbbN NbbNCCCb     bCCCNbbN NbbbN", "NbbbN NbbNCCCb     bCCCNbbN NbbbN",
//                            "NbbbNNNbbNCCCb     bCCCNbbNNNbbbN", " NNN   NNN   N     N   NNN   NNN ",
//                            "   N   N                 N   N   ", " NNN   NNN   N     N   NNN   NNN ",
//                            "NbbbNNNbbNCCCb     bCCCNbbNNNbbbN", "NbbbN NbbNCCCb     bCCCNbbN NbbbN",
//                            "NNNN   NNNCCCb     bCCCNNN   NNNN", " CCC   CCC   N     N   CCC   CCC ",
//                            " CCC   CCC             CCC   CCC ", " CCC   CCC             CCC   CCC ",
//                            "NbbbN NbbbN           NbbbN NbbbN", "  N     N               N     N  ",
//                            "  N     N               N     N  ", "                                 ",
//                            "  N     N               N     N  ", "  N     N               N     N  ",
//                            "NbbbN NbbbN           NbbbN NbbbN", " CCC   CCC             CCC   CCC ",
//                            " CCC   CCC             CCC   CCC ", " CCC   CCC             CCC   CCC ",
//                            "NbbbN NbbbN    NbN    NbbbN NbbbN")
//                    .aisle( "                                 ", " CCC   CCC   N     N   CCC   CCC ", " CbC   CbC   N     N   CbC   CbC ",
//                            " CCCCCCCCC   N     N   CCCCCCCCC ", "   C   C                 C   C   ",
//                            "   C   C                 C   C   ", "   C   C                 C   C   ",
//                            " CCCCCCCCC   N     N   CCCCCCCCC ", " CbC   CbC   N     N   CbC   CbC ",
//                            " CCC   CCC   N     N   CCC   CCC ", "                                 ",
//                            "                                 ", "                                 ",
//                            " NNN   NNN             NNN   NNN ", "                                 ",
//                            "                                 ", "                                 ",
//                            "                                 ", "                                 ",
//                            " NNN   NNN             NNN   NNN ", "                                 ",
//                            "                                 ", "                                 ",
//                            " NNN   NNN     NbN     NNN   NNN ")
//                    .aisle( "                                 ", " CCC   CCC             CCC   CCC ", " CbC   CbC             CbC   CbC ",
//                            " CCCCCCCCC             CCCCCCCCC ", "   C   C                 C   C   ",
//                            "   C   C                 C   C   ", "   C   C                 C   C   ",
//                            " CCCCCCCCC             CCCCCCCCC ", " CbC   CbC             CbC   CbC ",
//                            " CCC   CCC             CCC   CCC ", "                                 ",
//                            "                                 ", "                                 ",
//                            "                                 ", "                                 ",
//                            "                                 ", "                                 ",
//                            "                                 ", "                                 ",
//                            "                                 ", "                                 ",
//                            "                                 ", "                                 ",
//                            "  N     N      NbN      N     N  ")
//                    .aisle( "                                 ", " CCC   CCC             CCC   CCC ", " CbC   CbC             CbC   CbC ",
//                            " CCCCCCCCC             CCCCCCCCC ", "   C   C                 C   C   ",
//                            "   C   C                 C   C   ", "   C   C                 C   C   ",
//                            " CCCCCCCCC             CCCCCCCCC ", " CbC   CbC             CbC   CbC ",
//                            " CCC   CCC             CCC   CCC ", "                                 ",
//                            "                                 ", "                                 ",
//                            "                                 ", "                                 ",
//                            "                                 ", "                                 ",
//                            "                                 ", "                                 ",
//                            "                                 ", "                                 ",
//                            "                                 ", "                                 ",
//                            "  N     N      NbN      N     N  ")
//                    .aisle( " NNN   NNN             NNN   NNN ", "NbbbN NbbbN           NbbbN NbbbN", "NbbbN NbbbN           NbbbN NbbbN",
//                            "NbbbNNNbbbN           NbbbNNNbbbN", " NNN   NNN             NNN   NNN ",
//                            "   N   N                 N   N   ", " NNN   NNN             NNN   NNN ",
//                            "NbbbNNNbbbN           NbbbNNNbbbN", "NbbbN NbbbN           NbbbN NbbbN",
//                            "NbbbN NbbbN           NbbbN NbbbN", " NNN   NNN             NNN   NNN ",
//                            "                                 ", "                                 ",
//                            "                                 ", "                                 ",
//                            "                                 ", "                                 ",
//                            "                                 ", "                                 ",
//                            "                                 ", "                                 ",
//                            "                                 ", "                                 ",
//                            "  N     N     NsNsN     N     N  ")
//                    .aisle( "                                 ", "                                 ", "  N     N               N     N  ",
//                            "                                 ", "                                 ",
//                            "                                 ", "                                 ",
//                            "                                 ", "  N     N               N     N  ",
//                            "                                 ", "                                 ",
//                            "                                 ", "                                 ",
//                            "                                 ", "                                 ",
//                            "                                 ", "                                 ",
//                            "                                 ", "                                 ",
//                            "                                 ", "                                 ",
//                            "                                 ", "                                 ",
//                            "  N     N    NbbbbbN    N     N  ")
//                    .aisle( "                                 ", "                                 ", "  N     N               N     N  ",
//                            "                                 ", "                                 ",
//                            "                                 ", "                                 ",
//                            "                                 ", "  N     N               N     N  ",
//                            "                                 ", "                                 ",
//                            "                                 ", "                                 ",
//                            "                                 ", "                                 ",
//                            "                                 ", "                                 ",
//                            "                                 ", "                                 ",
//                            "                                 ", "                                 ",
//                            "                                 ", "                N                ",
//                            " NsNNNNNsNNNNsbbbbbsNNNNsNNNNNsN ")
                    .aisle( "#################################", "#################################", "##X####X#################X####X##",
                            "#################################", "#################################",
                            "#################################", "#################################",
                            "#################################", "##X####X#################X####X##",
                            "#################################", "#################################",
                            "#################################", "#################################",
                            "#################################", "#################################",
                            "#################################", "#################################",
                            "#################################", "#################################",
                            "#################################", "#################################",
                            "################S################", "###############NNN###############",
                            "##NBBBBBNBBBBNBBBBBNBBBBNBBBBBN##")
                    .where('S', selfPredicate())
                    .where('N', states(getCasingState()))
                    .where('B', states(getCasingState2()))
                    .where('C', states(getCasingState3()))
                    .where('X', frames(Materials.Bronze))
                    .where('#', any())
                    .build();
        }

        //this should get changed too
        protected IBlockState getCasingState() {
            return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.PRIMITIVE_BRICKS);
        }

        protected IBlockState getCasingState2() {
            return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.BRONZE_BRICKS);
        }

        protected IBlockState getCasingState3() {
            return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.COKE_BRICKS);
        }

        //this should get changed too
        @Override
        public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
            return Textures.PRIMITIVE_BRICKS;
        }

        public void randomDisplayTick() {
            if (this.isActive()) {
                VanillaParticleEffects.defaultFrontEffect(this, 0.3F, EnumParticleTypes.SMOKE_LARGE,
                        EnumParticleTypes.FLAME);
            }
        }
}
