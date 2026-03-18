public class MovingNonZeros {
    public static void main(String[] args) {
        //0,1,0,2,3,0,4,0;
        //op : 1,2,3,4,0,0,0,0

        int a[]={1,2,3,4,0,0,0,0};
        int index=0;
        for(int i=0;i<a.length;i++){
            if(a[i]!=0){
                a[index]=a[i];
                index++;

            }
            
        }

        while(index<a.length){
            a[index]=0;
            index++;
        }

        for(int i=0;i<a.length;i++){
            System.out.print(a[i]);
        }
        
    }
    
}
