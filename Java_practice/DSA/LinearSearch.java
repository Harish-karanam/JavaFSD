public class LinearSearch {
    //works on sorted and unsorted array

   public static void main(String[] args) {
     int a[]={3,1,5,6,4,2};
    int find=3;
    for(int i=0;i<a.length;i++){
        if(a[i]==find){
            System.out.println(i);
            break;

        }else{
            System.out.println("not found");
        }
    }
   }
    
}
