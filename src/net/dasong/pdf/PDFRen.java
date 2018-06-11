package net.dasong.pdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;

public class PDFRen {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("Usage: java -jar pdfren.jar pdfDir");

			System.exit(1);
		}

		String pdfDirStr = args[0];
		String pdfDestDirStr = pdfDirStr + "/dest";
		File pdfDestDir = new File(pdfDestDirStr);

		if (!pdfDestDir.exists()) {
			pdfDestDir.mkdir();
		}

		PDFParser parser;
		PDDocument pdfdocument;
		PDDocumentInformation pddi;
		String fileName = null;
		String title = null;

		File pdfDir = new File(pdfDirStr);
		File[] files = pdfDir.listFiles(new PDFFilenameFilter());

		for (int i = 0; i < files.length; i++) {
			try {
				fileName = files[i].getName();

				parser = new PDFParser(new FileInputStream(files[i]));
				parser.parse();

				pdfdocument = parser.getPDDocument();
				pddi = pdfdocument.getDocumentInformation();

				if (pddi.getTitle() != null) {
					title = pddi.getTitle().replace("*", " ").replace("/", "&").replace("’", "'");
				}

				pdfdocument.close();

				System.out.println(fileName + ": " + title);

				FileUtils.copyFile(files[i], new File(pdfDestDirStr + "/" + title + ".pdf"));
			} catch (FileNotFoundException e) {
				System.err.println(fileName + ": " + e.getMessage());
			} catch (IOException e) {
				System.err.println(fileName + ": " + e.getMessage());
			}
		}

		System.out.println("Done.");
	}
}
