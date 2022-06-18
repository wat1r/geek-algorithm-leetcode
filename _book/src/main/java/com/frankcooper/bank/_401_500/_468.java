package com.frankcooper.platform.leetcode.bank._401_500;

public class _468 {


    public static void main(String[] args) {
        _1st handler = new _1st();
//        handler.validIPAddress("172.16.254.1");
//        handler.validIPAddress("1e1.4.5.6");
//        handler.validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334");
//        handler.validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334:");
//        handler.validIPAddress("20EE:FGb8:85a3:0:0:8A2E:0370:7334");
        handler.validIPAddress("1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111.1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111.1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111.1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");

    }


    static class _1st {
        public String validIPAddress(String IP) {
            String[] arr = IP.split("\\.");
            if (arr.length <= 1) {
                if (IP.endsWith(":")) return "Neither";
                arr = IP.split(":");
                boolean ipv6 = isIPV6(arr);
                if (ipv6) return "IPv6";
            } else {
                if (IP.endsWith(".")) return "Neither";
                boolean ipv4 = isIPV4(arr);
                if (ipv4) return "IPv4";
            }
            return "Neither";
        }


        private boolean isIPV4(String[] arr) {
            if (arr.length != 4) return false;
            for (String a : arr) {
                if (a.length() == 0 || a.length() > 3) return false;
                if (a.startsWith("0") && a.length() > 1) return false;
                if (!isDigit(a)) return false;
                if (Integer.parseInt(a) >= 256 || Integer.parseInt(a) < 0) return false;
            }
            return true;
        }


        private boolean isIPV6(String[] arr) {
            if (arr.length != 8) return false;
            for (String a : arr) {
                if (a.length() == 0 || a.length() > 4) return false;
                if (!checkIPV6(a)) return false;
            }
            return true;
        }


        private boolean isDigit(String a) {
            char[] chas = a.toCharArray();
            for (char c : chas) {
                int ic = (int) c - (int) '0';
                if (!(ic >= 0 && ic <= 9)) return false;
            }
            return true;
        }


        private boolean checkIPV6(String a) {
            for (int i = 0; i < a.length(); ++i) {
                char c = a.charAt(i);
                if (!Character.isDigit(c) && !('a' <= c && c <= 'f') && !('A' <= c && c <= 'F')) {
                    return false;
                }
            }
            return true;
        }
    }

    static class _2nd {

        public static void main(String[] args) {
            _2nd handler = new _2nd();
            String queryIP = "1.1.1.1.";
            queryIP = "12..33.4";
            queryIP = "192.0.0.1";
            handler.validIPAddress(queryIP);

        }

        //case -> "2001:0db8:85a3:0:0:8A2E:0370:7334:"
        //case -> "1.1.1.1."
        //case -> "12..33.4"
        //case -> ""
        //case -> "192.0.0.1"
        public String validIPAddress(String queryIP) {
            String[] arr = queryIP.split("\\.");
            if (arr.length > 1) {
                if (queryIP.endsWith(".")) return "Neither";
                if (isIPv4(queryIP)) {
                    return "IPv4";
                }
            } else {
                if (queryIP.endsWith(":")) return "Neither";
                if (isIPv6(queryIP)) {
                    return "IPv6";
                }
            }
            return "Neither";
        }


        private boolean isIPv6(String ip) {
            String[] arr = ip.split(":");
            if (arr.length != 8) return false;
            for (String a : arr) {
                if (a.length() == 0 || a.length() > 4) return false;
                if (!checkIPv6Segment(a)) return false;
            }
            return true;
        }


        private boolean checkIPv6Segment(String s) {
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (!Character.isDigit(c) && !(c >= 'a' && c <= 'f') && !(c >= 'A' && c <= 'F')) {
                    return false;
                }
            }
            return true;
        }


        private boolean isIPv4(String ip) {
            String[] arr = ip.split("\\.");
            if (arr.length != 4) return false;
            for (String a : arr) {
                if (a.length() == 0 || a.length() > 3) return false;
                if (a.startsWith("0") && a.length() > 1) return false;
                if (!isDigit(a)) return false;
                if (Integer.parseInt(a) >= 256 || Integer.parseInt(a) < 0) return false;
            }
            return true;
        }

        private boolean isDigit(String s) {
            for (int i = 0; i < s.length(); i++) {
                if (!((s.charAt(i) - '0' >= 0 && s.charAt(i) - '0' <= 9))) {
                    return false;
                }
            }
            return true;
        }
    }

}
