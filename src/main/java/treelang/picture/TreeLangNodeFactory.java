package treelang.picture;

import treelang.TStorage;

public class TreeLangNodeFactory {

	private static TreeLangNodeFactory instance = null;

	private TreeLangNodeFactory() {

	}

	public static TreeLangNodeFactory gI() {
		if (instance == null)
			instance = new TreeLangNodeFactory();
		return instance;
	}

	public Integer getTNodeWithArguments(Class<? extends TPicture> type, TPicture[] args) {
		Integer result = null;
		if(type.equals(TDivide.class))
			result = (new TDivide(args[0], args[1])).hashCode();
		else if(type.equals(TList.class))
			result = (new TList(args)).hashCode();
		else if(type.equals(TMultiply.class))
			result = (new TMultiply(args[0], args[1])).hashCode();
		else if(type.equals(TTranslate.class))
			result = (new TTranslate(args[0], args[1], args[2])).hashCode();
		else if(type.equals(TForLoop.class))
			result = (new TForLoop(args[0], args[1], args[2])).hashCode();
		else if(type.equals(TLambda.class))
			result = (new TLambda(args[0], args[1], args[2])).hashCode();
		else if(type.equals(TMinus.class))
			result = (new TMinus(args[0], args[1])).hashCode();
		else if(type.equals(TPlus.class))
			result = (new TPlus(args[0], args[1])).hashCode();
		return result;
	}
}
