package countryKeyFiguresTest;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] myIntArray = new int[]{4,2,8,5,9};
		int temp=0;
		
		for (int i=0; i< myIntArray.length; i++){
		
			if (myIntArray[i+1]<myIntArray[i]){
				temp=myIntArray[i];
				myIntArray[i]=myIntArray[i+1];
				myIntArray[i+1]=temp;
			}
		
		}
		
	}
}
