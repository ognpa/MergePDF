package com.priya.utility;

import java.io.File;
import java.io.FilenameFilter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.util.PDFMergerUtility;

public class TestPDF {

	public static void main(String args[]) {
		new TestPDF().doIt();
	}

	private void doIt() {
		String folder = "/Users/priya/Desktop/test";

		GenericExtFilter filter = new GenericExtFilter(ext);

		File[] files = getFiles(folder, filter);
/*		try {
			new TestPDF().decrpyt(folder, files);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Decrypted");
		
*/
		new TestPDF().combine(folder, files);

	}

	public void decrpyt(String folder, File[] files) throws Exception {
		for (File file : files) {
			PDDocument doc=null;
			try{
			doc = PDDocument.load(file);
			if (doc.isEncrypted()) {
				try {
					doc.decrypt("");
					doc.setAllSecurityToBeRemoved(true);
				} catch (Exception e) {
					throw new Exception(
							"The document is encrypted, and we can't decrypt it.",
							e);
				}
				doc.save(file);
			}
			} finally {
				doc.close();
			}
		}
	}

	public void combine(String dir, File[] filesInFolder) {

		try {
			PDFMergerUtility mergePdf = new PDFMergerUtility();
			for (File string : filesInFolder) {
				mergePdf.addSource(string);
			}
			mergePdf.setDestinationFileName("Combined.pdf");
			
			mergePdf.mergeDocuments();
		} catch (Exception e) {
			System.out.print(e);
			e.printStackTrace();
		
		}
	}

	public class GenericExtFilter implements FilenameFilter {

		private String ext;

		public GenericExtFilter(String ext) {
			this.ext = ext;
		}

		public boolean accept(File dir, String name) {
			return (name.endsWith(ext));
		}
	}

	private static final String ext = ".pdf";

	private File[] getFiles(String dir, GenericExtFilter filter) {
		// TODO Auto-generated method stub
		String folder = dir;

		File _folder = new File(folder);
		File[] filesInFolder;
		filesInFolder = _folder.listFiles(filter);
		return filesInFolder;
	}

	public void createNew() {
		PDDocument document = null;
		try {
			String filename = "test.pdf";
			document = new PDDocument();
			PDPage blankPage = new PDPage();
			document.addPage(blankPage);
			document.save(filename);
		} catch (Exception e) {

		}
	}

}