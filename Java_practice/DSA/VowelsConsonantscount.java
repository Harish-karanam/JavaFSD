public class VowelsConsonantscount {
    public static void main(String[] args) {
        String name="apple";
        int vowels=0;
        int consonants=0;
        name=name.toLowerCase();
        for(int i=0;i<name.length();i++){
            char ch=name.charAt(i);
            if(ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u'){
                vowels++;
            }else{
                consonants++;
            }
        }
        System.out.println("vowels "+vowels);
        System.out.println("consonants "+consonants);
        
    }
    
}
