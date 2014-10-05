import java.io.*;
import java.util.*;

public class dict {
	/**
	 * 
	 * @param aFile
	 *            the dictionary file
	 * @param bFile
	 *            the post that is going to be modified
	 */
	static public void getContents(File aFile, File bFile) {
		StringBuilder contents = new StringBuilder();

		try {
			// use buffering, reading one line at a time
			BufferedReader input = new BufferedReader(new FileReader(aFile));
			BufferedReader secondIn = new BufferedReader(new FileReader(bFile));
			File file = new File("output.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			try {
				String line = null;
				String line2 = null;

				/*
				 * read each line, split it into words, add it to hashmap
				 */
				while ((line = input.readLine()) != null) {

					HashMap map = new HashMap();
					String[] words = line.split(" ");
					for (String test : words) {
						map.put(test, "1");
					}

					while ((line2 = secondIn.readLine()) != null) {

						String[] words1 = line2.split(" ");
						for (String word : words1) {
							if (!map.containsKey(word)) {
								bw.write(word + " ");
							}

						}
					}

				}
			} finally {
				input.close();
				secondIn.close();
				bw.close();

			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * Change the contents of text file, removing any words that are in the
	 * dictionary
	 * 
	 * 
	 * @throws IllegalArgumentException
	 *             if param does not comply.
	 * @throws FileNotFoundException
	 *             if the file does not exist.
	 * @throws IOException
	 *             if problem encountered during write.
	 */
	public static void main(String... aArguments) throws IOException {

		File dict = new File("filename.txt"); // this is the dictionary
		File post = new File("test.txt"); // this is the post we want to modify

		getContents(dict, post);

		System.out.println("Complete! Check output.txt for the final product.");

	}
}
