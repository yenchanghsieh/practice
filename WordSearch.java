/*Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

For example,
Given board =

[
  ["ABCE"],
  ["SFCS"],
  ["ADEE"]
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.*/
这个题目基本上就是DFS喽。然后注意一下递归回溯的问题。我们可以建设一个boolean的数组来记录访问过的值。在return false之前，我们应该把之前置

过的标记位置回来. 时间复杂度： http://www1.mitbbs.ca/article_t1/JobHunting/32524193_32524299_2.html

time 复杂度是m*n*4^(k-1). 也就是m*n*4^k.
m X n is board size, k is word size.

recuision最深是k层，recursive部分空间复杂度应该是O(k) + O(m*n)(visit array)
与解1是一样的，但我们可以省掉O(m*n)的空间复杂度。具体的作法是：在进入DFS后，把访问过的节点置为'#'，访问完毕之后再置回来即可。


// Nine Chapter
public class Solution {
    // recursion
    public boolean exist(char[][] board, String word) {
        if(board == null || board.length == 0)
            return false;
        if(word.length() == 0)
            return true;
        
        for(int i = 0; i< board.length; i++){
            for(int j=0; j< board[0].length; j++){
                if(board[i][j] == word.charAt(0)){
                    
                    boolean rst = find(board, i, j, word, 0);
                    if(rst)
                        return true;
                }
            }
        }
        return false;
    }
    
    private boolean find(char[][] board, int i, int j, String word, int start){
        if(start == word.length())
            return true;
        
        if (i < 0 || i>= board.length || 
     j < 0 || j >= board[0].length || board[i][j] != word.charAt(start)){
            return false;
	 }
        
        board[i][j] = '#'; // should remember to mark it
        boolean rst = find(board, i-1, j, word, start+1) 
		|| find(board, i, j-1, word, start+1) 
		|| find(board, i+1, j, word, start+1) 
		|| find(board, i, j+1, word, start+1));
        board[i][j] = word.charAt(start);
        return rst;
    }
}


// My Code
public class Solution {
    public boolean exist(char[][] board, String word) {  
        if(word==null || word.length()==0)  
            return true;  
        if(board==null || board.length==0 || board[0].length==0)  
            return false;  
        boolean[][] used = new boolean[board.length][board[0].length];  
        for(int i=0;i<board.length;i++)  
        {  
            for(int j=0;j<board[0].length;j++)  
            {  
                if(search(board,word,0,i,j,used))  
                    return true;  
            }  
        }  
        return false;  
    }  
    private boolean search(char[][] board, String word, int index, int i, int j, boolean[][] used)  
    {  
        if(index == word.length())  
            return true;  
        if(i<0 || j<0 || i>=board.length || j>=board[0].length || used[i][j] || board[i][j]!=word.charAt(index))  
            return false;  
        used[i][j] = true;  
        boolean res = search(board,word,index+1,i-1,j,used)   
                    || search(board,word,index+1,i+1,j,used)  
                    || search(board,word,index+1,i,j-1,used)   
                    || search(board,word,index+1,i,j+1,used);  
        used[i][j] = false;  
        return res;  
    }

}


// My way
public class Solution {
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || word == null || word.length() == 0) {
            return false;
        }
        int row = board.length;
        int col = board[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (dfs(board, word, new boolean[row][col], i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean dfs(char[][] board, String word, boolean[][] visit, int row, int col, int index) {
        if(row < 0 || col < 0 || row >= board.length || col >= board[0].length || 
            visit[row][col] || board[row][col]!=word.charAt(index)) {
                return false;
            }
        if (index == word.length() - 1 && board[row][col] == word.charAt(index)) {
            return true;
        }
        visit[row][col] = true;
        boolean res = dfs(board,word,visit,row + 1, col, index + 1) ||
                      dfs(board,word,visit,row - 1, col, index + 1) ||
                      dfs(board,word,visit,row, col + 1, index + 1) ||
                      dfs(board,word,visit,row, col - 1, index + 1);
        visit[row][col] = false;
        return res;
    }
}



public class Solution {
    public boolean exist(char[][] board, String word) {
    int m = board.length;
    int n = board[0].length;
 
    boolean result = false;
    for(int i=0; i<m; i++){
        for(int j=0; j<n; j++){
           if(dfs(board,word,i,j,0)){
               result = true;
           }
        }
    }
 
    return result;
}
 
public boolean dfs(char[][] board, String word, int i, int j, int k){
    int m = board.length;
    int n = board[0].length;
 
    if(i<0 || j<0 || i>=m || j>=n){
        return false;
    }
 
    if(board[i][j] == word.charAt(k)){
        char temp = board[i][j];
        board[i][j]='#';
        if(k==word.length()-1){
            return true;
        }else if(dfs(board, word, i-1, j, k+1)
        ||dfs(board, word, i+1, j, k+1)
        ||dfs(board, word, i, j-1, k+1)
        ||dfs(board, word, i, j+1, k+1)){
            return true;
        }
        board[i][j]=temp;
    }
 
    return false;
}
}


