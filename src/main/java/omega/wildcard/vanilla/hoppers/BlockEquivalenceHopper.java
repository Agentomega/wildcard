package omega.wildcard.vanilla.hoppers;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockEquivalenceHopper extends BlockFilterHopper {

	protected BlockEquivalenceHopper(Material mType, MapColor cType) {
		super(mType, cType);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityEquivalenceHopper();
	}

}
