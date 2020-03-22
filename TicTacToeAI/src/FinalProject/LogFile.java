package FinalProject;

import java.io.*;

public class LogFile {
	
	/**
	 * To write all the matches played into a file
	 * @param match is an array of match converted to String to store in the log file
	 * @throws IOException is exception if there's any IO exception 
	 */
	public void writeLog(String match) throws IOException {
		
		File file = new File(fileName);
		
		//If file exists then update the content
		if(file.exists()) {
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
				
				bw.newLine();
				bw.write(match);
				bw.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			
			file.createNewFile();
		}
	}
	
	/**
	 * To read all the history matches that is stored in the log file 
	 * @return string of all the matches that is stored in the log file, null, if file not exist
	 * @throws FileNotFoundException if the file is not existed
	 */
	public String readLog() throws FileNotFoundException {
		
		StringBuilder sB = new StringBuilder();
		String nameOfFile = fileName;
		String historyMatch; 
		
		File file = new File(nameOfFile);
		
		if(file.exists()) {
			try {
				BufferedReader bf = new BufferedReader(new FileReader(file));
				
				if(!bf.ready()) {
					bf.close();
					throw new IOException();
				}
				
				while((historyMatch = bf.readLine()) != null) {
					sB.append(historyMatch);
				}
				
				bf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			return null; 
		}
		
		return sB.toString();
	}
	
	private static final String fileName = "history.txt";
}