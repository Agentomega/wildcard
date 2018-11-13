package omega.wildcard.vanilla.hoppers;

public enum HopperType {
	EQUIVALENCE("Equivalence Hopper"),
	REGEX("Regex Hopper");
	
	private String displayName;
	
	HopperType(String displayName) {
		this.displayName = displayName;
	}
	
	public String getDisplayName() {
		return displayName;
	}
}
