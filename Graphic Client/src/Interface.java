import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import java.util.ArrayList;
import javax.ws.rs.client.*;
import javax.ws.rs.core.*;
import java.lang.reflect.Type;
import org.glassfish.jersey.client.ClientConfig;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;


public class Interface {

	protected Shell shell;
	private Text text;
	public Label label;
	private Label label_1;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Interface window = new Interface();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage("C:\\Users\\valla\\Downloads\\dizionario-alfa.ico"));
		shell.setSize(450, 400);
		shell.setText("Translator");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(10, 44, 408, 31);
		
		Button btnTraduci = new Button(shell, SWT.NONE);
		
		TheClient Client= new TheClient();
		Gson gson = new Gson();
		Type wordListType = new TypeToken<ArrayList<Word>>(){}.getType();
		StringBuilder output = new StringBuilder("Searching the word XYZ in the database...\n");
		
		btnTraduci.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				StringBuilder title = new StringBuilder("Searching the word \""+ text.getText()+"\" in the database...\n");
				label_1.setText(title.toString());
				StringBuilder output = new StringBuilder("");
				String downloadedJson=Client.activeClient(text.getText());
				try {
					ArrayList<Word> wordList = gson.fromJson(downloadedJson, wordListType);
				
			        	
				if (wordList.size() > 1) {

				        	output.append("-------Search Results-------\n");
			        		for (int i = 0; i < wordList.size(); i++) {
			        			Word receivedWord = wordList.get(i);
			        			output.append(receivedWord.english + ":  " + receivedWord.italian + "."+"\n");
			        		}
			        		label.setText(output.toString());
			       	} else {
			        		Word receivedWord = wordList.get(0);
			        		if (receivedWord.english.equals("")) {
			        			output.append("Sorry, the searched word is not in the database!\n");
			        			label.setText(output.toString());
			    		} else {
			    			output.append("-------Search Results-------"+"\n");
			    			output.append(receivedWord.english + ":  " + receivedWord.italian);
			    			label.setText(output.toString());
			    		}
			        }
			    
				
				}
				catch(Exception exep){
					output.append(downloadedJson);
					label.setText(output.toString());
					
				}
			}
		});
		btnTraduci.setBounds(10, 81, 105, 35);
		btnTraduci.setText("Traduci");
		
		label = new Label(shell, SWT.NONE);
		label.setBounds(10, 162, 408, 172);
		
		label_1 = new Label(shell, SWT.NONE);
		label_1.setBounds(10, 122, 408, 25);

	}
}
