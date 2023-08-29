package com.am.standalone;

public class TryCatch {
	public static void main(String[] args) throws Exception {

		try {
			System.out.println('A');
			try {
				System.out.println("Main Try");
				// throw new Exception("threw exception in B");
				try {
					System.out.println("Inside Child try");
					String s = null;
					int c = s.indexOf("c");
				} catch (Exception e) {
					throw new Exception("threw exception in Child Try");
				} finally {
					System.out.println("Finally Child");
				}
			} finally {
				System.out.println("Finally of Main Try");
			}

			System.out.println("After Finally of Main try");
			// any code here in the first try block
			// is unreachable if an exception occurs in the second try block

		} catch (Exception e) {
			System.out.println('Y');
		} finally {
			System.out.println('Z');
		}
		System.out.println("After All the Blocks");

	}

}
