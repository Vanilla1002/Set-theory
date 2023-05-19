/*Hello my name is Harel, this is my first java project,I wrote a code that adding all java set theory operations
 I worked with arrays and didn't use hashset once, instead I created a method that gets array and makes it be like a
set.
there is a list of all the operations I added for now:isIn,subset,equal,union,intersection,relative,symmetricDiff
∅ means emptySet
*/

import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        run();
    }

    static int[] set(int[] arr) {//creating set (hashset) by getting an array and deleting duplicates if there
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    count += 1;
                    break;
                }
            }
        }
        int[] newArr = new int[arr.length - count];
        int x = 0;
        for (int i : arr) {
            if (!isIn(i, newArr)) {
                newArr[x] = i;
                x += 1;
            }
        }
        return newArr;
    }
    static boolean isIn(int x, int[] arr) {//takes an int and checks if the set containing the int
        for (int i : arr) {
            if (x == i)
                return true;
        }
        return false;

    }

    static boolean subset(int[] arr, int[] arr1){//Checks if every A element isIn B
        for (int i:arr) {
            if (!isIn(i, arr1)) return false;
        }
        return true;
    }

    static boolean equal(int[] arr, int[] arr1) {//checks if the Sets are equal
        return subset(arr, arr1) && subset(arr1, arr);
    }

    static int[] union(int[] arr, int[] arr1) {//creates a new set that have every element of the two arrays
        int[] newArr = new int[arr.length + arr1.length];
        int x = 0;
        int y = arr.length;
        for (int i : arr) {
            newArr[x] = i;
            x += 1;
        }
        for (int i : arr1) {
            newArr[y] = i;
            y += 1;
        }
        return set(newArr);
    }

     static int[] intersection(int[] arr, int[] arr1) {//creates an array with the shared elements of the 2 arrays
        int count = 0;
        int x = 0;
        for (int i : arr) {
            if (isIn(i, arr1))
                count += 1;
        }
        int[] newArr = new int[count];
        for (int i : arr) {
            if (isIn(i, arr1)) {
                newArr[x] = i;
                x += 1;
            }
        }
        return newArr;
    }
    static int[]relative(int[] arr,int[] arr1){//creates an array with the elements that in A ,but not in B
        int count = 0;
        int x = 0;
        for (int i : arr) {
            if (!isIn(i, arr1))
                count += 1;
        }
        int[] newArr = new int[count];
        for (int i : arr) {
            if (!isIn(i, arr1)) {
                newArr[x] = i;
                x += 1;
            }
        }
        return newArr;

    }
    static int[]symmetricDiff(int[] arr,int[] arr1){//creates an array that contain all the elements that not common
        int count = 0;
        int x = 0;
        for (int i : arr) {
            if (!isIn(i,arr1))
                count += 1;
        }
        for (int i : arr1) {
            if (!isIn(i,arr))
                count += 1;
        }
        int[] newArr = new int[count];
        for (int i : arr) {
            if (!isIn(i,arr1)) {
                newArr[x] = i;
                x += 1;
            }
        }
        for (int i:arr1){
            if (!isIn(i,arr)){
                newArr[x]=i;
                x+=1;
            }
        }
        return newArr;
    }
    static int[]symmetricDiff1(int[] arr,int[] arr1){//symmetricDiff but with relative use (slower and cost more memory)
        int x = 0;
        int[] objArr = relative(arr,arr1);
        arr1 = relative(arr1,arr);
        int[] newArr = new int[arr1.length + objArr.length];
        for (int i=0;i<arr1.length;i++)
            newArr[i]=arr1[i];
        for (int i:objArr){
            newArr[arr1.length+x]=i;
            x+=1;
        }
        return newArr;

    }
   public static int[] setInput (){//"scans" arrays and returns set
       Scanner scan = new Scanner(System.in);
       System.out.println("What length your set will be?");
       int x=scan.nextInt();
       System.out.println("now tell me your elements");
       int[] arr = new int[x];
       for (int i=0;i<x;i++){
           int y= scan.nextInt();
           arr[i]=y;

       }
       return set(arr);  
   }
   static void run(){//run all together
       System.out.println("lets write an set ");
       int[] A = setInput();
       System.out.print("your set is: A:");
       printSet(A);
       System.out.println("lets write another set");
       int[] B = setInput();
       System.out.print("your set is: B:");
       printSet(B);
       if (equal(A, B)) System.out.println("The sets are equal");
       else {
           System.out.println("does A subset in B?");
           System.out.println(subset(A,B));
           System.out.println("does B subset in A");
           System.out.println(subset(B,A));
           System.out.print("the union(∪) between the sets is:");
           printSet(union(A, B));
           System.out.println("the intersection(∩) between the sets is:");
           printSet(intersection(A, B));
           System.out.println("the objects that belong to A and not to B (relative(/)");
           printSet(relative(A, B));
           System.out.println("the objects that belong to B and not to A (relative(/)");
           printSet(relative(B, A));
           System.out.println("the symmetric(∆) between the sets");
           printSet(symmetricDiff(A, B));
       }
   }


    public static void printSet(int[] arr) {//prints sets
        if (arr.length==0){
            System.out.println("∅");
            return;
        }
        System.out.print("{");
        for (int i : arr) {
            System.out.print(i);
            System.out.print(",");
        }
        System.out.println("\b}");
    }
}