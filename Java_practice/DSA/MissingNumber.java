public class MissingNumber {
    public static void main(String[] args) {
        //find missing number
       int a[]={1,2,3,5};
       int n=5;
       int expected=n*(n+1)/2;
       int solution=0;
       for(int num:a){
           solution=solution+num;
           //solution+=num;
           
       }
       int actualmissing=expected-solution;
       System.out.println(actualmissing);
    }
    
}
