package org.omega.wildcard.vanilla.hoppers;

public enum FilterLocation {
	INCOMING("Incoming"),
	OUTGOING("Outgoing");
	
	private String displayName;
	
	FilterLocation(String displayName) {
		this.displayName = displayName;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public boolean isIncoming() {
		return this == INCOMING;
	}
	
	public boolean isOutgoing() {
		return this == OUTGOING;
	}
}
