// T = Integer for the moment, key can be null
public class BiNode<T> {
	private T key;
	private BiNode<T> left;
	private BiNode<T> right;

	// Public BiNode Constructor with no child
	public BiNode(T key){
		this.key = key;
		left = null;
		right = null;
	}

	// Public BiNode Constructor with children
	public BiNode(T key, BiNode<T> left, BiNode<T> right){
		this.key = key;
		this.left = left;
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
	public boolean setLeft(BiNode<T> left){
		if(left == null){
			return false;
		}

		this.left = left;
		return true;
	}

	public BiNode<T> getLeft(){
		return left;
	}

	// Set Right and Get Right
	public boolean setRight(BiNode<T> right){
		if(right == null){
			return false;
		}

		this.right = right;
		return true;
	}

	public BiNode<T> getRight(){
		return right;
	}
}