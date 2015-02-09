
package chekers;
//Cheks users input in <form> tag

import com.deskind.mavenwebapp.dao.HibernateStudentDao;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormCheker {
    
    //Patterns 
    public static String correctPattern = "^[A-Z][a-z]+$";
    public static String emptyPattern = "";
    
    public static Matcher punctuationMatcher;
    public static Matcher numberMatcher;
    public static Matcher spaceMatcher;
    
    //Matchers
//    Matcher punctuationMatcher = Pattern.compile("\\p{Punct}").matcher(firstAndLast);
//    Matcher numberMatcher = Pattern.compile("\\d+").matcher(firstAndLast);
//    Matcher spaceMatcher = Pattern.compile("\\s+").matcher(firstAndLast);
    
    static public String chekFormParameter(String parameterOne, String parameterTwo){
        
        String concatenatedString = parameterOne + parameterTwo;
        
        punctuationMatcher = Pattern.compile("\\p{Punct}").matcher(concatenatedString);
        numberMatcher = Pattern.compile("\\d+").matcher(concatenatedString);
        spaceMatcher = Pattern.compile("\\s+").matcher(concatenatedString);
        
        if(Pattern.matches(correctPattern, parameterOne) && Pattern.matches(correctPattern, parameterTwo)){
                    return "Operation success!";
                }else if(Pattern.matches(emptyPattern, parameterOne) || Pattern.matches(emptyPattern, parameterTwo)){
                    return "Fist or Last name can't be empty!";
                }else if(punctuationMatcher.find()||numberMatcher.find()||spaceMatcher.find()){
                    return "First or Last names can't contain PUNCTUATION, SPACE, NUMBERS!";
                }else return "error";
        
    }
    
    static public String chekFormParameter(String parameterOne){
        
        punctuationMatcher = Pattern.compile("\\p{Punct}").matcher(parameterOne);
        numberMatcher = Pattern.compile("\\d+").matcher(parameterOne);
        spaceMatcher = Pattern.compile("\\s+").matcher(parameterOne);
        
        if(Pattern.matches(correctPattern, parameterOne)){
                    return "Operation success!";
                }else if(Pattern.matches(emptyPattern, parameterOne)){
                    return "Fist or Last name can't be empty!";
                }else if(punctuationMatcher.find()||numberMatcher.find()||spaceMatcher.find()){
                    return "First or Last names can't contain PUNCTUATION, SPACE, NUMBERS!";
                }else return "error";
        
    }
}
