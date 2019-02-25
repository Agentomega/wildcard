package omega.wildcard.vanilla.hoppers;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public abstract class BlockFilterHopper extends BlockContainer {

	public BlockFilterHopper() {
		super(Material.IRON, MapColor.STONE);
		// TODO Auto-generated constructor stub
	}
	
	public BlockFilterHopper(Material mType, MapColor cType) {
		super(mType, cType);
	}
}
