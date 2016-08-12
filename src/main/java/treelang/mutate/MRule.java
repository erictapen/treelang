package treelang.mutate;

import java.util.HashMap;
import java.util.Stack;

import treelang.TStorage;
import treelang.picture.TPicture;
import treelang.picture.TreeLangNodeFactory;

/**
 * Template for every rule; one origin expression and one target.
 * 
 * @author justin
 *
 */
public class MRule {

	private final MExpression origin;
	private final MExpression target;

	public MRule(MExpression origin, MExpression target) {
		super();
		this.origin = origin;
		this.target = target;
	}

	public boolean matches(Integer tpic) {
		return origin.matches(tpic);
	}

	/**
	 * recursively apply the rule on tpic
	 * 
	 * @param tpic
	 * @return
	 */
	public Integer apply(Integer tpic) {
		if (MTreeLangTree.class.equals(origin.getClass()) && MTreeLangTree.class.equals(target.getClass())) {
			return TStorage.gI().get(tpic).replaceAll(origin.getValue(), target.getValue());
		}
		// apply for all childs of tpic
		Integer result = tpic;
		TPicture[] oldTNodeArgs = TStorage.gI().getAll(TStorage.gI().get(tpic).getArgs());
		TPicture[] newTNodeArgs = new TPicture[oldTNodeArgs.length];
		boolean newConstructionNeeded = false;
		for (int i = 0; i < oldTNodeArgs.length; i++) {
			newTNodeArgs[i] = TStorage.gI().get(this.apply(oldTNodeArgs[i].hashCode()));
			newConstructionNeeded |= !(newTNodeArgs[i].hashCode() == oldTNodeArgs[i].hashCode());
		}
		if (newConstructionNeeded) {
			result = TreeLangNodeFactory.gI().getTNodeWithArguments(TStorage.gI().get(tpic).getClass(), newTNodeArgs);
		}

		// if it matches on tpic, then complex things can happen
		if (this.matches(result)) {
			HashMap<String, Integer> vars = new HashMap<String, Integer>();
			Stack<TPicture> picTodo = new Stack<TPicture>();
			picTodo.push(TStorage.gI().get(result));
			Stack<MExpression> mTodo = new Stack<MExpression>();
			mTodo.push(origin);
			while (!picTodo.isEmpty()) {
				if (mTodo.peek().getClass().equals(MWildcard.class)) {
					TPicture topPic = picTodo.pop();
					MWildcard topM = (MWildcard) mTodo.pop();
					vars.put(topM.getName(), topPic.hashCode());
					MExpression[] topMchildren = topM.getChildren();
					Integer[] topPicArgs = topPic.getArgs();
					/*
					 * if there aren't any topMchildren, no tpics should be
					 * added, because MEexpression structure summerizes multiple
					 * treelang nodes with one MTreeLangTree.
					 */
					for (int i = 0; i < topMchildren.length; i++) {
						mTodo.push(topMchildren[i]);
						picTodo.push(TStorage.gI().get(topPicArgs[i]));
					}
				} else if (mTodo.peek().getClass().equals(MTreeLangNode.class)) {
					TPicture topPic = picTodo.pop();
					MExpression topM = mTodo.pop();
					MExpression[] topMchildren = topM.getChildren();
					Integer[] topPicArgs = topPic.getArgs();
					/*
					 * if there aren't any topMchildren, no tpics should be
					 * added, because MEexpression structure summerizes multiple
					 * treelang nodes with one MTreeLangTree.
					 */
					for (int i = 0; i < topMchildren.length; i++) {
						mTodo.push(topMchildren[i]);
						picTodo.push(TStorage.gI().get(topPicArgs[i]));
					}
				} else if (mTodo.peek().getClass().equals(MTreeLangTree.class)) {
					picTodo.pop();
					mTodo.pop();
				}
			}
			result = this.target.getTpicture(result, vars);
		}

		return result;
	}

	public String toString() {
		return origin.toString() + "\n-->\n" + target.toString() + "\n";
	}

}
