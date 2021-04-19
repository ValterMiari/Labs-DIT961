package Lab2;

public class Bid {
	final private String name;
	final private int bid;

	public Bid(String name, int bid) {
		this.name = name;
		this.bid = bid;
	}

	public int hashCode() {
		return 1 + 23*bid + 31*name.hashCode();
	}

	public boolean equals(Object obj){
		if (obj == null || !(obj instanceof Bid)) return false;

		Bid bid = (Bid) obj;

		// TODO: compare the objects
		return bid.hashCode() == this.hashCode();
	}


	public String getName() { return name; }

	public int getBid() { return bid; }

	@Override
	public String toString() {
		return "Bid{" +
				"name='" + name + '\'' +
				", bid=" + bid +
				'}';
	}
}

