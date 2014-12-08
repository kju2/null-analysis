package org.example.whatever;

import javax.annotation.Nullable;

public class NullableExample {

	/**
	 * WRONG!
	 */
	public void accessNullableParameterWithoutCheck(@Nullable Object o) {
		// Potential null pointer access: The variable o may be null at this location.
		o.toString();
	}
	
	/**
	 * CORRECT! Possible null value is checked before it is accessed.
	 */
	public void accessNullableParameterWithCheck(@Nullable Object o) {
		if (o == null) {
			// handle null
			return;
		}
		o.toString();
	}
}
