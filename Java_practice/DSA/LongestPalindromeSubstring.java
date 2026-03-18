class LongestPalindromeSubstring{
    public static void main(String[] args) {
        /*1 first take an input 
        2 create an empty container
        3 one loop for input
        4 check condition left should be greater than 0 and right should be less than input length and also check character of left and right should be equal if not stop if yes left-- right++
        5 one container to store the palindrome which we got while checking in previous step than container should hold left and right+1 value
        6 current should be greater than longest container which we created at first 
        so current value will be assigned to longest container 
        7 finally longest will be stored and return the output
         */
        String s="banana";
        String longest="";

        for(int i=0;i<s.length();i++){
            int left=i,right=i;
            while(left>=0 && right<s.length() && s.charAt(left)==s.charAt(right)){
                String current=s.substring(left,right+1);
                if(current.length()>longest.length()){
                    longest=current;
                }
                left --;
            right++;
            }
            
        }
        System.out.println("longest pallindrome "+longest);
    }
}