package omega.wildcard.vanilla.hoppers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.IHopper;
import net.minecraft.tileentity.TileEntity;
import omega.wildcard.Constants;

public abstract class TileEntityFilterHopper extends TileEntity implements IHopper {

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
		ItemStack result = index < Constants.HOPPER_INV ? inventory[index].splitStack(count) : null;
		
		if (null != result) {
			markDirty();
		}
		
		return result;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		ItemStack stack = getStackInSlot(index);
		inventory[index] = null;
		
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
		// TODO Auto-generated method stub
		return false;
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
		// TODO: Finish logic which checks if index has other item in it
		return !(index >= Constants.HOPPER_INV || !matchesFilter(stack));
	}

	@Override
	public int getField(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getYPos() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getZPos() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	abstract boolean matchesFilter(ItemStack stack);
}
