package com.github.kju2.nullanalysis;

public class NullAnalysisExample {

	public void nullPointerAccess(Object o) {
		if (o != null) {
			return;
		}
		
		// ERROR: Null pointer access: The variable o can only be null at this location.
		o.toString();
	}
	
	public void potentialNullPointerAccess(Object o, int i) {
		if (i > 0) {
			o = null;
		}
		
		// WARNING: Potential null pointer access: The variable o may be null at this location.
		o.toString();
	}
	
	public void redundantNullCheck(Object o) {
		if (o == null) {
			return;
		}
		
		// ERROR: Redundant null check: The variable o cannot be null at this location.
		if (o != null) {
			// Do something!
		}
	}
}
