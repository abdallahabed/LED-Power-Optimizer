package algorithmFirstProj;

/*
 * In this class, the optimal solution for maximum number of leds powered by power source
 * is given using a dynamic programming approach (LCS-style).
 *
 * NOTE: c is sized [powers.length + 1][leds.length + 1] so callers should use
 *       c[powers.length][leds.length] for the final length.
 */

public class LCS {

    // fields
    private int power; // Number of power sources
    private int[] powers; // Array of the batteries (1..n)
    private int[] leds; // Array of the LEDs as read from file
    private char[][] b; // DP arrows table sized [powers+1][leds+1]
    private int[][] c; // DP lengths table sized [powers+1][leds+1]
    private String lcs = ""; // The matched LED sequence as "1 2 4 5 "
    private String lcss[]; // tokens of lcs

    public LCS(int[] powersArr, int[] ledsArr) {
        setPowers(powersArr);
        setLeds(ledsArr);
        setPower(powersArr.length);
        computeLCS();
        // build tokens
        setLcss(getLcs().trim().isEmpty() ? new String[0] : getLcs().trim().split("\\s+"));
    }

    private void computeLCS() {
        // Convention: rows => powers (n), cols => leds (m)
        int n = (powers == null) ? 0 : powers.length;
        int m = (leds == null) ? 0 : leds.length;

        c = new int[n + 1][m + 1];
        b = new char[n + 1][m + 1];

        // initialize (Java arrays default to 0/'\0', but explicit is clearer)
        for (int i = 0; i <= n; i++) {
            c[i][0] = 0;
        }
        for (int j = 0; j <= m; j++) {
            c[0][j] = 0;
        }

        // Fill DP
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (powers[i - 1] == leds[j - 1]) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                    b[i][j] = '\\'; // diagonal -> match
                } else if (c[i][j - 1] > c[i - 1][j]) {
                    c[i][j] = c[i][j - 1];
                    b[i][j] = '<'; // left
                } else {
                    c[i][j] = c[i - 1][j];
                    b[i][j] = '^'; // up
                }
            }
        }

        // Build LCS sequence (we want the led numbers that match)
        StringBuilder sb = new StringBuilder();
        buildSequence(sb, n, m);
        lcs = sb.toString().trim() + (sb.length() > 0 ? " " : "");
    }

    // recursive backtracking to collect leds[j-1] when there is a diagonal match
    private void buildSequence(StringBuilder sb, int i, int j) {
        if (i == 0 || j == 0) return;
        if (b[i][j] == '\\') {
            buildSequence(sb, i - 1, j - 1);
            // when match occurred, leds[j-1] equals powers[i-1] (they matched)
            sb.append(leds[j - 1]).append(" ");
        } else if (b[i][j] == '<') {
            buildSequence(sb, i, j - 1);
        } else { // '^' or default
            buildSequence(sb, i - 1, j);
        }
    }

    /*
     * Getters & setters
     */
    public String[] getLcss() {
        return lcss;
    }

    public void setLcss(String lcss[]) {
        this.lcss = lcss;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int[] getPowers() {
        return powers;
    }

    public void setPowers(int[] powers) {
        this.powers = powers;
    }

    public int[] getLeds() {
        return leds;
    }

    public void setLeds(int[] leds) {
        this.leds = leds;
    }

    public char[][] getB() {
        return b;
    }

    public void setB(char[][] b) {
        this.b = b;
    }

    public int[][] getC() {
        return c;
    }

    public void setC(int[][] c) {
        this.c = c;
    }

    public String getLcs() {
        return lcs;
    }

    public void setLcs(String lcs) {
        this.lcs = lcs;
    }
}
