package aws.codility.q2;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Solution {

	private static String getFileType(String fileExtension) {
		String fileType = null;
		switch (fileExtension) {
		case "mp3":
		case "aac":
		case "flac":
			fileType = "music";
			break;
		case "jpg":
		case "bmp":
		case "gif":
			fileType = "image";
			break;
		case "mp4":
		case "avi":
		case "mkv":
			fileType = "movie";
			break;
		case "7z":
		case "txt":
		case "zip":
			fileType = "other";
			break;
		default:
			fileType = "unknown";
		}

		return fileType;
	}

	private static int solution() {
		String S = "my.song.mp3 11b\ngreatSong.flac 1000b\nnot3.txt 5b\nvideo.mp4 200b\ngame.exe 100b\nmov!e.mkv 10000b";

		Stack<Integer> stack = new Stack<>();

		String tags[] = S.split("\n");

		int depth = 0;

		for (String tag : tags) {
			if (tag.startsWith("<ol>") || tag.startsWith("<OL>") || tag.startsWith("<ul>") || tag.startsWith("<UL>")) {
				stack.push(1);

				if (stack.size() > depth) {
					depth = stack.size();
				}
			} else if (tag.startsWith("<\\ol>") || tag.startsWith("<\\OL>") || tag.startsWith("<\\ul>")
					|| tag.startsWith("<\\UL>")) {
				stack.pop();
			}

		}

		return depth;
	}

	public static void main(String[] args) {
		solution();

		System.out.println(" ");
	}

}
