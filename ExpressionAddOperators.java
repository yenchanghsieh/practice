/*Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

Examples: 
"123", 6 -> ["1+2+3", "1*2*3"] 
"232", 8 -> ["2*3+2", "2+3*2"]
"105", 5 -> ["1*0+5","10-5"]
"00", 0 -> ["0+0", "0-0", "0*0"]
"3456237490", 9191 -> []*/

/*This problem has a lot of edge cases to be considered:

overflow: we use a long type once it is larger than Integer.MAX_VALUE or minimum, we get over it.
0 sequence: because we can't have numbers with multiple digits started with zero, we have to deal with it too.
a little trick is that we should save the value that is to be multiplied in the next recursion.*/
public class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> rst = new ArrayList<String>();
        if(num == null || num.length() == 0) return rst;
        helper(rst, "", num, target, 0, 0, 0);
        return rst;
    }
    public void helper(List<String> rst, String path, String num, int target, int pos, long eval, long multed){
        if(pos == num.length()){
            if(target == eval)
                rst.add(path);
            return;
        }
        for(int i = pos; i < num.length(); i++){
            if(i != pos && num.charAt(pos) == '0') break;
            long cur = Long.parseLong(num.substring(pos, i + 1));
            if(pos == 0){
                helper(rst, path + cur, num, target, i + 1, cur, cur);
            }
            else{
                helper(rst, path + "+" + cur, num, target, i + 1, eval + cur , cur);

                helper(rst, path + "-" + cur, num, target, i + 1, eval -cur, -cur);

                helper(rst, path + "*" + cur, num, target, i + 1, eval - multed + multed * cur, multed * cur );
            }
        }
    }
}

1+2+3 1+2-3 1+2*3
1-2+3 1-2-3 1-2*3
1*2+3 1*2-3 1*2*3



请问第三题在string的最开头可以放符号么？我试了一下好似能行，下面是我试着写的代码，请求指点. 1point3acres.com/bbs
public static boolean insertOp(String s,int target){
                if(s.length()==0)return false;
                return insertHelp(s,target,0); 
        }
        
        public static boolean insertHelp(String s, int target, int sum){
                if(s.length()==0){
                        return sum==target;
                }else{
                        for(int op=-1;op<2;op+=2){
                                
                                for(int i=1;i<=s.length();i++){
                                        int curSum=sum;
                                        String numStr = s.substring(0,i);
                                        int num = Integer.parseInt(numStr)*op;
                                        curSum+=num;
                                        boolean result = insertHelp(s.substring(i),target,curSum);
                                        if(result)return true;
                                }
                        }
                        return false;
                }
        }
