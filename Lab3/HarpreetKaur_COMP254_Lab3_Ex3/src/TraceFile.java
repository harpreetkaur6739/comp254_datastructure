import java.io.File;

public class TraceFile {

	public static void main(String[] args) {
		String fileName = "test.txt";
		File rootPath = new File("E:\\Professional\\Projects\\COMP254_DataStructure");
		String path = find(rootPath, fileName);
		if(!path.equals("")) {
			System.out.println("File '" + fileName + "\' found at following paths: \n" + path);
		}else {
			System.out.println("File '" + fileName + "\'not found!");
		}
		
	}
	
	public static String find(File path, String fileName) {
		String filePath = "";
		File[] list = path.listFiles();
		if(list!= null) {
			for(File file : list) {
				if(file.isDirectory()) {
					filePath += find(file, fileName);
				}else if(file.getName().equalsIgnoreCase(fileName)) {
					filePath += file.getParentFile() + "\\" + fileName + "\n";
				}
			}
		}
		return filePath;
	}

}
