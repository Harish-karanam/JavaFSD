//removing duplicates forom an array

import java.util.HashSet;

class RemoveDuplicates{
    public static void main(String[] args) {
        int a[]={1,1,2,3,4,4,3,5};
        //op: 1,3,4
        HashSet<Integer> seen= new HashSet<>();
        HashSet<Integer>duplicate=new HashSet<>();

        for(int num:a){
            if(!seen.add(num)){
                duplicate.add(num);
            }
        }
       System.out.println("duplicates "+duplicate);
       
        // another method
        // for(int i=0;i<a.length;i++){
        //     if(seen.contains(a[i])){
        //         duplicate.add(a[i]);
        //     }else{
        //         seen.add(a[i]);
        //     }
        // }
        // System.out.println("duplicates "+duplicate);
        
    }
}