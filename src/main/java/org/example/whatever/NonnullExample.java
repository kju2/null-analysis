package org.example.whatever;

import java.util.Collections;
import java.util.Random;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class NonnullExample {

	@Nullable
	public static Object methodWithNonnullParameter(@Nonnull Object o) {
		Random r = new Random();
		
		if (r.nextBoolean()) {
			return o;
		}
		return null;
	}
	
	public void callMethodWithNonnullParameter() {
		// Null type mismatch: required '@Nonnull Object' but the provided value is null.
		methodWithNonnullParameter(null);
		
		Object o1 = new Object();
		Object o2 = methodWithNonnullParameter(o1);
		
		// Null type mismatch: required '@Nonnull Object' but the provided value is specified as @Nullable.
		methodWithNonnullParameter(o2);
		
		o1 = null;
		// Null type mismatch: required '@Nonnull Object' but the provided value is null.
		methodWithNonnullParameter(o1);
		
	}
	
	public void redundantNullCheck(@Nonnull Object o) {
		// Redundant null check: The variable o is specified as @Nonnull.
		if (o != null) {
			return;
		}
		o.toString();
	}
	
	/**
	 * Methods from the JDK and most external libraries don't have nonnull-annotations.
	 * <br>
	 * Since it isn't possible to add the annotation in this places there isn't enough information 
	 * available to prove it will never ever be null.
	 */
	public void limitationsOfNonnull1() {
		// Null type safety: The expression of type 'List' needs unchecked conversion to conform to '@Nonnull Object'.
		methodWithNonnullParameter(Collections.emptyList());
	}

	@Nullable
	public Object someMethod(@Nonnull Object o, int i) {
		if (i > 0) {
			return o;
		}
		return null;
	}
	
	/**
	 * Analysis isn't powerful enough.
	 */
	public void limitationsOfNonnull2() {
		Object o = someMethod(new Object(), 1);
		// Null type mismatch: required '@Nonnull Object' but the provided value is inferred as @Nullable.
		methodWithNonnullParameter(o);
	}

	// Solution
	@Nonnull
	public static <T> T checkNotNull(T reference) {
		if (reference == null) {
			throw new NullPointerException();
		}
		return reference;
	}
	
	public void solutionForLimitationsOfNonnull1() {
		methodWithNonnullParameter(checkNotNull(Collections.emptyList()));
	}
	
	public void solutionForLimitationsOfNonnull2() {
		Object o = checkNotNull(someMethod(new Object(), 1));
		methodWithNonnullParameter(o);
	}
}
