package amazon;

/*
lc341
题目给了NestedInteger这种数据结构，然后要我们处理List<NestedInteger>,将其扁平化。
首先List<NestedInteger> 是一个列表，里面元素类型是NestedInteger。
而ListNestedInteger是什么呢，它可能是一个整数，

这道题目挺难得，直接看题解和lc284有点像，建议一起看：
https://leetcode-cn.com/problems/flatten-nested-list-iterator/solution/java-liang-chong-jie-fa-by-wwwhang/
 */


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class NestedIterator implements Iterator<Integer> {

    List<Integer> list = new ArrayList<>();
    ListIterator<Integer> it;

    public NestedIterator(List<NestedInteger> nestedList) {
        dfs(nestedList);
        it = list.listIterator();
    }

    @Override
    public Integer next() {
        return it.next();
    }

    @Override
    public boolean hasNext() {
        return it.hasNext();
    }

    private void dfs(List<NestedInteger> nestedList){
        for (NestedInteger nestedinteger: nestedList) {
            if (nestedinteger.isInteger()) {
                list.add(nestedinteger.getInteger());
            } else {
                dfs(nestedinteger.getList());
            }
        }
    }


    public interface NestedInteger {
    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
     // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
    }
}
