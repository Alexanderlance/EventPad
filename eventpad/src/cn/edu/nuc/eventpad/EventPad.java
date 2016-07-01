package cn.edu.nuc.eventpad;


import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public  class EventPad {
	public static File file=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame=new JFrame("无标题 - 记事本");
//		setIconImage(Toolkit.getDefaultToolkit().getImage(frame.getClass().getResource("/image/pad.ico")));
		/*Icon icon=new ImageIcon("/image/pad.jpg");
		JLabel ic=new JLabel(icon);*/
		/*String imagePath = "image/pad.jpg";// 构造图片的路径
		Image imageIcon = Toolkit.getDefaultToolkit().getImage(frame.getClass().getResource(imagePath));*/
		//Toolkit tool=frame.getToolkit(); //得到一个Toolkit对象
		//Icon myimage=tool.getImage("/image/pad.jpg"); //由tool获取图像
		Image icon = new ImageIcon(EventPad.class.getResource("/image/pad.jpg")).getImage();
		frame.setIconImage(icon);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(frame);
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (InstantiationException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IllegalAccessException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (UnsupportedLookAndFeelException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		JTextArea text=new JTextArea();
		text.setEditable(true);
		frame.getContentPane().add(new JScrollPane(text));
		JMenu menuFile=new JMenu("文件(F)");
		//setIconImage(Toolkit.getDefaultToolkit().getImage(EventPad.class.getResource("/image/pad.jpg")));
//		setIconImage(Toolkit.getDefaultToolkit().getImage(EventPad.class.getResource("/image/6200e46ajw1e8qgp5bmzyj2050050aa8.jpg")));
		JMenuBar menuBar=new JMenuBar();
		JMenuItem newItem=new JMenuItem("新建（N）");
		JMenuItem openItem=new JMenuItem("打开（O）");
		JMenuItem saveItem=new JMenuItem("保存（S）");
		JMenuItem anotherSaveItem=new JMenuItem("另存为（L）");
		JMenuItem exitItem=new JMenuItem("退出（X）");
		newItem.setMnemonic('N');
		openItem.setMnemonic('O');
		saveItem.setMnemonic('S');
		anotherSaveItem.setMnemonic('L');
//		exitItem.setMnemonic('X');
		newItem.setAccelerator(KeyStroke.getKeyStroke('N',InputEvent.CTRL_MASK));
		openItem.setAccelerator(KeyStroke.getKeyStroke('O',InputEvent.CTRL_MASK));
		saveItem.setAccelerator(KeyStroke.getKeyStroke('S',InputEvent.CTRL_MASK));
		anotherSaveItem.setAccelerator(KeyStroke.getKeyStroke('L',InputEvent.CTRL_MASK));
//		exitItem.setAccelerator(KeyStroke.getKeyStroke('X',InputEvent.CTRL_MASK));
		menuFile.add(newItem);
		menuFile.add(openItem);
		menuFile.add(saveItem);
		menuFile.add(anotherSaveItem);
		menuFile.add(exitItem);
		menuBar.add(menuFile);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				/*JOptionPane.showMessageDialog(null, "是否保存该文件？");
				JButton j1=new JButton("是");
				JButton j2=new JButton("否");
				JButton j3=new JButton("取消");	*/
				System.exit(1);
			}
		});
		newItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				text.setText("");
			}
		});
		openItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JLabel label = new JLabel("现在没有打开的文件") ;
//				File file=null;
				int result=0;
				JFileChooser fileChooser=new JFileChooser();
//				if(e.getSource()==openItem){
					text.setText("");
					fileChooser.setApproveButtonText("打开");
					fileChooser.setDialogTitle("打开文件");
					result=fileChooser.showOpenDialog(frame);
					if(result==JFileChooser.APPROVE_OPTION){
						file=fileChooser.getSelectedFile();
						label.setText("打开的文件名为："+file.getName());
					}else if(result==JFileChooser.CANCEL_OPTION){
						label.setText("现在没有打开的文件");
					}else{
						label.setText("您的操作有误");
					}
					if(file!=null){
						Scanner scan=null;
						try{
							scan = new Scanner(new FileInputStream(file)) ;
							scan.useDelimiter("\n") ;
							while(scan.hasNext()){
								text.append(scan.next()) ;
								text.append("\n") ;
							}
							
						}catch(Exception e1){
							
						}finally{
							scan.close() ;
						}
					}
				}
			});
//		});
		saveItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				PrintStream out=null;
				/*JFileChooser fileChooser = new JFileChooser() ;	
				file=fileChooser.getSelectedFile();*/
				
				if(file==null ||!file.exists()){
					JFileChooser fileChooser = new JFileChooser() ;	
					int result=fileChooser.showSaveDialog(null);
					if(result==JFileChooser.APPROVE_OPTION){
						file=fileChooser.getSelectedFile();
						if(! file.exists()){
							try {
								file.createNewFile();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								
							}
						}
					}else{
							return;
					}
				}
				try {
					out = new PrintStream(new FileOutputStream(file)) ;
					out.print(text.getText()) ;
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
				
				}
			
				finally{
					out.flush();
					out.close();
					
				}
			}
		});
		anotherSaveItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				File file=null;
				int result=0;
				JFileChooser fileChooser = new JFileChooser() ;	
				//if(e.getSource()==anotherSaveItem){
					result=fileChooser.showSaveDialog(frame);
					if(result==JFileChooser.APPROVE_OPTION){
						file=fileChooser.getSelectedFile();
					}
					if(file!=null){
						PrintStream out=null;
						try{
							out = new PrintStream(new FileOutputStream(file)) ;
							out.print(text.getText()) ;
						}catch(Exception e1){
							
						}finally{
							out.close() ;
						}
					}
				}
			});
	//	});
		exitItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(1);
			}
			
		});
/*		//youwenti
		exitItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int result=0;
				JFileChooser fileChooser = new JFileChooser() ;	
				file=fileChooser.getSelectedFile();
				if(file.exists()){
					System.exit(1);
					
				}else{
					JOptionPane.showMessageDialog(null, "文件没有保存，是否保存？");
					JButton j1=new JButton("是");
					
					JButton j2=new JButton("否");
	
					JButton j3=new JButton("取消");
					if(e.getSource()=="是"){
						anotherSaveItem.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								File file=null;
								int result=0;
								JFileChooser fileChooser = new JFileChooser() ;	
								//if(e.getSource()==anotherSaveItem){
									result=fileChooser.showSaveDialog(frame);
									if(result==JFileChooser.APPROVE_OPTION){
										file=fileChooser.getSelectedFile();
									}else if(result==JFileChooser.CANCEL_OPTION){
									}
									if(file!=null){
										try{
											PrintStream out = new PrintStream(new FileOutputStream(file)) ;
											out.print(text.getText()) ;
											out.close() ;
										}catch(Exception e1){}
									}
								}
							});
					}else if(e.getSource()=="否"){
						System.exit(1);
					}else if(e.getSource()=="取消"){
						
					}
				}
			}
		});*/
		frame.setJMenuBar(menuBar);
		frame.setVisible(true);
		frame.setSize(300, 200);
		frame.setLocation(300, 200);
		
	}
/*	private static void setIconImage(Image image) {
		// TODO Auto-generated method stub
		
	}*/

}
