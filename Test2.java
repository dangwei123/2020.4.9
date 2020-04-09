给定一个 没有重复 数字的序列，返回其所有可能的全排列。
class Solution {
    List<List<Integer>> res=new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        back(nums,0,new boolean[nums.length],new ArrayList<>());
        return res;
    }
    private void back(int[] nums,int len,boolean[] isVisited,List<Integer> row){
        if(len==nums.length){
            res.add(new ArrayList(row));
            return;
        }
        for(int i=0;i<nums.length;i++){
            if(isVisited[i]){
                continue;
            }
            row.add(nums[i]);
            isVisited[i]=true;
            back(nums,len+1,isVisited,row);
            row.remove(row.size()-1);
            isVisited[i]=false;
        }
    }
}

给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。

说明：解集不能包含重复的子集。
class Solution {
    List<List<Integer>> res=new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        back(nums,0,new ArrayList<>());
        return res;
    }
    private void back(int[] nums,int index,List<Integer> row){
        res.add(new ArrayList(row));
        if(index==nums.length){
            return;
        }
        for(int i=index;i<nums.length;i++){
            row.add(nums[i]);
            back(nums,i+1,row);
            row.remove(row.size()-1);
        }
    }
}

给定一个二维网格和一个单词，找出该单词是否存在于网格中。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
class Solution {
    private int row;
    private int col;
    public boolean exist(char[][] board, String word) {
        row=board.length;
        if(row==0) return false;
        col=board[0].length;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(board[i][j]==word.charAt(0)){
                    if(back(board,i,j,word,0,new boolean[row][col])){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private boolean back(char[][] board,int i,int j,String word,int k,boolean[][] visit){
        if(k==word.length()){
            return true;
        }
        if(i<0||j<0||i>=row||j>=col||visit[i][j]||board[i][j]!=word.charAt(k)){
            return false;
        }
        visit[i][j]=true;
        if(back(board,i+1,j,word,k+1,visit)||
          back(board,i-1,j,word,k+1,visit)||
          back(board,i,j+1,word,k+1,visit)||
          back(board,i,j-1,word,k+1,visit)){
            return true;
        }
        visit[i][j]=false;
        return false;
    }
}

给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。

给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
class Solution {
    List<String> res=new ArrayList<>();
    String[] arr={"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    public List<String> letterCombinations(String digits) {
        if(digits.length()==0) return res;
        back(digits,"",0);
        return res;
    }
    private void back(String digits,String s,int index){
        if(s.length()==digits.length()){
            res.add(s);
            return;
        }
        int k=digits.charAt(index)-'0';
        for(int i=0;i<arr[k].length();i++){
            s+=arr[k].charAt(i);
            back(digits,s,index+1);
            s=s.substring(0,s.length()-1);
        }
    }
}

