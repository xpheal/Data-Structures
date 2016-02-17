public class arraylist{
	private int[] array;
	private int numItem = 0;
	private int ctr = 0;

	public arraylist(int size){
		array = new int[size];
	}

	// public void binarysearch(int x){
	// 	int low
	// }

	// public int[] sort(int left, int right, int size){
	// 	if(x.length <= 1){
	// 		return x;
	// 	}

	// 	int[] left = 
	// }

	// public void sort(){
	// 	sort(0, numItem/2);
	// }

	// Insert integer into array
	public boolean insert(int x){
		// return false if array is full
		if(numItem == array.length){
			return false;
		}

		array[numItem] = x;
		numItem++;

		return true;
	}

	// Search for integer x, return true if found, otherwise else
	public boolean search(int x){
		for(int i = 0, j = numItem; i < j; i++){
			if(array[i] == x){
				ctr = i;
				return true;
			}
		}

		return false;
	}

	// Remove the integer x
	public boolean remove(int x){
		if(search(x)){
			for(int i = ctr, j = array.length - 1; i < j; i++){
				array[i] = array[i + 1];
			}
			numItem--;
			return true;
		}
		else{
			return false;
		}
	}

	public void printList(){
		for(int i = 0, j = numItem; i < j; i++){
			System.out.println(i + ": " + array[i]);
		}
	}

	public int numItem(){
		return numItem;
	}

	public int getLength(){
		return array.length;
	}
}