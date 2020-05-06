package com.frankcooper.bank;

import java.util.Arrays;

public class _1160 {


    public static void main(String[] args) {
        _1160 handler = new _1160();
        String[] words = {"cat", "bt", "hat", "tree"};
        String chars = "atach";

//        words = new String[]{"dyiclysmffuhibgfvapygkorkqllqlvokosagyelotobicwcmebnpznjbirzrzsrtzjxhsfpiwyfhzyonmuabtlwin", "ndqeyhhcquplmznwslewjzuyfgklssvkqxmqjpwhrshycmvrb", "ulrrbpspyudncdlbkxkrqpivfftrggemkpyjl", "boygirdlggnh", "xmqohbyqwagkjzpyawsydmdaattthmuvjbzwpyopyafphx", "nulvimegcsiwvhwuiyednoxpugfeimnnyeoczuzxgxbqjvegcxeqnjbwnbvowastqhojepisusvsidhqmszbrnynkyop", "hiefuovybkpgzygprmndrkyspoiyapdwkxebgsmodhzpx", "juldqdzeskpffaoqcyyxiqqowsalqumddcufhouhrskozhlmobiwzxnhdkidr", "lnnvsdcrvzfmrvurucrzlfyigcycffpiuoo", "oxgaskztzroxuntiwlfyufddl", "tfspedteabxatkaypitjfkhkkigdwdkctqbczcugripkgcyfezpuklfqfcsccboarbfbjfrkxp", "qnagrpfzlyrouolqquytwnwnsqnmuzphne", "eeilfdaookieawrrbvtnqfzcricvhpiv", "sisvsjzyrbdsjcwwygdnxcjhzhsxhpceqz", "yhouqhjevqxtecomahbwoptzlkyvjexhzcbccusbjjdgcfzlkoqwiwue"
//                , "hwxxighzvceaplsycajkhynkhzkwkouszwaiuzqcleyflqrxgjsvlegvupzqijbornbfwpefhxekgpuvgiyeudhncv", "cpwcjwgbcquirnsazumgjjcltitmeyfaudbnbqhflvecjsupjmgwfbjo", "teyygdmmyadppuopvqdodaczob", "qaeowuwqsqffvibrtxnjnzvzuuonrkwpysyxvkijemmpdmtnqxwekbpfzs", "qqxpxpmemkldghbmbyxpkwgkaykaerhmwwjonrhcsubchs"};
//        chars = "usdruypficfbpfbivlrhutcgvyjenlxzeovdyjtgvvfdjzcmikjraspdfp";


        words = new String[]{"qobxtxjzdngkrzamsxzdvbvkiwijokwdyndqllhqpaoxzwonriclsm",
                "fahtqqnuzhhhrcblquaosdfdcqoskxcsdnhtwvvvzsxkpjprytieieniafnltxmuzwkdnttofpibi",
                "xedhntgrqegfs", "wrssfvsbcehbahbvojnzgacbgveitirkjmmyiorwykynqddgydzgfvlvplfnyumgxturxraonpchd", "hpmdzhpgijirecxzkuyhptiytnuscu", "xljgysrjjukphjgzbpn", "gmwbirxt", "yhvpsvsnhfmbmcpihnqtodspbvexnpgcqrrughbakbufyjispkquvfppkaffypdpnvikjygdaarcigipfhuvzzzbgw", "gvvbgpjolobpbxcnhnzuqrsqgrkanwmnnkqynakkooailoafunopsrlijqhaqmszssxikftcfoqsw", "naktgxfyuvuoh", "muui", "ghzqskipqprrzeligdjyowypeerogxonvztsq", "onosezgutawtzteoagbftclsqpfsxtwetourxjxurdqevrir", "fcskqxwkzsldsjihahalnw", "lsstgzxjxabcbspjwelqmhtnurgfmdtpaihxnxad", "nwrwtwetgjwooevhxhkzlvsyghtkldioyjhkkniepktqs", "utohzbqvkqkq", "vdoqnhtidgicevprrrwlbtldtaxpsxdhxhgbwlkbeglkbhrujtafswjkozdmdpvwhsuskofmxxtprtpopacslinwozth", "zqlzxpemomnbzxlorvlkxt", "kubshvnldnkofitnnjere", "czxmqpowzzhdbhgtlqdokrzxowsvwyavfhcycctgdvrsfzmanrlktfaetnuayrqkvhcbxezfahkrxgaaztovrxuhnll", "rrcesnfbxglhjawldnnuiiepdsofbrsbjczlemusqwvenicxmtdmpwfrnizymfmqynvtkbrmablcugroshc", "thholqebekkysstqzlbbdapktxumygplqganucwnahmrihiraxdnvbiaqhykcti", "fagqnapzeeglbdzsbneosxmptmwopjcxztukhpjkqjmjbkpbzrfaqskctehyboeddmilkwlurcb", "wtjdykncubkduhxiwwusoxvzpnaxpnzdjmwddnonsmmvwmuaxghetgrwpoeqbprxgviwzagyqopfdakrqjgiy", "nsftehpgzstetbganbtozdopptseucdqkhzdmujnzjdvufqtikgeepttnrabb", "ozihktgnvljzhqwanxemtzxphzqvmoblvi", "iwdsrekqllbbyarzzmbqbvldvxctdeswiyahiwfoefhfsfwktdzaulnalewbusazjhcbtxjuck", "dylhds", "idnaddnzbodhjrpshhahnbbnrskruxszxeeyanumazvahktizectmmvjbhnhbrohsyqhrgq", "scyzsykrwzuozmbrbenfiqpxchtpmcxepjiglaeionsmbwrzeidupayusczcooudpcgkgspbuyzcdfymsejucb", "otpcfrhckxmnseayhwoyjjfkceaoznmmkikpdsuueyqmbsuelmhqnmdsjs", "xoghnhpypfiibqrpgtyux", "cxhpbcziptgandiwxtctdjpboiqwv", "gyjosuhwgbqpcdsdqadopdqozjxpzwxbqaffnxaddhgrxmunpidvpnijxnbawshcznkmprpgkhvzxmzbaftedgtfgjhaisdnva", "jlnxshfthqgzlnvjzztrnjswwsotpybxecyqhnfkbfwvmxpcs", "mtddspmqwbnoiajpexsuhxogarduzsoammqqelh", "sixkvxgnbalipwmkbcwpugpvolsvvlmaaeeobmoeogspbkbsskwjdqkite", "xhsutriuynfrldjbhexhxjgcfwcujvwsqfiaqwvjnkjkswdpaynelhryrzfeqjhajezmolk", "zfiredtxenzgtrkiamuoubetexzbnwkdtxbtihhtsfubnmryxq", "sot", "szgkkhuhkcnr", "firxkgvkzqlnallzwjispsizoawobemuhqrhpyvknasjzwctamfuonder", "vyei", "fidqszoicndwifns", "xkickycwzj", "gmybflbjunudxrwguzditaxmyadgdjeengut", "gvlwwnmrddhzwewugdtobauecealfhasyftgxkiqeluysxxmroukfziifpryvddggttojhcszeyjetbsldmorqnbuqreuxfw", "vbhuerxkcjlkamgruturkbrubbscmvzqruwvlrlyylcvuiothkhpznjzsfnyfoaqkziyjqzdreeygmtbdljwnaojexfgmjlup", "bxjvop", "aswdmctosieicucsjwxych", "lxbtlhnrfyaznrqgxrltmxkjmhdqjjgnvstxaygmuxznjfiiukm", "npncdabkmbgfhnedcmbfyjiplzwymgvsfrifvvjayobsygwolqbwkblqutakcshnlsqadtfiqmpauslvqd", "kuyemuuymacyvmahtagmcewkspverpjtjscccnrczchgkjzppdxcalxxcxrwnepk", "jxwouobfvzttz", "yucpsdhqvzboeezcqpxsepuuk", "iwbdmxdcbypnzqmgkrjhfivkrmnv", "lpqklgcwdvgbghfyoyejnpexrelywqfdtczflwetbxvzigtvisi", "dlasodatffcreungntdmezgfqklrabyymsnhdberufcrgpxgsziklznhdprbczhbxgtuslufycjronozdqumzlnidkvaydg", "oejzlmrrbdysqlezifcvgjnmvodfvmrnjmnfkejdbnnyljzjaxfecrfefdgarqbtwoijuktacywmsubtxtgzkbnstgrsrjpkdfe", "mlegvhxielwlfadlnqvnipcuizpdxgtvro", "vwgmwkbturocovaykdsjaftbtgmtwknnmhexfgcfchpwwgcgecfobbencotjizxbtdrijwfjxdsxanwfjyjamrxrdaiusgr", "xjmkcsekcorldyrjiavrhuhqtndujymc", "rmxwcaorznumwxgwnibnxwzvnxjhzwqzgmkgifnnnnzpgtsvprycjtdeirtpqbduursabbidzkfbexgthkoacagkb", "tkrsxhztwgvqxamjtexklnooaloydjhejlbasavskttwxiabarogvmfdfjttaxhgqljlbfnrvrwwbxkurhufiknoxfmemcv", "cojlyayladyrhofzetaddteqrjbyxtvyszgdorexqmgznqmuvemegbwki", "zktqnurtpttlcjgkmnprqeyeepqunfqqvjwuevwbvnaupyofwiqwhpyumyfwpjrruhleromrmpvczlkxqiuq", "gyxl", "qmhwlymfsjixvvjhkczylqcsnbjxliasetxciggtfl", "gizysqkqbyhzeagzsscvdigtcfiupyhqovaaioxfrphugxzrcjvwqwc", "hpgkulrmbaixnjiapmjiwhwsgromfqpxqkkiscjwpiicslwcijginxfiwqakeezeohhskxgvgdkezmqys", "vibswdyqaxjvqsffwmcismooheyhwzqvyzezluejztlgddshvwcryzcllaisxrygwqyyoiaivfvgtzicycyrkckv", "oyclwdejlifmocfjsbgmernsyitkfaahjxfnwnusrdypsziawlpsjgnavytdihpxcmugshnqbkyfts", "xwcbiporfbktpvqhznjuaxfcdykifuzwdsdhxirwwxinoffywgxscrtuwhvuwjejzqirsfijgguouapqpmfdnpsfm", "qffrjfkj", "rpynimubaxdgbrkdawgugnhobaowxdnzkiidworcsnejgqctftyksvbhiwkcnyffmsbxwptqn", "oeqdvldvfbklukstxwomapaauaozblhxudkdxihepqagndnlkvbqhluscvczhrsrhodnftoszhjdymuywdtjgzbmkrdf", "xviupppkeswkctwlqwyuhokbhqqjshmaeiouhununbhrkabacenkg", "habomjnlznqvckmowgelhnglfizogckplbymkdowfpicxydzgoskckraxpdysksvzezcpg", "zemawxwjeowraaqsqytsshtavnvoyiyollelxqabmjwhxtrdijiacbbjiiyzwkxboot", "jcnpsxnkbqdbh", "bftrbaurtzkftodotjguzjmwxrrmswxwclohotuanypmhtemmsaicwckowmcdmpnhcfzptekpgljfvwpqjgilxu", "bbaigjqxdmuchkkb", "effbptpwafzqbsebbjmvdcxdbmnlfqjklongafzkvaqmkehefedjxgxlbdhltihtgfqjjsdis", "lhuxgqpwcgpujfvvnlrxhgbiwxmxzhglyhkdkafafojtlkuxkmjwlxrd", "ussuyjqsxwsdhkjeipwycrkcxxjatoqhygzymgikqdnqiyyzlbcdlgtmneyickucbkpkza", "ykmsksjorovmdymlbgprvprnyxwppvwgmzfjsqouvgara", "wduqkbncayo", "qdfkyomjlhfozonwpdxllqdnvpohyijqmyymuclnydzmlqksntdfj", "ynzekkkljzrvnwclzcfgtvcmstxgadxpztofckypbgqgbnumnkeaqclaspzxjbygtkjznxeduhbksr", "aw", "jbremnahqoiqktpbkteefdkbrerrxmhqbbselpnfzapgmxidrhbixetaetjcroufa", "wtufuqgljvxzsurhviikdxyuythezjnvwrxqrykmotkhlphlyfljjsfugzwxxfqkc", "ytjuaagqnfxqwiopolnejmoxow", "oqoskpzkwxsffgeuuhdklidtmjobzayatyaqefgwgqbo", "qkhtpuhhkspffkpryvdjasbxhtfrmptpljszvtgvhfvqpxypxfdaphfqdmigzgfg", "goddugelwdvemxwureitzwqmbqeqtymrlrzahuxnpgufaagbpexwpoahvdnsyvgxw", "dfhxrctagxkuasofoejsalcerkbtsjwnbnoahsumzfyiiskhzzwyykeefszrzrbztbrzhxxgaajb", "tjsardsbhmhefysdqtbhzsxukbwdpioqaohloaancxdkkmofniojrvxj",
                "jzjozqfqiwyfadplibubtpcfxxfvjtbizxlmpaccjpihvnrtvfqtfiaztvfbednyemfmahbljdvykddawaujdksenm"};
        chars = "fcxpzkzkqeyeijquyfixvlzjpzomujrqzxeoynjiflnmdrpdkrm";

        handler.countCharacters(words, chars);
    }


    public int countCharacters(String[] words, String chars) {
        int[] arr = new int[26];
        for (char c : chars.toCharArray()) arr[c - 'a']++;
        int res = 0;
        int[] bak = Arrays.copyOf(arr, 26);
        for (String word : words) {
            boolean get = true;
            for (char c : word.toCharArray()) {
                if (--arr[c - 'a'] < 0) {
                    get = false;
                    break;
                }
            }
            if (get) res += word.length();
            arr = Arrays.copyOf(bak, 26);
        }
        return res;
    }
}
