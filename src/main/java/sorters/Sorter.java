package sorters;

public interface Sorter extends Recorder {

    void sortArray(int[] array);

    static void swapValues (int firstIndex, int secondIndex, int[] array){
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }
}
