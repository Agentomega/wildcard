package omega.wildcard.vanilla.hoppers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.IHopper;
import net.minecraft.tileentity.TileEntity;
import omega.wildcard.Constants;

public abstract class TileEntityFilterHopper extends TileEntity implements IHopper {
	// 0 because we have no numeric-based fields we want to dynamically edit in Hoppers
	private static final int FIELD_COUNT = 0;
	
	private ItemStack[] inventory = new ItemStack[Constants.HOPPER_INV];

	@Override
	public int getSizeInventory() {
		return Constants.HOPPER_INV;
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack stack : inventory) {
			if (null != stack && !stack.isEmpty()) {
				return false;
			}
		}

		return true;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return index < Constants.HOPPER_INV ? inventory[index] : null;
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		ItemStack stack = getStackInSlot(index);
		ItemStack result = null;
		
		if( null != stack && stack.getCount() <= count ) {
			result = removeStackFromSlot(index);
		} else if ( null != stack ) {
			result = stack.splitStack(count);
		}
		
		if (null != result) {
			markDirty();
		}

		return result;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		ItemStack stack = getStackInSlot(index);
		setInventorySlotContents(index, null);

		if (null != stack) {
			markDirty();
		}

		return stack;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if (index < Constants.HOPPER_INV) {
			inventory[index] = stack;
			markDirty();
		}
	}

	@Override
	public int getInventoryStackLimit() {
		return Constants.VANILLA_STACK_LIMIT;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		// default calculation from TileEntityHopper
		// TODO: clean this up
		return this.getWorld().getTileEntity(this.getPos()) != this?false:player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory(EntityPlayer player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeInventory(EntityPlayer player) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		ItemStack slotStack = inventory[index];
		// TODO: Needs overriding in equivalence hopper because matchesFilter
		// handles this
		return !(index >= Constants.HOPPER_INV || !matchesFilter(stack)
				|| (null != slotStack && !(slotStack.isStackable() && ItemStack.areItemsEqual(slotStack, stack))));
	}

	@Override
	public int getField(int id) {
		// Return default because there are no fields
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		// Noop because there are no fields
	}

	@Override
	public int getFieldCount() {
		return FIELD_COUNT;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasCustomName() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getXPos() {
		return this.getPos().getX();
	}

	@Override
	public double getYPos() {
		return this.getPos().getY();
	}

	@Override
	public double getZPos() {
		return this.getPos().getZ();
	}

	abstract boolean matchesFilter(ItemStack stack);
}
