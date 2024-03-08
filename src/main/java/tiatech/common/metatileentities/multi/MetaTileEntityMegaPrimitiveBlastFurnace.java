package tiatech.common.metatileentities.multi;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockMachineCasing.MachineCasingType;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.multi.MetaTileEntityPrimitiveBlastFurnace;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

    public class MetaTileEntityMegaPrimitiveBlastFurnace extends RecipeMapMultiblockController {

        public MetaTileEntityMegaPrimitiveBlastFurnace(ResourceLocation metaTileEntityId) {
            super(metaTileEntityId, RecipeMaps.PYROLYSE_RECIPES);
            // this.recipeMapWorkable = new PyrolyseOvenWorkableHandler(this);
        }

        //temporary structure. RecipeMapMultiblockController complains if we dont have one.
        @Override
        protected BlockPattern createStructurePattern() {
            return FactoryBlockPattern.start()
                    .aisle("XXX", "XXX", "XXX")
                    .aisle("XXX", "X#X", "XXX")
                    .aisle("XXX", "XSX", "XXX")
                    .where('S', selfPredicate())
                    .where('X', states(getCasingState()).setMinGlobalLimited(6).or(autoAbilities()))
                    .where('#', air())
                    .build();
        }

        //this should get changed too
        protected IBlockState getCasingState() {
            return MetaBlocks.MACHINE_CASING.getState(MachineCasingType.ULV);
        }

        //this should get changed too
        @Override
        public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
            return Textures.VOLTAGE_CASINGS[0];
        }

        @Override
        public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
            return new MetaTileEntityPrimitiveBlastFurnace(metaTileEntityId);
        }
}
