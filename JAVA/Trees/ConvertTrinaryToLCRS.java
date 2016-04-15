import java.util.*;

/*
	How to read the output after printing the tree	
	THE NEXT ROW OF THE CURRENT ROW DISPLAYS THE CHILDREN OF THE CURRENT ROW RESPECTIVELY
	IF THE NODE IN THE CURRENT ROW IS NULL, THE CHILDREN OF THAT ROW WILL BE SKIPPED AND 
	WON'T BE DISPLAYED

	Example:
	00 = null

	[00, X]
	means that the left node is null and the right node is X

	[ X, Y]
	[00,00][ A,00]
	means that node X has a null left child and a null right child
	node Y has a left child with key A and a null right child

	[ X, Y]
	[00,00][ A,00]
	[ B, 00]
	means that node Y has a left child A
	and A has a left child B
	IF THE NODE IS NULL, THE CHILD WON'T BE DISPLAYED
*/

public class ConvertTrinaryToLCRS{
	// recursive function that convert trinary tree into a Left Child Right Sibling binary tree
	public static BiNode<Integer> convertTrinaryTOLCRS(TriNode<Integer> head){
		BiNode<Integer> curr = null;
		BiNode<Integer> temp;

		if(head != null){
			curr = new BiNode<Integer>(head.getKey());

			// add the right child first
			if(head.getRight() != null){
				curr.setLeft(convertTrinaryTOLCRS(head.getRight()));
			}

			// add the mid child next
			if(head.getMid() != null){
				if(curr.getLeft() == null){
					curr.setLeft(convertTrinaryTOLCRS(head.getMid()));
				}
				else{
					temp = convertTrinaryTOLCRS(head.getMid());
					temp.setRight(curr.getLeft());
					curr.setLeft(temp);
				}
			}

			// add the left child last
			if(head.getLeft() != null){
				if(curr.getLeft() == null){
					curr.setLeft(convertTrinaryTOLCRS(head.getLeft()));
				}
				else{
					temp = convertTrinaryTOLCRS(head.getLeft());
					temp.setRight(curr.getLeft());
					curr.setLeft(temp);
				}
			}
		}

		return curr;
	}

	// print out Binary Tree for debug, print 00 if there is nothing there or it is null
	public static void printBiTreeRoot(BiNode<Integer> head){
		BiNode<Integer> curr = head;
		LinkedList<BiNode<Integer>> biQueue = new LinkedList<BiNode<Integer>>();

		// Print the root, return straight away if root is null
		if(curr != null){
			System.out.printf("[");
			printNull(curr);
			System.out.printf("]\n");
		}
		else{
			System.out.println("[00]");
			return;
		}

		// Add node to queue
		biQueue.add(curr);
		int count = 1;
		int nextLine = 1;
		boolean allNull = false;
		while(true){
			BiNode<Integer> temp = biQueue.removeFirst();

			// If at the start of a new row there no non null nodes, break
			if(count == 1){
				if(allNull){
					break;
				}
				else{
					allNull = true;
				}
			}

			// Print out the node
			if(temp == null){
				// System.out.printf("[00,00]");
			}
			else{			
				System.out.printf("[");

				// Print left node
				printNull(temp.getLeft());
				System.out.printf(",");

				// Print right node
				printNull(temp.getRight());
				System.out.printf("]");
			}

			// Add all the children
			if(temp == null){
				biQueue.add(null);
				biQueue.add(null);
			}
			else{
				if(temp.getLeft() != null || temp.getRight() != null){
					allNull = false;
				}
				biQueue.add(temp.getLeft());
				biQueue.add(temp.getRight());
			}

			if(count >= nextLine){
				System.out.printf("\n");
				count = 1;
				nextLine *= 2;
			}
			else{
				count ++;
			}
		}
	}

	// print out Ternary Tree for debug, print 00 if there is nothing there or it is null
	public static void printTriTreeRoot(TriNode<Integer> head){
		TriNode<Integer> curr = head;
		LinkedList<TriNode<Integer>> triQueue = new LinkedList<TriNode<Integer>>();

		// Print the root, return straight away if root is null
		if(curr != null){
			System.out.printf("[");
			printNull(curr);
			System.out.printf("]\n");
		}
		else{
			System.out.println("[00]");
			return;
		}

		// Add node to queue
		triQueue.add(curr);
		int count = 1;
		int nextLine = 1;
		boolean allNull = false;
		while(true){
			TriNode<Integer> temp = triQueue.removeFirst();

			// If at the start of a new row there no non null nodes, break
			if(count == 1){
				if(allNull){
					break;
				}
				else{
					allNull = true;
				}
			}

			// Print out the node
			if(temp == null){
				// System.out.printf("[00,00,00]");
			}
			else{			
				System.out.printf("[");

				// Print left node
				printNull(temp.getLeft());
				System.out.printf(",");

				// Print mid node
				printNull(temp.getMid());
				System.out.printf(",");

				// Print right node
				printNull(temp.getRight());
				System.out.printf("]");
			}

			// Add all the children
			if(temp == null){
				triQueue.add(null);
				triQueue.add(null);
				triQueue.add(null);
			}
			else{
				if(temp.getLeft() != null || temp.getRight() != null || temp.getMid() != null){
					allNull = false;
				}
				triQueue.add(temp.getLeft());
				triQueue.add(temp.getMid());
				triQueue.add(temp.getRight());
			}

			if(count >= nextLine){
				System.out.printf("\n");
				count = 1;
				nextLine *= 3;
			}
			else{
				count ++;
			}
		}
	}

	// Print 00 if it is null, else print the integer
	public static void printNull(TriNode<Integer> x){
		if(x == null){
			System.out.printf("%2s", "00");
		}
		else{
			System.out.printf("%2d", x.getKey());
		}
	}

	public static void printNull(BiNode<Integer> x){
		if(x == null){
			System.out.printf("%2s", "00");
		}
		else{
			System.out.printf("%2d", x.getKey());
		}
	}

	// print out Binary Tree for debug

	public static void main(String[] args){
		TriNode<Integer> triHead = null;
		BiNode<Integer> biHead = null;

		// Manually add item into ternary tree
		TriNode<Integer> tempLeft = new TriNode<Integer>(2, new TriNode<Integer>(5), new TriNode<Integer>(6), null);
		TriNode<Integer> tempMid = new TriNode<Integer>(3);
		TriNode<Integer> tempRight = new TriNode<Integer>(4, new TriNode<Integer>(7, new TriNode<Integer>(8), new TriNode<Integer>(9), null), null, null);
		triHead = new TriNode<Integer>(1, tempLeft, tempMid, tempRight);

		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("Trinary tree: ");
		printTriTreeRoot(triHead);

		biHead = convertTrinaryTOLCRS(triHead);

		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("Binary tree: ");
		printBiTreeRoot(biHead);
	}
}