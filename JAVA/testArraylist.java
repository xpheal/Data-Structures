public class testArraylist{
	public static void main(String args[]){
		int size = 0;

		arraylist test1 = new arraylist(size);

		// for(int i = 0, j = 20; i < j; i++){
		// 	if(!test1.insert(((i + 143)) * 5 % 43)){
		// 		System.out.println("Array is full");
		// 	}
		// }

		// test1.remove(19);
		// test1.remove(1);

		System.out.println(test1.getLength());

		test1.printList();
	}
}