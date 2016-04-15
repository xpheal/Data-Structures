// T = Integer for the moment, key can be null
public class TriNode<T> {
	private T key;
	private TriNode<T> left;
	private TriNode<T> mid;
	private TriNode<T> right;

	// Public TriNode Constructor with no child
	public TriNode(T key){
		this.key = key;
		left = null;
		mid = null;
		right = null;
	}

	// Public TriNode Constructor with children
	public TriNode(T key, TriNode<T> left, TriNode<T> mid, TriNode<T> right){
		this.key = key;
		this.left = left;
		this.mid = mid;
		this.right = right;
	}

	// Set key and Get key
	public boolean setKey(T key){
		this.key = key;
		return true;
	}

	public T getKey(){
		return key;
	}

	// Set Left and Get Left
	public boolean setLeft(TriNode<T> left){
		if(left == null){
			return false;
		}

		this.left = left;
		return true;
	}

	public TriNode<T> getLeft(){
		return left;
	}

	// Set Mid and Get Mid
	public boolean setMid(TriNode<T> mid){
		if(mid == null){
			return false;
		}

		this.mid = mid;
		return true;
	}

	public TriNode<T> getMid(){
		return mid;
	}

	// Set Right and Get Right
	public boolean setRight(TriNode<T> right){
		if(right == null){
			return false;
		}

		this.right = right;
		return true;
	}

	public TriNode<T> getRight(){
		return right;
	}
}