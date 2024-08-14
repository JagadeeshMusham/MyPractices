package hacker_rank.balanced_brackets;

import java.util.ArrayList;
import java.util.List;

public class BalancedBrackets {

	public static boolean isBalanced(String expression) {
		boolean bBalanced = true;

		char expressionChars[] = new char[1000];
		int size = 0;

		for (int counter = 0; counter < expression.length(); counter++) {
			char c = expression.charAt(counter);
			switch (c) {
			case '[':
			case '{':
			case '(':
				expressionChars[size++] = c;
				continue;
			case ')':
				if (size == 0) {
					bBalanced = false;
					break;
				}
				
				if (expressionChars[size-1] == '(') {
					--size;
				}
				else {
					bBalanced = false;
					break;
				}
				continue;
			case '}':
				if (size == 0) {
					bBalanced = false;
					break;
				}
				
				if (expressionChars[size-1] == '{') {
					--size;
				}
				else {
					bBalanced = false;
					break;
				}
				continue;
			case ']':
				if (size == 0) {
					bBalanced = false;
					break;
				}
				
				if (expressionChars[size-1] == '[') {
					--size;
				}
				else {
					bBalanced = false;
					break;
				}
			}
		}
		
		if (bBalanced && size != 0) {
			bBalanced = false;
		}

		return bBalanced;
	}

	public static void main(String[] args) {
		List<String> expressionList = new ArrayList<>();

		expressionList.add("[()][{}()][](){}([{}(())([[{}]])][])[]([][])(){}{{}{[](){}}}()[]({})[{}{{}([{}][])}]");
		expressionList.add("[()][{}[{}[{}]]][]{}[]{}[]{{}({}(){({{}{}[([[]][[]])()]})({}{{}})})}");
		expressionList.add("([])[{{{][)[)])(]){(}))[{(})][[{)(}){[(]})[[{}(])}({)(}[[()}{}}]{}{}}()}{({}](]{{[}}(([{]");
		expressionList.add("){[]()})}}]{}[}}})}{]{](]](()][{))])(}]}))(}[}{{)}{[[}[]");
		expressionList.add("}(]}){");
		expressionList.add("((]()(]([({]}({[)){}}[}({[{])(]{()[]}}{)}}]]{({)[}{(");
		expressionList.add("{}{({{}})}[][{{}}]{}{}(){{}[]}{}([[][{}]]())");
		expressionList.add("(){}[()[][]]{}(())()[[([])][()]{}{}(({}[]()))()[()[{()}]][]]");
		expressionList.add("()([]({}[]){}){}{()}[]{}[]()(()([[]]()))()()()[]()(){{}}()({[{}][]}[[{{}({({({})})})}]])");
		expressionList.add("[]([{][][)(])}()([}[}(})}])}))]](}{}})[]({{}}))[])(}}[[{]{}]()[(][])}({]{}[[))[[}[}{(]})()){{(]]){][");
		expressionList.add("{()({}[[{}]]()(){[{{}{[[{}]{}((({[]}{}()[])))]((()()))}(()[[[]]])((()[[](({([])()}))[]]))}]})}");
		expressionList.add("()(){{}}[()()]{}{}");
		expressionList.add("{}()([[]])({}){({[][[][[()]]{{}[[]()]}]})}[](())((())[{{}}])");
		expressionList.add("{}(((){}){[]{{()()}}()})[]{{()}{(){()(){}}}}{()}({()(()({}{}()((()((([])){[][{()}{}]})))))})");
		expressionList.add("][[{)())))}[)}}}}[{){}()]([][]){{{{{[)}]]{([{)()][({}[){]({{");
		expressionList.add("{{}(");
		expressionList.add("{[{((({}{({({()})()})[]({()[[][][]]}){}}))){}}]}{}{({((){{}[][]{}[][]{}}[{}])(())}[][])}");
		expressionList.add("()[[][()[]][]()](([[[(){()[[]](([]))}]]]))");
		expressionList.add("()[]({}{})(()){{{}}()()}({[]()}())[](){}(({()}[{}[{({{}}){({}){({})((({()})))}}}]]))");
		expressionList.add("}[{){({}({)})]([}{[}}{[(([])[(}){[]])([]]}(]]]]{][");
		expressionList.add("[{]{[{(){[}{}(([(]}])(){[[}(]){(})))}}{{)}}{}][({(}))]}({)");
		expressionList.add(")})[(]{][[())]{[]{{}}[)[)}[]){}](}({](}}}[}{({()]]");
		expressionList.add("[[[({[]}({[][[[[][[{(()[][])}()[][]][]{}]]]]}))][(()){}]]]()[{}([]{}){}{{}}]");
		expressionList.add("({[]({[]})}())[][{}[{{(({{{([{}])}}}))}}]]");
		expressionList.add("([((()))()])[][][]{}()(([]))[]()[]((){}[]){}(){{}[]}[[{[]}]]");
		expressionList.add("[[(((({}{[]{}()}){}{{}}){({[]{[{}]{(){}(((){()}))}()}}[[]]()()[()])[[{}{}]()]}))]]{}[]{}({({{}})})");
		expressionList.add("");


		for (String expression : expressionList) {
			System.out.println((isBalanced(expression)) ? "YES" : "NO");
		}
	}
}
