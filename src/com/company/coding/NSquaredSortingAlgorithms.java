package com.company.coding;

public class NSquaredSortingAlgorithms{

    public static String ANSI_RED = "\u001B[31m";
    public static String ANSI_GREEN = "\u001B[32m";
    public static String ANSI_BLUE = "\u001B[34m";
    public static String ANSI_YELLOW = "\u001B[33m";
    public static String ANSI_PURPLE = "\u001B[35m";
    public static String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static String RED_UNDERLINED = "\033[4;31m";
    public static String RESET = "\033[0m";

    public static boolean showAnimation = true;
    public static int delay = 1000;

    public static void bubbleSortOptimized(int[] input){
        int n = input.length;
        if(n <= 1) return;
        int newN = n;
        boolean swap = true;
        while(n > 0 && swap){//If no swaps happen, that means data is sorted
            swap = false;
            //Compare adjacent elements, and swap if second element is smaller
            for(int i = 1; i < n; i++){
                print2(input, 1, n-1, i-1, i, false, n-1,"Last Index: ", 0);
                if(input[i-1] > input[i]){
                    int t = input[i];
                    input[i] = input[i-1];
                    input[i-1] = t;
                    swap = true;
                    newN = i;
                    print2(input, 1, n-1, i-1, i, true, n-1,"Last Index: ", 0);
                }
            }
            n = newN;//With each iteration the next biggest element reaches its sorted position
            print2(input, -1, -1, -1, -1, false, n-1,"Last Index: ", 1);
        }
    }

    public static void insertionSort(int[] input){
        int n  = input.length;
        if(n <= 1) return;
        for(int i = 1; i < n; i++){
            //Get the key to insert in right place
            int key = input[i];
            //Check if any preceding keys are greater
            //Find the right index for the key in left half of array
            //Stop at first condition failure, since data is sorted
            print2(input, -1, -1, -1, -1, false, key,"Key", 1);
            int j = i-1;
            while((j >= 0) && (input[j] > key)){
                print2(input, 0, i, j, -1, false, key,"Key", 2);
                input[j+1] = input[j];
                print2(input, 0, i, j+1, j, true, key,"Key", 0);
                j--;

            }
            input[j+1] = key;//Insert the key in right place
            print2(input, -1, -1, j+1, i, true, key,"Key", 0);
        }
    }

    public static void selectionSort(int[] input){
        int size = input.length;
        int least = 0;
        for(int i = 0; i < size-1; i++){
            least = i;//Find the ith smallest element's index
            print2(input, -1, -1, -1, -1, false, input[least], "Least", 1);
            for(int j = i+1; j < size; j++){
                int oldLeast = least;
                print2(input, i+1, size-1, j, oldLeast, false, input[least], "Least", 0);
                if(input[j] < input[least]){
                    least = j;
                    print2(input, i+1, size-1, j, oldLeast, false, input[least], "Least", 1);
                }

            }
            swap(input, i, least);//Insert it in ith position
            print2(input, -1, -1, i, least, true, input[least], "Least", 0);
        }

    }

    public static void swap(int[] input, int index1, int index2){
        int t = input[index1];
        input[index1] = input[index2];
        input[index2] = t;
    }

    public static void print2(int[] a, int innerLoopSt, int innerLoopEd, int indx1, int indx2, boolean swap,
                              int key, String desc, int keyHighlight){
        for(int i = 0; i< a.length; i++){
            if((i == indx1 || i == indx2)){
                if(swap){
                    System.out.print(ANSI_BLUE_BACKGROUND+ANSI_YELLOW + a[i] +RESET+ " ");
                }else {
                    System.out.print(ANSI_YELLOW_BACKGROUND+ANSI_BLUE + a[i] +RESET+ " ");
                }
            }
            else if(i >= innerLoopSt && i <= innerLoopEd )
                System.out.print(RED_UNDERLINED+ANSI_RED + a[i] +RESET+ " ");
            else
                System.out.print(ANSI_GREEN + a[i] +RESET+ " ");
        }
        if(key != -1) {
            System.out.print("  ");
            if(keyHighlight==1)
                System.out.print(ANSI_PURPLE+desc+" "+ANSI_CYAN_BACKGROUND+key+RESET);
            else if(keyHighlight==2)
                System.out.print(ANSI_PURPLE+desc+" "+RESET+ANSI_YELLOW_BACKGROUND+ANSI_BLUE+key+RESET);
            else
                System.out.print(ANSI_PURPLE+desc+" "+ key+RESET);
        }
        if(showAnimation) {
            System.out.flush();
            System.out.print("\r");
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println();
        }

    }

    public static void printLegend(){
        System.out.println();
        System.out.println("--------------LEGEND---------------");
        System.out.print(ANSI_GREEN + "Outer Loop" +RESET+" ");
        System.out.print(RED_UNDERLINED+ANSI_RED + "Inner Loop" +RESET+" ");
        System.out.print(ANSI_PURPLE+ANSI_CYAN_BACKGROUND+"Key Updated"+RESET);
        System.out.println();
        System.out.print(ANSI_YELLOW_BACKGROUND+ANSI_BLUE + "Values Compared" +RESET+" ");
        System.out.print(ANSI_BLUE_BACKGROUND+ANSI_YELLOW + "Values Updated" +RESET+" ");

        System.out.println();
        System.out.println("-----------------------------------");
    }

    public static void main(String[] args){
        int[] random = new int[] {8,2,4,9,3,6};
        int[] sorted = new int[] {12,34,45,56,67,78};
        int[] reverse = new int[] {90, 80, 60, 50, 30, 10};

        //Toggle animation and set animation speed
        //showAnimation = true;
        //delay= 2000;

        printLegend();
        System.out.println("Bubble sort optimized");
        bubbleSortOptimized(random);
        printLegend();
        System.out.println("Bubble sort optimized Sorted Data");
        bubbleSortOptimized(sorted);
        printLegend();
        System.out.println("Bubble sort optimized Reversed Data");
        bubbleSortOptimized(reverse);

        System.out.println();
        printLegend();
        System.out.println("Insertion sort");
        random = new int[] {8,2,4,9,3,6};
        insertionSort(random);
        printLegend();
        System.out.println("Insertion sort Sorted data");
        sorted = new int[] {12,34,45,56,67,78};
        insertionSort(sorted);
        printLegend();
        System.out.println("Insertion sort Reversed data");
        reverse = new int[] {90, 80, 60, 50, 30, 10};
        insertionSort(reverse);

        System.out.println();
        printLegend();
        System.out.println("Selection sort");
        random = new int[] {8,2,4,9,3,6};
        selectionSort(random);
        printLegend();
        System.out.println("Selection sort Sorted data");
        sorted = new int[] {12,34,45,56,67,78};
        selectionSort(sorted);
        printLegend();
        System.out.println("Selection sort Reverse data");
        reverse = new int[] {90, 80, 60, 50, 30, 10};
        selectionSort(reverse);
    }
}
