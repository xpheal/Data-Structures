public class testArraylist{
	public static void main(String args[]){
		int size = 20;

		arraylist test1 = new arraylist(size);

		for(int i = 0, j = 20; i < j; i++){
			if(!test1.insert(((i + 143)) * 5 % 43)){
				System.out.println("Array is full");
			}
		}

		System.out.println("Before sort: ");
		test1.printList();

		test1.sort();

		System.out.println("After mergesort: ");
		test1.printList();
	}
}