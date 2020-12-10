public class Matcher {

	// bad smells: comments (extract method), speculative generality (remove method)

	public boolean match(int[] expected, int[] actual, int clipLimit, int delta) {

		tooLargeValues(actual, clipLimit);

		if (!lengthsSimilarity(expected, actual)) {
			return false;
		}
		return !entriesWithinExpectedDelta(expected, actual, delta);
	}
	private boolean lengthsSimilarity(int[] expected, int[] actual) {
		return actual.length == expected.length;
	}
	private boolean entriesWithinExpectedDelta(int[] expected, int[] actual, int delta) {
		for (int i = 0; i < actual.length; i++)
			if (Math.abs(expected[i] - actual[i]) > delta) {
				return true;
			}
		return false;
	}
	private void tooLargeValues(int[] actual, int clipLimit) {
		for (int i = 0; i < actual.length; i++)
			if (actual[i] > clipLimit)
				actual[i] = clipLimit;
	}
}