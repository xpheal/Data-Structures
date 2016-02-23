public class arraylist{
	private int[] array;
	private int numItem = 0;
	private int ctr = 0;

	public arraylist(int size){
		array = new int[size];
	}

	public void sort(){
		sort(0, numItem);
		return;
	}


	// Mergesort
	public void sort(int offset, int size){
		if(size <= 1){
			return;
		}

		sort(offset, size/2);
		if(size % 2 != 0){
			sort(offset + size/2, size/2 + 1);
		}
		else{
			sort(offset + size/2, size/2);
		}

		int i = offset;
		int j = offset + size/2;

		int[] temp = new int[size];
		int k = 0;

		while(i < offset + size/2 && j < offset + size){
			if(array[i] > array[j]){
				temp[k] = array[j];
				j++;
				k++;
			}
			else{
				temp[k] = array[i];
				i++;
				k++;
			}
		}

		while(i < offset + size/2){
			temp[k] = array[i];
			i++;
			k++;
		}

		while(j < offset + size){
			temp[k] = array[j];
			j++;
			k++;
		}

		for(k = 0, i = offset; k < size; k++, i++){
			array[i] = temp[k];
		}
	}

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
		if(isEmpty()){
			return;
		}
		System.out.print("[");
		for(int i = 0, j = numItem - 1; i < j; i++){
			System.out.print(array[i] + ",");
		}
		System.out.print(array[numItem - 1] + "]\n");
	}

	public int numItem(){
		return numItem;
	}

	public boolean isEmpty(){
		if(numItem == 0){
			return true;
		}
		return false;
	}

	public int getLength(){
		return array.length;
	}
}