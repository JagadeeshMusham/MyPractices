package hacker_rank.balanced_brackets;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
				continue;
			}
		}
		
		if (bBalanced && size != 0) {
			bBalanced = false;
		}

		return bBalanced;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
//		int t = in.nextInt();
		int t = 3;
		List<String> expressionList = new ArrayList<>();
		for (int a0 = 0; a0 < t; a0++) {
//			String expression = in.next();
//			expressionList.add(expression);
		}
		
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
		expressionList.add("");
		expressionList.add("");
		expressionList.add("");
		expressionList.add("");
		expressionList.add("");
		expressionList.add("");
		expressionList.add("");
		expressionList.add("");
		expressionList.add("");
		expressionList.add("");
		expressionList.add("");
		expressionList.add("");
		expressionList.add("");
		expressionList.add("");
		expressionList.add("");
		expressionList.add("");
		expressionList.add("");
		expressionList.add("");
		expressionList.add("");
		expressionList.add("");
		expressionList.add("");
		expressionList.add("");


		for (String expression : expressionList) {
			System.out.println((isBalanced(expression)) ? "YES" : "NO");
		}
	}
}
