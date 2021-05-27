package com.frankcooper.interview;

import java.util.*;

import org.junit.Assert;

public class _10_05 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String[] words = {"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
            String s = "ball";
            words = new String[]{"CitZMIXZKoFbxvOlaza", "hBlKXdKJfBD"};
            s = "hBlKXdKJfBD";
            words = new String[]{"", "", "", "", "", " dQWlBwMkQGCAH", "", "", "", "", "", "", "", "", " kbmYemYUbb", "", "", "", "", "", "AhdDHirVKcLkr ", "", "", "", "", "", "", "", "", "", "", "", "BNAbUAatDzPXTHQfEhiH", "", "", "", "", "", "BRrrNNUbmm", "", "", "BrluxJYeZ", "", "", "", "", "", "", "", "", "", "", "C", "", "", "", "COZgRwNRaTVH", "", "", "", "", "", "", "", "", "", "COy", "", "", "", "", "", "CZiiiXfgR", "", "", "", "", "", "", "", "", "CvsytGxtr", "", "", "", "", "DYgNOJzvt dKo", "", "", "", "", "", "", "", "", "ETxOvZADWeGZymn", "", "", "", "", "Enw", "", "", "", "", "ErObvXdjyJ", "", "", "", "", "", "", "", "Fxt", "", "", "", "", "", "Hcg", "", "IFCmcsMQZVDw", "", "", "", "", "", "IxwAsjDo", "", "", "", "", "JJRmOfMVv", "", "", "", "", "", "", "", "", "", "KbmELArhbtQloxzJkOZB", "", "", "KmrXslnWHv bviiXljAK", "", "", "", "", "", "KoQgClvxqrOEIM nCY", "", "", "", "", "", "LBZAnpDJskac", "", "LKSy", "", "", "", "", "", "LMCUFc ynZCbTqJRDOIH", "", "", "", "", "", "", "", "", "", "LZui eEOVxOo", "", "", "", "", "", "", "LmeGZUSZgqJ", "", "", "", "MMsARKwsJ", "", "", "My", "", "", "", "NGcuzKOVRNyqDFuE", "", "", "NHEReECTTxUCeElZquZe", "", "", "", "", "OKoDrnWrGZyTYUDS", "", "", "", "", "", "", "", "", "", "OitAuWpxzogGOVGbVqRc", "", "", "", "", "", "", "", "", "OvSVSZYgfaTD", "", "", "PW", "", "", "", "", "", "", "", "", "", "Pf", "", "PmqWrQsrrFVpQe", "", "", "", "", "", "", "", "", "", "QSZxNOguO", "", "", "", "", "QxhpjdwPNUOTj", "", "", "", "", "", "", "", "", "", "R", "", "", "", "", "", "RZZnlbAHKZJH", "", "", "", "", "", "", "RcrNcLvsvCdYCe", "", "", "", "", "", "RunaVcyf", "", "", "SkCZwclZ", "TNawyWMprE", "", "", "", "", "", "", "", "", "TUKi", "TeBcKqVdjVIJtzRy", "", "", "TmGbS GYWH", "", "", "", "", "", "UgjPSTLdQAesaBYVV", "", "", "UnpPgKLLgnHsDRw", "VDHEYbbmcSibGkoOY", "", "", "VFhbWJAydDCFyZvbFA", "", "", "", "", "", "", "", "", "", "", "", "VWQZxHx YvKZgxkScVR", "", "", "", "", "", "WJqaQdZZXBrXXtIWGq", "", "", "WiAdRTHgdJNLKZPrV", "", "", "", "", "", "", "", "", "X", "", "", "", "", "", "", "XgbKyxpd", "", "", "", "XjYPV", "", "", "", "", "", "", "", "YNY UFJJNRMHtA", "", "", "", "", "", "", "", "YeNY", "", "", "", "", "", "", "", "", "", "ZKcuWQvdKD", "", "", "", "ZjjfTUB", "", "", "", "", "", "", "ZxhS", "", "", "", "", "", "", "aNRzbCptSLqKcwHn", "", "", "", "", "", "", "aWTfJvpFLsNzL", "", "", "", "", "", "", "", "", "", "aquRNbNXxsRQwBHVeIWa", "", "", "", "", "", "bsy", "", "", "", "", "", "", "c cxJUciWsLmXZSCIG", "", "", "", "", "", "", "dHGYGGa", "", "", "eBnLl", "", "ffesVvulHsm", "", "", "", "", "", "", "", "", "", "", "", "ggXhLRpAHZYPh", "", "", "", "", "", "", "", "", "", "", "", "gksuHImKFIzbHmQBzksg", "grWcl", "", "", "", "", "", "", "hLwNisMg", "", "", "", "", "", "", "", "", "", "", "htZGqEaqFSsHlDQX", "", "", "", "", "", "", "", "", "", "jGppHHwT", "jd", "", "", "", "", "", "", "", "kWof", "", "", "", "", "", "", "", "kvPEgk", "lDBdeQZRgQNlDIP", "", "", "", "", "", "", "", "", "", "lIceBio", "", "", "", "", "", "", "", "", "", "", "", "mIIIss", "", "", "", "", "", "", "", "", "", "mJKai", "", "", "", "", "muIekSC", "", "nMJipECkkgnEcMmyfob", "", "", "", "", "", "", "", "nXivxaBbI", "q", "", "", "", "", "", "", "", "", "", "qAmqnvNBHnB", "", "", "", "", "", "", "", "", "", "qPOf tD", "", "", "", "", "", "", "", "", "", "qilsrkiifEeOj", "", "", "", "", "", "", "", "", "", "rPfjvzoHWzecujWUoRX", "", "", "", "", "rYf", "", "", "", "", "", "", "", "", "", "", "", "sGUgT PfifYJYdkd", "", "", "", "", "", "", "", "", "", "sf LOmMcRojVEc", "", "tvJQ", "", "", "", "", "uabKCYiiKmmpUns", "", "", "", "", "", "", "", "", "", "", "", "udz", "", "uqSPk OkChjuD", "", "", "", "", "", "", "y", "", "", "", "", "", "yHQWPlWSNwtb", "", "", "", "", "", "", "", "", "", "", "yWfXCUsa", "", "", "", "", "", "", "zEZXmgrvQoOTwOKoEIEd", "", "", "zZEMDDvzsOgTmzc", "", "", "", "", "", "", "", "", "", "", "", "zk", "", "", "", "", "", "", "", "", "znWcoGeFJicW nPjHUGm"};
            s = "kKfytHTqoIrKNCQjzJIF";
            handler.findString(words, s);

        }


        public int findString(String[] words, String s) {
            int n = words.length, l = 0, r = n - 1;
            while (l <= r) {
                while (words[l].equals("")) l++;
                while (words[r].equals("")) r--;
                int m = l + (r - l) / 2;
                while (words[m].equals("")) m++;
                int t = words[m].compareTo(s);
                if (s.equals(words[m])) return m;
                else if (t > 0) r = m - 1;
                else l = m + 1;
            }
            return -1;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public void test() {

        }

//        public:
//
//        int findString(vector<string>&words, string s) {
//            int L = 0, R = words.size() - 1;
//            while (L <= R) {
//                int mid = L + (R - L) / 2;
//                if (words[mid] == s) {
//                    return mid;
//                } else {
//                    if (words[mid] == "") {
//                        while (mid != R) if (words[R] == s) return mid;
//                        else R--;
//                        R = mid;
//                    } else if (words[mid] > s) R = mid - 1;
//                    else L = mid + 1;
//                }
//            }
//            return -1;
//        }

    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
