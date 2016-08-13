package treelang.picture.alias;

import java.io.File;
import java.util.ArrayList;
import treelang.TStorage;
import treelang.parser.SyntaxErrorException;
import treelang.parser.TreeLangParser;
import treelang.picture.TLambda;
import treelang.picture.TList;
import treelang.picture.TPicture;

/**
 * TNode for high level functions, which can be implemented in treelang core
 * functionalities. This way, new functions can be implemented faster.
 * 
 * 
 * @author justin
 *
 */
public abstract class AbstractAlias implements TPicture {

	final int ARGUMENT_COUNT = 0;

	final Integer hash;

	public AbstractAlias(Integer hash) {
		TStorage.gI().registerAliasHash(hash);
		this.hash = hash;
	}

	@Override
	public int hashCode() {
		return hash;
	}

	@Override
	public Integer replaceAll(Integer origin, Integer target) {
		return this.hash;
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
	 * The whole expression will be transformed into an instance of an alias class, which has the hash of the expression with lambdas wrapped around.
	 * 
	 * @param caption
	 * @param picChilds
	 * @return
	 */
	public static TPicture generateAliasExpression(String caption, ArrayList<TPicture> picChilds) {
		try {
			TreeLangParser p = new TreeLangParser();
			TList aliasFromFile = (TList) p.parse(new File("alias/" + caption + ".tree")).getTPic();
			//need to find the hash
			Integer[] children = aliasFromFile.getChildren();
			Integer resultHash = children[children.length - 1];
			for (int i = children.length - 2; i >= 0; i--) {
				TPicture lambda = new TLambda(TStorage.gI().get(children[i]), picChilds.get(i), TStorage.gI().get(resultHash));
				TStorage.gI().putNode(lambda.hashCode(), lambda);
				resultHash = lambda.hashCode();
			}
			TPicture result = null;
			switch (caption) {
			case "Rectangle":
				result = new AliasRectangle(picChilds.get(0), picChilds.get(1), resultHash);
				break;
			case "Line":
				result = new AliasLine(picChilds.get(0), picChilds.get(1), resultHash);
				break;
			default:
				System.out.println("Alias " + caption + " is not registered!");
				break;
			}
			TStorage.gI().forcePutNode(resultHash, result);
			return result;
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Class<?> getAliasType(String caption) {
		switch (caption) {
		case "Rectangle":
			return AliasRectangle.class;
		case "Line":
			return AliasLine.class;
		default:
			return null;
		}
	}

}
