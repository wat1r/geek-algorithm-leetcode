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
            String[] words = {"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
            String s = "ball";
            words = new String[]{"CitZMIXZKoFbxvOlaza", "hBlKXdKJfBD"};
            s = "hBlKXdKJfBD";
            Assert.assertEquals(1, handler.findString(words, s));
        }


        public int findString(String[] words, String s) {
            int n = words.length, l = 0, r = n;
            while (l < r) {
                int m = l + (r - l) / 2;
                int t = m;
                while (m < r && words[m].equals("")) {
                    m++;
                }
                if (r == m) {
                    r = t;
                    continue;
                }
                if (words[m].equals(s)) return m;
                else if (words[m].compareTo(s) > 0) r = m;
                else l = m + 1;
            }
            return -1;
        }

    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }


        public int findString(String[] words, String s) {
            int left = 0, right = words.length;
            while (left < right) {
                int mid = left + (right - left) / 2;
                while (mid > left && words[mid].equals("")) {
                    mid--;
                }



                if (words[mid].equals(s)) {
                    return mid;
                    //mid在s后面
                } else if (words[mid].compareTo(s) > 0) {
                    right = mid;
                    //mid在s前面
                } else if (words[mid].compareTo(s) < 0) {
                    left = mid + 1;
                }
            }
            return -1;
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
