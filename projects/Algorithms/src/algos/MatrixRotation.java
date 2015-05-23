package algos;

public class MatrixRotation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] m = { { 1,2,3,4 }, { 5, 6,7,8 }, { 9, 10,11,12 }, { 13, 14,15,16 } };
		
		int[][] mM = rotate(m, 4);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.println(mM[i][j]);
			}
		}
	}

	public static int[][] rotate(int[][] m, int N) {
		if (N < 2) {
			return m; // no rotation needed
		}
		int layer = 0; // The current layer being rotated
		for (; layer < N / 2; layer++) {
			int right = N-1-layer;
			for (int count = layer; count < N - 1 - layer; count++) {
				int top = m[layer][count]; // save top element
				// left to top
				m[layer][count] = m[right-count][layer];
				// bottom to left
				m[right-count][layer] = m[right][right-count];
				// right to bottom
				m[right][right-count] = m[count][right];
				// top to right
				m[count][right] = top;
			}
		}
		return m;
	}
}
