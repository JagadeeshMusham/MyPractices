package general;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class FileAndStreams {
	public static void main(String[] args) {
		String srcFilePath = "D:/11.txt";
		String dstFilePath = "D:/1.txt";

		String str = "This is first file in Java";

		// FileOutputStream fos = null;
		try {
			// fos = new FileOutputStream(dstFilePath);
			Writer wr = new FileWriter(srcFilePath);

			Reader rd = new FileReader(dstFilePath);

			int fRead = 0;
			do {
				byte[] bytes = str.getBytes();
				// fos.write(fRead);
				char [] charBuff = new char[200];
				fRead = rd.read(charBuff, fRead, 100);
				if (fRead == -1) {
					break;
				}
				wr.write("\n" +str + "\n");
				wr.write(charBuff);
				
				String aa = String.valueOf(charBuff);
				aa.split(",");
				
				
			} while (true);

			wr.close();
			rd.close();
			// if (fos != null) {
			// fos.close();
			// }

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
