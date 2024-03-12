package tiatech.api.capability.impl;

import gregtech.api.GTValues;
import gregtech.api.capability.impl.PrimitiveRecipeLogic;
import gregtech.api.metatileentity.multiblock.ParallelLogicType;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.NotNull;
import tiatech.TiaTech;
import tiatech.api.metatileentity.TiaTechRecipeMapPrimitiveMultiblockController;

public class TiaTechPrimitiveRecipeLogic extends PrimitiveRecipeLogic {
    public TiaTechPrimitiveRecipeLogic(TiaTechRecipeMapPrimitiveMultiblockController tileEntity, RecipeMap<?> recipeMap) {
        super(tileEntity, recipeMap);
    }

    @Override
    protected IItemHandlerModifiable getInputInventory() {
        TiaTechRecipeMapPrimitiveMultiblockController controller = (TiaTechRecipeMapPrimitiveMultiblockController) metaTileEntity;
        return controller.getInputInventory();
    }

    @Override
    protected IItemHandlerModifiable getOutputInventory() {
        TiaTechRecipeMapPrimitiveMultiblockController controller = (TiaTechRecipeMapPrimitiveMultiblockController) metaTileEntity;
        return controller.getOutputInventory();
    }

    @Override
    public long getMaxVoltage() {
        return GTValues.V[GTValues.MAX];
    }

    @Override
    public void update() {
        TiaTechRecipeMapPrimitiveMultiblockController controller = (TiaTechRecipeMapPrimitiveMultiblockController) metaTileEntity;
        if (isActive && !controller.isStructureFormed()) {
            progressTime = 0;
            wasActiveAndNeedsUpdate = true;
        }

        super.update();
    }

    @Override
    protected boolean setupAndConsumeRecipeInputs(@NotNull Recipe recipe,
                                                  @NotNull IItemHandlerModifiable importInventory) {
        TiaTechRecipeMapPrimitiveMultiblockController controller = (TiaTechRecipeMapPrimitiveMultiblockController) metaTileEntity;
        if (controller.checkRecipe(recipe, false) &&
                super.setupAndConsumeRecipeInputs(recipe, importInventory)) {
            controller.checkRecipe(recipe, true);
            return true;
        } else return false;
    }
}
