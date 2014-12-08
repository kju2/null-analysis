package org.example.whatever;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class ParametersAreNonnullByDefaultExample {

	@ParametersAreNonnullByDefault
	public void doNothingWithParameters(Object o, String s, Integer i) {
		// Do nothing!
	}
	
	public void nullPointerAccess(Object o) {
		// ERROR: Redundant null check: The variable o is specified as @Nonnull.
		if (o != null) {
			return;
		}
		
		// WARNING: Dead code.
		o.toString();
	}
	
	public void potentialNullPointerAccess(Object o, int i) {
		if (i > 0) {
			// ERROR: Null type mismatch: required '@Nonnull Object' but the provided value is null.
			o = null;
		}
		
		o.toString();
	}
	
	public void redundantNullCheck(Object o) {
		// ERROR: Null comparison always yields false: The variable o is specified as @Nonnull.
		if (o == null) {
			return;
		}
		
		// ERROR: Redundant null check: The variable o is specified as @Nonnull.
		if (o != null) {
			// Do something!
		}
	}
	
	public void defaultCanBeOverriden(@Nullable Object o) {
		if (o != null) {
			return;
		}
	}
}
