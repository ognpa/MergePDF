package com.priya.utility;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.util.PDFMergerUtility;

public class PDFBulkMerger {
	public static void main(String[] args) {

		PDFBulkMerger merge = new PDFBulkMerger();
		if (args.length < 2) {//V V primitive check
			System.out.println("Provide folder and destination file");
		}
		String folder = args[0];
		String destinationFile = args[1];
		merge.doIt(folder, destinationFile);

	}

	private void doIt(String folder, String destinationFile) {
		File[] filesInFolder = getFiles(folder, new PDFExtFilter(ext));
		try {
			mergeFiles(filesInFolder, destinationFile);
		} catch (COSVisitorException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	


	class PDFExtFilter implements FilenameFilter {
		private String ext;

		public PDFExtFilter(String ext) {
			this.ext = ext;
		}

		public boolean accept(File dir, String name) {
			return (name.endsWith(ext));
		}
	}

	private static final String ext = ".pdf";

	private File[] getFiles(String folder, PDFExtFilter filter) {
		File dir = new File(folder);
		File[] filesInFolder;
		filesInFolder = dir.listFiles(filter);
		return filesInFolder;
	}

	public void mergeFiles(File[] filesInFolder, String destinationFile)
			throws COSVisitorException, IOException {
		PDFMergerUtility mergePdf = new PDFMergerUtility();
		for (File file : filesInFolder) {
			mergePdf.addSource(file);
		}
		mergePdf.setDestinationFileName(destinationFile);
		mergePdf.mergeDocuments();

	}

}
