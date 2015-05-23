package sort;

/**
 * 
 * @author vakamat
 *	This class will sort the input array of integers using divide and conquer approach.
 *	Works with negative integers.
 *	Complexity(Avg. case) - O(nlogn)
 */
public class MergeSort {
	private int[] array;
    private int[] tempMergArr;
    
    /**
     * accepts an integer array and returns the sorted version of it
     * @param int[]
     * @return int[]
     */
    public int[] sort(int inputArr[]) {
        this.array = inputArr;
        this.tempMergArr = new int[inputArr.length];
        doMergeSort(0, inputArr.length - 1);
        return inputArr;
    }
    
    /**
     *	Handles the recursive part of the merge sort. 
     *     
     */
    private void doMergeSort(int lowerIndex, int higherIndex) {
        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2; //Find the mid point
            doMergeSort(lowerIndex, middle); //sort left array
            doMergeSort(middle + 1, higherIndex); //sort right array
            merge(lowerIndex, middle, higherIndex); //Merge the left and right sorted arrays
        }
    }
    
    /**
     * Handles the merge part of the merge sort.
     * 
     */
    private void merge(int lowerIndex, int middle, int higherIndex) {
    	
    	//Copy all elements that needs to be merged into a temp array
        for (int i = lowerIndex; i <= higherIndex; i++) {
            tempMergArr[i] = array[i];
        }
        
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        
        //The merge begins here
        while (i <= middle && j <= higherIndex) {
            if (tempMergArr[i] >= tempMergArr[j]) {
                array[k] = tempMergArr[i];
                i++;
            } else {
                array[k] = tempMergArr[j];
                j++;
            }
            k++;
        }
        
        //Copy the leftover elements from left array to the original array. Elements of right array will already be available at this point.
        while (i <= middle) {
            array[k] = tempMergArr[i];
            k++;
            i++;
        }
    }
    
}
