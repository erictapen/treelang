package treelang.picture.alias;

import java.io.File;
import java.util.ArrayList;
import java.util.Stack;

import treelang.TStorage;
import treelang.mutate.MExpression;
import treelang.parser.SyntaxErrorException;
import treelang.parser.TreeLangParser;
import treelang.picture.TIdentifier;
import treelang.picture.TLambda;
import treelang.picture.TList;
import treelang.picture.TPicture;

/**
 * TNode for high level functions, which can be implemented in treelang core
 * functionalities. This way, new functions can be implemented faster.
 * 
 * An Alias has no arguments, but can contain TIdentifiers. These are saved at
 * compile time and are replaced if an lambda on higher level provides some
 * value for the identifiers.
 * 
 * @author justin
 *
 */
public abstract class AbstractAlias implements TPicture {

	final int ARGUMENT_COUNT = 0;

	@Override
	public Integer[] getArgs() {
		return new Integer[0];
	}

	@Override
	public Integer replaceAll(Integer origin, Integer target) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer replace(Integer origin, Integer target, Stack<Byte> dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Stack<Byte>> findMatches(MExpression expression) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Generate alias node. Looks for an accompanying file in alias/ which must have the form
	 * @formatter:off
	 * List
	 * -openVariables...
	 * -Expression without Lambda expressions at the beginning
	 * --bla
	 * ---bl
	 * ---bl
	 * @formatter:off
	 * 
	 * @param caption
	 * @param picChilds
	 * @return
	 */
	public static TPicture generateAliasExpression(String caption, ArrayList<TPicture> picChilds) {
		try {
			TreeLangParser p = new TreeLangParser();
			TList aliasWithIdents = (TList) TStorage.gI().get(p.parse(new File("alias/" + caption + ".tree")));
			Integer[] aliasChilds = aliasWithIdents.getChildren();
			// identifiers have length - 1 because the last one is the actual
			// picture
			TPicture[] identifiers = new TPicture[aliasChilds.length - 1];
			for (int i = 0; i < identifiers.length; i++) {
				TPicture lambdaNode = TStorage.gI().get(aliasChilds[i]);
				identifiers[i] = ((TIdentifier) lambdaNode).getIdent(); //TODO ??? aus irgendeinem grund dachte ich, da we lambda, aber es ist eigentlich ein ident
			}
			TPicture result = TStorage.gI().get(aliasChilds[aliasChilds.length - 1]);
			for (int i = identifiers.length - 1; i >= 0; i--) {
				result = new TLambda(identifiers[i], picChilds.get(i), result);
				TStorage.gI().put(result.hashCode(), result);
			}
			return result;
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
		}
		return null;
	}

}
