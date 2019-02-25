package omega.wildcard.common;

public final class Constants {
	public static final String MODID = "wildcard";
	public static final String NAME = "Wildcard";
	public static final String VERSION = "0.1";
	
	/*
	 * General Constants
	 */
	public static final String BLANK = "";
	
	public static final String NBT_CUSTOM_NAME = "CustomName";
	
	/*
	 * Constants for Vanilla
	 */
	public static final int VANILLA_STACK_LIMIT = 64;
	
	/*
	 * Constants for Hoppers
	 */
	public static final int HOPPER_INV = 4;
	
	/*
	 * Constructor to appease Sonar
	 */
	private Constants() {
		//noop
	}
}
