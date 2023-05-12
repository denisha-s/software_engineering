
public enum Setting {
	OFF("[---]"), LOW("[--+]"), MEDIUM("[-++]"), HIGH("[+++]");
	private String set;

	Setting (String aSet) {
		set = aSet;
	}

	public String toString() {
		return set;
	}
}
