public class StringRecursion 
{
    
    public static void printReverse(String str)
    {
        int index = str.length()-1;

        if(str == null || str.equals(""))
        {
            return;
        }

        if(index != -1)
        {
            System.out.println(str.charAt(index));
            index -=1;
            printReverse(str);
        }
    }

    public static String trim(String str)
    {
        if(str == null)
        {
            return null;
        }

        if(str.equals(""))
        {
            return str;
        }

        StringBuilder newStr = new StringBuilder();
        int index = 0;

        if(index != str.length())
        {
            if(str.charAt(index) != " ")
            {
                newStr.append(str.charAt(index));
                trim(str);
            }
        }

        return newStr.toString();
    }

    public static int find(char ch, String str)
    {
        int index = 0;

        if(str.equals("") || str == null)
        {
            return -1;
        }

        if(index != str.length())
        {
            if(str.charAt(index) == ch)
            {
                return index;
            }
            else
            {
                find(ch, str);
            }
            
        }


        return index;
    }


    public static String weave(String str1, String str2)
    {
        int length1 = str1.length();
        int length2 = str2.length();
        int total;
        int index = 0;

        if(str2 == null || str1 == null)
        {
            throw new IllegalArgumentException();
        }

        if(str1.equals(""))
        {
            return str2;
        }

        if(str2.equals(""))
        {
            return str1;
        }

        if(length1 != length2)
        {
            if(length1 > length2)
            {
                total = length1;
            }
            else
            {
                total = length2;
            }
        }
        StringBuilder newStr = new StringBuilder();

        if(str1.length()-1 != 0 ||str2.length()-1 != 0)
        {

            newStr.append(str1.charAt(index));
            newStr.append(str2.charAt(index));
            index++;
            weave(str1.substring(index, length1), str2.substring(index, length2));
        }
        else
        {
            if(str1.length() > str2.length())
            {
                newStr.append(str1.substring(index, str1.length()));
            }
            if(str2.length() > str1.length())
            {
                newStr.append(str2.substring(index, str2.length()));
            }

        }

        return newStr.toString();
    }

    public static void main(String[] args) 
    {
        
    }
}
