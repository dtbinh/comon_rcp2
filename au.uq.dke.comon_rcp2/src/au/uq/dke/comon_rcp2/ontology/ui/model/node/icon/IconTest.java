package au.uq.dke.comon_rcp2.ontology.ui.model.node.icon;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ImageIcon;

import org.apache.commons.io.FileUtils;

public class IconTest {

	public static void main(String[] args) {
		String outerDatabaseDirectory = "C:/comonviz/";
		String defaultIconFilePath = "/table.png";
		File outerDatabaseFile = new File(outerDatabaseDirectory
				+ defaultIconFilePath);

		InputStream innerSourceFileStream = new IconTest().getClass()
				.getResourceAsStream(defaultIconFilePath);
		try {
			FileUtils.copyInputStreamToFile(innerSourceFileStream,
					outerDatabaseFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ImageIcon icon = new ImageIcon(
				IconTest.class.getResource(defaultIconFilePath));
		return;

	}
}
