package JunitTD5;

public class ABR<T extends Comparable<T>>{

	public ABR() {
		// TODO Auto-generated constructor stub
	}
	
	private T value = null;
	private T valueNULL = null;
	private ABR<T> left;
	private ABR<T> right;
	
	public void add(T i) {
		if(value == null) {
			value = i;
		}
		else if(value.compareTo(i)> 0) {
			if (left == null) left = new ABR<T>();
			left.add(i);
		}
		else if(value.compareTo(i)< 0) {
			if (right == null) right = new ABR<T>();
			right.add(i);
		}
	//on ne fait rien si il y a egalite
	}

	public void inWalk() {
		if (left != null) left.inWalk();
		if (value != null) System.out.print(value + "\t");
		if (right != null) right.inWalk();
	}

	public T max() {
		if(value == null) {
			return null;
		}
		if (right == null) {
			return value;
		}
		return right.max();
	}

	public boolean contains(T i) {
		if(value == null) {
			return false;
		}
		else if(value.compareTo(i)> 0) {
			if (left == null) return false;
			return left.contains(i);
		}
		else if(value.compareTo(i)< 0) {
			if (right == null) return false;
			return right.contains(i);
		}
		//else i == value
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
