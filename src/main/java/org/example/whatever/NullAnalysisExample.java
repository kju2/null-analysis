package org.example.whatever;

public class NullAnalysisExample {

	public void nullPointerAccess(Object o) {
		if (o != null) {
			return;
		}
		
		// ERROR: Null pointer access: The variable customer can only be null at this location.
		o.toString();
	}
	
	public void potentialNullPointerAccess(Object o, int i) {
		if (i > 0) {
			o = null;
		}
		
		// WARNING: Potential null pointer access: The variable o1 may be null at this location.
		o.toString();
	}
	
	public void redundantNullCheck(Object o) {
		if (o == null) {
			return;
		}
		
		// ERROR: Redundant null check: The variable article cannot be null at this location.
		if (o != null) {
			// Show the article.
		}
	}
}
