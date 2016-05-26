package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyClass {

    public int reverse(int x) {
        int result = 0;
        while (x != 0) {
            result = result * 10 + x % 10; // 每一次都在原来结果的基础上变大10倍，再加上余数
            x = x / 10; // 对x不停除10
        }
//            if (result < Integer.MIN_VALUE || result > Integer.MAX_VALUE) {
        if (result < -2147483648 || result > 2147483647) {
            return 0;
        } else {
            return result;
        }
    }

    public int reverse1(int num) {
        int result = 0;
        String strNums = String.valueOf(num);
        int zero = strNums.length() - 1;
        int a;
        for (int i = 0; i < strNums.length(); i++) {
            a = num % 10;
            double x = Math.pow(10, zero);
            result = result + (int) (a * x);
            num = num / 10;
            zero--;
        }
        return result;
    }

    /**
     * {"abcabcbb",3}{"bbbbb",1}{"pwwkew",4} {"dvdf",3}
     */
    public int getStr(String str) {
        char[] originArray = str.toCharArray();
        List<Character> list = new ArrayList<>();
        for (int i = 1; i < originArray.length; i++) {
            if (originArray[i - 1] != originArray[i]) {
                if (!list.contains(originArray[i - 1])) {
                    list.add(originArray[i]);
                }
            }
        }
        return list.size();
    }

    public  List getLongestNoRepeatSubString(String src) {
        char[] arr = src.toCharArray();
        int len = arr.length;//the length of the array
        List<String> subList = new ArrayList<String>();//which is used to save the substring
        int max;//the length of the longest substring
        int start;//the start of the longest substring
        Map<Character, Integer> charMap = new HashMap<Character, Integer>();//to save the substirng
        max = start = 0;
        int i;
        for (i = 0; i < len; i++) {
            if (!charMap.containsKey(arr[i])) {//if charMap don't include arr[i],then put it into charMap
                charMap.put(arr[i], i);
            } else {
                int pre = charMap.get(arr[i]);
                int tempLen = i - start;//the length of the current substring
                if (tempLen >= max) {
                    if (tempLen > max) subList.clear();//Refresh
                    max = tempLen;
                    subList.add(src.substring(start, i));//save
                    charMap.put(arr[i], i);
                } else {
                    charMap.put(arr[i], i);
                }
                start = pre + 1;//reset the start position
            }
        }
        if (i - start >= max) {//to deal with the no-repeated String
            if (i - start > max) subList.clear();
            subList.add(src.substring(start, i));
        }
        return subList;
    }

    public  List longestsubString (String src){
        char[] arr=src.toCharArray();
        int len=arr.length;//数组长
        List<String> subList=new ArrayList<>();//最长的子串可能会有多少，用这个临时保存
        int max;//最长子段长度
        int start;//最长子段的开始位置
        Map<Character,Integer> charMap=new HashMap();//保存对应字符及其最后出现的位置
        max=start=0;
        int i;
        for(i=0;i<len;i++){
            if(!charMap.containsKey(arr[i])){//不包含的话就加入到map中
                charMap.put(arr[i], i);
            }else{//包含的话就得处理了,看在重复出现前的子串是否是更长
                int pre=charMap.get(arr[i]);
                int tempLen=i-start;//当前子串长度
                if(tempLen>=max){//找到新的最长子串了
                    if(tempLen>max)subList.clear();//需要刷新列表
                    max=tempLen;
                    subList.add(src.substring(start, i));//保存这个最长子串
                    charMap.put(arr[i], i);//置成新的位置
                }else{
                    charMap.put(arr[i], i);//置成新的位置
                }
                start=pre+1;//只要重复了，都得重新开始设置start的位置
            }
        }
        if(i-start>=max){//这个主要是处理这种情况：abcd 没有重复的~~
            if(i-start>max)subList.clear();
            subList.add(src.substring(start, i));
        }
        return subList;
    }

    public int lenghOfLongestSubString(String src) {
        Map<Character, Integer> map = new HashMap<>();
        int left=0;//子字符串的开始位置
        int max=0;//子字符串的最大长度
        char[] srcArray = src.toCharArray();
        for (int i=0;i<srcArray.length;i++) {
            int j;
            if (map.containsKey(srcArray[i])) {
                j = map.get(srcArray[i]);
                if (left <= j) {
                    left = j + 1;
                }
            }
            map.put(srcArray[i], i);
            max = Math.max(i - left + 1, max);
        }
        return max;
    }



    public static void main(String[] args) {
        MyClass myClass = new MyClass();
//        System.out.println(new MyClass().reverse(1000002));
//        System.out.println(new MyClass().getLongestNoRepeatSubString("pwwkew"));
//        System.out.println(new MyClass().getLongestNoRepeatSubString("abcabcbb"));
//        System.out.println(new MyClass().getLongestNoRepeatSubString("bbbbb"));
//        System.out.println(new MyClass().getLongestNoRepeatSubString("dvdf"));

        System.out.println(myClass.lenghOfLongestSubString("dvdf"));
        System.out.println(myClass.lenghOfLongestSubString("pwwkew"));
        System.out.println(myClass.lenghOfLongestSubString("abcabcbb"));
        System.out.println(myClass.lenghOfLongestSubString("bbbbb"));

    }
}
/**
 * aba
 * left=0;pre=-1;max=0;
 * 第一次循环：i=1,s[1]!=s[0] ,pre=i-1=0;
 * 第二次循环：i=2,s[2]!=s[1],pre!=-1,s[2]==s[pre/0]
 *            pre=2-1=1,
 * 跳出循环，max=max{0,3-0}=3
 *
 * abc
 * left=0;pre=-1;max=0;
 * 第一次循环：i=1,s[1]!=s[0] ,pre=i-1=0;
 * 第二次循环：i=2,s[2]!=s[1],s[2]!=s[0],
 *         则：max=max{0,2-0}=2,left=1
 *             pre=2-1=1;
 * 跳出循环：max=max{2,3-1}=2
 *
 */
