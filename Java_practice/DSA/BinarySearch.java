public class BinarySearch {
    //works on sorted array
    public static void main(String[] args) {
        int a[]={1,2,3,4,5,6};
        int find=6;
        int low=0;
        int high=a.length-1;
        while(low<=high){
            int mid=(low+high)/2;
            if(a[mid]==find){
                System.out.println(mid);
                break;
            }else if(a[mid]>low){
                low=low+1;
                
            }else{
                high=high-1;
            }
        }
        
    }
    
}

//another method 
int [] num ={2,55,6,78,99,3};
System.out.println(Arrays.toString(num));

//sort the array
Arrays.sort(num)
System.out.println(Arrays.toString(num));

//using binary search to find 55

int index=Arrays.binarySearch(num, 55);
System.out.println(index);
