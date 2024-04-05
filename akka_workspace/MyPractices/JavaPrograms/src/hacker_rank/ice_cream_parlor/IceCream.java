package hacker_rank.ice_cream_parlor;

public class IceCream implements Comparable<IceCream> {
	int flavor;
	int price;

	public IceCream(int flavor, int price) {
		this.flavor = flavor;
		this.price = price;
	}

	@Override
	public int compareTo(IceCream o) {
		if (this.price == o.price)
			return 0;
		else if (this.price > o.price){
			return 1;
		}
		else {
			return -1;
		}
	}

	public boolean equals(IceCream o) {
		return false;
	}
}
