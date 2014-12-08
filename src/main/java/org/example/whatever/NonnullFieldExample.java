package org.example.whatever;

import javax.annotation.Nullable;

/**
 * Explanation and code sample from "Flow analysis vs. syntactic analysis".
 * 
 * @see <a href="http://help.eclipse.org/luna/topic/org.eclipse.jdt.doc.user/tasks/task-using_null_annotations.htm#syntactic_analysis">Flow analysis vs. syntactic analysis</a>
 */
public class NonnullFieldExample {
	
	@Nullable
	Object f;
    
	void syntacticFieldAnalysis() {
        if (this.f != null) {
            this.f.toString();
        }
    }
	
	void limitsOfSyntacticFieldAnalysis() {
		if (this.f != null) {
			System.out.println("Syntactic field analysis can't handle code between the check");
			System.out.println("and the method call.");
			// ERROR: Potential null pointer access: The field f is specified as @Nullable.
			this.f.toString();
		}
		
		// Solution 1:
		// Rewrite code so that it looks like this:
		if (this.f != null) {
            this.f.toString();
        }
		
		// Solution 2:
		// Use a local variable
		@Nullable Object f1 = this.f;
		if (f1 != null) {
			System.out.println("Syntactic field analysis can't handle code between the check");
			System.out.println("and the method call.");
			f1.toString();
		}
	}
}
