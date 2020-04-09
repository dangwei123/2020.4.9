给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
class Solution {
    List<String> res=new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        backTrack(n,"",0,0);
        return res;
    }
    private void backTrack(int n,String s,int left,int right){
        if(right>left||left>n||right>n){
            return;
        }
        if(left==n&&right==n){
            res.add(s);
            return;
        }
        backTrack(n,s+"(",left+1,right);
        backTrack(n,s+")",left,right+1);
    }
}

给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map=new HashMap<>();
        for(String s:strs){
            char[] c=s.toCharArray();
            Arrays.sort(c);
            String t=new String(c);
            if(map.containsKey(t)){
                map.get(t).add(s);
            }else{
                List<String> tmp=new ArrayList<>();
                tmp.add(s);
                map.put(t,tmp);
            }
        }
        return new ArrayList(map.values());
    }
}

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<Integer,List<String>> map=new HashMap<>();
        int[] arr={2,3,5,7,11,13,17,19,23,29,
                   31,37,41,43,47,53,59,61,67,71,
                   73,79,83,89,97,101};
        for(String s:strs){
            char[] c=s.toCharArray();
            int t=1;
            for(int i=0;i<c.length;i++){
                t*=arr[c[i]-'a'];
            }
            if(map.containsKey(t)){
                map.get(t).add(s);
            }else{
                List<String> tmp=new ArrayList<>();
                tmp.add(s);
                map.put(t,tmp);
            }
        }
        return new ArrayList(map.values());
    }
}


给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int len=s.length();
        if(len==1){
            return 1;
        }
        int res=0;
        int left=0;
        for(int right=1;right<len;right++){
            int index=s.substring(left,right).indexOf(s.charAt(right));
            if(index>=0){
                left=left+index+1;
            }
            res=Math.max(res,right-left+1);
        }
        return res;
    }
}

给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
class Solution {
    public String longestPalindrome(String s) {
        char[] c=s.toCharArray();
        int maxlen=0;
        int start=0;
        for(int i=0;i<c.length;i++){
            int len1=judge(c,i,i);
            int len2=judge(c,i,i+1);
            if(len1>maxlen){
                maxlen=len1;
                start=i-(len1-1)/2;
            }
            if(len2>maxlen){
                maxlen=len2;
                start=i-(len2-1)/2;
            }
        }
        return s.substring(start,start+maxlen);
    }
    private int judge(char[] c,int left,int right){
        while(left>=0&&right<c.length&&c[left]==c[right]){
            left--;
            right++;
        }
        return right-left-1;
    }
}

class Solution {
    public String longestPalindrome(String s) {
        int len=s.length();
        if(len<2) return s;
        boolean[][] dp=new boolean[len][len];
        for(int i=0;i<len;i++){
            dp[i][i]=true;
        }
        int end=0;
        int begin=0;
        for(int i=1;i<len;i++){
            for(int j=0;j<i;j++){
                if(s.charAt(i)==s.charAt(j)){
                    if(i-j<3){
                        dp[j][i]=true;
                    }else{
                        dp[j][i]=dp[j+1][i-1];
                    }
                }else{
                    dp[j][i]=false;
                }
                
                if(dp[j][i]&&i-j>end-begin){
                    begin=j;
                    end=i;
                }
            }
        }
        return s.substring(begin,end+1);
    }
}

