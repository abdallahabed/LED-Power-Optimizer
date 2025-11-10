package algorithmFirstProj;

/*
 * LCS Algorithm - Same as fixed version
 * Computes optimal LED/Power matching using dynamic programming
 */
public class LCS {

	private int power;
	private int[] powers;
	private int[] leds;
	private char[][] b;
	private int[][] c;
	private String lcs = "";
	private String[] lcss;

	public LCS(int[] powers, int[] leds) {
		setPowers(powers);
		setLeds(leds);
		setPower(powers.length);

		longestCommonSubArrayLength(powers, leds);
		print(b, leds, powers.length, leds.length);
		setLcss(lcs.trim().split(" "));
	}

	private int longestCommonSubArrayLength(int[] powers, int[] leds) {
		int n = powers.length;
		int m = leds.length;

		c = new int[n + 1][m + 1];
		b = new char[n + 1][m + 1];

		for (int i = 0; i <= n; i++)
			c[i][0] = 0;
		for (int j = 0; j <= m; j++)
			c[0][j] = 0;

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (powers[i - 1] == leds[j - 1]) {
					c[i][j] = c[i - 1][j - 1] + 1;
					b[i][j] = '\\';
				} else if (c[i - 1][j] >= c[i][j - 1]) {
					c[i][j] = c[i - 1][j];
					b[i][j] = '^';
				} else {
					c[i][j] = c[i][j - 1];
					b[i][j] = '<';
				}
			}
		}

		return c[n][m];
	}

	private void print(char[][] b, int[] leds, int i, int j) {
		if (i == 0 || j == 0)
			return;

		if (b[i][j] == '\\') {
			print(b, leds, i - 1, j - 1);
			lcs += leds[j - 1] + " ";
		} else if (b[i][j] == '^') {
			print(b, leds, i - 1, j);
		} else {
			print(b, leds, i, j - 1);
		}
	}

	// Getters & Setters
	public String[] getLcss() { return lcss; }
	public void setLcss(String[] lcss) { this.lcss = lcss; }
	public int getPower() { return power; }
	public void setPower(int power) { this.power = power; }
	public int[] getPowers() { return powers; }
	public void setPowers(int[] powers) { this.powers = powers; }
	public int[] getLeds() { return leds; }
	public void setLeds(int[] leds) { this.leds = leds; }
	public char[][] getB() { return b; }
	public void setB(char[][] b) { this.b = b; }
	public int[][] getC() { return c; }
	public void setC(int[][] c) { this.c = c; }
	public String getLcs() { return lcs; }
	public void setLcs(String lcs) { this.lcs = lcs; }
}
