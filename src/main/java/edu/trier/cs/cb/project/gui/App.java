package edu.trier.cs.cb.project.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import edu.trier.cs.cb.project.AbstractMachine;
import edu.trier.cs.cb.project.Instruction;

public class App implements AbstractMachine.Listener {

	protected Shell shell;
	private Text top;
	private Text pp;
	private Text pc;
	private Text fp;
	private Text memory;
	private Text code;
	private Display display;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			App window = new App();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private AbstractMachine machine;
	
	public App() {
		machine = new AbstractMachine();
		machine.addListener(this);
	}
	
	public void onBeforeExecuting(final Instruction i) {
		Runnable job = new Runnable() {
			public void run() {
				code.setText(code.getText() + "\n" + i.toString());
				pc.setText(""+machine.getPC());
				top.setText(""+machine.getTOP());
				pp.setText(""+machine.getPP());
				fp.setText(""+machine.getFP());
				
				StringBuilder sb = new StringBuilder();
				int i = 0;
				for (Integer v : machine.getStack()) {
					sb.append(i + ": " + v + "\n");
					i++;
				}
				memory.setText(sb.toString());
			}
		};
		display.syncExec(job);
	}

	public void onAfterExecuting() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
		createContents();
		bindActions();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	private void bindActions() {
		
	}
	
	private void run() {
		machine.execute(new Instruction[] {
			new Instruction(Instruction.CONST, 3),
			new Instruction(Instruction.INVOKE, 1, 3, 0), // --> fac
			new Instruction(Instruction.HALT),
			// fac
			new Instruction(Instruction.LOAD, 0, 0),
			new Instruction(Instruction.CONST, 1),
			new Instruction(Instruction.SUB),
			new Instruction(Instruction.IFZERO, 14), // --> iftrue
			new Instruction(Instruction.LOAD, 0, 0),
			new Instruction(Instruction.CONST, 1),
			new Instruction(Instruction.SUB),
			new Instruction(Instruction.INVOKE, 1, 3, 0), // --> fac
			new Instruction(Instruction.LOAD, 0, 0),
			new Instruction(Instruction.MULT),
			new Instruction(Instruction.RETURN),
			// iftrue
			new Instruction(Instruction.CONST, 1),
			new Instruction(Instruction.RETURN)
		});
		machine.printStack();		
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(741, 447);
		shell.setText("SWT Application");
		shell.setLayout(null);
		
		top = new Text(shell, SWT.BORDER);
		top.setBounds(62, 10, 75, 26);
		
		Label lblTop = new Label(shell, SWT.NONE);
		lblTop.setBounds(10, 15, 35, 16);
		lblTop.setText("TOP");
		
		pp = new Text(shell, SWT.BORDER);
		pp.setBounds(62, 42, 75, 26);
		
		Label lblPp = new Label(shell, SWT.NONE);
		lblPp.setText("PP");
		lblPp.setBounds(10, 47, 35, 16);
		
		pc = new Text(shell, SWT.BORDER);
		pc.setBounds(62, 106, 75, 26);
		
		Label lblFp = new Label(shell, SWT.NONE);
		lblFp.setText("PC");
		lblFp.setBounds(10, 111, 35, 16);
		
		Label lblFp_1 = new Label(shell, SWT.NONE);
		lblFp_1.setText("FP");
		lblFp_1.setBounds(10, 79, 35, 16);
		
		fp = new Text(shell, SWT.BORDER);
		fp.setBounds(62, 74, 75, 26);
		
		memory = new Text(shell, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		memory.setBounds(341, 10, 388, 196);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setBounds(250, 178, 85, 28);
		btnNewButton.setText("Run");
		btnNewButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event arg0) {
				run();
			}
		});
		
		code = new Text(shell, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		code.setBounds(10, 212, 719, 196);

	}


}
