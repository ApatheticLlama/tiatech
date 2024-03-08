package tiatech.common.metatileentities;

import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;

import tiatech.TiaTech;
import tiatech.common.metatileentities.multi.*;

public class TiaTechMetaTileEntities {

    public static MetaTileEntityMegaPrimitiveBlastFurnace MEGA_PRIMITIVEBLASTFURNACE;

    public static void init() {
        //use ids 15000-15099
        MEGA_PRIMITIVEBLASTFURNACE = registerMetaTileEntity(15000, new MetaTileEntityMegaPrimitiveBlastFurnace(TiaTech.ID("mega_primitiveblastfurnace")));
    }
}
