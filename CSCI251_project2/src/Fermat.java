/**
 * Fermat.java
 * 
 * @author	Derek Brown <djb3718@rit.edu>
 *
 * Purpose	Calculate the number of witnesses a number has using Fermat's
 * 		Little Theorem,  Gain experience using the PJ2 library.
 */

import java.math.BigInteger;
import edu.rit.pj2.*;

public class Fermat extends Task {

	IntVbl numWitness;
	
	public static boolean isWitness( BigInteger p, BigInteger a ) {
		if( a.modPow(p, p).compareTo(a) != 0 ) {
			return true;
		}//end if
		return false;
	}//end isWitness

	public void main( final String[] args ) {
		if( args.length != 1 ) {
			System.err.println("Usage: java pj2 Fermat <p>");
			System.exit(1);
		}//end if
		
		int input;
		try {
			input = Integer.parseInt( args[0] );
		} catch( NumberFormatException e ) {
			System.err.println( args[0] +
				" not an integer. Abort." );
			e.printStackTrace( System.err );
			System.exit( 1 );
		}//end try/catch
		input = Integer.parseInt( args[0] );
		
		if( input < 3 ) {
			System.err.println(
				"<p> must be greater than or equal to 3");
			System.exit(1);
		}//end if
		
		numWitness = new IntVbl.Sum(0);
		parallelFor( 2, input-1 ).exec( new Loop() {
			IntVbl thrNumWitness;
			public void start() {
				thrNumWitness = threadLocal( numWitness );
			}//end start
			public void run( int i ) {
				int val = Integer.parseInt( args[0] );
				BigInteger p = BigInteger.valueOf( val );
				BigInteger a = BigInteger.valueOf(i);
				if( isWitness( p, a ) ) {
					++thrNumWitness.item;
				}//end if
			}//end run
		});//end for
		System.out.println(numWitness.item);
	}//end main
}//end Fermat
