package omega.wildcard.vanilla.hoppers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.IHopper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import omega.wildcard.common.Constants;
import omega.wildcard.vanilla.VanillaConstants;

public abstract class TileEntityFilterHopper extends TileEntity implements IHopper, ITickable {
	// 0 because we have no numeric-based fields we want to dynamically edit in
	// Hoppers
	private static final int FIELD_COUNT = 0;
	private static final int STACK_LIMIT = Constants.VANILLA_STACK_LIMIT;
	
	protected NonNullList<ItemStack> inventory;
	protected NBTTagCompound nbtTComp;

	private FilterLocation filterOn;
	private String customName;
	
	public abstract String getDefaultName();

	public abstract boolean matchesFilter(ItemStack stack);
	
	public TileEntityFilterHopper() {
		init();
	}
	
	public void init() {
		inventory = NonNullList.withSize(Constants.HOPPER_INV, ItemStack.EMPTY);
		filterOn = FilterLocation.INCOMING;
		customName = Constants.BLANK;
	}
	
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		init();
		
		if (compound.hasKey(Constants.NBT_CUSTOM_NAME)) {
			customName = compound.getString(Constants.NBT_CUSTOM_NAME);
		}
		
		if (compound.hasKey(VanillaConstants.NBT_FILTER_ON)) {
			filterOn = FilterLocation.valueOf(compound.getString(VanillaConstants.NBT_FILTER_ON));
		}
	}
	
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		
		if (hasCustomName()) {
			compound.setString(Constants.NBT_CUSTOM_NAME, customName);
		}
		
		compound.setString(VanillaConstants.NBT_FILTER_ON, filterOn.getDisplayName());
		
		return compound;
	}

	@Override
	public int getSizeInventory() {
		return Constants.HOPPER_INV;
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack stack : inventory) {
			if (!stack.isEmpty()) {
				return false;
			}
		}

		return true;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return index < Constants.HOPPER_INV ? inventory.get(index) : null;
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(inventory, index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(inventory, index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		inventory.set(index, stack);
		
		if(stack.getCount() > STACK_LIMIT) {
			stack.setCount(STACK_LIMIT);
		}
	}

	@Override
	public int getInventoryStackLimit() {
		return STACK_LIMIT;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		// default calculation from TileEntityHopper
		// TODO: clean this up
		return this.getWorld().getTileEntity(this.getPos()) != this ? Boolean.FALSE
				: player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D,
						(double) this.pos.getZ() + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory(EntityPlayer player) {
		// TODO: Should be able to noop here, but double-check
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		// TODO: Should be able to noop here, but double-check.
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		ItemStack slotStack = getStackInSlot(index);
		return ItemStack.EMPTY.equals(slotStack) || (slotStack.isStackable() && ItemStack.areItemsEqual(slotStack, stack));
	}

	@Override
	public int getField(int id) {
		// Return default because there are no numeric fields
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		// Noop because there are no numeric fields
	}

	@Override
	public int getFieldCount() {
		return FIELD_COUNT;
	}

	@Override
	public void clear() {
		inventory.clear();
	}

	@Override
	public String getName() {
		return hasCustomName() ? getCustomName() : getDefaultName();
	}

	@Override
	public boolean hasCustomName() {
		return !Constants.BLANK.equals(customName);
	}
	
	public String getCustomName() {
		return customName;
	}
	
	public void setCustomName(String customName) {
		this.customName = customName;
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
}
