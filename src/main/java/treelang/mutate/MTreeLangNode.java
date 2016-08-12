package treelang.mutate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import treelang.TStorage;
import treelang.picture.TForLoop;
import treelang.picture.TLambda;
import treelang.picture.TList;
import treelang.picture.TMinus;
import treelang.picture.TMultiply;
import treelang.picture.TPicture;
import treelang.picture.TPlus;
import treelang.picture.TTranslate;
import treelang.picture.alias.AbstractAlias;

/**
 * Simple rule expression which simulats a single treelang node with possible
 * {@link MWildcard} under it.
 * 
 * @author justin
 *
 */
public class MTreeLangNode implements MExpression {

	private final Class<?> type;
	private final MExpression[] children;
	private final String name;

	public MTreeLangNode(Class<?> type, MExpression[] args) {
		this.type = type;
		this.children = args;
		this.name = null;
	}
	public MTreeLangNode(Class<?> type, MExpression[] args, String name) {
		this.type = type;
		this.children = args;
		this.name = name;
	}

	@Override
	public MExpression[] getChildren() {
		return children;
	}

	@Override
	public boolean matches(Integer tpic) {
		if (!TStorage.gI().get(tpic).getClass().equals(type)) //why?
			return false;
		Integer[] tpicChilds = TStorage.gI().get(tpic).getArgs();
		for (int i = 0; i < tpicChilds.length; i++) {
			if (!children[i].matches(tpicChilds[i]))
				return false;
		}
		return true;
	}

	@Override
	public Integer getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getTpicture(Integer tpic, HashMap<String, Integer> vars) {
		TPicture[] tpics = new TPicture[children.length];
		for (int i = 0; i < children.length; i++) {
			tpics[i] = TStorage.gI().get(children[i].getTpicture(tpic, vars));
		}
		// Alle treelang nodes without children
		// don't need to be handled here,
		// because they will be handled as
		// MTreeLangTree.
		if (TList.class.equals(type)) {
			return (new TList(new ArrayList<TPicture>(Arrays.asList(tpics)))).hashCode();
		} else if (TForLoop.class.equals(type)) {
			return (new TForLoop(tpics[0], tpics[1], tpics[2])).hashCode();
		} else if (TLambda.class.equals(type)) {
			return (new TLambda(tpics[0], tpics[1], tpics[2])).hashCode();
		} else if (TMinus.class.equals(type)) {
			return (new TMinus(tpics[0], tpics[1])).hashCode();
		} else if (TMultiply.class.equals(type)) {
			return (new TMultiply(tpics[0], tpics[1])).hashCode();
		} else if (TPlus.class.equals(type)) {
			return (new TPlus(tpics[0], tpics[1])).hashCode();
		} else if (TTranslate.class.equals(type)) {
			return (new TTranslate(tpics[0], tpics[1], tpics[2])).hashCode();
		} else if(AbstractAlias.class.equals(type)) {
			return (AbstractAlias.generateAliasExpression(this.name, new ArrayList<TPicture>(Arrays.asList(tpics)))).hashCode();
		}
		return null; //this shouldn't happen
	}

	@Override
	public String toString() {
		String res = type.toString();
		for(MExpression x : this.children) {
			res += "\n\t" + x.toString().replaceAll("\n", "\n\t");
		}
		return res;
	}

}
