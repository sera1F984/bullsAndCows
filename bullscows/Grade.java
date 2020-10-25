package bullscows;

public class Grade {
    private int cows;
    private int bulls;
    private String secretCode;
    private String inputCode;

    public Grade(String inputCode, String secretCode) {
        cows = 0;
        bulls = 0;
        this.inputCode = inputCode;
        this.secretCode = secretCode;
    }

    public void checkCode() {
        for (int i = 0; i < inputCode.length(); i++) {
            if (inputCode.charAt(i) == secretCode.charAt(i)) {
                this.bulls++;
            } else {
                for (int j = 0; j < inputCode.length(); j++) {
                    if (inputCode.charAt(i) == secretCode.charAt(j)) {
                        this.cows++;
                    }
                }
            }
        }
    }

    public boolean guessedTheCode() {
        return bulls == secretCode.length();
    }

    @Override
    public String toString() {
        if (bulls == 0 && cows == 0) {
            return "None.";
        } else if (bulls == 0 && cows > 0) {
            return "Grade: " + cows + " cow(s).";
        } else if (cows == 0 && bulls > 0) {
            return "Grade: " + bulls + " bull(s).";
        } else if (bulls == secretCode.length()) {
            return "Congratulations! You guessed the secret code.";
        }
        return "Grade: " + bulls + " bull(s) and " + cows+ " cow(s).";
    }
}
