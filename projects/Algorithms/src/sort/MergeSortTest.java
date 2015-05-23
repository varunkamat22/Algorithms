package sort;

public class MergeSortTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] inArray = {-2,-1,0,1,2,3,4,5,6};
		int sorted[] = new MergeSort().sort(inArray);
		System.out.println("Sorted array(Descending)-");
		for(int ele:sorted){
			System.out.print(ele+" ");
		}
	}

}
