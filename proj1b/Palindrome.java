public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> d = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

    public boolean isPalindrome(String word) {
        int len = word.length();
        if (len < 2) return true;
        for (int i = 0; i < len / 2; i++) {
            if (word.charAt(i) != word.charAt(len - 1 - i)) {
                return false;
            }
        }
        return true;
    }

//    public boolean isPalindrome(String word) {
//        if (word.length() < 2) return true;
//        Deque d = this.wordToDeque(word);
//        while (!d.isEmpty()) {
//            if (d.removeFirst() != d.removeLast()) {
//                return false;
//            }
//        }
//        return true;
//    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        int len = word.length();
        if (len < 2) return true;
        for (int i = 0; i < len / 2; i++) {
            if (!cc.equalChars(word.charAt(i), word.charAt(len - 1 - i))) {
                return false;
            }
        }
        return true;
    }
}
