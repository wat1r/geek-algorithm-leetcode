package com.frankcooper.bank._101_200;

public class _194 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        /**
         * cat words.txt| tr -s ' ' '\n' | sort  | uniq -c  | sort -n -r  | awk '{print $2 ,$1 }'
         */

        /**
         * awk '{for(i=1;i<=NF;i++){asso_array[$i]++;}};END{for(w in asso_array){print w,asso_array[w];}}' words.txt | sort -rn -k2
         *
         * cat words.txt | xargs -n1 | sort | uniq -c | sort -rn | awk '{print $2,$1}'
         *
         */

        /**
         * awk '{
         *     for (i=1;i<=NF;i++){
         *         if (NR==1){
         *             res[i]=$i
         *         }
         *         else{
         *             res[i]=res[i]" "$i
         *         }
         *     }
         * }END{
         *     for(j=1;j<=NF;j++){
         *         print res[j]
         *     }
         * }' file.txt

         */

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }
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
