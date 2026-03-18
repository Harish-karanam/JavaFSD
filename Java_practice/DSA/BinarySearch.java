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
