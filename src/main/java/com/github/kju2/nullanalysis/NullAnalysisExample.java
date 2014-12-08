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

	/**
	 * Limits of null analysis.
	 * 
	 * @see <a href="http://help.eclipse.org/luna/topic/org.eclipse.jdt.doc.user/tasks/task-using_null_annotations.htm">Copied from Eclipse documentation</a>
	 */
	public String flatten(String[] inputs1, String[] inputs2) {
		StringBuffer sb1 = null, sb2 = null;
		int len = Math.min(inputs1.length, inputs2.length);
		for (int i = 0; i < len; i++) {
			if (sb1 == null) {
				sb1 = new StringBuffer();
				sb2 = new StringBuffer();
			}
			sb1.append(inputs1[i]);
			// WARNING: Potential null pointer access: The variable sb2 may be null at this location.
			sb2.append(inputs2[i]);
		}
		if (sb1 != null)
			return sb1.append(sb2).toString();
		return "";
	}
}
