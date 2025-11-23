package ua.opnu;

import java.util.*;

public class Task {

    public static void main(String[] args) { }

    // ------------------- Task 1 -------------------
    public void removeShorterStrings(List<String> list) {
        if (list == null || list.size() < 2) return;

        for (int i = 0; i < list.size() - 1; i++) {
            String a = list.get(i);
            String b = list.get(i + 1);

            int lenA = (a == null ? 0 : a.length());
            int lenB = (b == null ? 0 : b.length());

            if (lenA <= lenB) {
                list.remove(i);
            } else {
                list.remove(i + 1);
            }
        }
    }

    // ------------------- Task 2 -------------------
    public void stutter(List<String> list) {
        if (list == null || list.isEmpty()) return;

        int pos = 0;
        while (pos < list.size()) {
            list.add(pos, list.get(pos));
            pos += 2;
        }
    }

    // ------------------- Task 3 -------------------
    public void switchPairs(List<String> list) {
        if (list == null || list.size() < 2) return;

        for (int i = 1; i < list.size(); i += 2) {
            String tmp = list.get(i);
            list.set(i, list.get(i - 1));
            list.set(i - 1, tmp);
        }
    }

    // ------------------- Task 4 -------------------
    public void removeDuplicates(List<String> list) {
        if (list == null || list.size() < 2) return;

        int i = 1;
        while (i < list.size()) {
            if (Objects.equals(list.get(i), list.get(i - 1))) {
                list.remove(i);
            } else {
                i++;
            }
        }
    }

    // ------------------- Task 5 -------------------
    public void markLength4(List<String> list) {
        if (list == null) return;

        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if (s != null && s.length() == 4) {
                list.add(i, "****");
                i++;
            }
        }
    }

    // ------------------- Task 6 -------------------
    public boolean isPalindrome(Queue<Integer> q) {
        if (q == null || q.isEmpty()) return true;

        ArrayDeque<Integer> st = new ArrayDeque<>();
        int size = q.size();

        // copy to stack and restore order
        for (int i = 0; i < size; i++) {
            int val = q.remove();
            q.add(val);
            st.push(val);
        }

        boolean ok = true;

        for (int i = 0; i < size; i++) {
            int v = q.remove();
            if (v != st.pop()) ok = false;
            q.add(v);
        }

        return ok;
    }

    // ------------------- Task 7 -------------------
    public void reorder(Queue<Integer> q) {
        if (q == null || q.isEmpty()) return;

        Deque<Integer> neg = new ArrayDeque<>();
        int initial = q.size();
        int nonNeg = 0;

        for (int i = 0; i < initial; i++) {
            int val = q.remove();
            if (val < 0) {
                neg.push(val);
            } else {
                q.add(val);
                nonNeg++;
            }
        }

        while (!neg.isEmpty()) {
            q.add(neg.pop());
        }

        // rotate queue to restore non-negative relative order
        for (int i = 0; i < nonNeg; i++) {
            q.add(q.remove());
        }
    }

    // ------------------- Task 8 -------------------
    public void rearrange(Queue<Integer> q) {
        if (q == null) return;

        Queue<Integer> odd = new ArrayDeque<>();
        int n = q.size();

        for (int i = 0; i < n; i++) {
            int val = q.remove();
            if (val % 2 == 0) {
                q.add(val);
            } else {
                odd.add(val);
            }
        }

        while (!odd.isEmpty()) {
            q.add(odd.remove());
        }
    }

    // ------------------- Task 9 -------------------
    public int maxLength(Set<String> set) {
        if (set == null || set.isEmpty()) return 0;

        int max = 0;
        for (String s : set) {
            if (s != null && s.length() > max) {
                max = s.length();
            }
        }
        return max;
    }

    // ------------------- Task 10 -------------------
    public void removeEvenLength(Set<String> set) {
        if (set == null) return;

        set.removeIf(x -> x != null && x.length() % 2 == 0);
    }

    // ------------------- Task 11 -------------------
    public int numInCommon(List<Integer> a, List<Integer> b) {
        if (a == null || b == null) return 0;

        Set<Integer> s = new HashSet<>(a);
        s.retainAll(new HashSet<>(b));

        return s.size();
    }

    // ------------------- Task 12 -------------------
    public boolean isUnique(Map<String, String> map) {
        if (map == null) return true;

        return new HashSet<>(map.values()).size() == map.size();
    }

    // ------------------- Task 13 -------------------
    public Map<String, Integer> intersect(Map<String, Integer> m1, Map<String, Integer> m2) {
        Map<String, Integer> res = new HashMap<>();
        if (m1 == null || m2 == null) return res;

        for (var e : m1.entrySet()) {
            Integer v = m2.get(e.getKey());
            if (v != null && v.equals(e.getValue())) {
                res.put(e.getKey(), v);
            }
        }
        return res;
    }

    // ------------------- Task 14 -------------------
    public Map<String, Integer> reverse(Map<Integer, String> map) {
        Map<String, Integer> out = new HashMap<>();
        if (map == null) return out;

        for (var e : map.entrySet()) {
            String val = e.getValue();
            int key = e.getKey();

            out.merge(val, key, Math::max);
        }
        return out;
    }

    // ------------------- Task 15 -------------------
    public int rarest(Map<String, Integer> m) {
        if (m == null || m.isEmpty()) return 0;

        Map<Integer, Integer> count = new HashMap<>();
        for (var v : m.values()) {
            count.put(v, count.getOrDefault(v, 0) + 1);
        }

        int bestValue = Integer.MAX_VALUE;
        int bestFreq = Integer.MAX_VALUE;

        for (var e : count.entrySet()) {
            int val = e.getKey();
            int freq = e.getValue();

            if (freq < bestFreq || (freq == bestFreq && val < bestValue)) {
                bestFreq = freq;
                bestValue = val;
            }
        }

        return bestValue;
    }

    // ------------------- Task 16 -------------------
    public int maxOccurrences(List<Integer> list) {
        if (list == null || list.isEmpty()) return 0;

        Map<Integer, Integer> freq = new HashMap<>();
        for (int x : list) {
            freq.put(x, freq.getOrDefault(x, 0) + 1);
        }

        int max = 0;
        for (int c : freq.values()) {
            if (c > max) max = c;
        }
        return max;
    }
}
