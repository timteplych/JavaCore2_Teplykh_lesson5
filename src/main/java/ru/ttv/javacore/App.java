package ru.ttv.javacore;

/**
 * @author Timofey Teplykh
 *
 */
public class App 
{

    public static void main( String[] args )
    {
        final int size = 10000000;
        float[] arr = new float[size];
        for(int i=0; i<size; i++){
            arr[i] = 1.00f;
        }
        firstMethod(arr);


        for(int i=0; i<size; i++){
            arr[i] = 1.00f;
        }
        secondMethod(arr);
    }

    private static void firstMethod(float[] arr){
        final long a = System.currentTimeMillis();
        for(int i=0; i<arr.length; i++){
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println(System.currentTimeMillis() - a);
    }

    private static void secondMethod(float[] arr){
        final int h = arr.length/2;
        final long a = System.currentTimeMillis();
        float[] arr1 = new float[h];
        float[] arr2 = new float[h];
        System.arraycopy(arr, 0, arr1, 0, h);
        System.arraycopy(arr, h, arr2, 0, h);

        ArrayProcessingThread arrayProcessingThread1 = new ArrayProcessingThread(arr1);
        ArrayProcessingThread arrayProcessingThread2 = new ArrayProcessingThread(arr2);
        arrayProcessingThread1.start();
        arrayProcessingThread2.start();
        try {
            arrayProcessingThread1.join();
            arrayProcessingThread2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.arraycopy(arr1, 0, arr, 0, h);
        System.arraycopy(arr2, 0, arr, h, h);
        System.out.println(System.currentTimeMillis() - a);
    }


}
