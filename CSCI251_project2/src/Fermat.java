/**
 * Fermat.java
 * 
 * @author	Derek Brown <djb3718@rit.edu>
 *
 * Purpose	Calculate the number of witnesses a number has using Fermat's
 * 			Little Theorem,  Gain experience using the PJ2 library.
 */

import java.math.BigInteger;

public class Fermat {
	
	// Attributes
	private BigInteger p;
	private BigInteger numWitness;
	
	// Constructor
	public Fermat( int value ) {
		p = BigInteger.valueOf(value);
		numWitness = BigInteger.valueOf(0);
	}//end Fermat constructor
	
	// Methods
	
	public boolean isWitness( BigInteger p, BigInteger a ) {
		if( a.modPow(p, p).compareTo(a) != 0 ) {
			return true;
		}//end if
		return false;
	}//end isWitness

	public static void main( String[] args ) {
		if( args.length != 1 ) {
			System.err.println("Usage: java pj2 Fermat <p>");
			System.exit(1);
		}//end if
		
		int input;
		try {
			input = Integer.parseInt( args[0] );
		} catch( NumberFormatException e ) {
			System.err.println( args[0] + " not an integer. Abort." );
			e.printStackTrace( System.err );
			System.exit( 1 );
		}//end try/catch
		input = Integer.parseInt( args[0] );
		
		if( input < 3 ) {
			System.err.println("<p> must be greater than or equal to 3");
		}//end if
		
		Fermat F = new Fermat( input );

		for( int i = 2 ; i < F.p.intValue() ; i++ ) {
			BigInteger a = BigInteger.valueOf(i);
			if( F.isWitness( F.p, a ) ) {
				F.numWitness = F.numWitness.add(BigInteger.valueOf(1));
			}//end if
		}//end for
		System.out.println(F.numWitness);
	}//end main
}//end Fermat
