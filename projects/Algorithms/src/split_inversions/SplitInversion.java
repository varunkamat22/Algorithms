package split_inversions;

public class SplitInversion {
	private int[] inArray;
	
	public SplitInversion(int[] inArray){
		this.inArray = inArray;
	}
	
	public int countInversions(int left, int right){
		if(left == right)
			return 0;
		int mid = (left+right)/2;
		return countInversions(left, mid)+countInversions(mid+1, right)+mergeAndCount(left,mid,right);
	}
	
	private int mergeAndCount(int left, int mid,int right){
		System.out.println();
		System.out.print(left);		System.out.print(mid);		System.out.print(right);
		int i = left;
		int j = mid+1;
		int inversions = 0;
		int[] temp = new int[right+1];
		int tempCount = 0;
		while(i <= mid && j <= right){
			if(inArray[i] <= inArray[j]){
				temp[tempCount++] = inArray[i];
				i++;
			}else{
				temp[tempCount++] = inArray[j];
				j++;
				inversions+= mid-i+1; //Every time an element in right is moved then number of inversions for that element is equal to remaining elements in left.
			}
		}
		while(i <= mid)
			temp[tempCount++] = inArray[i];
		while(j <= right)
			temp[tempCount++] = inArray[j];
		return inversions;
	}
	
	public static void main(String[] args) {
		int[] inArray = {6,1,2,3,4,5};
		SplitInversion splitInversion = new SplitInversion(inArray);
		System.out.println(splitInversion.countInversions(0, inArray.length-1));
	}
}

